package hebust.graduation.contract;

import java.util.List;

import hebust.graduation.presenter.BasePresenter;
import hebust.graduation.view.BaseView;

public interface MainViewContract {

    interface View extends BaseView<Presenter> {

        void showTabs(List<String> channels);

        void showFeedDetail();

        void showLoading();

        void showErrorPage(boolean hideTabs);

        void onPageRefresh();
    }


    interface Presenter extends BasePresenter {

        void loadTabs(boolean forceUpdate);

        void openFeedDetail();

        void refreshFeed();
    }

}
