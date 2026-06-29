package org.example.proyectoshector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.sql.*;

public class PanelController {


    @FXML
    private ListView<String> lista;

    private ObservableList<String> usuariosData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cargarDatos();
        System.out.println(usuariosData);
        lista.setItems(usuariosData);
    }

    public String cargarDatos() {
        String URL = "jdbc:mysql://localhost:3306/proyectosHector";
        String USER = "hector";
        String PASSWORD = "hector";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Base de datos conectada");

            String sql = "SELECT * FROM clientes";
            PreparedStatement sentencia = connection.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                String nick = resultado.getString(4);
                String usuarios = " Nombre Proyecto: " + nick;
                usuariosData.add(usuarios);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al registrar el driver de MySQL: " + e);
            e.printStackTrace();
        }
        return null;
    }
}
