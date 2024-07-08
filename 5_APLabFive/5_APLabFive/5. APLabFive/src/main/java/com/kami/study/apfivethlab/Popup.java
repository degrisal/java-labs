package com.kami.study.apfivethlab;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Popup {
    public static void showPopup(String messageType, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(messageType);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}