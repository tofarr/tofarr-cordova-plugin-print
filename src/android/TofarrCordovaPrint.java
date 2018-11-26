package org.tofarr.plugin;

import android.app.Activity;
import android.content.Context;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

public class TofarrCordovaPrint extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        LOG.i("TofarrCordovaPrint", "execute:1");
        if (action.equals("print")) {

          final WebView view = (WebView)this.webView.getView();
          final Activity activity = this.cordova.getActivity();

          activity.runOnUiThread((new Runnable(){

            public void run(){
              final WebView printView = new WebView(activity);
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
              printView.loadUrl(view.getUrl());
            }
          }));
          callbackContext.success("Print Complete");
          return true;
        } else {
          return false;
        }
    }
}
