package com.sena.core.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.Locale;


    /*
    | ---------------
    | Class containing Util functions
    | ---------------
    */


public final class Utils {

    public static final String TAG = "Gogoski";
    public static final String SEPARATOR_COMMA = ",";
    public static final String STK_PACKAGE_NAME = "velox_it.gogoski";

    public static void d(Object tag, String message) {
            Log.d(TAG, String.format(Locale.getDefault(), "%s : %s", tag.getClass().getSimpleName(), message));

    }

    public static void d(String tag, String message) {
            Log.d(TAG, String.format(Locale.getDefault(), "%s : %s", tag, message));

    }

    public static void e(Object tag, String message) {
            Log.e(TAG, String.format(Locale.getDefault(), "%s : %s", tag.getClass().getSimpleName(), message));
    }

    public static void e(String tag, String message) {
            Log.e(TAG, String.format(Locale.getDefault(), "%s : %s", tag, message));
    }

    public static void runTask(Task task, String label) {
        new Runner(task, label).start();
    }

    public static <T> void runTask(Activity a, ResultTask<T> task, String label) {
        new ResultRunner<>(a, task, label).start();
    }

    public static String getDeviceId(Context c) {
        TelephonyManager telephonyManager = (TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = telephonyManager.getDeviceId();
        if (deviceId == null) {
            deviceId = getSerialNo();
        }
        return deviceId;
    }

    public static String getSerialNo() {
        String serial = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            serial = (String) get.invoke(c, "ril.serialnumber", "unknown-serial-no");
        } catch (Exception e) {
        }
        return serial;
    }

    public static boolean isConnected(Context c) {
        ConnectivityManager manager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }

    public interface Task {
        void run() throws Exception;
    }

    public interface ResultTask<T> {
        T run() throws Exception;

        void onFinish(T result);
    }

    private static class Runner extends Thread {
        private Task task;

        public Runner(Task task, String label) {
            super(label);
            this.task = task;
        }

        @Override
        public void run() {
            try {
                task.run();
            } catch (Exception e) {
                e(getName(), "Could not run Task [" + getName() + "]:");
                e(getName(), e.getMessage());
                e.printStackTrace();
//                throw new RuntimeException(m);
            }
        }
    }

    private static class ResultRunner<T> extends Thread {

        private ResultTask<T> task;
        private Activity a;

        public ResultRunner(Activity a, ResultTask<T> task, String label) {
            super(label);
            this.a = a;
            this.task = task;
        }

        @Override
        public void run() {
            try {
                final T result = task.run();
                if (a == null) {
                    task.onFinish(result);
                } else {
                    a.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            task.onFinish(result);
                        }
                    });
                }
            } catch (Exception e) {
                e(getName(), "Could not run ResultTask [" + getName() + "]:");
                e(getName(), e.getMessage());
                e.printStackTrace();
//                throw new RuntimeException(m);
            }
        }
    }

    public static int convertDpToPixel(float dp, Context context){
        float px = dp *  (context.getResources().getDisplayMetrics().densityDpi / 160f);
        return (int)px;
    }

    /**
     * Get the rounded Bitmap from the given bitmap
     * @param bm Bitmap
     * @param targetWidth Required size of Width
     * @param targetHeight Required size of height
     * @return Bitmap
     */
    public static Bitmap getRoundedBitmap(Bitmap bm, int targetWidth, int targetHeight) {
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = bm;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth,
                        targetHeight), null);
        return targetBitmap;
    }





    public static void vibrate(Context c, long l) {
        Vibrator vibrator = (Vibrator) c.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(l);
    }



    public static void pause(long length) {
        try {
            Thread.sleep(length);
        } catch (Exception e) {
        }
    }
}
