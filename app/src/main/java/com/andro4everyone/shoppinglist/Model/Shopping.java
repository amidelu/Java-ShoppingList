package com.andro4everyone.shoppinglist.Model;

public class Shopping {
    private String name;
    private String quantity;
    private String dateItemAdded;
    private int id;

    //Empty Constructor
    public Shopping() {

    }

    //Constructor with signature
    public Shopping(String name, String quantity, String dateItemAdded, int id) {
        this.name = name;
        this.quantity = quantity;
        this.dateItemAdded = dateItemAdded;
        this.id = id;
    }

    //Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDateItemAdded() {
        return dateItemAdded;
    }

    public void setDateItemAdded(String dateItemAdded) {
        this.dateItemAdded = dateItemAdded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
