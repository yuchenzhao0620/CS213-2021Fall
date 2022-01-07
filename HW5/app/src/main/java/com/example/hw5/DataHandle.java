package com.example.hw5;

import android.graphics.drawable.Drawable;

public class DataHandle {
    private static final StoreOrders tempOrder = new StoreOrders();
    private static Orders order;
    private static Pizza pizza;
    private static Drawable temp_drawable;

    public static StoreOrders getTempOrder(){
        return tempOrder;
    }

    public static void newOrders(){
        order = new Orders();
    }

    public static Orders getOrders(){
        return order;
    }

    public static void setPizza(Pizza p){
        pizza = p;
    }

    public static Pizza getPizza(){
        return pizza;
    }

    public static void setDrawable(Drawable d){
        temp_drawable = d;
    }

    public static Drawable getDrawable(){
        return temp_drawable;
    }

}
