package ytr.roer;


import ytr.roer.policy.CachePolicy;

/**
 * Encapsulates a parsed response for delivery.
 *
 * @param <T> Parsed type of this response
 */
public class Response<T> {

    /**
     * Callback interface for delivering parsed responses.
     */
    public interface Listener<T> {
        /**
         * Called when a response is received.
         */
        public void onResponse(T response);
    }

    /**
     * Callback interface for delivering error responses.
     */
    public interface ErrorListener {
        /**
         * Callback method that an error has been occurred with the
         * provided error code and optional user-readable message.
         */
        public void onErrorResponse(RoerError error);
    }

    /**
     * Returns a successful response containing the parsed result.
     */
    public static <T> Response<T> success(T result, CachePolicy.Entry cacheEntry) {
        return new Response<>(result, cacheEntry);
    }

    /**
     * Returns a failed response containing the given error code and an optional
     * localized message displayed to the user.
     */
    public static <T> Response<T> error(RoerError error) {
        return new Response<T>(error);
    }

    /**
     * Parsed response, or null in the case of error.
     * result of data.
     */
    public final T result;

    /**
     * Cache metadata for this response, or null in the case of error.
     */
    public final CachePolicy.Entry cacheEntry;

    /**
     * Detailed error information if <code>errorCode != OK</code>.
     */
    public final RoerError error;

    /**
     * True if this response was a soft-expired one and a second one MAY be coming.
     */
    public boolean intermediate = false;

    /**
     * Returns whether this response is considered successful.
     */
    public boolean isSuccess() {
        return error == null;
    }


    private Response(T result, CachePolicy.Entry cacheEntry) {
        this.result = result;
        this.cacheEntry = cacheEntry;
        this.error = null;
    }

    private Response(RoerError error) {
        this.result = null;
        this.cacheEntry = null;
        this.error = error;
    }
}
