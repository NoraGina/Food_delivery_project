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
import javafx.stage.Stage;
import model.Product;
import repository.*;

import java.net.URL;
import java.util.ResourceBundle;

public class RestaurantProductController implements Initializable {

    static RestaurantDao restaurantDao = new RestaurantHibernate();
    static UserDao userDao = new UserHibernate();
    static ProductDao productDao = new ProductHibernate();

    @FXML private Label userLbl;
    @FXML private Label idRestaurantLbl;
    @FXML private Label idProductLbl;

    @FXML private ComboBox<String> restaurantCmb;
    @FXML private ComboBox<String>productCmb;
    @FXML private TextField restaurantNameTxt;
    @FXML private TextField restaurantPhoneTxt;
    @FXML private TextField restaurantEmailTxt;
    @FXML private TextField productNameTxt, priceTxt,descriptionTxt;
    @FXML private ObservableList<Product> data = FXCollections.observableArrayList();
    @FXML private TableView<Product> productTable;

    @FXML private TableColumn<Product, String> productNameCol;
    @FXML private TableColumn<Product, String>descriptionCol;
    @FXML private TableColumn<Product, String>priceCol;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // FUNCTION TO GET  USER NAME From Home
    public void getUserNameFromLoginForRestaurantProduct(String userName){
        userLbl.setText(userName);
    }

    @FXML
    private void showRestaurantProduct(Event event){

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new URL("file:C:\\Users\\Gina\\IdeaProjects\\" +
                    "Food_Delivery_Application_Hub\\src\\main\\java\\view\\RestaurantProduct.fxml"));
            Parent loginParent = loader.load();

            //RestaurantProductController restaurantProductController = loader.getController();
            //restaurantProductController.getUserNameFromLogin(userLbl.getText());

            Scene scene = new Scene(loginParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();


            window.setScene(scene);
            window.centerOnScreen();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}


