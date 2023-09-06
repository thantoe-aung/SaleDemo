package com.codelab.calculator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codelab.calculator.R;
import com.codelab.calculator.callbacks.CodeClickCallback;
import com.codelab.calculator.models.ItemModel;
import com.codelab.calculator.models.SaleModel;

import java.util.List;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder> {

    public Context context;
    private List<ItemModel> data;
    private CodeClickCallback callback;

    public ItemRecyclerAdapter(Context context,List<ItemModel> data,CodeClickCallback callback){
        this.context=context;
        this.data=data;
        this.callback=callback;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv, parent, false);
        return new ItemViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemModel model=data.get(position);
        holder.btnCode.setText(model.getDescription());
        holder.btnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.ItemClick(model);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        Button btnCode;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            btnCode=itemView.findViewById(R.id.btnCode);
        }
    }
}
