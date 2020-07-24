package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Product;
import model.Restaurant;
import repository.*;

import java.net.URL;
import java.util.*;

public class RestaurantProductController implements Initializable {

    static RestaurantDao restaurantDao = new RestaurantHibernate();
    static UserDao userDao = new UserHibernate();
    static ProductDao productDao = new ProductHibernate();

    @FXML private Label userLbl;
    @FXML private Label idRestaurantLbl;
    @FXML private Label idProductLbl;

    @FXML private MenuBar myMenuBar;
    @FXML private MenuItem loginMenuItem;
    @FXML private MenuItem statisticsItem;

    @FXML private ComboBox<String> restaurantCmb;
    @FXML private ComboBox<String>productCmb;
    @FXML private TextField restaurantNameTxt;
    @FXML private TextField restaurantPhoneTxt;
    @FXML private TextField restaurantEmailTxt;
    @FXML private TextField productNameTxt;
    @FXML private TextField  descriptionTxt;
    @FXML private TextField  priceTxt;

    @FXML private ObservableList<Product> data = FXCollections.observableArrayList();
    @FXML private TableView<Product> productTable;
    @FXML private TableColumn<Product, String> productNameCol;
    @FXML private TableColumn<Product, String>descriptionCol;
    @FXML private TableColumn<Product, String>priceCol;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fillComboBoxRestaurant();

        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productTable.setEditable(true);
        productTable.setItems(data);
    }

    //Delete product
    @FXML
    private void deleteProduct(){
        productDao.deleteProduct(Long.parseLong(idProductLbl.getText()));
        clearTextFieldRestaurant();
        clearTextFieldsProduct();
    }

    //Update Product
    @FXML
    private void updateProduct(){
        Product product = findProductByNameAndRestaurantId();
        product.setProductName(productNameTxt.getText());
        product.setDescription(descriptionTxt.getText());
        product.setPrice(Double.parseDouble(priceTxt.getText()));

        productDao.updateProduct(product);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Update product");
        alert.setContentText("Product successfully updated!");
        alert.showAndWait();
        clearTextFieldRestaurant();
        clearTextFieldsProduct();
    }

    private Product findProductByNameAndRestaurantId(){
        return productDao.
                findProductByNameAndRestaurant(productCmb.getValue(), Long.parseLong(idRestaurantLbl.getText()));
    }

    //GET Product Info In to Text fields
    @FXML
    private void getInfoProduct(){
        Product product = findProductByNameAndRestaurantId();
        productNameTxt.setText(product.getProductName());
        idProductLbl.setText(product.getIdProduct().toString());
        descriptionTxt.setText(product.getDescription());
        priceTxt.setText(product.getPrice().toString());
    }

    @FXML
    private void saveProduct(){
        Restaurant restaurant = restaurantDao.findRestaurantById(Long.parseLong(idRestaurantLbl.getText()));
        Set<Product> productSet = new HashSet<>();
        for(Product product : productTable.getItems()){
            productSet.add(product);
            restaurant.setProductSet(productSet);
            productDao.createProduct(product);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Save product");
        alert.setContentText("Product successfully saved!");
        alert.showAndWait();
        clearTextFieldRestaurant();
        clearTableProduct();
        fillComboBoxProduct();
    }

    @FXML
    // Restaurant ComboBox Action
    private void restaurantComboBoxAction(){
        getInfoRestaurantByNameInToTextFields();
        fillComboBoxProduct();
    }

    //BIND product with restaurant
    private void fillComboBoxProduct(){
        productCmb.getItems().clear();
        List<String>productNamesList =
                productDao.findAllProductsByIdRestaurant(Long.parseLong(idRestaurantLbl.getText()));
        if(!(productNamesList.isEmpty())){
            ObservableList<String>productNames = FXCollections.observableList(productNamesList);
            productCmb.getItems().addAll(productNames);
        }
    }

    //Retrieve restaurant info
    private void getInfoRestaurantByNameInToTextFields(){

        Restaurant restaurant = restaurantDao.findRestaurantByName(restaurantCmb.getValue());
        restaurantNameTxt.setText(restaurant.getRestaurantName());
        idRestaurantLbl.setText(restaurant.getIdRestaurant().toString());
        restaurantPhoneTxt.setText(restaurant.getRestaurantPhone());
        restaurantEmailTxt.setText(restaurant.getRestaurantEmail());
    }

    //DElete Restaurant
    @FXML
    private void deleteRestaurant(){
        restaurantDao.deleteRestaurant(Long.parseLong(idRestaurantLbl.getText()));
        clearTextFieldRestaurant();
    }

    //Update Restaurant
    @FXML
    private void updateRestaurant(){
        Restaurant restaurant = restaurantDao.findRestaurantById(Long.parseLong(idRestaurantLbl.getText()));
        restaurant.setRestaurantName(restaurantNameTxt.getText());
        restaurant.setRestaurantPhone(restaurantPhoneTxt.getText());
        restaurant.setRestaurantEmail(restaurantEmailTxt.getText());
        restaurant.setUser(userDao.findUserByUserName(userLbl.getText()));
        restaurantDao.updateRestaurant(restaurant);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Update restaurant");
        alert.setContentText("Restaurant successfully updated!");
        alert.showAndWait();
        clearTextFieldRestaurant();
    }

    //SAVE RESTAURANT
    @FXML
    private void saveRestaurant(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Options Save Restaurant");
        alert.setHeaderText("How do you want to save the Restaurant");
        alert.setContentText("Choose your option.");

        ButtonType buttonTypeOne = new ButtonType("Save Restaurant with products");
        ButtonType buttonTypeTwo = new ButtonType("Save Restaurant without products");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo,  buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            saveRestaurantWithProducts();
        } else if (result.get() == buttonTypeTwo) {
            saveRestaurantWithOutProducts();

        } else {
            // ... user chose CANCEL or closed the dialog
            productNameTxt.requestFocus();
        }
    }

    //SAVE RESTAURANT WITHOUT PRODUCTS
    private void saveRestaurantWithOutProducts(){
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(restaurantNameTxt.getText());
        restaurant.setRestaurantPhone(restaurantPhoneTxt.getText());
        restaurant.setRestaurantEmail(restaurantEmailTxt.getText());
        restaurant.setUser(userDao.findUserByUserName(userLbl.getText()));

        restaurantDao.createRestaurant(restaurant);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Save restaurant without products");
        alert.setContentText("Restaurant without products successfully saved!");
        alert.showAndWait();
        clearTextFieldRestaurant();
        restaurantCmb.getItems().clear();
        fillComboBoxRestaurant();
    }

    //SAVING RESTAURANT WITH PRODUCTS
     private void saveRestaurantWithProducts(){
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(restaurantNameTxt.getText());
        restaurant.setRestaurantPhone(restaurantPhoneTxt.getText());
        restaurant.setRestaurantEmail(restaurantEmailTxt.getText());
        restaurant.setUser(userDao.findUserByUserName(userLbl.getText()));
        Set<Product> productSet = new HashSet<>();
        for(Product product : productTable.getItems()){
            product.setRestaurant(restaurant);
            product.setUser(userDao.findUserByUserName(userLbl.getText()));
            productSet.add(product);
            restaurant.setProductSet(productSet);
        }
        restaurantDao.createRestaurant(restaurant);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Restaurant with products successfully saved!");
        alert.setContentText("Restaurant with product successfully saved!");
        alert.showAndWait();
        clearTableProduct();
        clearTextFieldRestaurant();
        restaurantCmb.getItems().clear();
        fillComboBoxRestaurant();
    }

    //CREATE PRODUCT FOR TABLE
    @FXML
    private void createProductForTable(){
        Product product = new Product();
        product.setProductName(productNameTxt.getText());
        product.setDescription(descriptionTxt.getText());
        product.setPrice(Double.parseDouble(priceTxt.getText()));
        product.setRestaurant(restaurantDao.findRestaurantByName(restaurantNameTxt.getText()));
        data.add(product);
        productTable.setItems(data);
        clearTextFieldsProduct();

    }

    @FXML
    private void handleRestaurantName(KeyEvent ke){
        if(ke.getCode().equals(KeyCode.ENTER)){
            restaurantPhoneTxt.requestFocus();
        }
    }

    @FXML
    private void handlePhone(KeyEvent ke){
        if (ke.getCode().equals(KeyCode.ENTER)) {
            restaurantEmailTxt.requestFocus();
        }
    }

    @FXML
    private void handleEmail(KeyEvent ke){
        if(ke.getCode().equals(KeyCode.ENTER)){
            productNameTxt.requestFocus();
        }
    }

    @FXML
    private void handleProductName(KeyEvent ke){
        if (ke.getCode().equals(KeyCode.ENTER)) {
            descriptionTxt.requestFocus();
        }
    }

    @FXML
    private void handleDescription(KeyEvent ke){
        if (ke.getCode().equals(KeyCode.ENTER)) {
            priceTxt.requestFocus();
        }
    }

    @FXML
    private void handlePrice(KeyEvent ke){
        if (ke.getCode().equals(KeyCode.ENTER)) {
            createProductForTable();
            productNameTxt.requestFocus();
        }
    }


    //CLEAR TEXT FIELDS PRODUCT
    private void clearTextFieldsProduct(){
        productNameTxt.setText("");
        idProductLbl.setText("");
        descriptionTxt.setText("");
        priceTxt.setText("");
    }

    //CLEAR TEXT FIELDS RESTAURANT
    private void clearTextFieldRestaurant(){
        restaurantEmailTxt.setText("");
        restaurantPhoneTxt.setText("");
        restaurantNameTxt.setText("");
    }

    // CLEAR TABLE PRODUCT
    private void clearTableProduct(){
        for(int i = 0; i<productTable.getItems().size(); i++){
            productTable.getItems().clear();
        }
    }

    //BIND  COMBO BOX RESTAURANT WITH DATABASE
    @FXML
    private void fillComboBoxRestaurant(){

        ObservableList<String> names = FXCollections.observableArrayList();

        for(Restaurant restaurant:restaurantDao.readAllRestaurants()){
            names.addAll(restaurant.getRestaurantName());
        }
        restaurantCmb.getItems().addAll(names);
    }

    // FUNCTION TO GET  USER NAME From Home
    public void getUserNameFromLoginForRestaurantProduct(String userName){
        userLbl.setText(userName);
    }


    @FXML
    private void showStatistics(Event event){

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new URL("file:C:\\Users\\Gina\\IdeaProjects\\" +
                    "Food_Delivery_Application_Hub\\src\\main\\java\\view\\Statistics.fxml"));
            Parent loginParent = loader.load();

            StatisticsController statisticsController = loader.getController();
            statisticsController.getUserNameFromRestaurantProductForStattisrics(userLbl.getText());

            Scene scene = new Scene(loginParent);
            Stage window = (Stage) myMenuBar.getScene().getWindow();

            window.setScene(scene);
            window.centerOnScreen();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    private void showLogin(Event event){

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new URL("file:C:\\Users\\Gina\\IdeaProjects\\" +
                    "Food_Delivery_Application_Hub\\src\\main\\java\\view\\ProviderLogin.fxml"));
            Parent loginParent = loader.load();

            Scene scene = new Scene(loginParent);
            Stage window = (Stage) myMenuBar.getScene().getWindow();

            window.setScene(scene);
            window.centerOnScreen();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}


