package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class ProviderMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:C:\\Users\\Gina\\IdeaProjects\\Food_Delivery_Application_Hub\\src\\main\\java\\view\\ProviderMain.fxml"));
        Pane pane = loader.load();
        primaryStage.setScene(new Scene(pane));
        primaryStage.setTitle("Restaurant food delivery");
        primaryStage.show();
    }
}
