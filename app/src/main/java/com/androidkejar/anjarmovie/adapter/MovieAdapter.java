package com.androidkejar.anjarmovie.adapter;

import android.content.Context;
import android.graphics.Movie;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.androidkejar.anjarmovie.R;
import com.androidkejar.anjarmovie.item.MovieItem;
import com.androidkejar.anjarmovie.model.MovieModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.ImageVideoBitmapDecoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shohieb on 4/16/2016.
 */
public class MovieAdapter extends ArrayAdapter<MovieItem> {

    ArrayList<MovieItem> itemList;
    Context context;
    int resources;
    LayoutInflater vi;

    public MovieAdapter(Context context, int resource, ArrayList<MovieItem> itemList) {
        super(context, resource,itemList);
        vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemList = itemList;
        this.context = context;
        this.resources = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
     convertView = vi.inflate(resources,null);
        if(vi != null){
            TextView title = (TextView) convertView.findViewById(R.id.movie_title);
            RatingBar rating = (RatingBar) convertView.findViewById(R.id.movie_rating);
            TextView txtDate = (TextView) convertView.findViewById(R.id.movie_date);
            TextView description = (TextView) convertView.findViewById(R.id.movie_description);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.movie_image);

            title.setText(itemList.get(position).getTitle());
            txtDate.setText(itemList.get(position).getTitle());
            description.setText(itemList.get(position).getTitle());
            rating.setRating(itemList.get(position).getRating());

            Glide.with(context).load(itemList.get(position).getImage())
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imageView);
        }

        return convertView;
    }
}
