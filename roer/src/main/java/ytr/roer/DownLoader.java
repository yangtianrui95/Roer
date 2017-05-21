package ytr.roer;

import java.io.File;

/**
 * Created by tianrui on 17-5-19.
 * <p>
 * Large file download utility class.
 */
public class DownLoader {

    private volatile static DownLoader sInstance;


    public static DownLoader getInstance() {
        if (sInstance == null) {
            synchronized (DownLoader.class) {
                if (sInstance == null) {
                    sInstance = new DownLoader();
                }
            }
        }
        return sInstance;
    }

    public void get(String requestUrl, File dest) {

    }

}
