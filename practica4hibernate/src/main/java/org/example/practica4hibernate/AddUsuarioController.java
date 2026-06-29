package org.example.practica4hibernate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.controllers.TransporteController;
import org.example.models.Usuario;

public class AddUsuarioController {
    private static TransporteController controller = new TransporteController();

    @FXML
    private Button BotonVolver;

    @FXML
    private Button botonAlta;

    @FXML
    private Label mesageError;

    @FXML
    private PasswordField passwordEntrada1;

    @FXML
    private PasswordField passwordEntrada2;

    @FXML
    private TextField usuarioEntrada;

    @FXML
    private CheckBox checkPermiso;

    @FXML
    public void initialize() {
    }

    @FXML
    void newUser(ActionEvent event) {
        String nickname = usuarioEntrada.getText();
        String password = passwordEntrada1.getText();
        String permiso = "no";
        if (checkPermiso.isSelected()) {
            permiso = "si";
        }
        if (!nickname.matches("[a-zA-Z0-9]+([ '-][a-zA-Z0-9]+)*")) {
            System.out.println("El nombre no debe tener caracteres especiales ni estar vacio, solo letras y numeros");
            mesageError.setText("El nombre no debe tener caracteres especiales ni estar vacio, solo letras y numeros");
        } else if (passwordEntrada1.getText().equals(passwordEntrada2.getText())) {
            if (controller.comprobarUsuario(nickname)) {
                mesageError.setText("No se pudo introducir este usuario ya existe");
                System.out.println("No se pudo introducir este usuario ya existe");
            } else {
                controller.insertarEntidad(new Usuario(nickname, password, permiso));
                System.out.println("El nombre se intodujo en la base de datos");
                mesageError.setText("El nombre se añadio exitosamente");
                usuarioEntrada.setText("");
                passwordEntrada1.setText("");
                passwordEntrada2.setText("");
            }
        } else {
            mesageError.setText("La contraseña tiene que ser la misma");
            System.out.println("La contraseña no es la misma");
        }
    }

    @FXML
    void volverLogin(ActionEvent event) {
        HelloApplication.volverPanelPrincipal(0, 0);
    }

}
