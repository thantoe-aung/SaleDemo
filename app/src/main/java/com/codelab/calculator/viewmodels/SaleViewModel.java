package com.codelab.calculator.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.codelab.calculator.models.SaleModel;
import com.codelab.calculator.repository.SaleRepository;

import java.util.List;

public class SaleViewModel extends AndroidViewModel {

    private SaleRepository repository;

    // below line is to create a variable for live
    // data where all the courses are present.
    private LiveData<List<SaleModel>> itemList;


    public SaleViewModel(@NonNull Application application) {
        super(application);
        repository = new SaleRepository(application);
        itemList = repository.getAllItems();
    }

    public void insert(SaleModel model) {
        repository.insert(model);
    }

    // below line is to update data in our repository.
    public void update(SaleModel model) {
        repository.update(model);
    }

    // below line is to delete the data in our repository.
    public void delete(SaleModel model) {
        repository.delete(model);
    }

    // below method is to delete all the courses in our list.
    public void deleteAllItem() {
        repository.deleteAllItems();
    }

    // below method is to get all the courses in our list.
    public LiveData<List<SaleModel>> getItemList() {
        return itemList;
    }

    public int getItemCount(){
        return  itemList.getValue().size();
    }
}
