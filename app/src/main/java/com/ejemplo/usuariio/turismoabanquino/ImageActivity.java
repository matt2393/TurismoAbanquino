package com.ejemplo.usuariio.turismoabanquino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageActivity extends AppCompatActivity {

    private ImageView imageView;
    private Toolbar toolbar;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imageView=findViewById(R.id.imagen_full);

        url=getIntent().getStringExtra("URL_IMG");
        if(url!=null) {
            Glide.with(this)
                    .load(url)
                    .into(imageView);
        }
    }
}
