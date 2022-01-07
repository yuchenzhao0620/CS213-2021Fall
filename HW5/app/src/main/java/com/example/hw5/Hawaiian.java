package com.example.hw5;

/**
 * Hawaiian class
 * A subclass of Pizza class
 * Including the hawaiian flavor of pizza
 *
 * @author YuchenZhao yz1116, Jinrui Li jl2340
 */
public class Hawaiian extends Pizza{

    /**
     * To calculate the price of pizza
     * @return the price of this pizza
     */
    @Override
    public double price() {
        double amount = 0;
        if(this.size.equals(Size.Small)){
            amount = 10.99;
        }else if(this.size.equals(Size.Medium)){
            amount += 12.99;
        }else if(this.size.equals(Size.Large)){
            amount += 14.99;
        }
        if(this.toppings.size() > 2){
            amount += (this.toppings.size() - 2) * priceTopping;
        }
        return amount;
    }
}
