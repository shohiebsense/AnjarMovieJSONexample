package com.androidkejar.anjarmovie.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.androidkejar.anjarmovie.MovieDetailActivity;
import com.androidkejar.anjarmovie.R;
import com.androidkejar.anjarmovie.adapter.MovieAdapter;
import com.androidkejar.anjarmovie.item.MovieItem;
import com.androidkejar.anjarmovie.item.MovieRatingSorter;
import com.androidkejar.anjarmovie.item.MovieTitleSorter;
import com.androidkejar.anjarmovie.model.MovieModel;
import com.androidkejar.anjarmovie.network.MovieNetwork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Shohieb on 4/16/2016.
 */
public class MovieFragment extends android.support.v4.app.Fragment {

    MovieNetwork movieNetwork;
    ArrayList<String> data2;
    ArrayAdapter<String> adapter;
    MovieAdapter movieAdapter;
    List<MovieModel> movieModelList;

    //Contoh pake Item
    ArrayList<MovieItem> itemList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.movie_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.sortbyrating:
                Collections.sort(itemList,new MovieRatingSorter());
                movieAdapter.notifyDataSetChanged();
                break;
            case R.id.sortbytitle:
                Collections.sort(itemList, new MovieTitleSorter());
                movieAdapter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_fragment, container, false);


        data2 = new ArrayList<>();
     /*   String a = "Title: AAA Rating: 8.9";
        data2.add(a);
*/

        //contoh Movie Item
      /*  MovieItem movieItem = new MovieItem();
        movieItem.setId("1");
        movieItem.setTitle("asd");
        movieItem.setDescription("desc");
        movieItem.setRating("8.12");
        itemList.add(movieItem);*/


        itemList = new ArrayList<>();
        movieAdapter = new MovieAdapter(getActivity(),R.layout.item_movie,itemList);
        final GridView movieGridView = (GridView) view.findViewById(R.id.gridViewMovie);
        movieGridView.setAdapter(movieAdapter);
        new MovieNetwork(itemList,movieAdapter).execute();

        movieGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String forecast = itemList.get(position).getId();
                String description = itemList.get(position).getDescription();
                String image = itemList.get(position).getImage();
                Float rating = itemList.get(position).getRating();

                MovieItem movieItem = (MovieItem) movieGridView.getItemAtPosition(position);
                String anuanu = movieItem.getId().toString();
                Toast.makeText(getActivity(),forecast + " xxx " + anuanu,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(),MovieDetailActivity.class);
                /*intent.putExtra("intentdata",forecast + "  XXX " + anuanu);*/
                intent.putExtra("id",movieItem.getId());
                intent.putExtra("title",movieItem.getTitle());
                intent.putExtra("description",movieItem.getDescription());
                intent.putExtra("image",movieItem.getImage());
                intent.putExtra("rating",movieItem.getRating());

                startActivity(intent);

            }
        });
        return view;
    }


}