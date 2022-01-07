package com.example.hw5;

/**
 * PizzaMaker class
 * To create an instance of subclasses based on the chosen flavor
 *
 * @author YuchenZhao yz1116, Jinrui Li jl2340
 */
public class PizzaMaker {

    /**
     * To create a pizza object
     *
     * @param flavor the flavor for the pizza
     * @return  an object of pizza
     */
    public static Pizza createPizza(String flavor){
        if(flavor.equals("Deluxe")){
            return new Deluxe();
        }else if(flavor.equals("Hawaiian")){
            return new Hawaiian();
        }else{
            return new Pepperoni();
        }
    }
}
