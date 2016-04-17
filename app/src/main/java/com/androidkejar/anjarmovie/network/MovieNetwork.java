package com.androidkejar.anjarmovie.network;

import android.graphics.Movie;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.androidkejar.anjarmovie.adapter.MovieAdapter;
import com.androidkejar.anjarmovie.item.MovieItem;
import com.androidkejar.anjarmovie.item.MovieRatingSorter;
import com.androidkejar.anjarmovie.item.MovieTitleSorter;
import com.androidkejar.anjarmovie.model.MovieModel;
import com.androidkejar.anjarmovie.network.interfaces.BaseUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Shohieb on 4/16/2016.
 */
public class MovieNetwork extends AsyncTask<String,Void,String> implements BaseUrl {

    String response = "kosong";
    ArrayList<MovieItem> itemList;
    MovieAdapter movieAdapter;

    public MovieNetwork(ArrayList<MovieItem> itemList,MovieAdapter movieAdapter){
        this.itemList = itemList;
        this.movieAdapter = movieAdapter;
    }

    @Override
    protected void onPreExecute() {
        Log.e("forecase","method preexecute");
        Log.e("forecase",response);
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Uri builduri = Uri.parse(domain).buildUpon()
                    .appendQueryParameter("api_key", appId)
                    .build();

            //URL url = new URL(getForecase);
            URL url = new URL(builduri.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(conn.getInputStream());
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String nextline = null;
            while ((nextline = reader.readLine()) != null) {
                sb.append(nextline);
            }
            response = sb.toString();
            Log.d("respon",response);
            //parsing data json
            parsingjson(response);
        }
        catch (IOException e){
            Log.e("error",e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(!response.equals("kosong")) {
            Log.e("forecase", "method post execute");
            Log.e("forecase", response);

            movieAdapter.notifyDataSetChanged();
        }
    }

    void parsingjson(String response){
        Log.e("hasildata",response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for (int a = 0;a<jsonArray.length();a++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(a);
                String original_title = jsonObject1.getString("original_title");
                String id = jsonObject1.getString("id");
                String description = jsonObject1.getString("overview");
                String date = jsonObject1.getString("release_date");
                String image = "http://image.tmdb.org/t/p/w500"+jsonObject1.getString("poster_path");
                String rating = jsonObject1.getString("vote_average");



                MovieItem movieItem = new MovieItem();
                movieItem.setId(id);
                movieItem.setTitle(original_title);
                movieItem.setDescription(description);
                movieItem.setRating(Float.parseFloat(rating));
                movieItem.setDate(date);
                movieItem.setImage(image);

                itemList.add(movieItem);
                Log.e("hasildata "+a,original_title);
                /*movieAdapter.add(movieModel);
                arrayList.add(MovieModel);*/
            }

        }catch (JSONException e){
            Log.e("errorr",e.toString());
        }
    }


}
