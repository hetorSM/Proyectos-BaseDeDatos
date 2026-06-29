package com.example.colegiohectorsm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ventana.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 542, 334);
        stage.setTitle("Registro del colegio");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    //Recoge solo los datos necesarios que se piden de la lista obtenida y luego envia una lista con las preferencias
    public static ArrayList<String> listaNombres(ArrayList<Alumno> alumnoArrayList, boolean apellidos, boolean nia, boolean curso) {
        ArrayList<String> arrayList = new ArrayList<>();
        String datos = "";
        for (Alumno alumno : alumnoArrayList) {
            if (nia) {
                datos = alumno.getNia() + " ";
            }
            datos += alumno.getNombre();
            if (apellidos) {
                datos += " " + alumno.getApellidos();
            }
            if (curso) {
                datos += " " + alumno.getCurso();
            }
            arrayList.add(datos);
            datos = "";
        }
        //System.out.println("Lista organizada con exito");
        return arrayList;
    }
}