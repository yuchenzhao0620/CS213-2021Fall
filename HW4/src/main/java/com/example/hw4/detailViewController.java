package com.example.hw4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * DetailViewController class
 * Handle the event and control the interface
 *
 * @author YuchenZhao yz1116, Jinrui Li jl2340
 */
public class detailViewController {

    @FXML
    private TextField detailShowPhoneNumber;

    @FXML
    private Button placeOrder;

    @FXML
    private ListView<String> showDetails = new ListView<>();

    @FXML
    private TextField showSales;

    @FXML
    private TextField showSubTotal;

    @FXML
    private TextField showTotal;

    private mainViewController controller;

    /**
     * To set the main controller to this class
     * @param mainViewController a controller
     */
    public void setMainController(mainViewController mainViewController) {
        this.controller = mainViewController;
        this.detailShowPhoneNumber.setText(this.controller.pizzas.getPhoneNumber());
        setShowDetails();
        calculateSales();
    }

    /**
     * To calculate the sales and shows on the interface
     */
    private void calculateSales(){
        double subTotal = 0;
        double subSales = 0;
        for(int i = 0; i < this.controller.pizzas.getPizzas().size(); i++){
            subTotal += this.controller.pizzas.getPizzas().get(i).price();
            subSales += this.controller.pizzas.getPizzas().get(i).price() *
                    this.controller.pizzas.getPizzas().get(i).getTaxRate();
        }
        this.showSubTotal.setText(String.format("%.2f",subTotal));
        this.showSales.setText(String.format("%.2f",subSales));
        this.showTotal.setText(String.format("%.2f",subTotal + subSales));
    }

    /**
     * To set the details shows on the interface
     */
    private void setShowDetails(){
        ObservableList<String> details = FXCollections.observableArrayList();
        for(int i = 0; i < this.controller.pizzas.getPizzas().size(); i++){
            details.add(this.controller.getDetails(i));
        }
        this.showDetails.setItems(details);
    }

    /**
     * Execute an order is added.
     */
    @FXML
    private void placeOrder(ActionEvent event) {
        if(this.detailShowPhoneNumber.getText() != null && this.showDetails.getItems().size() != 0) {
            if (this.controller.tempOrder.getOrders().size() == 0) {
                this.controller.tempOrder.setOrders(this.controller.pizzas);
            } else {
                for (int i = 0; i < this.controller.tempOrder.getOrders().size(); i++) {
                    if (!Objects.equals(this.controller.tempOrder.getOrders().get(i).getPhoneNumber(), this.controller.pizzas.getPhoneNumber())) {
                        this.controller.tempOrder.setOrders(this.controller.pizzas);
                    }
                }
            }
            Stage stage = (Stage) placeOrder.getScene().getWindow();
            stage.close();
        }else{
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("ERROR");
            errorAlert.setContentText("The number of Pizzas can not be zero!");
            errorAlert.showAndWait();
            Stage stage = (Stage) placeOrder.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Execute remove a pizza from the order.
     */
    @FXML
    private void removePizza(ActionEvent event) {
        try {
            this.controller.pizzas.removePizzas(this.showDetails.getSelectionModel().getSelectedIndex());
            this.showDetails.getItems().remove(this.showDetails.getSelectionModel().getSelectedItem());
            calculateSales();
        }catch (IndexOutOfBoundsException ex){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("ERROR");
            errorAlert.setContentText("You have to select a pizza!");
            errorAlert.showAndWait();
        }
    }

}
