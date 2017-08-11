package com.alyafei.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.webkit.WebView;
import android.widget.Toast;


public class WebViewTest extends AppCompatActivity {
    WebView webView2;
    EditText txtURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView2 =(WebView) findViewById(R.id.webView2);
        webView2.setWebViewClient(new myWebView()); // important ,  // this to open the webview inside your application.
        //webView2.getSettings().setJavaScriptEnabled(true); // just attributes.
        webView2.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        txtURL =(EditText) findViewById(R.id.txtURL);
        webView2.loadUrl("https://www.google.com"); //default
    }

    public void goBack(View view) {
        webView2.goBack();
    }

    public void goForward(View view) {
        webView2.goForward();
    }

    // this to open the webview inside your application.
    private class myWebView extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }

    public void loadURL(View view) { // go button
        String URL=txtURL.getText().toString();
        LoadURLMethod(URL);
    }

    private void LoadURLMethod(String URL ) {
        Toast.makeText(this,URL,Toast.LENGTH_SHORT).show();
        webView2.loadUrl("https://"+URL);
        //txtURL.setText("");
    }

}
