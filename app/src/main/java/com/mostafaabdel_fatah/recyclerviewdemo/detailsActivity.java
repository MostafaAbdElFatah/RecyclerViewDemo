package com.mostafaabdel_fatah.recyclerviewdemo;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class detailsActivity extends AppCompatActivity {

    String text = "null";
    int imageSource ;
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.CollapsingToolbarLayout);

                //*********************** Get Data From Previous activity **********************************
        try {
            Intent intent = getIntent();
            text = intent.getStringExtra("name").toString();
            imageSource = intent.getIntExtra("image",R.drawable.bg);
        } catch (Exception e) {
            Toast.makeText(this, "Message from Detalis: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        final ImageView imageView = (ImageView) findViewById(R.id.imageuser);
        Picasso.with(imageView.getContext()).load(imageSource).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        applyPalette(palette);
                    }
                });
            }

            @Override
            public void onError() {

            }
        });
        /********************************************************************/
        TextView textView = (TextView) findViewById(R.id.namtextView);
        textView.setText(text);
        Button home =(Button) findViewById(R.id.backbtn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(detailsActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);*/
        //******************************************************************************************

    }
    private  void  applyPalette(Palette palette){
        int primaryDark = getResources().getColor(R.color.colorPrimary);
        int primary = getResources().getColor(R.color.colorPrimary);
        collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
        collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
        updateBackground((FloatingActionButton)findViewById(R.id.fab),palette);
        supportStartPostponedEnterTransition();

    }
    private  void updateBackground(FloatingActionButton fab , Palette palette){
        int lightVibrantColor = palette.getLightVibrantColor(getResources().getColor(android.R.color.white));
        int vibrantColor = palette.getVibrantColor(getResources().getColor(R.color.accent));

        fab.setRippleColor(lightVibrantColor);
        fab.setBackgroundTintList(ColorStateList.valueOf(vibrantColor));
    }
}
