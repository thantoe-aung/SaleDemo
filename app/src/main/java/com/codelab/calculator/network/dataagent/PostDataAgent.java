package com.codelab.calculator.network.dataagent;

import com.codelab.calculator.models.Post;

import java.util.List;

public interface PostDataAgent {

    void getPostsFromNetwork(GetPostsNetworkDelegate delegate);

    interface GetPostsNetworkDelegate{
        void onSuccess(List<Post> postList);
        void onFailure(String errorMessage);
    }
}
