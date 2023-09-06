package com.codelab.calculator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.codelab.calculator.R;
import com.codelab.calculator.models.SaleModel;

import java.util.List;

public class SaleRecyclerAdapter extends ListAdapter<SaleModel,SaleRecyclerAdapter.SaleViewHolder> {

    public SaleRecyclerAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<SaleModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<SaleModel>() {
        @Override
        public boolean areItemsTheSame(SaleModel oldItem, SaleModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(SaleModel oldItem, SaleModel newItem) {
            // below line is to check the course name, description and course duration.
            return oldItem.getDescription().equals(newItem.getDescription());
        }
    };

    @NonNull
    @Override
    public SaleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sale_rv_item, parent, false);
        return new SaleViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleViewHolder holder, int position) {
        SaleModel model=getItem(position);
        holder.txtSr.setText(String.valueOf(model.getSr()));
        holder.txtName.setText(model.getDescription());
        holder.txtQty.setText(String.valueOf(model.getQty()));
        holder.txtSalePrice.setText(String.valueOf(model.getPrice()));
        holder.txtAmount.setText(String.valueOf(model.getAmount()));
    }




    public class SaleViewHolder extends RecyclerView.ViewHolder{

        TextView txtSr,txtName,txtQty,txtSalePrice,txtAmount;

        public SaleViewHolder(@NonNull View itemView) {
            super(itemView);

             txtSr=itemView.findViewById(R.id.txtSr);
             txtName=itemView.findViewById(R.id.txtName);
             txtQty=itemView.findViewById(R.id.txtQty);
             txtSalePrice=itemView.findViewById(R.id.txtSalePrice);
             txtAmount=itemView.findViewById(R.id.txtAmount);

        }
    }

}
