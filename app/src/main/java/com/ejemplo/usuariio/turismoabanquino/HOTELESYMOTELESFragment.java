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


public class HOTELESYMOTELESFragment extends Fragment {
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_hotelesymotele, container, false);

        WebView myWebView = (WebView)view.findViewById(R.id.WebViewHoteles);
        myWebView.loadUrl("https://www.google.com/travel/hotels/Abancay?g2lb=4181926%2C4208993%2C4209588%2C4223281%2C4225902%2C4227717%2C4207631%2C4215556%2C4220469%2C4222767&hl=es-419&gl=pe&un=0&q=hoteles%20y%20moteles%20en%20abancay&rp=OAJAAA&ictx=1&ved=0ahUKEwjn3cTyw7bfAhXvx1kKHVgeACAQjGoIWw&hrf=CgUI4QEQACIDUEVOKhYKBwjiDxAMGBkSBwjiDxAMGBoYASgAWAFyAggCmgEJEgdBYmFuY2F5ogEUCgkvbS8wNHYxX2YSB0FiYW5jYXmSAQIgAQ&tcfs=Ei4KCS9tLzA0djFfZhIHQWJhbmNheRoYCgoyMDE4LTEyLTI1EgoyMDE4LTEyLTI2GAJSAA");
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
