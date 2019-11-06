package com.cruxpay.sdk.cruxpayandroidsdk;

import android.content.Context;
import android.content.res.AssetManager;

import org.liquidplayer.javascript.JSException;
import org.liquidplayer.javascript.JSFunction;
import org.liquidplayer.javascript.JSObject;
import org.liquidplayer.javascript.JSContext;
import org.liquidplayer.javascript.JSValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class CruxPayJavaScriptCoreRuntime extends JSObject {

    private AndroidStorage as;
    private JSContext jsContext;

    public CruxPayJavaScriptCoreRuntime () {
        this.jsContext = new JSContext();
    }

    public void init(final Context context) {
        this.as = new AndroidStorage(context);
        try {
            String myFile = this.getFromFile(context, "parcel-bundle.js");
            jsContext.evaluateScript("var window = this");
            jsContext.evaluateScript("var self = this");
            try {
                jsContext.evaluateScript(myFile);
            } catch (JSException e) {
                System.out.println("Caught error in myFile catch block");
                System.out.println(e.toString());
                System.out.println(e.stack());
            }
        } catch (IOException e) {
            System.out.println("Caught error in myFile catch block");
            System.out.println(e.toString());
        }

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

    public JSContext getJSContext() {
        return this.jsContext;
    }

    public static String getFromFile(Context androidContextObject, String fileName) throws IOException {
        AssetManager assetManager = androidContextObject.getAssets();
        System.out.println(assetManager.list("/"));
        InputStream is = assetManager.open(fileName);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader input = new BufferedReader(isr);
        String line;
        StringBuilder returnString = new StringBuilder();
        while ((line = input.readLine())!= null) {
            returnString.append(line);
            returnString.append("\n");
        }
        return new String(returnString);

    }

}
