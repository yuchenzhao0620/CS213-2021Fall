package com.example.hw4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * CustomizeViewController class
 * To handle customize of pizza with toppings.
 *
 * @author YuchenZhao yz1116, Jinrui Li jl2340
 */
public class customizeViewController {
    @FXML
    private Button addOrder;

    @FXML
    private ListView<Topping> additional;

    @FXML
    private ImageView picture;

    @FXML
    private ListView<Topping> selected;

    @FXML
    private TextField showPrice;

    @FXML
    private ComboBox<Size> size;

    protected Pizza pizza;

    private mainViewController controller;

    /**
     * To set the main controller to this class
     * @param controller a controller
     */
    public void setMainController(mainViewController controller) {
        this.controller = controller;
        setPicture();
        size.getItems().addAll(Size.Small, Size.Medium, Size.Large);
        size.setValue(Size.Small);
        setListView(this.controller.getButton());
        this.pizza = PizzaMaker.createPizza(this.controller.getButton().getId());
        this.pizza.setSize(size.getSelectionModel().getSelectedItem());
        setToppings();
        setShowPrice();
    }

    /**
     * To show the different flavor of pizza's picture
     */
    private void setPicture(){
        this.picture.setImage(this.controller.getImage(this.controller.getButton()));
    }

    /**
     * To set the information to the listview
     * @param button a button to about the flavor of pizza
     */
    private void setListView(Button button){
        if(button == this.controller.getDeluxe()){
            ObservableList<Topping> addition = FXCollections.observableArrayList(Topping.Chicken, Topping.Beef,
                    Topping.Ham, Topping.Pineapple, Topping.BlackOlives, Topping.Cheese);
            this.additional.setItems(addition);
            ObservableList<Topping> select = FXCollections.observableArrayList(Topping.Sausage, Topping.GreenPepper,
                    Topping.Onion, Topping.Pepperoni, Topping.Mushroom);
            this.selected.setItems(select);
        }else if(button == this.controller.getPepperoni()){
            ObservableList<Topping> addition = FXCollections.observableArrayList(Topping.Chicken, Topping.Beef,
                    Topping.Ham, Topping.Pineapple, Topping.BlackOlives, Topping.Cheese, Topping.Sausage,
                    Topping.GreenPepper, Topping.Onion, Topping.Mushroom);
            this.additional.setItems(addition);
            ObservableList<Topping> select = FXCollections.observableArrayList(Topping.Pepperoni);
            this.selected.setItems(select);
        }else if(button == this.controller.getHawaiian()){
            ObservableList<Topping> addition = FXCollections.observableArrayList(Topping.Chicken, Topping.Beef,
                    Topping.Pineapple, Topping.BlackOlives, Topping.Sausage, Topping.GreenPepper, Topping.Onion,
                    Topping.Pepperoni, Topping.Mushroom);
            this.additional.setItems(addition);
            ObservableList<Topping> select = FXCollections.observableArrayList(Topping.Ham,
                    Topping.Cheese);
            this.selected.setItems(select);
        }
    }

    /**
     * Set topping which are selected to a pizza object.
     */
    private void setToppings(){
        for(int i = 0; i < selected.getItems().size(); i++) {
            this.pizza.addTopping(selected.getItems().get(i));
        }
    }

    /**
     * Set the price to the TextField
     */
    private void setShowPrice(){
        this.showPrice.setText(String.valueOf(this.pizza.price()));
    }

    /**
     * Execute a pizza will add.
     */
    @FXML
    private void addOrderEvent(ActionEvent event) {
        this.controller.pizzas.setPizzas(this.pizza);
        Stage stage = (Stage) addOrder.getScene().getWindow();
        stage.close();
    }

    /**
     * Pick the size of pizza.
     */
    @FXML
    private void selectSize(ActionEvent select){
        this.pizza.setSize(size.getSelectionModel().getSelectedItem());
        setShowPrice();
    }

    /**
     * Execute add topping for this pizza
     */
    @FXML
    private void addToppingEvent(ActionEvent event) {
        if(additional.getSelectionModel().getSelectedItem() != null) {
            if(selected.getItems().size() < 7) {
                selected.getItems().add(additional.getSelectionModel().getSelectedItem());
                this.pizza.addTopping(additional.getSelectionModel().getSelectedItem());
                additional.getItems().remove(additional.getSelectionModel().getSelectedItem());
            }else{
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("ERROR");
                errorAlert.setContentText("The max number of toppings is 7!");
                errorAlert.showAndWait();
            }
        }
        setShowPrice();
    }

    /**
     * Execute remove topping for this pizza
     */
    @FXML
    private void removeToppingEvent(ActionEvent event) {
        if(selected.getSelectionModel().getSelectedItem() != null) {
            if(isValid()) {
                additional.getItems().add(selected.getSelectionModel().getSelectedItem());
                this.pizza.removeTopping(selected.getSelectionModel().getSelectedItem());
                selected.getItems().remove(selected.getSelectionModel().getSelectedItem());
            }
        }
        setShowPrice();

    }

    /**
     * To get the different flavor of topping's number
     * @return the number of topping
     */
    private int getTopping(){
        if(this.controller.getButton() == this.controller.getDeluxe()){
            return 5;
        }else if(this.controller.getButton() == this.controller.getHawaiian()){
            return 2;
        }else{
            return 1;
        }
    }

    /**
     * To determine the number of topping for this pizza
     * @return if is valid or not
     */
    private boolean isValid(){
        int i = getTopping();
        if(selected.getItems().size() > i){
            return true;
        }else if(selected.getItems().size() <= i && selected.getItems().size() > 1){
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
            errorAlert.setHeaderText("ALERT");
            errorAlert.setContentText("The number of toppings is lower than the number of store customized toppings.");
            errorAlert.showAndWait();
            return true;
        }else{
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("ERROR");
            errorAlert.setContentText("The number of toppings can not be zero!");
            errorAlert.showAndWait();
            return false;
        }
    }

}