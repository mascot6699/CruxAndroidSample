package com.cruxpay.sdk.cruxpayandroidsdk;

import android.content.Context;

import org.liquidplayer.javascript.JSFunction;
import org.liquidplayer.javascript.JSObject;
import org.liquidplayer.javascript.JSContext;
import org.liquidplayer.javascript.JSValue;


public class CruxPayJavaScriptCoreRuntime extends JSObject {

    private AndroidStorage as;
    private JSContext jsContext;

    public CruxPayJavaScriptCoreRuntime (final Context context) {
        as = new AndroidStorage(context);
        jsContext = new JSContext();
        jsContext.property("factorial", new JSFunction(jsContext, "factorial") {
            public JSValue factorial(Integer x) {
                return new JSValue(jsContext, as.factorial(x));
            }
        });
        jsContext.property("setItem", new JSFunction(jsContext, "setItem") {
            public void setItem(String key, String value) {
                as.setItemWithKeyValue(key, value);
            }
        });
        jsContext.property("getItem", new JSFunction(jsContext, "getItem") {
            public JSValue getItem(final String key) {
                return new JSValue(jsContext, as.getItemWithKey(key));
            }
        });
    }

}
