package org.tofarr.plugin;

import android.app.Activity;
import android.content.Context;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.net.URL;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

public class TofarrCordovaPrint extends CordovaPlugin {

    @Override
    public boolean execute(String action, final JSONArray data, CallbackContext callbackContext) throws JSONException {
        if (action.equals("print")) {
          final String content = data.getString(0);
          final WebView view = (WebView)this.webView.getView();
          final Activity activity = this.cordova.getActivity();

          activity.runOnUiThread((new Runnable(){

            public void run(){
              final WebView printView = new WebView(activity);
              printView.getSettings().setJavaScriptEnabled(true);
              printView.setWebViewClient(new WebViewClient() {

                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                  return false;
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                  PrintManager printManager = (PrintManager) activity.getSystemService(Context.PRINT_SERVICE);
                  String jobName = "Print Document";
                  PrintDocumentAdapter printAdapter = view.createPrintDocumentAdapter(jobName);
                  PrintAttributes.Builder builder = new PrintAttributes.Builder();
                  builder.setMediaSize(PrintAttributes.MediaSize.ISO_A4);
                  PrintJob printJob = printManager.print(jobName, printAdapter, builder.build());
                }
              });

              String baseUrl = null;
              try{
                URL url = new URL(view.getUrl());
                String path = url.getFile().substring(0, url.getFile().lastIndexOf('/') + 1);
                baseUrl = url.getProtocol() + "://" + url.getHost() + path;
              }catch(Exception ex) {
                baseUrl = null;
              }

              String data = "<!doctype html><html>" + content + "</html>";
              printView.loadDataWithBaseURL(baseUrl, data, "text/html", "utf-8", null);

            }
          }));
          callbackContext.success("Print Complete");
          return true;
        } else {
          return false;
        }
    }
}
