package ytr.roer;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

import ytr.roer.policy.CachePolicy;
import ytr.roer.policy.DeliveryPolicy;
import ytr.roer.policy.impl.DiskBasedCache;
import ytr.roer.policy.impl.ExecutorDelivery;
import ytr.roer.policy.impl.NoCache;

/**
 * Created by tianrui on 17-4-29.
 */
public class RequestQueue {

    // 2N+1
    private final int DEFAULT_THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2 + 1;

    private final Executor mCacheExecutor = Executors.newSingleThreadExecutor();
    private final Executor mNetwortExecutor = Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE);

    private final BlockingQueue<Request<?>> mNetworkQueue = new PriorityBlockingQueue<>();
    private final BlockingQueue<Request<?>> mCacheQueue = new PriorityBlockingQueue<>();


    public static RequestQueue newRequestQueue() {
        final Network defaultNetwork = new BasicNetwork(new OkHttpStack());
        L.d("Cache path: " + Environment.getExternalStorageState() +" /roer");
        final CachePolicy defaultCache = new DiskBasedCache(new File(Environment.getExternalStorageDirectory(), "/roer"));
        final DeliveryPolicy defaultDeliver = new ExecutorDelivery(new Handler(Looper.getMainLooper()));
        return new RequestQueue(defaultNetwork, defaultCache, defaultDeliver);
    }

    public RequestQueue(Network network, CachePolicy cache, DeliveryPolicy deliver) {
        // put networkDispatcher into threadPool.
        for (int i = 0; i < DEFAULT_THREAD_POOL_SIZE; i++) {
            mNetwortExecutor.execute(new NetworkDispatcher(mNetworkQueue, network, cache, deliver));
        }

        // put cacheDispatcher into threadPool.
        mCacheExecutor.execute(new CacheDispatcher(mCacheQueue, mNetworkQueue, cache, deliver));

    }

    private Network getDefaultNetwork() {
        HttpStack httpStack = new HurlStack();
        return new BasicNetwork(httpStack);
    }


    public void add(Request<?> request){
        // Tag the request as belonging to this queue and add it to the set of current requests.
        request.setRequestQueue(this);
//        synchronized (mCurrentRequests) {
//            mCurrentRequests.add(request);
//        }
//
//        // Process requests in the order they are added.
//        request.setSequence(getSequenceNumber()); // 为请求添加序列号
//        request.addMarker("add-to-queue");
//
//        // If the request is uncacheable, skip the cache queue and go straight to the network.
//        // 不需缓存,直接走网络请求
//        if (!request.shouldCache()) { // 此请求是否由缓存处理
//            mNetworkQueue.add(request);
//            return request;
//        }
//
//        // 由缓存处理此请求
//        // Insert request into stage if there's already a request with the same cache key in flight.
//        // 如果有相同的请求正在被处理,则加入到相同的等待请求队列中
//        synchronized (mWaitingRequests) {
//
//            String cacheKey = request.getCacheKey();
//            // 如果Waiting队列中有待执行请求时, 先暂时不处理相同的请求.
//            if (mWaitingRequests.containsKey(cacheKey)) {
//                // There is already a request in flight. Queue up.
//                Queue<Request<?>> stagedRequests = mWaitingRequests.get(cacheKey);
//                if (stagedRequests == null) {
//                    stagedRequests = new LinkedList<Request<?>>();
//                }
//                stagedRequests.add(request);
//
//                mWaitingRequests.put(cacheKey, stagedRequests);
//                if (Roer.DEBUG) {
//                    Roer.v("Request for cacheKey=%s is in flight, putting on hold.", cacheKey);
//                }
//                // 没有相同的请求,加入到缓存队列中
//            } else {
//                // Insert 'null' queue for this cacheKey, indicating there is now a request in
//                // flight.
//                mWaitingRequests.put(cacheKey, null);
//                // 向Cache队列中存放数据,此时阻塞的Cache线程会被唤醒,所有的请求都是由Cache队列进行分发.
//                mCacheQueue.add(request);
//            }
//            return request;
//        }
        mCacheQueue.add(request);
    }


    public <T> void finish(Request tRequest) {

    }
}
