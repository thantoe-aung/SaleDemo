package com.codelab.calculator.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.codelab.calculator.R;
import com.codelab.calculator.models.Post;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostItemViewHolder extends BaseViewHolder<Post>{

    @BindView(R.id.txtTitle)
    TextView txtTitle;

    @BindView(R.id.txtBody)
    TextView txtBody;

    public PostItemViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bindData(Post mData) {
        mData=mData;
        txtTitle.setText(mData.getTitle());
        txtBody.setText(mData.getBody());
    }
}
