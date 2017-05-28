package hebust.graduation.presenter;

import hebust.graduation.contract.MediaContract;
import hebust.graduation.data.MediaDataSource;

public class MediaPresenter implements MediaContract.Presenter {

    private MediaDataSource mMediaDataSourcel;
    private MediaContract.View mView;


    public MediaPresenter(MediaDataSource mediaDataSourcel, MediaContract.View view) {
        mMediaDataSourcel = mediaDataSourcel;
        mView = view;
    }

    @Override
    public void start() {
        refresh();
    }

    @Override
    public void refresh() {
        mMediaDataSourcel.refreshData(new MediaDataSource.LoadMediaDataCallback() {
            @Override
            public void onLoadTextSuccess(String text) {
                mView.showText(text);
            }

            @Override
            public void onLoadTextError() {

            }
        });

        mView.showImage();
    }

    @Override
    public void share() {
        mView.shareInfo();
    }
}
