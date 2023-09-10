package com.codelab.calculator.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.codelab.calculator.R;
import com.codelab.calculator.adapters.ItemRecyclerAdapter;
import com.codelab.calculator.adapters.SaleRecyclerAdapter;
import com.codelab.calculator.callbacks.CodeClickCallback;
import com.codelab.calculator.models.ItemModel;
import com.codelab.calculator.models.SaleModel;
import com.codelab.calculator.repository.SaleRepository;
import com.codelab.calculator.viewmodels.SaleViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class SaleActivity extends AppCompatActivity implements CodeClickCallback {

    private RecyclerView saleRecyclerView,itemRecyclerView;
    private SaleViewModel viewModel;
    private List<ItemModel> itemModelList=new ArrayList<>();
    private ItemRecyclerAdapter itemRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);

        saleRecyclerView=findViewById(R.id.rcvSaleList);
        itemRecyclerView=findViewById(R.id.rcvItemList);

        viewModel = ViewModelProviders.of(this).get(SaleViewModel.class);

        saleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        saleRecyclerView.setHasFixedSize(true);

        itemRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        itemRecyclerView.setHasFixedSize(true);

        addDummyData();

        final SaleRecyclerAdapter adapter = new SaleRecyclerAdapter();
        saleRecyclerView.setAdapter(adapter);

        viewModel.getItemList().observe(this, new Observer<List<SaleModel>>() {
            @Override
            public void onChanged(List<SaleModel> models) {
                adapter.submitList(models);
            }
        });

        itemRecyclerAdapter=new ItemRecyclerAdapter(this,itemModelList,this);
        itemRecyclerView.setAdapter(itemRecyclerAdapter);

    }

    private void addDummyData(){
        for(int i=1;i<=7;i++){
            ItemModel item=new ItemModel(i,"Code "+i,i*1000);
            itemModelList.add(item);
        }

    }

    @Override
    public void ItemClick(ItemModel item) {
        int sr=viewModel.getItemCount() + 1;
        SaleModel saleItem=new SaleModel(sr,item.getDescription(),1,item.getPrice(),item.getPrice());
        viewModel.insert(saleItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  R.id.action_addItem:
                ShowAddDialog();
                return  true;
            case R.id.action_deleteItem:
                viewModel.deleteAllItem();
                return true;
            case R.id.action_nextItem:
               startActivity(PostActivity.newIntent(getApplicationContext()));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void ShowAddDialog(){

        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.add_item_layout);
        dialog.setTitle("Add Item");
        dialog.setCanceledOnTouchOutside(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.show();

        Button btnAdd=dialog.findViewById(R.id.btnAddItem);
        Button btnCancel=dialog.findViewById(R.id.btnCancel);

        final TextInputEditText descEdt=dialog.findViewById(R.id.edtDesc);
        TextInputEditText priceEdt=dialog.findViewById(R.id.edtPrice);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String des=descEdt.getText().toString();
                Double price=Double.parseDouble(priceEdt.getText().toString());
                ItemModel item=new ItemModel(itemModelList.size()+1,des,price);
                itemModelList.add(item);
                itemRecyclerAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

    }

}