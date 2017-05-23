package hebust.graduation.data;

import hebust.graduation.Constants;
import hebust.graduation.L;
import hebust.graduation.beans.Feed;
import ytr.roer.FastJsonRequest;
import ytr.roer.Request;
import ytr.roer.Response;
import ytr.roer.Roer;
import ytr.roer.RoerError;

/**
 * Created by tianrui on 17-5-22.
 * get feeds from net.
 */
public class RemoteFeedDataSource implements FeedDataSource {

    private String mChannel;

    public RemoteFeedDataSource(String channel) {
        mChannel = channel;
    }

    @Override
    public void getFeeds(final LoadFeedsCallback callback) {
        Roer.getInstance().addRequest(new FastJsonRequest<>(Request.Method.GET, Constants.Urls.HOST + "news/" + mChannel, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(RoerError error) {
                callback.onDataNotAvailable();
                L.d("error in network");
            }
        }, new Response.Listener<Feed>() {
            @Override
            public void onResponse(Feed response) {
                L.d("success in network");
                callback.onFeedsLoaded(response);
            }
        }, Feed.class));
    }

    @Override
    public void refreshFeeds(LoadFeedsCallback callback) {
        getFeeds(callback);
    }
}
