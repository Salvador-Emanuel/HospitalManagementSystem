/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Elga
 */
public class AlertMessage {

    private Alert alert;

    public void errorMessage(String message) {

        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Mensagem de Erro");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }

    public void sucessMessage(String messagre) {

        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Mensagem de Informação");
        alert.setHeaderText(null);
        alert.setContentText(messagre);
        alert.showAndWait();
    }

    public boolean confirmationMassage(String message) {

        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Mensagem de Confirmação");
        alert.setHeaderText(message);
        alert.setContentText("Mensagem de Confirmação");
        alert.showAndWait();
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get().equals(ButtonType.OK)) {
            return true;
        } else {
            return false;
        }

    }

}
