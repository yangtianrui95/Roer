package hebust.graduation.data;

import java.util.List;

import hebust.graduation.Constants;
import hebust.graduation.beans.Channel;
import ytr.roer.FastJsonRequest;
import ytr.roer.Request;
import ytr.roer.Response;
import ytr.roer.Roer;
import ytr.roer.RoerError;

/**
 * Created by tianrui on 17-5-21.
 * <p>
 * get channels from network.
 */
public class RemoteChannelDataSource implements ChannelDataSource {


    @Override
    public void getChannels(final LoadChannelsCallBack callBack) {
        Roer.getInstance().addRequest(new FastJsonRequest<>(Request.Method.GET, Constants.Urls.HOST + "channel", new Response.ErrorListener() {
            @Override
            public void onErrorResponse(RoerError error) {
                callBack.onDataNotAvailable();
            }
        }, new Response.Listener<Channel>() {
            @Override
            public void onResponse(Channel response) {
                callBack.onChannelsLoaded(response);
            }
        }, Channel.class));
    }

    @Override
    public void refreshChannels() {

    }

    @Override
    public void deleteChannels() {

    }

    @Override
    public void saveChannels(List<String> channels) {

    }
}
