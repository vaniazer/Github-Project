package com.example.github_projek.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.github_projek.model.api.User;
import com.example.github_projek.service.GithubAPI;
import com.example.github_projek.service.GithubResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    private GithubAPI githubAPI;
    private MutableLiveData<ArrayList<User>> listUser = new MutableLiveData<>();
    private MutableLiveData<GithubResponse> AllResponse = new MutableLiveData<>();
    private MutableLiveData<ArrayList<User>> listFollow = new MutableLiveData<>();


    public void setGithubAPI() {
        if (this.githubAPI == null){
            githubAPI = new GithubAPI();
        }
        githubAPI.getAPI().getUsers(50).enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                ArrayList<User> githubUsers = response.body();
                if (githubUsers != null){
                    listUser.postValue(githubUsers);
                    Log.d("viewmodel", githubUsers.toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Log.d("viewmodel", t.getMessage());
            }
        });
    }

    public LiveData<ArrayList<User>> getGithubUser(){
        return listUser;
    }

    // get user follower or following data =======================
    public void setGithubApiFollow(String username, String followType) {
        if (this.githubAPI == null){
            githubAPI = new GithubAPI();
        }
        githubAPI.getAPI().getUserFollow(username, followType).enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                ArrayList<User> githubUsers = response.body();
                if (githubUsers != null){
                    listFollow.postValue(githubUsers);
                    Log.d("viewmodelFollow", githubUsers.toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Log.d("viewmodelFollow", t.getMessage());
            }
        });
    }

    public LiveData<ArrayList<User>> getGithubUserFollow(){
        return listFollow;
    }

    // get detail data ===================
    public void setGithubApiDetail(String username){
        if (this.githubAPI == null){
            githubAPI = new GithubAPI();
        }
        githubAPI.getAPI().getDetailUser(username).enqueue(new Callback<GithubResponse>() {
            @Override
            public void onResponse(Call<GithubResponse> call, Response<GithubResponse> response) {
                GithubResponse githubResponse = response.body();
                if (githubResponse != null){
                    AllResponse.postValue(githubResponse);
                    Log.d("viewmodel", githubResponse.toString());
                }

            }

            @Override
            public void onFailure(Call<GithubResponse> call, Throwable t) {
                Log.d("viewmodel", t.getMessage());
            }
        });
    }

    public LiveData<GithubResponse> getGithubDetail(){ return AllResponse; }


}
