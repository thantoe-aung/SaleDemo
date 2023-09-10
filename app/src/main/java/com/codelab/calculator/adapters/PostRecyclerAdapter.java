package com.codelab.calculator.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.codelab.calculator.R;
import com.codelab.calculator.models.Post;
import com.codelab.calculator.viewholders.PostItemViewHolder;

public class PostRecyclerAdapter extends BaseRecyclerAdapter<PostItemViewHolder, Post>{

    @NonNull
    @Override
    public PostItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item_layout,parent,false);
        return new PostItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostItemViewHolder holder, int position) {

    }
}
