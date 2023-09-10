package com.codelab.calculator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.codelab.calculator.R;
import com.codelab.calculator.adapters.PostRecyclerAdapter;
import com.codelab.calculator.models.Post;
import com.codelab.calculator.network.ApiClient;
import com.codelab.calculator.network.dataagent.PostDataAgent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostActivity extends BaseActivity {

    @BindView(R.id.rcvPosts)
    RecyclerView rcvPosts;

    PostRecyclerAdapter adapter;


    public static Intent newIntent(Context context){
        Intent intent=new Intent(context,PostActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        ButterKnife.bind(this);

        adapter=new PostRecyclerAdapter();
        rcvPosts.setLayoutManager(new LinearLayoutManager(this));
        rcvPosts.setAdapter(adapter);

        ApiClient.getInstance().getPostsFromNetwork(new PostDataAgent.GetPostsNetworkDelegate() {
            @Override
            public void onSuccess(List<Post> postList) {
                adapter.setNewData(postList);
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(PostActivity.this,errorMessage,Toast.LENGTH_LONG).show();
            }
        });
    }
}