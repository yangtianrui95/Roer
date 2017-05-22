package hebust.graduation.contract;

import java.util.List;

import hebust.graduation.beans.Feed;
import hebust.graduation.presenter.BasePresenter;
import hebust.graduation.view.BaseView;

public class FeedContract {

    public interface View extends BaseView<Presenter> {

        void showFeedList(List<Feed.FeedItem> feedItems);

        void showErrorPage();

        void showLoading();

    }

    public interface Presenter extends BasePresenter {

        void refresh();

    }
}
