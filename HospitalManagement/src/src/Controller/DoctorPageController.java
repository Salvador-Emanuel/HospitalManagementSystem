/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Data;
import Model.Users;
import Model.AlertMessage;
import daoHospital.Database;
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
public class DoctorPageController implements Initializable {

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
    private TextField login_doctorID;

    @FXML
    private CheckBox register_checkBox;

    @FXML
    private PasswordField register_confirmPassword;

    @FXML
    private TextField register_doctorID;

    @FXML
    private TextField register_email;

    @FXML
    private AnchorPane register_form;

    @FXML
    private TextField register_fullName;

    @FXML
    private Hyperlink register_loginHere;

    @FXML
    private PasswordField register_password;

    @FXML
    private TextField register_showConfirmPassword;

    @FXML
    private TextField register_showPassword;

    @FXML
    private Button register_signUpBtn;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    @FXML
    void loginAcount(ActionEvent event) {

        if (login_doctorID.getText().isEmpty()
                || login_password.getText().isEmpty()) {
            alert.errorMessage("ID ou senha Incorrecta!");
        } else {
            String sql = "SELECT * FROM doctor WHERE doctor_id = ? AND password = ? AND delete_date IS NULL";

            connect = Database.connectDB();

            try {

//                if (!login_showPassword.isVisible()) {
//                    if (!login_showPassword.getText().equals(login_password.getText())) {
//                        login_showPassword.setText(login_password.getText());
//                    }
//                } else {
//                    if (!login_showPassword.getText().equals(login_password.getText())) {
//                        login_password.setText(login_showPassword.getText());
//                    }
//                }

                String checkStatus = "SELECT status FROM doctor WHERE doctor_id = '"
                        + login_doctorID.getText() + "'AND password ='"
                        + login_password.getText() + "'AND status = 'Activo'";

                prepare = connect.prepareStatement(checkStatus);
                result = prepare.executeQuery();

                if (result.next()) {
                    
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, login_doctorID.getText());
                    prepare.setString(2, login_password.getText());

                    result = prepare.executeQuery();
                    if (result.next()) {

                        Data.doctor_id = result.getString("doctor_id");
                        Data.doctor_name = result.getString("full_name");

                        Parent root = FXMLLoader.load(getClass().getResource("/View/DoctorMainForm.fxml"));
                        Stage stage = new Stage();

                        stage.setTitle("Sistema de gestão hospitalar | Doctor Main Form");
                        stage.setScene(new Scene(root));
                        stage.show();

                        login_loginBtn.getScene().getWindow().hide();

                    } else {
                        alert.errorMessage("ID ou senha incorrecta!");
                    }
                    
                } else {
                    alert.errorMessage("Usuário Inactivo. Por favor solicite o administrador do sistema!!!");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    void loginShowPassword(ActionEvent event) {

        if (login_checkBox.isSelected()) {
            login_showPassword.setText(login_password.getText());
            login_password.setVisible(false);
            login_showPassword.setVisible(true);
        } else {
            login_password.setText(login_showPassword.getText());
            login_password.setVisible(true);
            login_showPassword.setVisible(false);
        }

    }

    @FXML
    void registerAcount(ActionEvent event) {

        if (register_fullName.getText().isEmpty()
                || register_email.getText().isEmpty()
                || register_doctorID.getText().isEmpty()
                || register_password.getText().isEmpty()
                || register_confirmPassword.getText().isEmpty()) {
            alert.errorMessage("Por favor, preencha os campos vazios!");
        } else if (register_password.getText().equals(register_confirmPassword.getText())) {

            String checkDoctorID = "SELECT * FROM doctor WHERE doctor_id = '"
                    + register_doctorID.getText() + "'";

            connect = Database.connectDB();

            try {

                if (!register_showPassword.isVisible()) {
                    if (!register_showPassword.getText().equals(register_password.getText())) {
                        register_showPassword.setText(register_password.getText());
                    }
                } else {
                    if (!register_showPassword.getText().equals(register_password.getText())) {
                        register_password.setText(register_showPassword.getText());
                    }
                }

                prepare = connect.prepareStatement(checkDoctorID);
                result = prepare.executeQuery();

                if (result.next()) {
                    alert.errorMessage(register_doctorID.getText() + ", id já foi usado");
                } else if (register_password.getText().length() <= 8) {
                    alert.errorMessage("Palavra passe inválida, deve ter 8 ou mais caracteres");
                } else {
                    String insertData = "INSERT INTO doctor (full_name, email, doctor_id, password,date, status) VALUES (?,?,?,?,?,?)";

                    prepare = connect.prepareStatement(insertData);

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(1, register_fullName.getText());
                    prepare.setString(2, register_email.getText());
                    prepare.setString(3, register_doctorID.getText());
                    prepare.setString(4, register_password.getText());
                    prepare.setString(5, String.valueOf(sqlDate));
                    prepare.setString(6, "Activo");

                    prepare.executeUpdate();

                    alert.sucessMessage("Cadastrado com sucesso");
                }

            } catch (Exception e) {
                e.printStackTrace();

            }

        } else {
            alert.errorMessage("Palavra passe não correspondente! Por favor verifique!!!");
        }

    }

    @FXML
    void registerShowPassword(ActionEvent event) {

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

            register_showConfirmPassword.setText(register_showConfirmPassword.getText());
            register_showConfirmPassword.setVisible(false);
            register_confirmPassword.setVisible(true);
        }
    }

    public void registerDoctorID() {

        String doctorID = "MID-";
        int tempID = 0;

        String sql = "SELECT MAX(id) FROM doctor";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempID = result.getInt("MAX(id)");
            }

            if (tempID == 0) {
                tempID += 1;
                doctorID += tempID;
            } else {
                doctorID += (tempID + 1);
            }

            register_doctorID.setText(doctorID);
            register_doctorID.setDisable(true);

        } catch (Exception e) {
            e.printStackTrace();
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

    @FXML
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
    public void initialize(URL location, ResourceBundle resources) {

        userList();
        registerDoctorID();

    }

}
