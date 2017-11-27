package com.example.brunoluz.pj;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class mostraFoto extends AppCompatActivity {
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_foto);
        img = (ImageView) findViewById(R.id.imageView2);
        String f = getIntent().getStringExtra("img");
        img.setImageURI(Uri.parse(f));
        //img.setRotation(90);
    }
}
