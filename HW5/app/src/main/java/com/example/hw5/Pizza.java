package com.example.hw5;

import java.util.ArrayList;

/**
 * Pizza class
 * Including the pizza size and toppings
 *
 * @author YuchenZhao yz1116, Jinrui Li jl2340
 */
public abstract class Pizza {
    protected ArrayList<String> toppings = new ArrayList<String>();
    protected Size size;
    static final double priceTopping = 1.49;
    static final double taxRate = 0.006625;
    public abstract double price();

    /**
     * Add topping to the topping list
     * @param topping a topping need to added
     */
    public void addTopping(String topping){
        this.toppings.add(topping);
    }

    /**
     * Remove topping from the topping list
     * @param topping a topping need to remove
     */
    public void removeTopping(String topping){
        this.toppings.remove(topping);
    }

    /**
     * Set the size of this pizza
     * @param size the size of this pizza
     */
    public void setSize(Size size){
        this.size = size;
    }

    /**
     * Get the size of this pizza
     * @return the size of this pizza
     */
    public Size getSize(){
        return this.size;
    }

    /**
     * Get the TaxRate
     * @return the tax rate
     */
    public double getTaxRate(){
        return taxRate;
    }

}
