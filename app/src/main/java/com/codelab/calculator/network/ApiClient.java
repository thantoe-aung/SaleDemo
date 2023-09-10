package com.codelab.calculator.network;

import com.codelab.calculator.models.Post;
import com.codelab.calculator.network.dataagent.PostDataAgent;
import com.codelab.calculator.utils.AppConstants;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient implements PostDataAgent {
    private static ApiClient objInstance;
    private ApiInterface apiInterface;

    private ApiClient(){
        OkHttpClient httpClient=new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .build();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        apiInterface = retrofit.create(ApiInterface.class);

    }

    public  static  ApiClient getInstance(){
        if(objInstance == null){
            objInstance=new ApiClient();
        }
        return  objInstance;
    }


    @Override
    public void getPostsFromNetwork(GetPostsNetworkDelegate delegate) {
        Call<List<Post>> postResponseCall=apiInterface.getAllPosts();
        postResponseCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                delegate.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                delegate.onFailure(t.getLocalizedMessage());
            }
        });
    }
}
