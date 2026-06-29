package org.example.practica4hibernate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.controllers.TransporteController;
import org.example.models.Usuario;

public class LoginController {
    private static TransporteController controller = new TransporteController();

    @FXML
    private Button botonEntrar;

    @FXML
    private Button botonRegistrar;

    @FXML
    private PasswordField passwordEntrada;

    @FXML
    private TextField usuarioEntrada;
    @FXML
    private Label mensageText;

    @FXML
    public void initialize() {
        mensageText.setText("Introduce los datos");
    }

    @FXML
    void login(ActionEvent event) {
        String nombre = usuarioEntrada.getText();
        String password = passwordEntrada.getText();
        Usuario usuario = controller.buscarUsuario(nombre);
        if (usuario != null && controller.comprobarUsuario(usuario, nombre, password)) {
            System.out.println("Si existe inicio correcto");
            HelloApplication.setPermisoTiene(usuario.getUsuarioPermiso());
            HelloApplication.volverPanelPrincipal(2, 2);
        } else {
            mensageText.setText("El usuario o la contraseña son incorrectas");
            System.out.println("El usuario o la contraseña son incorrectas");
        }
    }

    @FXML
    void registrar(ActionEvent event) {
        HelloApplication.volverPanelPrincipal(1, 1);
    }
}
