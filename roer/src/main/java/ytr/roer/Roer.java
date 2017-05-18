package ytr.roer;

import android.widget.ImageView;

import ytr.roer.image.BitmapCache;
import ytr.roer.image.ImageLoader;
import ytr.roer.image.ImageRequest;

/**
 * Created by tianrui on 17-4-29.
 * <p>
 * Single instance to delegate network request.
 * we use single instance to manager our requestQueue.
 */
public class Roer {
    private volatile static Roer sInstance;

    // fixme test mock
    private RoerConfiguration mConfiguration = new RoerConfiguration();

    // use this requestQueue to perform all network request, both image and other.
    private RequestQueue mRequestQueue;

    private ImageLoader mImageLoader;

    public static Roer getInstance() {
        if (sInstance == null) {
            synchronized (Roer.class) {
                if (sInstance == null) {
                    sInstance = new Roer();
                }
            }
        }
        return sInstance;
    }

    private Roer() {
        mRequestQueue = RequestQueue.newRequestQueue();
        mImageLoader = new ImageLoader(mRequestQueue, new BitmapCache());
    }

    public void addRequest(final Request<?> request) {
        mRequestQueue.add(request);
    }


    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }


    public void bind(String requestUrl, ImageView iv) {
        bind(requestUrl, iv, 0, 0, ImageView.ScaleType.CENTER_INSIDE);
    }

    /**
     * bind a image to ImageView, before display them, we will resize this bitmap
     * with a suit size for widget.
     *
     * @param requestUrl requestUrl for request.
     * @param iv         widget for display image.
     */
    public void bind(String requestUrl, ImageView iv, int maxWidth, int maxHeight, ImageView.ScaleType scaleType) {
        final ImageLoader.ImageListener imageListener = getImageListener(iv, mConfiguration.getDefaultImageResId(), mConfiguration.getDefaultErrorResId());
        mImageLoader.get(requestUrl, imageListener, maxWidth, maxHeight, scaleType);
    }


    /**
     * The default implementation of ImageListener which handles basic functionality
     * of showing a default image until the network response is received, at which point
     * it will switch to either the actual image or the error image.
     *
     * @param view              The imageView that the listener is associated with.
     * @param defaultImageResId Default image resource ID to use, or 0 if it doesn't exist.
     * @param errorImageResId   Error image resource ID to use, or 0 if it doesn't exist.
     */
    public static ImageLoader.ImageListener getImageListener(final ImageView view,
                                                             final int defaultImageResId, final int errorImageResId) {
        return new ImageLoader.ImageListener() {
            @Override
            public void onErrorResponse(RoerError error) {
                if (errorImageResId != 0) {
                    view.setImageResource(errorImageResId);
                }
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (response.getBitmap() != null) {
                    view.setImageBitmap(response.getBitmap());
                } else if (defaultImageResId != 0) {
                    view.setImageResource(defaultImageResId);
                }
            }
        };
    }
}
