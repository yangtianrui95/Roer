package hebust.graduation.presenter;

import hebust.graduation.beans.Channel;
import hebust.graduation.contract.MainViewContract;
import hebust.graduation.data.ChannelDataSource;
import hebust.graduation.data.ChannelRepository;

public class MainViewPresenter implements MainViewContract.Presenter {

    // data model.
    private ChannelRepository mRepository;

    // view.
    private MainViewContract.View mView;


    public MainViewPresenter(ChannelRepository repository, MainViewContract.View view) {
        mRepository = repository;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        loadTabs(true);
    }


    @Override
    public void loadTabs(boolean forceUpdate) {
        if (forceUpdate) {
            mRepository.refreshChannels();
        }
        mRepository.getChannels(new ChannelDataSource.LoadChannelsCallBack() {

            @Override
            public void onChannelsLoaded(Channel channel) {
                if (channel != null) {
                    mView.showTabs(channel.getData());
                }
            }

            @Override
            public void onDataNotAvailable() {
                mView.showErrorPage(true);
            }
        });
    }

    @Override
    public void openFeedDetail() {

    }

    @Override
    public void refreshFeed() {
        mView.showLoading();
        mRepository.refreshChannels();

    }
}
