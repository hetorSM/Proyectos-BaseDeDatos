package org.example.cursobasicofx;

import javafx.scene.control.Alert;

public class VentanaEmergente {

    public static void error(String texto1, String texto2) {
        Alert dig = new Alert(Alert.AlertType.ERROR);
        dig.setTitle("HAY UN ERROR!!!");
        dig.setHeaderText("ERROR: " + texto1);
        dig.setContentText("ERROR. " + texto2);
        dig.showAndWait();
    }

    public static void alerta(String texto1, String texto2) {
        Alert dig = new Alert(Alert.AlertType.WARNING);
        dig.setTitle("ALERTA!!!");
        dig.setHeaderText(texto1);
        dig.setContentText(texto2);
        dig.showAndWait();
    }

    public static void confirmar(String texto1, String texto2) {
        Alert dig = new Alert(Alert.AlertType.INFORMATION);
        dig.setTitle("Informacion");
        dig.setHeaderText(texto1);
        dig.setContentText(texto2);
        dig.showAndWait();
    }

}
