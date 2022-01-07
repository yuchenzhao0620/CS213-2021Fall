package com.example.hw5;

/**
 * Pepperoni class
 * A subclass of Pizza class
 * Including the pepperoni flavor of pizza
 *
 * @author YuchenZhao yz1116, Jinrui Li jl2340
 */
public class Pepperoni extends Pizza{

    /**
     * To calculate the price of pizza
     * @return the price of this pizza
     */
    @Override
    public double price() {
        double amount = 0;
        if(this.size.equals(Size.Small)){
            amount = 8.99;
        }else if(this.size.equals(Size.Medium)){
            amount += 10.99;
        }else if(this.size.equals(Size.Large)){
            amount += 12.99;
        }
        if(this.toppings.size() > 1){
            amount += (this.toppings.size() - 1) * priceTopping;
        }
        return amount;
    }
}
