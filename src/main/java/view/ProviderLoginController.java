package view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Department;
import repository.UserDao;
import repository.UserHibernate;

import java.net.URL;
import java.util.ResourceBundle;

public class ProviderLoginController implements Initializable {

    @FXML private TextField userNameTxt;

    @FXML private PasswordField passwordField;

    @FXML private TextField departmentTxt;

    @FXML
    private ComboBox<Department> departmentCmb;

    @FXML private Button signUpBtn;


    static UserDao userDao = new UserHibernate();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        departmentCmb.getItems().addAll(Department.ADMINISTRATOR, Department.EMPLOYEE);
    }

    //Disable UPDATE AND SIGN UP BUTTONS FOR ADMINISTRATOR
    @FXML
    private void disableSignUpButton(){
        if(userDao.isAdministratorExist(userNameTxt.getText(), passwordField.getText(), departmentCmb.getValue())==1){
            signUpBtn.setDisable(false);
        }

        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Allowed for administrator only !");
            alert.showAndWait();
        }
    }



    @FXML
    private void handleUserName(KeyEvent ke){
        if (ke.getCode().equals(KeyCode.ENTER)) {
            passwordField.requestFocus();

        }
    }


    @FXML
    private void handlePassword(KeyEvent ke){
        if (ke.getCode().equals(KeyCode.ENTER)) {
            departmentCmb.requestFocus();

        }
    }

    @FXML
    private void departmentComboBoxAction(){
        getItemsInTextField();
        if(departmentCmb.getValue().equals(Department.ADMINISTRATOR )){
            disableSignUpButton();
        }
    }

    @FXML
    private void getItemsInTextField(){
        departmentTxt.setText(String.valueOf(departmentCmb.getValue()));
    }

    @FXML
    private void showProviderHome(Event event){
       if(userDao.isUserExist(userNameTxt.getText(), passwordField.getText())==1){
             try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(new URL("file:C:\\Users\\Gina\\IdeaProjects\\" +
                        "Food_Delivery_Application_Hub\\src\\main\\java\\view\\ProviderHome.fxml"));
                Parent loginParent = loader.load();

                ProviderHomeController providerHomeController = loader.getController();
                providerHomeController.getUserNameFromLogin(userNameTxt.getText());

                Scene scene = new Scene(loginParent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();


                window.setScene(scene);
                window.centerOnScreen();
            }catch(Exception e){
                e.printStackTrace();
            }

       }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Wrong user name or password!");
            alert.showAndWait();
    }
    }

    @FXML
    private void showSignUp(Event event){

            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(new URL("file:C:\\Users\\Gina\\IdeaProjects\\" +
                        "Food_Delivery_Application_Hub\\src\\main\\java\\view\\SignUp.fxml"));
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
