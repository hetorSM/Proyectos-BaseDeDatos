package com.example.colegiohectorsm;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class HelloController implements Initializable {
    ArrayList<Alumno> alumnoArrayList = new ArrayList<>();
    ArrayList<String> alumnosCiclo = new ArrayList<>();
    String cursosArraySrt[] = new String[]{"1FPB", "2FPB", "1SMX", "2SMX", "1ASIX", "2ASIX", "1DAW", "2DAW", "1DAM", "2DAM"};
    File rutaFile = new File("alumnosHector.txt");
    File rutaXmlFile = new File("alumnosHector.xml");

    @FXML
    private TextField apellidoTexto;

    @FXML
    private CheckBox apellidosCheck;

    @FXML
    private Button botonExportarXML;

    @FXML
    private Button botonGuardar;

    @FXML
    private Button botonImportarXML;

    @FXML
    private Button botonMostrar;

    @FXML
    private ChoiceBox<String> cajaTexto;

    @FXML
    private ListView<String> listaTexto;

    @FXML
    private CheckBox niaCheck;

    @FXML
    private TextField niaTexto;

    @FXML
    private TextField nombreTexto;

    @FXML
    private Label textoInformativo;

    @FXML
    private ChoiceBox<String> buscadorCursosCaja;

    @FXML
    private CheckBox cursoCheck;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Meter los datos en las cajas ChoiceBox
        cajaTexto.getItems().setAll(cursosArraySrt);
        ArrayList<String> cursosBuscar = new ArrayList<>();
        cursosBuscar.add("TODOS");
        cursosBuscar.addAll(Arrays.asList(cursosArraySrt));
        buscadorCursosCaja.getItems().setAll(cursosBuscar);

        buscadorCursosCaja.setOnAction((event) -> {
            mostrarAlumnosCurso();
        });

        textoInformativo.setText("Introduce los datos");
        //Leer los datos guardados si hay del archivo txt
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(rutaFile));
            String dato = "";
            Alumno alumno;
            while ((dato = bufferedReader.readLine()) != null) {
                alumno = new Alumno();
                if (alumno.separarMeterTodo(dato)) {
                    alumnoArrayList.add(alumno);
                } else {
                    System.out.println("Error, dato incorrecto no se pudo cargar el alumno");
                }
            }
            bufferedReader.close();
            System.out.println("El archivo alumno de texto fue leido con exito");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        listaTexto.getItems().setAll(HelloApplication.listaNombres(alumnoArrayList, false, false, false));
    }

    @FXML
    public void ExportarAlumno(ActionEvent event) {
        SistemaXML.escribirXML(rutaXmlFile, alumnoArrayList);
        textoInformativo.setText("Se han exportado los datos exitosamente");
    }

    @FXML
    public void ImportarAlumno(ActionEvent event) {
        alumnoArrayList = SistemaXML.leerFicheroXML(rutaXmlFile);
        listaTexto.getItems().setAll(HelloApplication.listaNombres(alumnoArrayList, false, false, false));
        textoInformativo.setText("Se han importado los datos del archivo XML exitosamente");
    }

    @FXML
    public void guardarAlumno(ActionEvent event) {
        try {   //Guardar los datos en un archivo de txt
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(rutaFile));
            for (Alumno alumno : alumnoArrayList) {
                bufferedWriter.write(alumno.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            textoInformativo.setText("Se han guardado los datos exitosamente");
            System.out.println("El archivo alumno de txt fue creado exitosamente");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void mostrarAlumno(ActionEvent event) {
        if (nombreTexto.getText() != "" && apellidoTexto.getText() != "" && niaTexto.getText() != "" && cajaTexto.getSelectionModel().getSelectedItem() != null) {
            if (!niaTexto.getText().matches("[0-9]+([ '-][0-9]+)*")) {
                System.out.println("El nia solo debe tener numeros");
                textoInformativo.setText("El nia solo debe tener numeros");
            } else if (!nombreTexto.getText().matches("[a-zA-z]+([ '-][a-zA-Z]+)*")) {
                System.out.println("El nombre no debe tener numeros ni caracteres especiales");
                textoInformativo.setText("El nombre no debe tener numeros ni caracteres especiales");
            } else if (!apellidoTexto.getText().matches("[a-zA-z]+([ '-][a-zA-Z]+)*")) {
                System.out.println("El apellido no debe tener numeros ni caracteres especiales");
                textoInformativo.setText("El apellido no debe tener numeros ni caracteres especiales");
            } else {
                Alumno alumno = new Alumno(nombreTexto.getText(), apellidoTexto.getText(), niaTexto.getText(), cajaTexto.getSelectionModel().getSelectedItem());
                alumnoArrayList.add(alumno);
                textoInformativo.setText("Se han añadido un alumno mas");
                System.out.println("El alumno fue introducido exitosamente");
                nombreTexto.setText("");
                apellidoTexto.setText("");
                niaTexto.setText("");
                cajaTexto.getSelectionModel().clearSelection();
            }
        } else {
            textoInformativo.setText("Todas las casillas deben estar completas introduce los datos que faltan");
            System.out.println("Error el dato/s introducido/s vacios");
        }
        mostrarAlumnosCurso();
        System.out.println("Mostrando el listado de alumnos");
    }

    @FXML
    public void activarCheck(ActionEvent event) {
        mostrarAlumnosCurso();
    }

    public void mostrarAlumnosCurso() {
        ArrayList<Alumno> alumnoMismoCurso = new ArrayList<>();
        if (buscadorCursosCaja.getValue() != null) {
            if (buscadorCursosCaja.getValue().equals("TODOS")) {
                alumnoMismoCurso.addAll(alumnoArrayList);
            } else {
                for (Alumno alumno : alumnoArrayList) {
                    if (alumno.getCurso().equals(buscadorCursosCaja.getValue())) {
                        alumnoMismoCurso.add(alumno);
                    }
                }
            }
            listaTexto.getItems().setAll(HelloApplication.listaNombres(alumnoMismoCurso, apellidosCheck.isSelected(),
                    niaCheck.isSelected(), cursoCheck.isSelected()));
        } else {
            listaTexto.getItems().setAll(HelloApplication.listaNombres(alumnoArrayList, apellidosCheck.isSelected(),
                    niaCheck.isSelected(), cursoCheck.isSelected()));
        }
    }
}
