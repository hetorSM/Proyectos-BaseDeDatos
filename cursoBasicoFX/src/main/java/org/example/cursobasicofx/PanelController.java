package org.example.cursobasicofx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class PanelController {


    @FXML
    private Button borrarBoton;

    @FXML
    private Button ediatrBoton;

    @FXML
    private Button addBoton;

    @FXML
    private ListView<String> lista;

    private ObservableList<String> usuariosData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cargarDatos();
        System.out.println(usuariosData);
        lista.setItems(usuariosData);
    }

    @FXML
    void addUsuario(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        System.out.println("Añadir nuevo usuario");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-view.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage2 = new Stage();
        stage2.setTitle("Add XLM");
        stage2.setScene(new Scene(root));
        stage2.show();
    }

    public String cargarDatos() {
        String URL = "jdbc:mysql://localhost:3306/conectados";
        String USER = "hector";
        String PASSWORD = "hector";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Base de datos conectada");

            String sql = "SELECT * FROM usuarios";
            PreparedStatement sentencia = connection.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            usuariosData.clear();
            while (resultado.next()) {
                String nickID = resultado.getString(1);
                String nick = resultado.getString(2);
                String pass = resultado.getString(3);
                String usuarios = "ID: " + nickID + "; Nombre: " + nick + "; Pass: " + pass;
                usuariosData.add(usuarios);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al registrar el driver de MySQL: " + e);
            e.printStackTrace();
        }
        return null;
    }


    @FXML
    void borrarUsuario(ActionEvent event) throws IOException {

        String URL = "jdbc:mysql://localhost:3306/conectados";
        String USER = "hector";
        String PASSWORD = "hector";
        Connection connection = null;
        String usuario = lista.getSelectionModel().getSelectedItem();
        String arraySplit[] = usuario.split(";");
        String nickname = arraySplit[1].replace(" Nombre: ", "");
        if (usuario != null) {
            System.out.println("Elemento seleccionado: " + usuario);
            System.out.println("Usuario seleccionado: " + nickname);
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Base de datos conectada");

                String COLUM_NAME = "usuario_name";
                String DELETE = "DELETE FROM usuarios";
                String DELETE_WHERE_NAME = DELETE + " WHERE " + COLUM_NAME + "=?";

                PreparedStatement sentencia = null;

                try {
                    sentencia = connection.prepareStatement(DELETE_WHERE_NAME);
                    sentencia.setString(1, nickname);
                    sentencia.executeUpdate();
                    System.out.println("Eliminacion terminada correctamente");
                    cargarDatos();
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
        } else {
            System.out.println("No se ha seleccionado ningún elemento.");
        }

    }

    @FXML
    void editarUsuario(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        System.out.println("Editar usuario");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editar-view.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage2 = new Stage();
        stage2.setTitle("Editar XLM");
        stage2.setScene(new Scene(root));
        stage2.show();
    }

}
