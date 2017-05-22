package ytr.roer;

import com.alibaba.fastjson.JSON;

/**
 * Created by tianrui on 17-5-21.
 */
public class FastJsonRequest<T> extends Request<T> {

    private Class<T> mClazz;
    private Response.Listener<T> mListener;


    public FastJsonRequest(int method, String url, Response.ErrorListener error, Response.Listener<T> listener, Class<T> clazz) {
        super(method, url, error);
        mListener = listener;
        mClazz = clazz;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            @SuppressWarnings("unchecked")
            T obj = (T) JSON.parseObject(response.data, mClazz);
            return Response.success(obj, HttpHeaderParser.parseCacheHeaders(response));
        } catch (ClassCastException e) {
            L.d("Error in case class " + mClazz.getName());
        }
        return null;
    }

    @Override
    public void deliverResponse(T response) {
        if (mListener != null) {
            mListener.onResponse(response);
        }
    }
}
