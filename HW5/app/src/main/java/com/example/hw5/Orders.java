package com.example.hw5;

import java.util.ArrayList;

/**
 * Order class
 * Including the phone number and pizzas
 *
 * @author YuchenZhao yz1116, Jinrui Li jl2340
 */
public class Orders {
    private String phoneNumber = null;
    private final ArrayList<Pizza> pizzas = new ArrayList<Pizza>();

    /**
     * Set phone number for this order
     * @param phoneNumber a phone number for this order
     */
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the phone number for this order
     * @return the phone number for this order
     */
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    /**
     * Set the pizza for this order
     * @param pizza the pizza for this order
     */
    public void setPizzas(Pizza pizza){
        this.pizzas.add(pizza);
    }

    /**
     * Remove a pizza from this order
     * @param i the index of the pizza in pizza list
     */
    public void removePizzas(int i){
        this.pizzas.remove(this.pizzas.get(i));
    }

    /**
     * Get pizza list
     * @return the pizza list for this order
     */
    public ArrayList<Pizza> getPizzas(){
        return this.pizzas;
    }

    /**
     * Get the pizza list details for this order
     *
     * @param i the index or pizza in pizza list
     * @return a string with the detail
     */
    public String getPizzaInfo(int i){
        return "Flavor: " + this.pizzas.get(i).getClass().getSimpleName() + " ||  Size: " + this.pizzas
                .get(i).getSize()+ " ||  Toppings: " + this.pizzas.get(i).toppings.toString()
                + " ||  Price: " + this.pizzas.get(i).price();
    }

}
