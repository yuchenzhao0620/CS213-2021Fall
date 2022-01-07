package com.example.hw4;

/**
 * Deluxe class
 * A subclass for Pizza class
 * Including the deluxe flavor of pizza
 *
 * @author YuchenZhao yz1116, Jinrui Li jl2340
 */
public class Deluxe extends Pizza {

    /**
     * To calculate the price of pizza
     * @return the price of this pizza
     */
    @Override
    public double price() {
        double amount = 0;
        if(this.size.equals(Size.Small)){
            amount = 12.99;
        }else if(this.size.equals(Size.Medium)){
            amount = 14.99;
        }else if(this.size.equals(Size.Large)){
            amount = 16.99;
        }
        if(this.toppings.size() > 5){
            amount += (this.toppings.size() - 5) * priceTopping;
        }
        return amount;
    }


}
