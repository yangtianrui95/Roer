package ytr.roer;

import android.util.Log;

/**
 * Created by tianrui on 17-4-29.
 */
public class L {

    private static final String TAG = "Roer";

    public static void d(String msg) {
        if (msg != null) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String s, int length, String url) {

    }

    public static void e(String msg) {
        if (msg != null) {
            Log.e(TAG, msg);
        }
    }
}
