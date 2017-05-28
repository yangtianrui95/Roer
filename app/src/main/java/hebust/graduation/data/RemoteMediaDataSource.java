package hebust.graduation.data;

import hebust.graduation.Constants;
import ytr.roer.Request;
import ytr.roer.Response;
import ytr.roer.Roer;
import ytr.roer.RoerError;
import ytr.roer.StringRequest;

public class RemoteMediaDataSource implements MediaDataSource {
    @Override
    public void refreshData(final LoadMediaDataCallback callback) {
        Roer.getInstance().addRequest(new StringRequest(Request.Method.GET, Constants.Urls.HOST + "static", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onLoadTextSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(RoerError error) {
                callback.onLoadTextError();
            }
        }));
    }

}
