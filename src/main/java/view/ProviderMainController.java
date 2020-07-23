package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class ProviderMainController {

    @FXML
    private void showLoginProvider(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new URL("file:C:\\Users\\Gina\\IdeaProjects\\Food_Delivery_Application_Hub\\src\\main\\java\\view\\ProviderLogin.fxml"));
            Parent loginParent = loader.load();

            Scene scene = new Scene(loginParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.centerOnScreen();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
