package hebust.graduation.data;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by tianrui on 17-5-21.
 * <p>
 * facade for channel data model.
 */
public class ChannelRepository implements ChannelDataSource {

    private LocalChannelDataSource mLocalChannelDataSource;
    private RemoteChannelDataSource mRemoteChannelDataSource;

    // is need refresh from net.
    private boolean mCacheIsDirty = false;


    public ChannelRepository(@NonNull LocalChannelDataSource localChannelDataSource,
                             @NonNull RemoteChannelDataSource remoteChannelDataSource) {
        mLocalChannelDataSource = localChannelDataSource;
        mRemoteChannelDataSource = remoteChannelDataSource;
    }

    @Override
    public void getChannels(LoadChannelsCallBack callBack) {
        if (mCacheIsDirty) {
            mRemoteChannelDataSource.getChannels(callBack);
            mCacheIsDirty = false;
        } else {
            mLocalChannelDataSource.getChannels(callBack);
        }
    }

    @Override
    public void refreshChannels() {
        mCacheIsDirty = true;
    }

    @Override
    public void deleteChannels() {
        mLocalChannelDataSource.deleteChannels();
        mCacheIsDirty = true;
    }

    @Override
    public void saveChannels(List<String> channels) {
        mLocalChannelDataSource.saveChannels(channels);
    }
}
