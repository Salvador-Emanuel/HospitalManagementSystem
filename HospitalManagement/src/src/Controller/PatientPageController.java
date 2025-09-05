/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AlertMessage;
import Model.Users;
import daoHospital.Database;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Elga
 */
public class PatientPageController implements Initializable {

    @FXML
    private CheckBox login_checkBox;

    @FXML
    private AnchorPane login_form;

    @FXML
    private Button login_loginBtn;

    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_patientID;

    @FXML
    private TextField login_showPassword;

    @FXML
    private ComboBox<?> login_user;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private AlertMessage alert = new AlertMessage();

    @FXML
    void loginAcount(ActionEvent event) {

        if (login_patientID.getText().isEmpty()
                || login_password.getText().isEmpty()) {
            alert.errorMessage("ID ou senha em vazio. Queira preencher por favor!");
        } else {
            String sql = "SELECT * FROM patient WHERE patient_id = ? AND password = ? AND date_delete IS NULL";

            connect = Database.connectDB();

            try {

                String checkStatus = "SELECT status FROM patient WHERE patient_id = '"
                        + login_patientID.getText() + "'AND password ='"
                        + login_password.getText() + "'AND status = 'Activo'";

                prepare = connect.prepareStatement(checkStatus);
                result = prepare.executeQuery();

                if (result.next()) {

                    alert.errorMessage("Usuário Inactivo. Por favor solicite o administrador do sistema!!!");

                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, login_patientID.getText());
                    prepare.setString(2, login_password.getText());

                    result = prepare.executeQuery();

                    if (result.next()) {
                        alert.sucessMessage("Acesso bem Sucedido!!!");
                    } else {
                        alert.errorMessage("ID ou senha incorrecta!");
                    }

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

    public void userList() {

        List<String> listU = new ArrayList<>();

        for (String data : Users.user) {
            listU.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listU);
        login_user.setItems(listData);

    }

    @FXML
    void switchPage(ActionEvent event) {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userList();
    }

}
