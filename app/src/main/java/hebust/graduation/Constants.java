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

    public static String[] IMAGE_URLS = new String[]{
            "http://b.hiphotos.baidu.com/zhidao/pic/item/a6efce1b9d16fdfafee0cfb5b68f8c5495ee7bd8.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496515403689&di=c73e5b3625866d63692e6f0fcb554194&imgtype=0&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fitem%2F201312%2F05%2F20131205172506_4jXjY.jpeg",
            "http://h.hiphotos.baidu.com/image/pic/item/3b87e950352ac65c0f1f6e9efff2b21192138ac0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2104380944,3853327462&fm=23&gp=0.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496515488940&di=e5ae3579cc65450bc6ce03950ba46957&imgtype=0&src=http%3A%2F%2Fbook.img.ireader.com%2Fgroup6%2FM00%2F54%2F72%2FCmQUNlYBTGiEEInvAAAAAKa2vB0568809414.jpg",
            "http://b.zol-img.com.cn/desk/bizhi/image/3/960x600/1375841395686.jpg",
            "http://pic.58pic.com/58pic/13/00/22/32M58PICV6U.jpg",
            "http://h.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0feeea8a30f5e6034a85edf720f.jpg",
            "http://img0.pconline.com.cn/pconline/bizi/desktop/1412/ER2.jpg",
            "http://pic.58pic.com/58pic/11/25/04/91v58PIC6Xy.jpg",
            "http://www.gratisography.com/pictures/409_1.jpg",
            "http://www.gratisography.com/pictures/395_1.jpg",
            "http://www.gratisography.com/pictures/407_1.jpg",
            "http://www.gratisography.com/pictures/395_1.jpg",
            "http://www.gratisography.com/pictures/375_1.jpg",
            "http://www.gratisography.com/pictures/379_1.jpg",
            "http://www.gratisography.com/pictures/295_1.jpg",
            "http://www.gratisography.com/pictures/243_1.jpg",
            "http://www.gratisography.com/pictures/240_1.jpg",
            "http://www.gratisography.com/pictures/236_1.jpg",
            "http://www.gratisography.com/pictures/237_1.jpg",
            "http://www.gratisography.com/pictures/234_1.jpg",
            "http://www.gratisography.com/pictures/231_1.jpg",
            "http://www.gratisography.com/pictures/219_1.jpg",
            "http://www.gratisography.com/pictures/216_1.jpg",
            "http://colorlisa.com/pictures/201_1.jpg",
            "http://colorlisa.com/pictures/197_1.jpg",
            "http://colorlisa.com/pictures/194_1.jpg",
            "http://colorlisa.com/pictures/173_1.jpg",

    };
}
