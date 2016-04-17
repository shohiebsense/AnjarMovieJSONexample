package com.androidkejar.anjarmovie.item;

import java.util.Comparator;

/**
 * Created by Shohieb on 4/16/2016.
 */
public class MovieItem  {
    String id,title,description,date,image;
    float rating;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public Float getRating() {
        return rating;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }



}
