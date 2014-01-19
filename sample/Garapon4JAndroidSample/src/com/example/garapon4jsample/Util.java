
package com.example.garapon4jsample;

import android.util.Log;

public class Util {

    private static boolean DEBUG = BuildConfig.DEBUG;

    private static String TAG = "garapon";

    public static void logv(final String logMessage) {
        if (logMessage == null) {
            return;
        }
        if (DEBUG) {
            Log.v(TAG, logMessage);
        }
    }

    public static void loge(final String logMessage) {
        if (logMessage == null) {
            return;
        }
        if (DEBUG) {
            Log.e(TAG, logMessage);
        }
    }

    public static void logv(int size) {
        logv(String.valueOf(size));

    }

    public static void logv(boolean result2) {
        logv(String.valueOf(result2));
    }

}
