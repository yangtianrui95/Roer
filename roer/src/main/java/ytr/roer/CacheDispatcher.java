package ytr.roer;

import android.os.Process;

import java.util.concurrent.BlockingQueue;

import ytr.roer.policy.CachePolicy;
import ytr.roer.policy.DeliveryPolicy;

/**
 * Created by tianrui on 17-4-29.
 * perform cache request.
 */

public class CacheDispatcher implements Runnable {

    /**
     * The queue of requests coming in for triage.
     */
    private final BlockingQueue<Request<?>> mCacheQueue;

    /**
     * The queue of requests going out to the network.
     */
    private final BlockingQueue<Request<?>> mNetworkQueue;

    /**
     * The cache to read from.
     */
    private final CachePolicy mCache;

    /**
     * For posting responses.
     */
    private final DeliveryPolicy mDelivery;

    /**
     * Used for telling us to die.
     */
    private volatile boolean mQuit = false;

    /**
     * Creates a new cache triage dispatcher thread.  You must call
     * in order to begin processing.
     *
     * @param cacheQueue   Queue of incoming requests for triage
     * @param networkQueue Queue to post requests that require network to
     * @param cache        Cache interface to use for resolution
     * @param delivery     Delivery interface to use for posting responses
     */
    public CacheDispatcher(
            BlockingQueue<Request<?>> cacheQueue, BlockingQueue<Request<?>> networkQueue,
            CachePolicy cache, DeliveryPolicy delivery) {
        mCacheQueue = cacheQueue;
        mNetworkQueue = networkQueue; // 处理过期的网络请求
        mCache = cache;
        mDelivery = delivery;
    }

    /**
     * Forces this dispatcher to quit immediately.  If any requests are still in
     * the queue, they are not guaranteed to be processed.
     */
    public void quit() {
        mQuit = true;
        //interrupt();
    }

    @Override
    public void run() {
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        L.d("CacheDispatcher #run()");
        // Make a blocking call to initialize the cache.
        mCache.initialize();

        Request<?> request;
        // 从队列中不断获取Request
        while (true) {
            // release previous request object to avoid leaking request object when mQueue is drained.
            request = null;
            try {
                // Take a request from the queue.
                request = mCacheQueue.take();

            } catch (InterruptedException e) {
                // We may have been interrupted because it was time to quit.
                if (mQuit) {
                    return;
                }
                continue;
            }
            try {

                // If the request has been canceled, don't bother dispatching it.
                if (request.isCanceled()) {
                    //request.finish("cache-discard-canceled");
                    request.finish();
                    continue;
                }

                // Attempt to retrieve this item from cache.
                CachePolicy.Entry entry = mCache.get(request.getCacheKey());
                if (entry == null) {
                    // Cache miss; send off to the network dispatcher.
                    // 没有找到缓存,从添加进入网络队列
                    L.d("CacheDis put in Network");
                    mNetworkQueue.put(request);
                    continue;
                }

                // If it is completely expired, just send it to the network.
                if (entry.isExpired()) { // 缓存失效
                    request.setCacheEntry(entry);
                    mNetworkQueue.put(request);
                    continue;
                }

                // We have a cache hit; parse its data for delivery back to the request.
                // 缓存命中
                Response<?> response = request.parseNetworkResponse(
                        new NetworkResponse(entry.data, entry.responseHeaders));

                // 新鲜度判断
                if (!entry.refreshNeeded()) { // 缓存命中,不需要刷新,直接作为结果返回
                    // Completely unexpired cache hit. Just deliver the response.
                    mDelivery.postResponse(request, response);
                } else {
                    // 需要刷新
                    // Soft-expired cache hit. We can deliver the cached response,
                    // but we need to also send the request to the network for
                    // refreshing.
                    request.setCacheEntry(entry);

                    // Mark the response as intermediate.
                    response.intermediate = true;

                    // Post the intermediate response back to the user and have
                    // the delivery then forward the request along to the network.
                    final Request<?> finalRequest = request;
                    // 先做为结果返回,然后进行postRequest进行刷新
                    mDelivery.postResponse(request, response, new Runnable() {
                        @Override
                        public void run() {
                            try {
                                mNetworkQueue.put(finalRequest);
                            } catch (InterruptedException e) {
                                // Not much we can do about this.
                            }
                        }
                    });
                }
            } catch (Exception e) {
            }
        }
    }
}
