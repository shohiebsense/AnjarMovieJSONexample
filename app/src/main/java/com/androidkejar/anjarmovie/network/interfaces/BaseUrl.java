package com.androidkejar.anjarmovie.network.interfaces;

/**
 * Created by Shohieb on 4/16/2016.
 */
public interface BaseUrl {
    String domain = " http://api.themoviedb.org/3/movie/top_rated?";
    String appId = "06ef206f4f8c13d2c042851d0642bfe5";
    String getDomain = domain + appId;
}