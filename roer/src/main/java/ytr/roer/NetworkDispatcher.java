package ytr.roer;

import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;

import java.util.concurrent.BlockingQueue;

import ytr.roer.policy.CachePolicy;
import ytr.roer.policy.DeliveryPolicy;

/**
 * Created by tianrui on 17-4-29.
 * perform network request.
 */

public class NetworkDispatcher implements Runnable {

    /** The queue of requests to service. */
    private final BlockingQueue<Request<?>> mQueue;
    /** The network interface for processing requests. */
    private final Network mNetwork;
    /** The cache to write to. */
    private final CachePolicy mCache;
    /** For posting responses and errors. */
    private final DeliveryPolicy mDelivery;
    /** Used for telling us to die. */
    private volatile boolean mQuit = false;

    /**
     * Creates a new network dispatcher thread.
     *
     * @param queue Queue of incoming requests for triage
     * @param network Network interface to use for performing requests
     * @param cache Cache interface to use for writing responses to cache
     * @param delivery Delivery interface to use for posting responses
     */
    public NetworkDispatcher(BlockingQueue<Request<?>> queue,
                             Network network, CachePolicy cache,
                             DeliveryPolicy delivery) {
        mQueue = queue;
        mNetwork = network;
        mCache = cache;
        mDelivery = delivery;
    }

    /**
     * Forces this dispatcher to quit immediately.  If any requests are still in
     * the queue, they are not guaranteed to be processed.
     */
    public void quit() {
        mQuit = true;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void addTrafficStatsTag(Request<?> request) {
        // Tag the request (if API >= 14)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            TrafficStats.setThreadStatsTag(request.getTrafficStatsTag());
        }
    }

    // 网络调度者
    @Override
    public void run() {
        L.d("NetworkDispatcher #run()");
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        Request<?> request;
        while (true) {
            long startTimeMs = SystemClock.elapsedRealtime();
            // release previous request object to avoid leaking request object when mQueue is drained.
            request = null;
            try {
                // Take a request from the queue.
                request = mQueue.take();
            } catch (InterruptedException e) {
                // We may have been interrupted because it was time to quit.
                if (mQuit) {
                    return;
                }
                continue;
            }

            try {

                // If the request was cancelled already, do not perform the
                // network request.
                if (request.isCanceled()) {
                    request.finish();
                    continue;
                }

                addTrafficStatsTag(request);
                L.d("NetworkDispatcher performRequest" );
                // Perform the network request.
                // 处理此Request, 此处是Volley的核心
                // BasicNetWork执行网络请求
                // 此处执行的网络请求,接收为byte[] , 此处需要每个request进行出理
                NetworkResponse networkResponse = mNetwork.performRequest(request);

                L.d("NetDis receive Response");
                // If the server returned 304 AND we delivered a response already,
                // we're done -- don't deliver a second identical response.
                if (networkResponse.notModified && request.hasHadResponseDelivered()) {
                    request.finish();
                    continue;
                }

                // Parse the response here on the worker thread.
                Response<?> response = request.parseNetworkResponse(networkResponse);

                // Write to cache if applicable.
                // TODO: Only update cache metadata instead of entire record for 304s.
                if (request.shouldCache() && response.cacheEntry != null) {
                    // fixme(tianrui) ? put all Cache??? cache only for static resource from service...
                    mCache.put(request.getCacheKey(), response.cacheEntry);
                }

                // Post the response back.
                request.markDelivered();
                mDelivery.postResponse(request, response);
            } catch (RoerError roerError) {
                roerError.setNetworkTimeMs(SystemClock.elapsedRealtime() - startTimeMs);
                parseAndDeliverNetworkError(request, roerError);
            } catch (Exception e) {
                RoerError roerError = new RoerError(e);
                roerError.setNetworkTimeMs(SystemClock.elapsedRealtime() - startTimeMs);
                mDelivery.postError(request, roerError);
            }
        }
    }

    private void parseAndDeliverNetworkError(Request<?> request, RoerError error) {
        error = request.parseNetworkError(error);
        mDelivery.postError(request, error);
    }
}
