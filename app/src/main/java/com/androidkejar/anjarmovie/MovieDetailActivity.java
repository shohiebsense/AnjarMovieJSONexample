package com.androidkejar.anjarmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Shohieb on 4/16/2016.
 */
public class MovieDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_movie);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String image = intent.getStringExtra("image");
        String description = intent.getStringExtra("description");
        float rating = intent.getFloatExtra("rating",0);

        TextView titleTextView = (TextView) findViewById(R.id.movie_title);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.movie_rating);
        TextView dateTextView = (TextView) findViewById(R.id.movie_date);
        TextView descriptionTextView = (TextView) findViewById(R.id.movie_description);
        ImageView imageView = (ImageView) findViewById(R.id.movie_image);

        titleTextView.setText(title);
        dateTextView.setText(image);
        descriptionTextView.setText(description);
        ratingBar.setRating(rating);

        Glide.with(this).load(image)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
    }
}
