package com.codelab.calculator.network;

import com.codelab.calculator.models.Post;
import com.codelab.calculator.utils.AppConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;



public interface ApiInterface {
    @GET(AppConstants.GET_POSTS)
    Call<List<Post>> getAllPosts();
}
