package hebust.graduation.contract;

import hebust.graduation.presenter.BasePresenter;
import hebust.graduation.view.BaseView;

public class FeedContract {

    public interface View extends BaseView<Presenter> {

        void showFeedList();

    }

    public interface Presenter extends BasePresenter {

        void refresh();


    }
}
