package com.example.hw4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * storeViewController class
 * Handle the event and control the interface
 *
 * @author YuchenZhao yz1116, Jinrui Li jl2340
 */
public class storeViewController {

    @FXML
    private Button cancelOrder;

    @FXML
    private ComboBox<String> phonePicker;

    @FXML
    private ListView<String> showPizza;

    @FXML
    private TextField showTotal;

    private mainViewController controller;

    /**
     * To set the main controller to this class
     * @param mainViewController a controller
     */
    public void setMainController(mainViewController mainViewController) {
        this.controller = mainViewController;
        setPhonePicker();
    }

    /**
     * To set the phone number from the store orders to the combo box
     */
    private void setPhonePicker(){
        for (int i = 0; i < this.controller.tempOrder.getOrders().size(); i++){
            this.phonePicker.getItems().add(this.controller.tempOrder.getOrders().get(i).getPhoneNumber());
        }
    }

    /**
     * Cancel the order from the store orders
     */
    @FXML
    private void cancelOrder(ActionEvent event) {
        try {
            String c = this.phonePicker.getSelectionModel().getSelectedItem();
            this.controller.tempOrder.removeOrder(this.controller.tempOrder.find(c));
            Stage stage = (Stage) cancelOrder.getScene().getWindow();
            stage.close();
        }catch (IndexOutOfBoundsException ex){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("ERROR");
            errorAlert.setContentText("You have to select a phone number!");
            errorAlert.showAndWait();
        }
    }

    /**
     * An event when click the export button
     * It will get the phone number and details
     * Passing to export method
     */
    @FXML
    private void exportOrder(ActionEvent event) {
        if(this.phonePicker.getSelectionModel().getSelectedItem() != null) {
            String phone = this.phonePicker.getSelectionModel().getSelectedItem();
            String detail = "Phone number: " + phone + "\n";
            for(int i = 0; i < showPizza.getItems().size(); i++){
                detail += showPizza.getItems().get(i) + "\n";
            }
            detail += "Order Price: $ " + showTotal.getText() + "\n";
            this.controller.tempOrder.export(phone,detail);
        }else{
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("ERROR");
            errorAlert.setContentText("You have to select a phone number!");
            errorAlert.showAndWait();
        }
    }

    /**
     * An event when user pick phone number from the combobox
     * get the phone number from the combobox
     */
    @FXML
    private void selectPhone(ActionEvent event) {
        String c = this.phonePicker.getSelectionModel().getSelectedItem();
        setShowPizza(this.controller.tempOrder.find(c));
        this.showTotal.setText(calculateTotal());
    }

    /**
     * To set the order details to the TextField
     * @param i the index of this order in the store orders
     */
    private void setShowPizza(int i){
        ObservableList<String> pizzas = FXCollections.observableArrayList();
        for(int j = 0; j < this.controller.tempOrder.getOrders().get(i).getPizzas().size(); j++){
            pizzas.add(this.controller.tempOrder.getOrders().get(i).getPizzas(j));
        }
        this.showPizza.setItems(pizzas);
    }

    /**
     * Calculate the total price
     * @return the total price
     */
    public String calculateTotal(){
        double totalPrice = 0;
        int j = this.controller.tempOrder.find(this.phonePicker.getSelectionModel().getSelectedItem());
        for(int i = 0; i < this.controller.tempOrder.getOrders().get(j).getPizzas().size(); i++){
            totalPrice += this.controller.tempOrder.getOrders().get(j).getPizzas().get(i).price() +
                    this.controller.tempOrder.getOrders().get(j).getPizzas().get(i).price() *
                            this.controller.tempOrder.getOrders().get(j).getPizzas().get(i).getTaxRate();
        }
        return String.format("%.2f", totalPrice);
    }

}