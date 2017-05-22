package hebust.graduation.data;

import hebust.graduation.Constants;
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

    @Override
    public void getFeeds(final LoadFeedsCallback callback) {
        Roer.getInstance().addRequest(new FastJsonRequest<>(Request.Method.GET, Constants.Urls.HOST + "news", new Response.ErrorListener() {
            @Override
            public void onErrorResponse(RoerError error) {
                callback.onDataNotAvailable();
            }
        }, new Response.Listener<Feed>() {
            @Override
            public void onResponse(Feed response) {
                callback.onFeedsLoaded(response);
            }
        }, Feed.class));
    }

    @Override
    public void refreshFeeds() {
        // TODO: 17-5-23  ????
    }
}
