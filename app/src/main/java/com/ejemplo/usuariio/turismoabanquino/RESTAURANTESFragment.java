package com.ejemplo.usuariio.turismoabanquino;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;



public class RESTAURANTESFragment extends Fragment {
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_restaurante, container, false);

        WebView myWebView = (WebView)view.findViewById(R.id.WebViewRestaurante);
        myWebView.loadUrl("https://www.google.com/search?q=restaurantes+en+abancay&npsic=0&rflfq=1&rlha=0&rllag=-13634894,-72880842,492&tbm=lcl&ved=2ahUKEwiS2OfSxLbfAhVHx1kKHfIcAR0QjGp6BAgFEEI&tbs=lrf:!2m4!1e17!4m2!17m1!1e2!2m1!1e2!2m1!1e3!3sIAE,lf:1,lf_ui:9&rldoc=1#rlfi=hd:;si:6257971062747785308;mv:!1m2!1d-13.627214700000001!2d-72.8694838!2m2!1d-13.641871799999999!2d-72.8883666");
        myWebView.setWebViewClient(new MyWebViewClient());
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        return view;

    }

    private class MyWebViewClient extends WebViewClient {

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        WebView mWebView;
        mWebView = (WebView) view.findViewById(R.id.WebViewRestaurante);
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                    } else {

                    }
                    return true;
            }
        }
        return onKeyDown(keyCode, event);
    }
}
