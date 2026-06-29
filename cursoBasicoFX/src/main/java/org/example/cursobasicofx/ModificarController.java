package org.example.cursobasicofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModificarController {
    private final String TABLA = "usuarios";
    private final String COLUM_ID = "usuario_id";
    private final String COLUM_NAME = "usuario_name";
    private final String COLUM_PASS = "pasware";
    private final String UPDATE = "UPDATE " + TABLA + " SET ";
    private final String UPDATE_WHERE_NAME = UPDATE + COLUM_NAME + " =?" + " WHERE " + COLUM_NAME + "=?";
    private final String UPDATE_WHERE_ID = UPDATE + COLUM_PASS + "=?" + " WHERE " + COLUM_NAME + "=?";
    @FXML
    private Button botonModifica;

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
    void modificaUser(ActionEvent event) {
        if (usuarioEntrada.getText().matches("[a-zA-Z0-9]+([ '-][a-zA-Z0-9]+)*")) {
            String nickname, nombreNuevo, password;
            nickname = usuarioEntrada.getText();
            System.out.println("Usuario: " + nickname);
            if (nombreCheck.isSelected()) {
                if (!AddController.comprobarDatos(nombreEntrada.getText())) {
                    if (nombreEntrada.getText().matches("[a-zA-Z0-9]+([ '-][a-zA-Z0-9]+)*")) {
                        System.out.print("Nombre nuevo: ");
                        nombreNuevo = nombreEntrada.getText();
                        updateNombre(nickname, nombreNuevo);
                        if (passwordCheck.isSelected()) {
                            nickname = nombreNuevo;
                        }
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
                System.out.print("Password nueva: ");
                password = passwordEntrada.getText();
                updatePassword(nickname, password);
                passwordEntrada.setText("");
                usuarioEntrada.setText("");
                mesageError.setText("Se ha modificado la contraseña exitosamente");
            }
            if (passwordCheck.isSelected() && nombreCheck.isSelected()) {
                mesageError.setText("Se ha modificado todos los datos exitosamente");
            } else if (!passwordCheck.isSelected() && !nombreCheck.isSelected()) {
                mesageError.setText("No se ha modificado los datos");
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
    }

    public void updateNombre(String nickname, String nombreNuevo) {
        String URL = "jdbc:mysql://localhost:3306/conectados";
        String USER = "hector";
        String PASSWORD = "hector";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Base de datos conectada");
            PreparedStatement sentencia = null;
            try {
                sentencia = connection.prepareStatement(UPDATE_WHERE_NAME);
                sentencia.setString(1, nombreNuevo);
                sentencia.setString(2, nickname);
                sentencia.executeUpdate();
                System.out.println("Modificacion correctamente");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (sentencia != null) {
                    try {
                        sentencia.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al registrar el driver de MySQL: " + e);
            e.printStackTrace();
        }
    }

    public void updatePassword(String nickname, String password) {
        String URL = "jdbc:mysql://localhost:3306/conectados";
        String USER = "hector";
        String PASSWORD = "hector";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Base de datos conectada");
            PreparedStatement sentencia = null;

            try {
                sentencia = connection.prepareStatement(UPDATE_WHERE_ID);
                sentencia.setString(1, password);
                sentencia.setString(2, nickname);
                sentencia.executeUpdate();
                System.out.println("Modificacion correctamente");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (sentencia != null) {
                    try {
                        sentencia.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al registrar el driver de MySQL: " + e);
            e.printStackTrace();
        }
    }

    @FXML
    void nombreActivar(ActionEvent event) {
        if (nombreCheck.isSelected()) {
            nombreEntrada.disableProperty().set(false);
        } else {
            nombreEntrada.disableProperty().set(true);
            nombreEntrada.setText("");
        }
    }

    @FXML
    void passwordActivar(ActionEvent event) {
        if (passwordCheck.isSelected()) {
            passwordEntrada.disableProperty().set(false);
        } else {
            passwordEntrada.disableProperty().set(true);
            passwordEntrada.setText("");
        }
    }

    @FXML
    void volverPanel(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("panel-view.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage2 = new Stage();
        stage2.setTitle("Panel XLM");
        stage2.setScene(new Scene(root));
        stage2.show();
    }

}
