package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SampleModel {

    @JsonProperty("id")
    private int id;

    @JsonProperty("product")
    private String product;

    @JsonProperty("price")
    private int price;

    @JsonProperty("quantity")
    private int quantity;

    public SampleModel(int id, String product, int price, int quantity) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getproduct() {
        return product;
    }
    public void setproduct(String product) {
        this.product = product;
    }
    public int getprice() {
        return price;
    }
    public void setprice(int price) {
        this.price = price;
    }
   public int getquantity() {
        return quantity;
    }
    public void setquantity(int quantity) {
        this.quantity = quantity;
    }
}
