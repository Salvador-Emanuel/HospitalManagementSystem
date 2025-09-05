/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AlertMessage;
import Model.Data;
import daoHospital.Database;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Elga
 */
public class EditAppointmentFormController implements Initializable {

    @FXML
    private TextArea editAppoint_address;

    @FXML
    private TextField editAppoint_appointmentID;

    @FXML
    private Button editAppoint_cancelBtn;

    @FXML
    private TextArea editAppoint_description;

    @FXML
    private TextField editAppoint_diagnosis;

    @FXML
    private ComboBox<String> editAppoint_doctor;

    @FXML
    private TextField editAppoint_fullName;

    @FXML
    private ComboBox<String> editAppoint_gender;

    @FXML
    private TextField editAppoint_mobileNumber;

    @FXML
    private ComboBox<String> editAppoint_specialized;

    @FXML
    private ComboBox<String> editAppoint_status;

    @FXML
    private TextField editAppoint_treatment;

    @FXML
    private Button editAppoint_updtateBtn;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private AlertMessage alert = new AlertMessage();

    
        public void displayFields() {
    
        editAppoint_appointmentID.setText(Data.temp_appID);
        editAppoint_fullName.setText(Data.temp_appName);
        editAppoint_gender.getSelectionModel().select(Data.temp_appGender);
        editAppoint_mobileNumber.setText(Data.temp_appMobileNumber);
        editAppoint_address.setText(Data.temp_appAddress);
        editAppoint_description.setText(Data.temp_appDescription);
        editAppoint_diagnosis.setText(Data.temp_appDiagnosis);
        editAppoint_treatment.setText(Data.temp_appTreatment);
        editAppoint_doctor.getSelectionModel().select(Data.temp_appDoctor);
        editAppoint_specialized.getSelectionModel().select(Data.temp_appSpecialized);
        editAppoint_status.getSelectionModel().select(Data.temp_appStatus);
    }

    public void doctorList() {

        String sql = "SELECT * FROM doctor WHERE delete_date IS NULL";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();
            while (result.next()) {
                listData.add(result.getString("doctor_id"));
            }

            editAppoint_doctor.setItems(listData);

            specializedList();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void specializedList() {
        String sql = "SELECT * FROM doctor WHERE delete_date IS NULL AND doctor_id = '"
                + editAppoint_doctor.getSelectionModel().getSelectedItem() + "'";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();
            if (result.next()) {
                listData.add(result.getString("specialized"));
            }
            editAppoint_specialized.setItems(listData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void genderList() {
        List<String> genderL = new ArrayList<>();

        for (String data : Data.gender) {
            genderL.add(data);
        }

        ObservableList listData = FXCollections.observableList(genderL);
        editAppoint_gender.setItems(listData);

    }

    public void statusList() {
        List<String> statusL = new ArrayList<>();

        for (String data : Data.status) {
            statusL.add(data);
        }

        ObservableList listData = FXCollections.observableList(statusL);
        editAppoint_status.setItems(listData);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        doctorList();
        genderList();
        statusList();
        displayFields();

    }

}
