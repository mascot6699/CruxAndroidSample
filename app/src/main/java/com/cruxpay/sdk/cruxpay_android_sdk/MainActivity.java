package com.cruxpay.sdk.cruxpay_android_sdk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.cruxpay.sdk.cruxpayandroidsdk.CruxPayJavaScriptCoreRuntime;

import org.json.JSONObject;
import org.liquidplayer.javascript.JSContext;
import org.liquidplayer.javascript.JSException;
import org.liquidplayer.javascript.JSValue;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CruxPayJavaScriptCoreRuntime runtime = new CruxPayJavaScriptCoreRuntime();
        runtime.init(getApplicationContext());
        JSContext jsContext = runtime.getJSContext();
        jsContext.evaluateScript("var window = this");
        jsContext.evaluateScript("var self = this");

        System.out.println("==================0=======");
        System.out.println(jsContext.evaluateScript("function getPassHashedEncryptionKey() { const user_password = \"foo\"; return \"fookey\";} const cruxClientOptions = { getEncryptionKey: getPassHashedEncryptionKey, walletClientName: \"cruxdev\"};"));
        System.out.println("==================1=======");

        try {
            System.out.println(jsContext.evaluateScript("var cruxClient = new window.CruxPay.CruxClient(cruxClientOptions)"));
        } catch (JSException e) {
            System.out.println("Caught error in Java catch block");
            System.out.println(e.toString());
        }
        String myFile = null;
        try {
            myFile = CruxPayJavaScriptCoreRuntime.getFromFile(getApplicationContext(), "storage.js");
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsContext.evaluateScript(myFile);
        jsContext.evaluateScript("s.setJSON('lol', {'lolwow': 'lolwow1'});");
        jsContext.evaluateScript("var f; s.getItem('lol').then((a)=>{f = a; console.log('uuuuuuuuuuuuuuuuuuuuuu' + a);});");
        JSValue f = jsContext.property("f");
        // Log.e("", "" + f.toString());

    }


}
