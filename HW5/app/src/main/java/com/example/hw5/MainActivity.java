package com.example.hw5;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ImageButton orderDeluxe;
    private ImageButton orderHawaiian;
    private ImageButton orderPepperoni;
    private Button currentOrder;
    private Button storeOrder;
    private TextView phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Main Menu");
        setContentView(R.layout.activity_main);
        orderDeluxe = findViewById(R.id.deluxe);
        orderHawaiian = findViewById(R.id.hawaiian);
        orderPepperoni = findViewById(R.id.pepperoni);
        currentOrder = findViewById(R.id.currentOrder);
        storeOrder = findViewById(R.id.storeOrder);
        phoneNumber = findViewById(R.id.editTextPhone);
        DataHandle.newOrders();
    }


    public void onPizza(View v) {
        try {
            long i = Long.parseLong(phoneNumber.getText().toString());
            if (String.valueOf(i).length() == 10) {
                if (!Objects.equals(DataHandle.getOrders().getPhoneNumber(), phoneNumber.getText().toString())) {
                    DataHandle.newOrders();
                    DataHandle.getOrders().setPhoneNumber(phoneNumber.getText().toString());
                }
                if (v.getId() == orderDeluxe.getId()) {
                    Intent intent = new Intent(MainActivity.this, Order.class);
                    intent.putExtra("flavor", "Deluxe");
                    DataHandle.setDrawable(this.orderDeluxe.getDrawable());
                    startActivity(intent);
                } else if (v.getId() == orderHawaiian.getId()) {
                    Intent intent = new Intent(MainActivity.this, Order.class);
                    intent.putExtra("flavor", "Hawaiian");
                    DataHandle.setDrawable(this.orderHawaiian.getDrawable());
                    startActivity(intent);
                } else if (v.getId() == orderPepperoni.getId()) {
                    Intent intent = new Intent(MainActivity.this, Order.class);
                    intent.putExtra("flavor", "Pepperoni");
                    DataHandle.setDrawable(this.orderPepperoni.getDrawable());
                    startActivity(intent);
                }
            } else {
                Context context = getApplicationContext();
                CharSequence text = "Phone number should be 10 digits integers!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }catch (Exception e){
            valid();
        }
    }

    public void onOrder(View v){
        if(v.getId() == currentOrder.getId()){
            Intent intent = new Intent(MainActivity.this, OrderView.class);
            startActivity(intent);
        }
        if(v.getId() == storeOrder.getId()){
            Intent intent = new Intent(MainActivity.this, StoreOrder.class);
            startActivity(intent);
        }
    }

    private void valid(){
        Context context = getApplicationContext();
        CharSequence text = "Not a valid phone number";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}