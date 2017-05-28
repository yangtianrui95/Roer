package hebust.graduation.contract;

import hebust.graduation.presenter.BasePresenter;
import hebust.graduation.presenter.MediaPresenter;
import hebust.graduation.view.BaseView;

/**
 * Created by tianrui on 17-5-28.
 */
public interface MediaContract {

    public interface View extends BaseView<MediaPresenter> {
        void showImage();

        void showText(String text);

        void shareInfo();
    }

    public interface Presenter extends BasePresenter {
        void refresh();

        void share();
    }
}
