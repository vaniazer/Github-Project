package com.example.github_projek.service;

import com.example.github_projek.model.api.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubInterface {
    @GET("users?")
    Call<ArrayList<User>> getUsers(@Query("since") int random);

    @GET("users/{username}")
    Call<GithubResponse> getDetailUser(@Path("username") String username);

    @GET("users/{username}/{follow}")
    Call<ArrayList<User>> getUserFollow(@Path("username") String username, @Path("follow") String follow);

}
