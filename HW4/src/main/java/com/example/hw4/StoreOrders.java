package com.example.hw4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * StoreOrder class
 * Including the order list
 *
 * @author YuchenZhao yz1116, Jinrui Li jl2340
 */
public class StoreOrders {
    private ArrayList<Order> orders = new ArrayList<Order>();

    /**
     * To set order to the order list
     * @param order an order need to add
     */
    public void setOrders(Order order){
        this.orders.add(order);
    }

    /**
     * Remove an order form the order list
     * @param i the index of the order in the order list
     */
    public void removeOrder(int i){
        this.orders.remove(this.orders.get(i));
    }

    /**
     * Get the order list
     * @return the order list
     */
    public ArrayList<Order> getOrders(){
        return this.orders;
    }

    /**
     * Find the index of this order
     * @param phoneNumber the phone number of this order
     * @return the index of this order
     */
    public int find(String phoneNumber){
        for(int i = 0; i < this.orders.size(); i++){
            if(Objects.equals(phoneNumber, this.orders.get(i).getPhoneNumber())){
                return i;
            }
        }
        return -1;
    }

    /**
     * To create the txt file use the phone number
     * Write an order's details into this file.
     *
     * @param phone a phone number of this order
     * @param detail the details of this order
     */
    public void export(String phone, String detail){
        try {
            File export = new File(phone + ".txt");
            if (export.createNewFile()) {
                FileWriter writer = new FileWriter(export.getName());
                writer.write(detail);
                writer.close();
            }
        }catch (IOException ignored){

        }
    }
}
