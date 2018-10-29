package com.example.ericmartins.echoapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.ericmartins.echoapp.R;


public class MuralActivity extends AppCompatActivity {

    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mural);

        WebView wv =(WebView)findViewById(R.id.webView);

        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(false);

        wv.loadUrl("https://www.facebook.com/g1/?__tn__=kC-R&eid=ARATGlqMeSZLeFP1xysYEkd9H2WZQUUr00JCStddpYV_y-h0m-lHHWHwvK0MclITkz6w91gdPWeOYY6B&hc_ref=ARQkAfMeRh7y22eI1PQUfBfvmNCX3lHhxXtvfEZuoYx5d3CoAGhHCKQp2aw9HvV0jMA&fref=nf&__xts__[0]=68.ARC0qqm__InM6YGqr42d0bX_HXkGcmIPl0ZyceRm95TCPAyR17D1RnxkLH1JNMeJYJgdn0LFDSylb4sEebI2NHyqjdSUvkZHXfeWOucMOQzG3qEv8mfgrZuLmLp7iWtOWNZHF7QW-fEEqHMLc2GwN-3DohaD3ReLKxBpkn9ZSu2r341hfA2aMoy140vOvD4pz_MIh23Pj5L05-U5QEPCtZ9742iQczLN6QhM8DF5");
    }
}
