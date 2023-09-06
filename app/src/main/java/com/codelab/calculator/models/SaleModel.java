package com.codelab.calculator.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sale_det")
public class SaleModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int sr;

    private  String description;

    private double qty;

    private double price;

    private  double amount;

    public SaleModel(){}

    public SaleModel(int sr,String des,double qty,double price,double amount){
        this.sr=sr;
        this.description=des;
        this.qty=qty;
        this.price=price;
        this.amount=amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSr() {
        return sr;
    }

    public void setSr(int sr) {
        this.sr = sr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
