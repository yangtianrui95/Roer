package ytr.roer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import okhttp3.*;
import okhttp3.Response;

/**
 * Created by tianrui on 17-4-29.
 * use okHttp to perform actual request.
 */
public class OkHttpStack implements HttpStack {

    private final OkHttpClient mOkHttpClient = new OkHttpClient();


    @Override
    public HttpResponse performRequest(Request<?> request, Map<String, String> headers) throws IOException, AuthFailureError {

        final String url = request.getUrl();

        okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
        // todo add HttpMethod
        builder.url(url);
        // add request header.
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.header(entry.getKey(), entry.getValue());
        }
        okhttp3.Request urlRequest = builder.build();
        // get OkHttp call .
        final Call call = mOkHttpClient.newCall(urlRequest);
        Response response = call.execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.message());
        }

        final ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        final StatusLine statusLine = new BasicStatusLine(protocolVersion, response.code(), response.message());
        final HttpResponse httpResponse = new BasicHttpResponse(statusLine);
        // add response header.
        final Headers resHeaders = response.headers();
        for (int i = 0; i < resHeaders.size(); i++) {
            httpResponse.addHeader(resHeaders.name(i), resHeaders.value(i));
        }

        // add response body if it has.
        if (hasResponseBody(request.getMethod(), response.code())) {
            httpResponse.setEntity(entityFromResponse(response));
        }

        return httpResponse;
    }

    private HttpEntity entityFromResponse(Response response) {
        BasicHttpEntity entity = new BasicHttpEntity();
        InputStream inputStream;
        inputStream = response.body().byteStream();
        entity.setContent(inputStream);
        entity.setContentLength(response.body().contentLength());
        entity.setContentType(response.body().contentType().toString());
        return entity;
    }


    /**
     * Checks if a response message contains a body.
     *
     * @param requestMethod request method
     * @param responseCode  response status code
     * @return whether the response has a body
     * @see <a href="https://tools.ietf.org/html/rfc7230#section-3.3">RFC 7230 section 3.3</a>
     */
    private static boolean hasResponseBody(int requestMethod, int responseCode) {
        return requestMethod != Request.Method.HEAD
                && !(HttpStatus.SC_CONTINUE <= responseCode && responseCode < HttpStatus.SC_OK)
                && responseCode != HttpStatus.SC_NO_CONTENT
                && responseCode != HttpStatus.SC_NOT_MODIFIED;
    }

}
