package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/conectados";
    private static final String USER = "hector";
    private static final String PASSWORD = "hector";

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Base de datos conectada");
        } catch (SQLException e) {
            System.out.println("Error al registrar el driver de MySQL: " + e);
            e.printStackTrace();
        } finally {
            if (connection != null) {
                System.out.println("Conexion establecida");
                try {
                    connection.close();
                    System.out.println("Conexion cerrada");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}