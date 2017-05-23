package hebust.graduation.data;

import hebust.graduation.beans.Feed;

public interface FeedDataSource {

    public interface LoadFeedsCallback {
        void onFeedsLoaded(Feed feed);

        void onDataNotAvailable();
    }

    void getFeeds(LoadFeedsCallback callback);

    void refreshFeeds(LoadFeedsCallback callback);
}
