package view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ProviderHomeController implements Initializable {
    @FXML
    private Label userNameLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // FUNCTION TO GET  USER NAME From Login
    public void getUserNameFromLogin(String userName){
        userNameLbl.setText(userName);
    }


    @FXML
    private void showRestaurantProduct(Event event){

            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(new URL("file:C:\\Users\\Gina\\IdeaProjects\\" +
                        "Food_Delivery_Application_Hub\\src\\main\\java\\view\\RestaurantProduct.fxml"));
                Parent loginParent = loader.load();

                RestaurantProductController restaurantProductController = loader.getController();
                restaurantProductController.getUserNameFromLoginForRestaurantProduct(userNameLbl.getText());

                Scene scene = new Scene(loginParent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();


                window.setScene(scene);
                window.centerOnScreen();
            }catch(Exception e){
                e.printStackTrace();
            }

    }

    @FXML
    private void showStatistics(Event event){

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new URL("file:C:\\Users\\Gina\\IdeaProjects\\" +
                    "Food_Delivery_Application_Hub\\src\\main\\java\\view\\Statistics.fxml"));
            Parent loginParent = loader.load();

            StatisticsController statisticController = loader.getController();
            statisticController.getUserNameFromLoginForStatistics(userNameLbl.getText());

            Scene scene = new Scene(loginParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();


            window.setScene(scene);
            window.centerOnScreen();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
