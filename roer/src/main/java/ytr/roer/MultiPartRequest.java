package ytr.roer;

/**
 * Created by tianrui on 17-5-19.
 */
public class MultiPartRequest<T> extends Request<T> {
    /**
     * Creates a new request with the given method (one of the values from {@link Method}),
     * URL, and error listener.  Note that the normal response listener is not provided here as
     * delivery of responses is provided by subclasses, who have a better idea of how to deliver
     * an already-parsed response.
     *
     * @param method
     * @param url
     * @param listener
     */
    public MultiPartRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }



    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        return null;
    }

    @Override
    public void deliverResponse(T response) {

    }
}
