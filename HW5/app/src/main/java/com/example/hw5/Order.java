package com.example.hw5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;


public class Order extends AppCompatActivity {
    private ImageView pizzaPicture;
    private TextView price;
    private Spinner size;
    private Button ordered;
    private Button returned;
    private CheckBox chicken;
    private CheckBox beef;
    private CheckBox ham;
    private CheckBox pineapple;
    private CheckBox blackOlives;
    private CheckBox cheese;
    private CheckBox sausage;
    private CheckBox greenPepper;
    private CheckBox onion;
    private CheckBox pepOni;
    private final ArrayList<CheckBox> toppings = new ArrayList<CheckBox>();
    private String flavor;
    private int original;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Order your Pizza");
        setContentView(R.layout.order);
        Intent intent = getIntent();
        this.flavor = intent.getStringExtra("flavor");
        setID();
        setInfo(this.flavor);
        DataHandle.setPizza(PizzaMaker.createPizza(this.flavor));
        setToppingArray();
        this.original = isChecked();
        size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DataHandle.getPizza().setSize((Size) size.getSelectedItem());
                setPrice();
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        setPrice();
    }

    /**
     * Connect the ID to the views
     */
    private void setID(){
        this.pizzaPicture = findViewById(R.id.pizza_p);
        this.price = findViewById(R.id.price);
        this.size = findViewById(R.id.size);
        this.ordered = findViewById(R.id.ordered);
        this.returned = findViewById(R.id.returned);
        this.chicken = findViewById(R.id.chicken);
        this.toppings.add(chicken);
        this.beef = findViewById(R.id.beef);
        this.toppings.add(beef);
        this.ham = findViewById(R.id.ham);
        this.toppings.add(ham);
        this.pineapple = findViewById(R.id.pineapple);
        this.toppings.add(pineapple);
        this.blackOlives = findViewById(R.id.blackOlives);
        this.toppings.add(blackOlives);
        this.cheese = findViewById(R.id.cheese);
        this.toppings.add(cheese);
        this.sausage = findViewById(R.id.sausage);
        this.toppings.add(sausage);
        this.greenPepper = findViewById(R.id.greenPepper);
        this.toppings.add(greenPepper);
        this.onion = findViewById(R.id.onion);
        this.toppings.add(onion);
        this.pepOni = findViewById(R.id.pepOni);
        this.toppings.add(pepOni);
    }

    /**
     * Set different information about each flavor of pizza
     * @param flavor The flavor of the pizza
     */
    private void setInfo(String flavor){
        size.setAdapter(new ArrayAdapter<Size>(this, R.layout.support_simple_spinner_dropdown_item,
                Size.values()));
        pizzaPicture.setImageDrawable(DataHandle.getDrawable());
        switch (flavor) {
            case "Deluxe":
                sausage.setChecked(true);
                greenPepper.setChecked(true);
                onion.setChecked(true);
                pepOni.setChecked(true);
                cheese.setChecked(true);
                break;
            case "Hawaiian":
                ham.setChecked(true);
                cheese.setChecked(true);
                break;
            case "Pepperoni":
                pepOni.setChecked(true);
                break;
        }
    }

    /**
     * Set toppings to the pizza object
     */
    private void setToppingArray(){
        for (CheckBox topping : toppings) {
            if (topping.isChecked()) {
                DataHandle.getPizza().addTopping(topping.getText().toString());
            }
        }
        DataHandle.getPizza().setSize((Size) this.size.getSelectedItem());
    }

    /**
     * Set price to the pizza object
     */
    private void setPrice(){
        this.price.setText(String.valueOf(DataHandle.getPizza().price()));
    }

    /**
     * count how many toppings is checked
     * @return number of toppings where checked
     */
    private int isChecked(){
        int count = 0;
        for (CheckBox topping : this.toppings) {
            if (topping.isChecked()) {
                count++;
            }
        }
        return count;
    }

    /**
     * check if the topping number is valid
     * @param count number of toppings where checked
     * @return if is valid or not
     */
    private boolean isValidToppings(int count){
        if(count > 7){
            return false;
        }else return count >= 1;
    }

    /**
     *
     * @param id
     * @param b
     */
    public void setT(int id, boolean b){
        for (CheckBox topping : toppings) {
            if (id == topping.getId()) {
                topping.setChecked(b);
            }
        }
    }

    public CheckBox getT(int id){
        for (CheckBox topping : toppings) {
            if (id == topping.getId()) {
                return topping;
            }
        }
        return null;
    }

    public void onPizzaOrder(View v){
        if(v.getId() == this.ordered.getId()) {
            DataHandle.getOrders().setPizzas(DataHandle.getPizza());
            Context context = getApplicationContext();
            CharSequence text = "Added!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        super.onBackPressed();
    }

    public void onCheckToppings(View c){
        if(isValidToppings(isChecked())){
            if(getT(c.getId()).isChecked()) {
                DataHandle.getPizza().addTopping(getT(c.getId()).getText().toString());
            }else{
                DataHandle.getPizza().removeTopping(getT(c.getId()).getText().toString());
            }
            System.out.println(DataHandle.getPizza().toppings.toString());
            if(isChecked() < this.original){
                Context context = getApplicationContext();
                CharSequence text = "The number of toppings is lower than the store customized! " +
                        "The price cannot be reduced.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }else{
            AlertDialog alertDialog = new AlertDialog.Builder(Order.this).create();
            alertDialog.setTitle("Wrong Topping number");
            if(isChecked() > 7) {
                alertDialog.setMessage("You only can check 7 toppings.");
                setT(c.getId(), false);
            }else if(isChecked() < 1) {
                alertDialog.setMessage("You have to check at least 1 topping.");
                setT(c.getId(), true);
            }
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    (dialog, which) -> dialog.dismiss());
            alertDialog.show();
        }
        setPrice();
    }
}