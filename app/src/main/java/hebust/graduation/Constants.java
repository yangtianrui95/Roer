package hebust.graduation;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    private final static Map<String, String> CHANNEL_MAP = new HashMap<String, String>() {{
        put(ChannelId.toutiao, "头条");
        put(ChannelId.society, "社会");
        put(ChannelId.hot, "热点");
        put(ChannelId.yule, "娱乐");
        put(ChannelId.video, "视频");
        put(ChannelId.finance, "财经");
        put(ChannelId.car, "汽车");
        put(ChannelId.tech, "科技");
        put(ChannelId.sport, "体育");
        put(ChannelId.funny, "搞笑");
    }};

    public interface Urls {
        String HOST = "http://120.24.167.150/";
    }


    public interface ChannelId {
        String toutiao = "toutiao";
        String society = "society";
        String hot = "hot";
        String yule = "yule";
        String video = "video";
        String finance = "finance";
        String car = "car";
        String tech = "tech";
        String sport = "sport";
        String funny = "funny";
    }

    public static String getChannelNameById(String channelId) {
        if (TextUtils.isEmpty(channelId)) {
            return null;
        }
        return CHANNEL_MAP.get(channelId);
    }
}
