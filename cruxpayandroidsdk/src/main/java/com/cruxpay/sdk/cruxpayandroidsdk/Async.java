package com.cruxpay.sdk.cruxpayandroidsdk;

import android.os.Handler;
import android.os.Looper;

public class Async {

    private static Handler mHandler;

    public static void init() {
        new Thread(new Runnable() {
            public void run() {
                Looper.prepare();
                mHandler = new Handler();
                Looper.loop();
            }
        }).start();
    }

    public static void run(String name, final Runnable runnable) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                runnable.run();
            }
        });
    }

    public static void runDelayed(final Runnable runnable, long delay) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runnable.run();
            }
        }, delay);
    }
}
