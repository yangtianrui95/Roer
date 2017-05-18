package ytr.roer.image;

import android.graphics.Bitmap;
import android.util.LruCache;

import ytr.roer.L;

/**
 * Created by tianrui on 17-5-13.
 * <p>
 * this class holds our bitmap in memory. It play a role in a level first cache.
 * We can get a image immediately if we use it.
 */
public class BitmapCache implements ImageCache {

    private final LruCache<String, Bitmap> mCache;

    private static final int DEFAULT_CACHE_SIZE = (int) (Runtime.getRuntime().maxMemory() * 0.15f);

    public BitmapCache() {
        this(DEFAULT_CACHE_SIZE);
    }

    public BitmapCache(int size) {
        L.d("BitmapCache size is " + size);
        mCache = new LruCache<String, Bitmap>(size) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                final int bitmapSize = value.getByteCount() / 1024;
                return bitmapSize == 0 ? 1 : bitmapSize;
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        if (url != null) {
            return mCache.get(url);
        }
        return null;
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        if (url != null && bitmap != null) {
            mCache.put(url, bitmap);
        }
    }

    @Override
    public void invalidateBitmap(String url) {
        if (url == null) {
            return;
        }
        synchronized (mCache) {
            mCache.remove(url);
        }
    }

    @Override
    public void clear() {
        mCache.evictAll();
    }
}
