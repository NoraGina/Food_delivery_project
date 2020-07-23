package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {

    @FXML
    private Label userNameLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // FUNCTION TO GET  USER NAME From Home
    public void getUserNameFromLoginForStatistics(String userName){
        userNameLbl.setText(userName);
    }
}
