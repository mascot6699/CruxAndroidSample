package com.cruxpay.sdk.cruxpayandroidsdk;

import android.content.Context;
import android.content.SharedPreferences;


public class AndroidStorage {
    private Context context;
    private static final String SHARE_PREFERENCE_NAME = "crux.storage";

    public AndroidStorage(Context context) {
        this.context = context;
    }

    public String getItemWithKey(final String key) {
        SharedPreferences sharedPref = this.context.getSharedPreferences(SHARE_PREFERENCE_NAME, Context.MODE_PRIVATE);
        String defaultValue = "";
        return sharedPref.getString(key, defaultValue);
    }

    public void setItemWithKeyValue(final String key, final String value) {
        SharedPreferences sharedPref = this.context.getSharedPreferences(SHARE_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
//        editor.apply();
    }

    public Integer factorial(Integer x) {
        int factorial = 1;
        for (; x > 1; x--) {
            factorial *= x;
        }
        return factorial;
    }

}
