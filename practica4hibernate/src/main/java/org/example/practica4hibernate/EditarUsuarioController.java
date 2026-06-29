package org.example.practica4hibernate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.example.controllers.TransporteController;
import org.example.models.Usuario;

public class EditarUsuarioController {
    private static TransporteController controller = new TransporteController();

    @FXML
    private Button borrarBoton;

    @FXML
    private Button ediatrBoton;

    @FXML
    private ListView<Usuario> lista;

    @FXML
    private Label mesageError;

    @FXML
    private CheckBox nombreCheck;

    @FXML
    private TextField nombreEntrada;

    @FXML
    private CheckBox passwordCheck;

    @FXML
    private PasswordField passwordEntrada;

    @FXML
    private TextField usuarioEntrada;

    @FXML
    private Button volverBoton;

    @FXML
    private CheckBox permisoCheck;

    private ObservableList<Usuario> usuariosData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cargarDatos();
        lista.setItems(usuariosData);
    }

    public void cargarDatos() {
        usuariosData.clear();
        usuariosData.addAll(controller.getListEntidades(Usuario.class));
    }

    @FXML
    void introduceUser(MouseEvent event) {
        usuarioEntrada.setText(lista.getSelectionModel().getSelectedItem().getUsuarioNombre());
    }

    @FXML
    void borrarUsuario(ActionEvent event) {
        controller.borrarEntidad(lista.getSelectionModel().getSelectedItem());
        cargarDatos();
        mesageError.setText("Usuario borrado");
    }

    @FXML
    void editarUsuario(ActionEvent event) {
        boolean correcto;
        String nickname, nombreNuevo, password;
        String permiso = "no";
        if (permisoCheck.isSelected()) {
            permiso = "si";
        }
        if (usuarioEntrada.getText().matches("[a-zA-Z0-9]+([ '-][a-zA-Z0-9]+)*")) {
            nickname = usuarioEntrada.getText();
            Usuario usuario = controller.buscarUsuario(nickname);
            if (usuario != null) {
                System.out.println("Usuario: " + nickname);
                usuario.setUsuarioPermiso(permiso);
                if (nombreCheck.isSelected()) {
                    correcto = controller.comprobarUsuario(nombreEntrada.getText());
                    if (!correcto) {
                        if (nombreEntrada.getText().matches("[a-zA-Z0-9]+([ '-][a-zA-Z0-9]+)*")) {
                            nombreNuevo = nombreEntrada.getText();
                            System.out.print("Nombre nuevo: " + nombreNuevo);
                            usuario.setUsuarioNombre(nombreNuevo);
                            if (passwordCheck.isSelected()) nickname = nombreNuevo;
                            usuarioEntrada.setText("");
                            nombreEntrada.setText("");
                            mesageError.setText("Se ha modificado el nombre exitosamente");
                        } else {
                            System.out.println("El nombre no debe tener caracteres especiales");
                            mesageError.setText("El nombre no debe tener caracteres especiales");
                        }
                    } else {
                        mesageError.setText("Este usuario ya existe");
                        System.out.println("Este usuario ya existe");
                    }
                }
                if (passwordCheck.isSelected()) {
                    password = passwordEntrada.getText();
                    System.out.print("Password nueva: " + password);
                    usuario.setUsuarioPassword(password);
                    passwordEntrada.setText("");
                    usuarioEntrada.setText("");
                    mesageError.setText("Se ha modificado la contraseña exitosamente");
                }
                if (passwordCheck.isSelected() && nombreCheck.isSelected()) {
                    mesageError.setText("Se han modificado todos los datos exitosamente");
                } else if (!passwordCheck.isSelected() && !nombreCheck.isSelected()) {
                    mesageError.setText("No se ha modificado los datos solo el permiso");
                }
                controller.actualizarEntidad(usuario);
            } else {
                System.out.println("El usuario no existe");
                mesageError.setText("El usuario no existe");
            }
        } else {
            if (!usuarioEntrada.getText().equals("")) {
                System.out.println("El usuario no debe tener caracteres especiales");
                mesageError.setText("El usuario no debe tener caracteres especiales");
            } else {
                System.out.println("Introduce un nombre no tiene que estar vacio");
                mesageError.setText("El nombre no debe estar vacio");
            }
        }
        cargarDatos();
    }

    @FXML
    void irBolver(ActionEvent event) {
        HelloApplication.volverPanelPrincipal(2, 2);
    }

    @FXML
    void nombreActivar(ActionEvent event) {
        if (nombreCheck.isSelected()) {
            nombreEntrada.disableProperty().set(false);
        } else {
            nombreEntrada.disableProperty().set(true);
        }
    }

    @FXML
    void passwordActivar(ActionEvent event) {
        if (passwordCheck.isSelected()) {
            passwordEntrada.disableProperty().set(false);
        } else {
            passwordEntrada.disableProperty().set(true);
        }
    }

}
