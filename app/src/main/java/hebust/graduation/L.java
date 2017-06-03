package hebust.graduation;

import android.util.Log;

public class L {

    private static String TAG = "tianrui";

    public static void setTAG(String TAG) {
        L.TAG = TAG;
    }

    public static void d(String msg) {
        Log.d(TAG, msg);
    }
}
