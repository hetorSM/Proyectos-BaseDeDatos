package org.example.practica4hibernate;

import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import org.example.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    private static String permisoTiene;
    private static String iconoFoto = "icon/logo-camion.png";
    private static final String ventana[] = new String[]{"login-view", "addusuario-view",
            "iniciopanel-view", "usuariopanel-view", "camionpanel-view", "trabajopanel-view"};
    private static final String ventanaTitulo[] = new String[]{"Inicio login",
            "Introducir usuario registro", "Panel Usuarios", "Panel Camiones", "Panel Trabajos"};

    private static Stage stage;

    @Override
    public void start(Stage stage1) throws IOException {
        stage = stage1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ventana[0] + ".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(ventanaTitulo[0]);
        URL contenedor = getClass().getResource(iconoFoto);
        stage.getIcons().add(new ImageView(contenedor.toString()).getImage());
        stage.setScene(scene);
        stage.show();
        // Activar el Hibernate que inicie la base de datos
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
    }

    public static void main(String[] args) {
        launch();
    }


    public static void volverPanelPrincipal(int index, int titulo) {
        if (index < 0 || index >= ventana.length) index = 0;
        if (titulo < 0 || titulo >= ventanaTitulo.length) titulo = 0;
        try {
            Stage stage = HelloApplication.getStage();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(ventana[index] + ".fxml"));
            Parent root = fxmlLoader.load();
            Stage stage2 = new Stage();
            HelloApplication.setStage(stage2);
            stage2.setTitle(ventanaTitulo[titulo]);
            URL contenedor = HelloApplication.class.getResource(iconoFoto);
            stage2.getIcons().add(new ImageView(contenedor.toString()).getImage());
            stage2.setScene(new Scene(root));
            stage2.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage1) {
        stage = stage1;
    }

    public static String getPermisoTiene() {
        return permisoTiene;
    }

    public static void setPermisoTiene(String permisoTiene1) {
        permisoTiene = permisoTiene1;
    }
}