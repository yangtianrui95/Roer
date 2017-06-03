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
            "http://pic47.nipic.com/20140830/7487939_180041822000_2.jpg",
            "http://pic41.nipic.com/20140518/4135003_102912523000_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496515403689&di=c73e5b3625866d63692e6f0fcb554194&imgtype=0&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fitem%2F201312%2F05%2F20131205172506_4jXjY.jpeg",
            "http://h.hiphotos.baidu.com/image/pic/item/3b87e950352ac65c0f1f6e9efff2b21192138ac0.jpg",
            "http://pic42.nipic.com/20140618/9448607_210533564001_2.jpg",
            "http://pic10.nipic.com/20101027/3578782_201643041706_2.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2104380944,3853327462&fm=23&gp=0.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496515488940&di=e5ae3579cc65450bc6ce03950ba46957&imgtype=0&src=http%3A%2F%2Fbook.img.ireader.com%2Fgroup6%2FM00%2F54%2F72%2FCmQUNlYBTGiEEInvAAAAAKa2vB0568809414.jpg",
            "https://cdn.pixabay.com/photo/2017/05/24/11/40/neuschwanstein-castle-2340327__340.jpg",
            "http://b.zol-img.com.cn/desk/bizhi/image/3/960x600/1375841395686.jpg",
            "https://cdn.pixabay.com/photo/2017/05/23/04/10/girl-2336113__340.jpg",
            "https://cdn.pixabay.com/photo/2017/05/22/20/18/rose-2335203__340.jpg",
            "https://cdn.pixabay.com/photo/2017/05/13/13/43/sunset-2309623__340.jpg",
            "http://pic41.nipic.com/20140518/4135003_102025858000_2.jpg",
            "http://www.1tong.com/uploads/wallpaper/landscapes/200-4-730x456.jpg",
            "http://pic.58pic.com/58pic/13/00/22/32M58PICV6U.jpg",
            "https://cdn.pixabay.com/photo/2017/05/24/16/09/london-2340879__340.jpg",
            "https://cdn.pixabay.com/photo/2017/05/29/06/33/natural-2353013__340.jpg",
            "http://pica.nipic.com/2007-12-21/2007122115114908_2.jpg",
            "https://cdn.pixabay.com/photo/2017/03/27/13/44/beach-2178822__340.jpg",
            "http://h.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0feeea8a30f5e6034a85edf720f.jpg",
            "http://img0.pconline.com.cn/pconline/bizi/desktop/1412/ER2.jpg",
            "http://pic.58pic.com/58pic/11/25/04/91v58PIC6Xy.jpg",
            "https://cdn.pixabay.com/photo/2017/03/27/12/20/daylight-2178350__340.jpg",
            "http://pic25.nipic.com/20121210/7447430_172514301000_2.jpg",
            "https://cdn.pixabay.com/photo/2017/05/15/23/58/alpaca-2316484__340.jpg",
            "https://cdn.pixabay.com/photo/2017/05/18/08/25/model-2322901__340.jpg",
            "https://cdn.pixabay.com/photo/2017/05/24/16/01/colorado-2340851__340.jpg",
            "https://cdn.pixabay.com/photo/2017/05/26/15/12/landscape-2346252__340.jpg",
            "https://cdn.pixabay.com/photo/2017/05/25/21/29/dublin-2344423__340.jpg",
            "https://cdn.pixabay.com/photo/2017/05/04/00/52/munich-2282588__340.jpg",
            "https://cdn.pixabay.com/photo/2017/05/12/22/39/cyprus-2308326__340.jpg",
            "https://cdn.pixabay.com/photo/2017/05/12/03/26/sunset-in-tam-giang-lagoon-2305953__340.jpg",
            "https://cdn.pixabay.com/photo/2017/05/13/08/53/train-2309016__340.jpg",
            "https://cdn.pixabay.com/photo/2017/05/10/15/05/himalayas-2301040__340.jpg",
    };
}
