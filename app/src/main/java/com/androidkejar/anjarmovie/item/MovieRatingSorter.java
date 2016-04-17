package com.androidkejar.anjarmovie.item;

import java.util.Comparator;

/**
 * Created by Shohieb on 4/17/2016.
 */
public class MovieRatingSorter implements Comparator<MovieItem> {

    @Override
    public int compare(MovieItem lhs, MovieItem rhs) {
        return lhs.getRating().compareTo(rhs.getRating());
    }
}
