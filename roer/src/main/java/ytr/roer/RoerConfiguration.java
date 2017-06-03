package ytr.roer;

import android.support.annotation.DrawableRes;

/**
 * Created by tianrui on 17-5-18.
 * <p>
 * this class holds all network request, image download, file download all configuration items.
 */
public final class RoerConfiguration {

    // Actual network request.
    private HttpStack mHttpStack;
    private int mNetworkPoolSize;

    // Image related.
    private int mDefaultImageResId;
    private int mDefaultErrorResId;
    // memory cache size for image request.
    private int mMemCacheSize;


    public RoerConfiguration() {
    }

    public RoerConfiguration(RoerConfigurationBuilder roerConfigurationBuilder) {
        mHttpStack = roerConfigurationBuilder.mHttpStack;
        mNetworkPoolSize = roerConfigurationBuilder.mNetworkPoolSize;
        mDefaultImageResId = roerConfigurationBuilder.mDefaultImageResId;
        mDefaultErrorResId = roerConfigurationBuilder.mDefaultErrorResId;
        mMemCacheSize = roerConfigurationBuilder.mMemCacheSize;
    }

    // File related.


    public HttpStack getHttpStack() {
        return mHttpStack;
    }

    public void setHttpStack(HttpStack httpStack) {
        mHttpStack = httpStack;
    }

    public int getNetworkPoolSize() {
        return mNetworkPoolSize;
    }

    public void setNetworkPoolSize(int networkPoolSize) {
        mNetworkPoolSize = networkPoolSize;
    }

    public int getDefaultImageResId() {
        return mDefaultImageResId;
    }

    public void setDefaultImageResId(int defaultImageResId) {
        mDefaultImageResId = defaultImageResId;
    }

    public int getDefaultErrorResId() {
        return mDefaultErrorResId;
    }

    public void setDefaultErrorResId(int defaultErrorResId) {
        mDefaultErrorResId = defaultErrorResId;
    }

    public int getMemCacheSize() {
        return mMemCacheSize;
    }

    public void setMemCacheSize(int memCacheSize) {
        mMemCacheSize = memCacheSize;
    }

    /**
     * Builder for RoerConfiguration.
     */
    public static final class RoerConfigurationBuilder {

        // Default Configuration.
        private static final int DEFAULT_NETWORK_THREADPOOL_SIZE = Runtime.getRuntime().availableProcessors() * 2 + 1;
        private static final HttpStack DEFAULT_HTTPSTACK = new OkHttpStack();
        private static final int DEFAULT_MEMCACHE_SIZE = (int) (Runtime.getRuntime().maxMemory() * 0.15);

        private HttpStack mHttpStack = DEFAULT_HTTPSTACK;
        private int mNetworkPoolSize = DEFAULT_NETWORK_THREADPOOL_SIZE;
        private int mDefaultImageResId;
        private int mDefaultErrorResId;
        // memory cache size for image request.
        private int mMemCacheSize = DEFAULT_MEMCACHE_SIZE;


        public RoerConfigurationBuilder httpStack(HttpStack httpStack) {
            mHttpStack = httpStack;
            return this;
        }

        public RoerConfigurationBuilder networkPoolSize(int networkPoolSize) {
            mNetworkPoolSize = networkPoolSize;
            return this;
        }

        public RoerConfigurationBuilder defaultImageResId(@DrawableRes int resId) {
            mDefaultImageResId = resId;
            return this;
        }

        public RoerConfigurationBuilder defaultErrorResId(@DrawableRes int resId) {
            mDefaultErrorResId = resId;
            return this;
        }

        public RoerConfigurationBuilder memCacheSize(int size) {
            mMemCacheSize = size;
            return this;
        }

        public RoerConfiguration build() {
            return new RoerConfiguration(this);
        }

    }
}
