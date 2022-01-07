package com.example.hw5;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class StoreOrder extends AppCompatActivity {
    private Spinner spinner;
    private ListView details;
    private TextView total;
    private Button cancel;
    private Button return_store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Store Order");
        setContentView(R.layout.storeview);
        spinner = findViewById(R.id.spinner);
        details = findViewById(R.id.details);
        total = findViewById(R.id.total);
        cancel = findViewById(R.id.cancel);
        return_store = findViewById(R.id.return_order);
        getPhone();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getDetails();
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void getDetails(){
        double current_total = 0;
        int i = DataHandle.getTempOrder().find(spinner.getSelectedItem().toString());
        ArrayList<String> detail = new ArrayList<>();
        for(int j = 0; j < DataHandle.getOrders().getPizzas().size(); j++) {
            detail.add(DataHandle.getTempOrder().getOrders().get(i).getPizzas().get(j).getClass().
                    getSimpleName() + " Pizza  ||  " + DataHandle.getTempOrder().getOrders().get(i).
                    getPizzas().get(j).toppings.size() + " Toppings  ||   $ " +
                    DataHandle.getTempOrder().getOrders().get(i).getPizzas().get(j).price());
            current_total += DataHandle.getTempOrder().getOrders().get(i).getPizzas().get(j).price() +
                    DataHandle.getTempOrder().getOrders().get(i).getPizzas().get(j).price() *
                            DataHandle.getTempOrder().getOrders().get(i).getPizzas().get(j).getTaxRate();
        }
        details.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, detail));
        total.setText("$ " + String.format("%.2f", current_total));
    }

    private void getPhone(){
        ArrayList<String> tempPhoneNumber = new ArrayList<>();
        for(int i = 0; i< DataHandle.getTempOrder().getOrders().size(); i ++){
            tempPhoneNumber.add(DataHandle.getTempOrder().getOrders().get(i).getPhoneNumber());
        }
        spinner.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,
                tempPhoneNumber));
    }

    public void onCancel(View v){
        if(v.getId() == this.cancel.getId()){
            if(spinner.getSelectedItem() != null) {
                alertCancel();
            }else{
                Context context = getApplicationContext();
                CharSequence text = "You have to selected a phone number!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }else{
            super.onBackPressed();
        }
    }

    private void alertCancel(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Confirm Cancel");
        alertDialog.setMessage("Are you sure you want Cancel this order?");
        alertDialog.setPositiveButton("YES", (dialog, which) -> {
            DataHandle.getTempOrder().removeOrder(spinner.getSelectedItemPosition());
            getPhone();
            details.setAdapter(null);
            total.setText(null);
        });
        alertDialog.setNegativeButton("NO", (dialog, which) -> dialog.cancel());
        alertDialog.show();
    }
}