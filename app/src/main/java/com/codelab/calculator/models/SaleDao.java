package com.codelab.calculator.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SaleDao {

    @Insert
    void  Insert(SaleModel model);

    @Update
    void  Update(SaleModel model);

    @Delete
    void  Delete(SaleModel model);

    @Query("DELETE FROM sale_det")
    void deleteAllItem();


    @Query("SELECT * FROM sale_det ORDER BY sr ASC")
    LiveData<List<SaleModel>> getAllItems();
}
