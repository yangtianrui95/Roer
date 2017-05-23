package hebust.graduation.presenter;

import hebust.graduation.beans.Feed;
import hebust.graduation.contract.FeedContract;
import hebust.graduation.data.FeedDataSource;

public class FeedPresenter implements FeedContract.Presenter {

    private FeedDataSource mFeedDataSource;
    private FeedContract.View mView;

    public FeedPresenter(FeedDataSource feedDataSource, FeedContract.View view) {
        mFeedDataSource = feedDataSource;
        mView = view;
    }

    @Override
    public void refresh() {
        mFeedDataSource.refreshFeeds(new FeedDataSource.LoadFeedsCallback() {
            @Override
            public void onFeedsLoaded(Feed feed) {
                if (feed == null) {
                    return;
                }
                mView.refresh(feed.getData());
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void start() {
        mFeedDataSource.getFeeds(new FeedDataSource.LoadFeedsCallback() {
            @Override
            public void onFeedsLoaded(Feed feed) {
                mView.showFeedList(feed.getData());
            }

            @Override
            public void onDataNotAvailable() {
                mView.showErrorPage();
            }
        });
    }
}
