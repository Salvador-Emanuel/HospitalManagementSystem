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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Elga
 */
public class CheckOutPatientFormController implements Initializable {
    
    @FXML
    private DatePicker checkout_checkoutDate;
    
    @FXML
    private DatePicker checkout_date;
    
    @FXML
    private Label checkout_doctor;
    
    @FXML
    private ImageView checkout_imageView;
    
    @FXML
    private Label checkout_name;
    
    @FXML
    private Label checkout_patientID;
    
    @FXML
    private Button checkout_payBtn;
    
    @FXML
    private Label checkout_totalPrice;
    
    @FXML
    private Label checkout_totalDays;
    
    @FXML
    private Button checkout_countBtn;
    
    private Image image;
    
    AlertMessage alert = new AlertMessage();
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    
    public void countBtn() {
        
        long countDays = 0;
        
        if (checkout_date.getValue() != null || checkout_checkoutDate.getValue() != null) {
            
            countDays = ChronoUnit.DAYS.between(checkout_date.getValue(), checkout_checkoutDate.getValue());
        } else {
            alert.errorMessage("Por favor, verifique se as datas estão devidamente preenchidas!!!");
        }
        
        double price = 500;
        
        double totalPrice = (price * countDays);
        
        checkout_totalDays.setText(String.valueOf(countDays));
        checkout_totalPrice.setText(String.valueOf(totalPrice));
        
    }
    
    public void paymentBtn() {
        
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        if (checkout_checkoutDate.getValue() == null
                || checkout_totalDays.getText().isEmpty()
                || checkout_totalPrice.getText().isEmpty()) {
            alert.errorMessage("Por favor, preencha a data da alta do paciente!!!");
        } else {
            
            if (alert.confirmationMassage("Confirmar o pagamento?")) {
                String sql = "INSERT INTO payment (patient_id, doctor, total_days, total_price, date, date_checkout,payment_day) VALUES (?,?,?,?,?,?,?)";
                
                connect = Database.connectDB();
                
                try {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, checkout_patientID.getText());
                    prepare.setString(2, checkout_doctor.getText());
                    prepare.setString(3, checkout_totalDays.getText());
                    prepare.setString(4, checkout_totalPrice.getText());
                    prepare.setString(5, String.valueOf(checkout_date.getValue()));
                    prepare.setString(6, String.valueOf(checkout_checkoutDate.getValue()));
                    prepare.setString(7, String.valueOf(sqlDate));
                    
                    prepare.executeUpdate();
                    
                    String updateData = "UPDATE patient SET payment_status = ? WHERE patient_id = "
                            + checkout_patientID.getText();
                    
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, "Pago");
                    prepare.executeUpdate();
                    
                    alert.sucessMessage("Pagamento efectuado com sucesso.");
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            } else {
                alert.errorMessage("Pagamento cancelado.");
            }
            
        }
        
    }
    
    public void displayFields() {
        
        checkout_patientID.setText(String.valueOf(Data.temp_patientID));
        checkout_name.setText(Data.temp_name);
        checkout_doctor.setText(Data.temp_doctorID);
        checkout_date.setValue(LocalDate.parse(Data.temp_date));
        checkout_imageView.setDisable(true);
        
        image = new Image("File:" + Data.temp_path, 200, 223, false, true);
        checkout_imageView.setImage(image);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        displayFields();
        
    }
    
}
