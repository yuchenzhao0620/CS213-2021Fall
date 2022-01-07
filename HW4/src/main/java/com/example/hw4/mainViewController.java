package com.example.hw4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * mainViewController class
 * This class will handle and control the interface.
 *
 * @author YuchenZhao yz1116, Jinrui Li jl2340
 */
public class mainViewController {

    @FXML
    private ImageView imageDeluxe;

    @FXML
    private ImageView imageHawaiian;

    @FXML
    private ImageView imagePepperoni;

    private Button item;

    @FXML
    private Button Deluxe;

    @FXML
    private Button Hawaiian;

    @FXML
    private Button Pepperoni;

    @FXML
    private TextField phonenumber;

    protected StoreOrders tempOrder;

    protected Order pizzas;

    /**
     * To start an order
     *
     * @param flavor an action event which is happened
     * @throws IOException throw out the I/O exception
     */
    @FXML
    private void orderView(ActionEvent flavor) throws IOException {
        try {
            if(phonenumber.getText().length() != 10){
                throw new NumberFormatException();
            }
            if(!Objects.equals(this.pizzas.getPhoneNumber(), phonenumber.getText())){
                createOrder();
                this.pizzas.setPhoneNumber(phonenumber.getText());
            }
            this.item = (Button) flavor.getSource();
            FXMLLoader fxmlLoader = new FXMLLoader(customizeViewController.class.getResource("customizeView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Pizza Price");
            stage.setScene(scene);
            customizeViewController customize = fxmlLoader.getController();
            customize.setMainController(this);
            stage.show();
        }catch(NumberFormatException e){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("You have to input a valid phone number!");
            errorAlert.showAndWait();
        }
    }

    /**
     * To start an interface of detail view interface
     *
     * @throws IOException throw out the I/O exception
     */
    @FXML
    private void detailView(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(customizeViewController.class.getResource("detailView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Detail View");
        stage.setScene(scene);
        detailViewController detail = fxmlLoader.getController();
        detail.setMainController(this);
        stage.show();
    }

    /**
     * To start an interface of store view interface
     *
     * @throws IOException throw out the I/O exception
     */
    @FXML
    private void storeView(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(storeViewController.class.getResource("storeView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Store View");
        stage.setScene(scene);
        storeViewController store = fxmlLoader.getController();
        store.setMainController(this);
        stage.show();
    }

    /**
     * To connect the orders class
     */
    public mainViewController(){
        this.tempOrder = new StoreOrders();
        createOrder();
    }

    /**
     * Create an order
     */
    private void createOrder(){
        this.pizzas = new Order();
    }

    /**
     * To get the image from the button
     *
     * @param button the button to show different pizzas.
     * @return the image from the button
     */
    public Image getImage(Button button){
        Image image;
        if(button == Deluxe){
            image = imageDeluxe.getImage();
        }else if(button == Pepperoni){
            image = imagePepperoni.getImage();
        }else{
            image = imageHawaiian.getImage();
        }
        return image;
    }

    /**
     * Get the detail for one pizza
     * @param i the index of the pizza in pizzas
     * @return a string of the detail
     */
    public String getDetails(int i){
        return "Flavor: " + this.pizzas.getPizzas().get(i).getClass().getSimpleName() + " ||  Size: " + this.pizzas.
                getPizzas().get(i).getSize()+ " ||  Toppings: " + this.pizzas.getPizzas().get(i).toppings.toString()
                + " ||  Price: " + this.pizzas.getPizzas().get(i).price();
    }

    /**
     * To get which button is clicked
     * @return a button is clicked
     */
    public Button getButton(){
        return this.item;
    }

    /**
     * TO get deluxe button
     * @return the deluxe button
     */
    public Button getDeluxe(){
        return this.Deluxe;
    }

    /**
     * To get pepperoni button
     * @return the pepperoni button
     */
    public Button getPepperoni(){
        return this.Pepperoni;
    }

    /**
     * To get hawaiian button
     * @return the hawaiian button
     */
    public Button getHawaiian(){
        return this.Hawaiian;
    }

}