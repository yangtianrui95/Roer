package ytr.roer;

import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.Map;

/**
 * Created by tianrui on 17-4-29.
 */
public interface HttpStack {
    HttpResponse performRequest(Request<?> request, Map<String, String> headers) throws IOException, AuthFailureError;
}
