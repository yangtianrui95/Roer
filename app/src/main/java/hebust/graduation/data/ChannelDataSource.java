package hebust.graduation.data;

import java.util.List;

import hebust.graduation.beans.Channel;

public interface ChannelDataSource {

    /**
     * callback for loaded channel from network or cache.
     */
    interface LoadChannelsCallBack {

        void onChannelsLoaded(Channel channel);

        void onDataNotAvailable();
    }


    void getChannels(final LoadChannelsCallBack callBack);

    void refreshChannels();

    void deleteChannels();

    void saveChannels(List<String> channels);

}
