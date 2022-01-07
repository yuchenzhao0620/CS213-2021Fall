package com.example.hw4;

import org.junit.Test;

public class DeluxeTest {

    @Test
    public void price() {
        Deluxe d1 = new Deluxe();
        d1.addTopping(Topping.GreenPepper);
        d1.setSize(Size.Small);
        System.out.println(d1.price());// 1 topping will print out 12.99
        d1.addTopping(Topping.Cheese);
        d1.addTopping(Topping.Beef);
        d1.addTopping(Topping.BlackOlives);
        d1.setSize(Size.Small);
        System.out.println(d1.price());// 4 topping small size will give 12.99
        d1.setSize(Size.Medium);
        System.out.println(d1.price());// 4 topping medium size will give 14.99
        d1.setSize(Size.Large);
        System.out.println(d1.price());// 4 topping large size will give 16.99
        d1.addTopping(Topping.Mushroom);
        System.out.println(d1.price());// 5 topping large size will give 16.99
        d1.addTopping(Topping.Onion);
        System.out.println(String.format("%.2f", d1.price())); // 6 topping large size will give 18.48
    }
}