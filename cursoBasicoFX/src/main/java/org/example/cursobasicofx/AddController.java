package org.example.cursobasicofx;

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
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.*;

public class AddController {

    @FXML
    private Button botonAlta;

    @FXML
    private PasswordField passwordEntrada1;

    @FXML
    private PasswordField passwordEntrada2;

    @FXML
    private TextField usuarioEntrada;

    @FXML
    private Label mesageError;


    @FXML
    private Button BotonVolver;


    @FXML
    void newUser(ActionEvent event) throws IOException {
        if (!comprobarDatos(usuarioEntrada.getText())) {
            if (!usuarioEntrada.getText().matches("[a-zA-Z0-9]+([ '-][a-zA-Z0-9]+)*")) {
                System.out.println("El nombre no debe tener caracteres especiales");
                mesageError.setText("El nombre no debe tener caracteres especiales");
            } else if (passwordEntrada1.getText().equals(passwordEntrada2.getText())) {
                String URL = "jdbc:mysql://localhost:3306/conectados";
                String USER = "hector";
                String PASSWORD = "hector";
                Connection connection = null;

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    System.out.println("Base de datos conectada");

                    String INSERT = "INSERT INTO usuarios (usuario_name, pasware) VALUES (?,?)";
                    PreparedStatement sentencia = null;
                    String nickname = usuarioEntrada.getText();
                    String password = passwordEntrada1.getText();
                    try {
                        sentencia = connection.prepareStatement(INSERT);
                        sentencia.setString(1, nickname);
                        sentencia.setString(2, password);
                        sentencia.executeUpdate();
                        System.out.println("Insertado correctamente");
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
                    System.out.println("El nombre se intodujo en la base de datos");
                    mesageError.setText("El nombre se añadio exitosamente");
                    usuarioEntrada.setText("");
                    passwordEntrada1.setText("");
                    passwordEntrada2.setText("");
                } catch (SQLException | ClassNotFoundException e) {
                    System.out.println("Error al registrar el driver de MySQL: " + e);
                    e.printStackTrace();
                }

            } else {
                mesageError.setText("La contraseña tiene que ser la misma");
                System.out.println("La contraseña no es la misma");
            }
        } else {
            mesageError.setText("Este usuario ya existe");
            System.out.println("Este usuario ya existe");
        }
    }

    public static boolean comprobarDatos(String nickname) {
        String URL = "jdbc:mysql://localhost:3306/conectados";
        String USER = "hector";
        String PASSWORD = "hector";
        Connection connection = null;
        boolean esCorrecto = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Base de datos conectada");
            PreparedStatement sentencia = null;
            ResultSet resultado = null;
            System.out.println("Comprobando...");
            try {
                String SELECT_WHERE_NAME = "SELECT usuario_id, usuario_name, pasware FROM usuarios WHERE usuario_name=?";
                sentencia = connection.prepareStatement(SELECT_WHERE_NAME);
                sentencia.setString(1, nickname);
                resultado = sentencia.executeQuery();
                System.out.println("Comprobacion de datos terminada lectura correctamente");
                return resultado.next();
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
        return false;
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

    public void add(ActionEvent event) {
        String URL = "jdbc:mysql://localhost:3306/conectados";
        String USER = "hector";
        String PASSWORD = "hector";
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Base de datos conectada");

            PreparedStatement sentencia = null;
            String nickname = usuarioEntrada.getText();
            String password = passwordEntrada1.getText();

            CallableStatement stmt = connection.prepareCall("{CALL InsertarUsuario(?,?)}");
            stmt.setString(1, nickname);
            stmt.setString(2, password);
            stmt.execute();

            System.out.println("Usuario insertado correctamente");
            Statement stmt2 = connection.createStatement();
            ResultSet rs = stmt2.executeQuery("SELECT ContarUsuarios()");
            if (rs.next()) {
                int totalUsuarios = rs.getInt(1);
                System.out.println(totalUsuarios);
            }
            //****************************************************
            //******************TRANSACCIONES*********************

            PreparedStatement pstmt1 = null;
            PreparedStatement pstmt2 = null;
            connection.setAutoCommit(false);
            String sql1= "insert into usuarios (usuario_nickname, usuario_password) values (?,?)";
            pstmt1 = connection.prepareStatement(sql1);
            pstmt1.setString(1,"usuario1");
            pstmt1.setString(2,"password1");
            pstmt1.executeUpdate();

            String sql2= "insert into usuarios (usuario_nickname, usuario_password) values (?,?)";
            pstmt2 = connection.prepareStatement(sql2);
            pstmt2.setString(1,"usuario2");
            pstmt2.setString(2,"password2");
            pstmt2.executeUpdate();

            connection.commit();
            System.out.println("Transaccion copletada");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al registrar el driver de MySQL: " + e);
            e.printStackTrace();
        }
    }
}
