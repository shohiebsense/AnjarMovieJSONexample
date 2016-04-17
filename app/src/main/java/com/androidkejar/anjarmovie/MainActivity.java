package com.androidkejar.anjarmovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.androidkejar.anjarmovie.ui.MovieFragment;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {


    //my api

    //06ef206f4f8c13d2c042851d0642bfe5
    //    http://api.themoviedb.org/3/movie/popular?api_key=[YOUR_API_KEY]
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.continer,new MovieFragment()).commit();
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(imageView);
    }
}
