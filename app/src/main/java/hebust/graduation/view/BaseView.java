package hebust.graduation.view;

import hebust.graduation.presenter.BasePresenter;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);
}
