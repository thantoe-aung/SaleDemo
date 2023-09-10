package com.codelab.calculator.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codelab.calculator.viewholders.BaseViewHolder;

import java.util.List;

public abstract class BaseRecyclerAdapter<T extends BaseViewHolder<W>,W> extends RecyclerView.Adapter<T> {

    public List<W> mData;

    @Override
    public void onBindViewHolder(@NonNull T holder, int position, @NonNull List<Object> payloads) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null? 0 : mData.size();
    }

    public void setNewData(List<W> data){
        mData=data;
        notifyDataSetChanged();
    }
}
