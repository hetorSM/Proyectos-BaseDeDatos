package org.example.cursobasicofx;

import java.io.IOException;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Button botonEntrar;

    @FXML
    private PasswordField passwordEntrada;

    @FXML
    private TextField usuarioEntrada;

    @FXML
    void login(ActionEvent event) throws IOException {
        String usuario = usuarioEntrada.getText();
        String password = passwordEntrada.getText();
        if (comprobarUsuario(usuario, password)) {
            System.out.println("Si existe");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("panel-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage2 = new Stage();
            stage2.setTitle("Panel XLM");
            stage2.setScene(new Scene(root));
            stage2.show();
        } else {
            System.out.println("No existe");
        }
    }

    public boolean comprobarUsuario(String nickname, String password) {
        String URL = "jdbc:mysql://localhost:3306/conectados";
        String USER = "hector";
        String PASSWORD = "hector";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Base de datos conectada");

            String sql = "SELECT * FROM usuarios WHERE usuario_name =? AND pasware=?";
            PreparedStatement sentencia = connection.prepareStatement(sql);
            sentencia.setString(1, String.valueOf(nickname));
            sentencia.setString(2, String.valueOf(password));
            ResultSet resultado = sentencia.executeQuery();
            return resultado.next();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al registrar el driver de MySQL: " + e);
            e.printStackTrace();
        }
        return false;
    }
}
