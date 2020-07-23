package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Department;
import model.User;
import repository.UserDao;
import repository.UserHibernate;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML private Label userIdLbl;
    @FXML private TextField firstNameTxt;
    @FXML private TextField lastNameTxt;
    @FXML private TextField userNameTxt;
    @FXML private PasswordField passwordField;
    @FXML private TextField phoneTxt;
    @FXML private TextField emailTxt;
    @FXML private TextField departmentTxt;
    @FXML private ComboBox<Department> departmentCmb;

    static UserDao userDao = new UserHibernate();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        departmentCmb.getItems().addAll(Department.EMPLOYEE, Department.ADMINISTRATOR);

    }

    //Clear text fields
    @FXML
    private void clearTextFields(){
        userNameTxt.setText("");
        firstNameTxt.setText("");
        lastNameTxt.setText("");
        passwordField.setText("");
        phoneTxt.setText("");
        emailTxt.setText("");
        userIdLbl.setText("");
        departmentTxt.setText("");
        //departmentCmb.getSelectionModel().clearSelection();
    }

    //Delete user
    @FXML
    private void deleteUser(){
        userDao.deleteUser(Long.parseLong(userIdLbl.getText()));
        clearTextFields();
    }

    //Update user
    @FXML
    private void updateUser(){
        User user = userDao.findUserById(Long.parseLong(userIdLbl.getText()));
        user.setFirstName(firstNameTxt.getText());
        user.setLastName(lastNameTxt.getText());
        user.setUserName(userNameTxt.getText());
        user.setDepartment(departmentCmb.getValue());
        user.setPassword(passwordField.getText());
        user.setPhone(phoneTxt.getText());
        user.setEmail(emailTxt.getText());

        userDao.updateUser(user);
        clearTextFields();
    }

    //Retrieve value by first and last name
    @FXML
    private void searchUserInfoByFirstAndLastName(){
        User user = userDao.findUserByFirstNameAndLastName(firstNameTxt.getText(), lastNameTxt.getText());
        if(user != null){
            userIdLbl.setText(user.getId().toString());
            userNameTxt.setText(user.getUserName());
            passwordField.setText(user.getPassword());
            phoneTxt.setText(user.getPhone());
            emailTxt.setText(user.getEmail());
            departmentTxt.setText(user.getDepartment().toString());
            departmentCmb.setValue(user.getDepartment());
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("There is no user by this name!");
            alert.showAndWait();
        }

    }


    //Save user
    @FXML
    private void saveUser(){
        if(validate()){
            if(userDao.isUserNameExist(userNameTxt.getText()) == 0){
                User user = new User();
                user.setFirstName(firstNameTxt.getText());
                user.setLastName(lastNameTxt.getText());
                user.setUserName(userNameTxt.getText());
                user.setDepartment(departmentCmb.getValue());
                user.setPassword(passwordField.getText());
                user.setPhone(phoneTxt.getText());
                user.setEmail(emailTxt.getText());
                userDao.createUser(user);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Account successfully saved!");
                alert.showAndWait();
                clearTextFields();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Account already exist! Chose another user name!");
                alert.showAndWait();
                userNameTxt.requestFocus();
            }
        }
    }


    //Validate user completed form
    public boolean validate() {
        StringBuilder errors = new StringBuilder();
        if (firstNameTxt.getText().trim().isEmpty()) {
            errors.append("- Please enter first name.\n");
            firstNameTxt.requestFocus();
        }
        if (lastNameTxt.getText().trim().isEmpty()) {
            errors.append("- Please enter last name.\n");
            lastNameTxt.requestFocus();
        }

        if (userNameTxt.getText().trim().isEmpty()) {
            errors.append("- Please enter user name.\n");
            userNameTxt.requestFocus();
        }
        if (passwordField.getText().trim().isEmpty()) {
            errors.append("- Please enter a password.\n");
            passwordField.requestFocus();
        }
        if (departmentTxt.getText().trim().isEmpty()) {
            errors.append("- Please enter department.\n");
            departmentCmb.requestFocus();
        }
        if (phoneTxt.getText().trim().isEmpty()) {
            errors.append("- Please enter your phone.");
            phoneTxt.requestFocus();
        }

        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Required Fields Empty");
            alert.setContentText(errors.toString());

            alert.showAndWait();
            return false;
        }

        return true;
    }

    @FXML
    private void handleFirstName(KeyEvent ke){
        if (ke.getCode().equals(KeyCode.ENTER)) {
            lastNameTxt.requestFocus();

        }
    }

    @FXML
    private void handleLastName(KeyEvent ke){
        User user = userDao.findUserByFirstNameAndLastName(firstNameTxt.getText(), lastNameTxt.getText());
        if (ke.getCode().equals(KeyCode.ENTER)) {
            if(user != null){
                userIdLbl.setText(user.getId().toString());
                userNameTxt.setText(user.getUserName());
                passwordField.setText(user.getPassword());
                phoneTxt.setText(user.getPhone());
                emailTxt.setText(user.getEmail());
                departmentTxt.setText(user.getDepartment().toString());
                departmentCmb.setValue(user.getDepartment());
            }
            userNameTxt.requestFocus();
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
                phoneTxt.requestFocus();
        }
    }

    @FXML
    private void handlePhone(KeyEvent ke){
        if (ke.getCode().equals(KeyCode.ENTER)) {
            emailTxt.requestFocus();
        }
    }

    @FXML
    private void handleEmail(KeyEvent ke){
        if (ke.getCode().equals(KeyCode.ENTER)) {
            departmentCmb.requestFocus();
        }
    }

    @FXML
    private void getItemsInTextField(){
        departmentTxt.setText(departmentCmb.getValue().toString());
    }
}
