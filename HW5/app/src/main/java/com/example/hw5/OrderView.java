package com.example.hw5;

import android.content.Context;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

public class OrderView extends AppCompatActivity {

    private TextView show_phone;
    private ListView showPizza;
    private Button place_order;
    private Button return_order;
    private TextView sub_total;
    private TextView tax;
    private TextView current_total;
    private final ArrayList<String> flavor = new ArrayList<>();
    private ArrayAdapter<String> p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("View your Order");
        setContentView(R.layout.orderview);
        this.show_phone = findViewById(R.id.show_phone);
        this.showPizza = findViewById(R.id.showPizza);
        this.place_order = findViewById(R.id.place_order);
        this.return_order = findViewById(R.id.return_order);
        this.sub_total = findViewById(R.id.sub_total);
        this.tax = findViewById(R.id.tax);
        this.current_total = findViewById(R.id.current_total);
        pizzaDetails();
        setInfo();
        setPrice();
        showPizza.setOnItemClickListener((parent, view, position, id) -> alertDialog(position));
    }

    private void setInfo(){
        show_phone.setText(DataHandle.getOrders().getPhoneNumber());
        this.p = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, this.flavor);
        showPizza.setAdapter(p);
    }

    private void pizzaDetails(){
        for(int i = 0; i < DataHandle.getOrders().getPizzas().size(); i++){
            this.flavor.add(DataHandle.getOrders().getPizzas().get(i).getClass().getSimpleName() + " Pizza  ||  "
                    + DataHandle.getOrders().getPizzas().get(i).toppings.size() + " Toppings  ||   $ " +
                    DataHandle.getOrders().getPizzas().get(i).price());
        }
    }

    private void setPrice(){
        double sub_t = 0;
        double tax_amount = 0;
        for(int i = 0; i < DataHandle.getOrders().getPizzas().size(); i++){
            sub_t += DataHandle.getOrders().getPizzas().get(i).price();
            tax_amount += DataHandle.getOrders().getPizzas().get(i).price() *
                    DataHandle.getOrders().getPizzas().get(i).getTaxRate();
        }
        this.sub_total.setText("$ " + String.format("%.2f", sub_t));
        this.tax.setText("$ " + String.format("%.2f", tax_amount));
        this.current_total.setText("$ " + String.format("%.2f", (sub_t + tax_amount)));
    }

    public void onPlaceOrder(View v){
        if (v.getId() == this.place_order.getId() && DataHandle.getOrders().getPizzas().size() != 0
                && DataHandle.getTempOrder().find(DataHandle.getOrders().getPhoneNumber()) == -1) {
            DataHandle.getTempOrder().setOrders(DataHandle.getOrders());
            Context context = getApplicationContext();
            CharSequence text = "Added to Store order!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            super.onBackPressed();
        } else if (v.getId() == this.place_order.getId() && DataHandle.getOrders().getPizzas().size() == 0) {
            Context context = getApplicationContext();
            CharSequence text = "Your Order is empty!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else if (v.getId() == this.return_order.getId()) {
            super.onBackPressed();
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Can not be added twice.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    public void alertDialog(int position){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Confirm Delete...");
        alertDialog.setMessage("Are you sure you want delete this?\n" + DataHandle.getOrders().getPizzaInfo(position));
        alertDialog.setPositiveButton("YES", (dialog, which) -> {
            DataHandle.getOrders().getPizzas().remove(position);
            flavor.clear();
            p.notifyDataSetChanged();
            pizzaDetails();
            setInfo();
            setPrice();
        });
        alertDialog.setNegativeButton("NO", (dialog, which) -> dialog.cancel());
        alertDialog.show();
    }
}