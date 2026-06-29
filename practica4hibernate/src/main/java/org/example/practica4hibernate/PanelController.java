package org.example.practica4hibernate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PanelController {
    @FXML
    private Button camionesboton;

    @FXML
    private Button trabajosboton;

    @FXML
    private Button usuariosboton;

    @FXML
    private Button volverboton;

    @FXML
    public void initialize() {
        String permiso = HelloApplication.getPermisoTiene();
        usuariosboton.setDisable(!permiso.equals("si"));
    }

    @FXML
    void irCamiones(ActionEvent event) {
        HelloApplication.volverPanelPrincipal(4, 4);
    }

    @FXML
    void irTrabajos(ActionEvent event) {
        HelloApplication.volverPanelPrincipal(5, 5);
    }

    @FXML
    void irUsuarios(ActionEvent event) {
        HelloApplication.volverPanelPrincipal(3, 3);
    }

    @FXML
    void volver(ActionEvent event) {
        HelloApplication.volverPanelPrincipal(0, 0);
    }

}
