package ytr.roer.image;

import android.graphics.Bitmap;

/**
 * Created by tianrui on 17-5-13.
 * <p>
 * Simple cache adapter interface. If provided to the ImageLoader, it
 * will be used as an L1 cache before dispatch to Volley. Implementations
 * must not block. Implementation with an LruCache is recommended.
 */
public interface ImageCache {
    Bitmap getBitmap(String url);

    void putBitmap(String url, Bitmap bitmap);

    void invalidateBitmap(String url);

    void clear();
}