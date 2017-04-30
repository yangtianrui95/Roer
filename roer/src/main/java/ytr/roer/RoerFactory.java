package ytr.roer;

import android.annotation.SuppressLint;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianrui on 17-4-29.
 */
public class RoerFactory {

    private static volatile RoerFactory sInstance;

    @SuppressLint("UseSparseArrays")
    private final Map<Integer, RequestQueue> mQueueMap = new HashMap<>();
    private final int DEFAULT_QUEUE_KEY = hashCode();


    public RoerFactory() {
        mQueueMap.put(DEFAULT_QUEUE_KEY, RequestQueue.newRequestQueue());
    }

    public static RoerFactory getInstance() {
        if (sInstance == null) {
            synchronized (RoerFactory.class) {
                if (sInstance == null) {
                    sInstance = new RoerFactory();
                }
            }
        }
        return sInstance;
    }


    public RequestQueue getDefaultQueue() {
        return mQueueMap.get(DEFAULT_QUEUE_KEY);
    }

}
