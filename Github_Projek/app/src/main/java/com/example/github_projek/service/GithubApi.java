package com.example.github_projek.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubAPI {
    private Retrofit retrofit;

    private static final String URL_BASE = "https://api.github.com/";

    public GithubInterface getAPI(){
        if (retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(GithubInterface.class);
    }
}
