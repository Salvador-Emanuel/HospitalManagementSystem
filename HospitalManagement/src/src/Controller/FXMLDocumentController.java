/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Data;
import Model.Users;
import daoHospital.Database;
import Model.AlertMessage;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Elga
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private CheckBox login_checkBox;

    @FXML
    private AnchorPane login_form;

    @FXML
    private Button login_loginBtn;

    @FXML
    private PasswordField login_password;

    @FXML
    private Hyperlink login_registerHere;

    @FXML
    private TextField login_showPassword;

    @FXML
    private ComboBox<?> login_user;

    @FXML
    private TextField login_username;

    @FXML
    private CheckBox register_checkBox;

    @FXML
    private TextField register_confirmPassword;
    
    @FXML
    private TextField register_showConfirmPassword;

    @FXML
    private TextField register_email;

    @FXML
    private AnchorPane register_form;

    @FXML
    private Hyperlink register_loginHere;

    @FXML
    private PasswordField register_password;

    @FXML
    private PasswordField register_pasword;

    @FXML
    private TextField register_showPassword;

    @FXML
    private Button register_signUpBtn;

    @FXML
    private TextField register_username;

    private AlertMessage alert = new AlertMessage();

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void loginAcount() {

        if (login_username.getText().isEmpty()
                || login_password.getText().isEmpty()) {
            alert.errorMessage("Nome do usuário ou palavra passe incorrecta");
        } else {

            String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

            connect = Database.connectDB();

            try {

                if (!login_showPassword.isVisible()) {
                    if (!login_showPassword.getText().equals((login_password.getText()))) {
                    }
                    login_showPassword.setText(login_password.getText());
                } else {
                    if (!login_showPassword.getText().equals(login_password.getText())) {
                        login_password.setText(login_showPassword.getText());
                    }
                }

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, login_username.getText());
                prepare.setString(2, login_password.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    
                    Data.admin_username = login_username.getText();
                    Data.admin_id = Integer.parseInt(result.getString("admin_id"));
                    
                    
                    Parent root = FXMLLoader.load(getClass().getResource("/View/AdminMainForm.fxml"));
                    Stage stage = new Stage();
                    
                    stage.setTitle("Sistema de Gestão Hospitalar | Portal do Administrador");
                    stage.setScene(new Scene(root));
                    stage.show();
                    
                    login_loginBtn.getScene().getWindow().hide();
                } else {
                    alert.errorMessage("Usuário ou palavra passe incorrecta");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loginShowPassword() {

        if (login_checkBox.isSelected()) {
            login_showPassword.setText(login_password.getText());
            login_showPassword.setVisible(true);
            login_password.setVisible(false);
        } else {
            login_showPassword.setText(login_password.getText());
            login_showPassword.setVisible(false);
            login_password.setVisible(true);
        }
    }

    public void registerAcount() {

        if (register_email.getText().isEmpty()
                || register_username.getText().isEmpty()
                || register_password.getText().isEmpty()
                || register_confirmPassword.getText().isEmpty()) {
            alert.errorMessage("Por favor preencha todos campos");
        } else {

            String checkUsername = "SELECT * FROM admin WHERE username = '"
                    + register_username.getText() + "'";

            connect = Database.connectDB();

            try {

                if (register_checkBox.isVisible()) {
                    if (!register_password.getText().equals(register_password.getText())) {
                        register_showPassword.setText(register_password.getText());
                    } else {
                        if (!register_showPassword.getText().equals(register_password.getText())) {
                            register_password.setText(register_showPassword.getText());
                        }
                    }
                }

                prepare = connect.prepareStatement(checkUsername);
                result = prepare.executeQuery();

                if (result.next()) {
                    alert.errorMessage(register_username.getText() + " , usuário já cadastrado!");
                } else if (register_password.getText().length() <= 8) {
                    alert.errorMessage("Palavra Passe inválida, deve ter 8 ou mais caracteres!");
                } else {
                    String insertData = "INSERT INTO admin (email, username, password, date) VALUES (?,?,?,?)";

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, register_email.getText());
                    prepare.setString(2, register_username.getText());
                    prepare.setString(3, register_password.getText());
                    prepare.setString(4, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.sucessMessage("Usuário cadastrado com sucesso!");
                    registerClear();

                    login_form.setVisible(true);
                    register_form.setVisible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void registerClear() {
        register_email.clear();
        register_username.clear();
        register_password.clear();
        register_confirmPassword.clear();
        register_showPassword.clear();
    }

    public void registerShowPassword() {

        if (register_checkBox.isSelected()) {
            register_showPassword.setText(register_password.getText());
            register_showPassword.setVisible(true);
            register_password.setVisible(false);

            register_showConfirmPassword.setText(register_confirmPassword.getText());
            register_showConfirmPassword.setVisible(true);
            register_confirmPassword.setVisible(false);
        } else {
            register_showPassword.setText(register_password.getText());
            register_showPassword.setVisible(false);
            register_password.setVisible(true);

            register_showConfirmPassword.setText(register_confirmPassword.getText());
            register_showConfirmPassword.setVisible(false);
            register_confirmPassword.setVisible(true);
        }
    }

    public void userList() {

        List<String> listU = new ArrayList<>();

        for (String data : Users.user) {
            listU.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listU);
        login_user.setItems(listData);

    }

    public void switchPage() {

        if (login_user.getSelectionModel().getSelectedItem() == "Portal do Administrador") {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/View/FXMLDocument.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Sistema de Gestão Hospitalar");

                stage.setMinWidth(400);
                stage.setMaxHeight(670);

                stage.setScene(new Scene(root));
                stage.show();

                login_user.getScene().getWindow().hide();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (login_user.getSelectionModel().getSelectedItem() == "Portal do Médico") {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/View/DoctorPage.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Sistema de Gestão Hospitalar");

                stage.setMinWidth(400);
                stage.setMaxHeight(670);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (login_user.getSelectionModel().getSelectedItem() == "Portal do Paciente") {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/View/PatientPage.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Sistema de Gestão Hospitalar");

                stage.setMinWidth(400);
                stage.setMaxHeight(670);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        login_user.getScene().getWindow().hide();
    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == login_registerHere) {
            login_form.setVisible(true);
            register_form.setVisible(false);
        } else if (event.getSource() == register_loginHere) {
            login_form.setVisible(false);
            register_form.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userList();
    }

}
