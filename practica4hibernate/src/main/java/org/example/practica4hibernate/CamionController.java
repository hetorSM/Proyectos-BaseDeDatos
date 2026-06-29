package org.example.practica4hibernate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.controllers.TransporteController;
import org.example.models.Camion;

public class CamionController {
    private static TransporteController controller = new TransporteController();

    @FXML
    private Button addBoton;

    @FXML
    private Button borrarBoton;

    @FXML
    private Button ediatrBoton;

    @FXML
    private ListView<Camion> lista;

    @FXML
    private TextField matriculaEntrada;

    @FXML
    private Label mesageError;

    @FXML
    private TextField nombreEntrada;

    @FXML
    private TextField idEntrada;

    @FXML
    private Button volverBoton;
    private ObservableList<Camion> usuariosData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        String permiso = HelloApplication.getPermisoTiene();
        addBoton.setDisable(!permiso.equals("si"));
        borrarBoton.setDisable(!permiso.equals("si"));
        ediatrBoton.setDisable(!permiso.equals("si"));
        cargarDatos();
        lista.setItems(usuariosData);
    }

    public void cargarDatos() {
        usuariosData.clear();
        usuariosData.addAll(controller.getListEntidades(Camion.class));
    }

    @FXML
    void addCamion(ActionEvent event) {
        String nomCamion = nombreEntrada.getText();
        String matriculaCamion = matriculaEntrada.getText();
        if (nomCamion.isEmpty() || matriculaCamion.isEmpty()) {
            System.out.println("Error no se puede poner un dato vacio");
            mesageError.setText("Error no se puede poner un dato vacio");
        } else {
            controller.insertarEntidad(new Camion(nomCamion, matriculaCamion));
            idEntrada.setText("");
            nombreEntrada.setText("");
            matriculaEntrada.setText("");
            mesageError.setText("Camion añadido");
        }
        cargarDatos();
    }

    @FXML
    void borrarCamion(ActionEvent event) {
        Camion camion = obtenerCamion();
        if (camion != null) {
            controller.borrarEntidad(camion);
            cargarDatos();
            idEntrada.setText("");
            nombreEntrada.setText("");
            matriculaEntrada.setText("");
            mesageError.setText("Camion borrado");
        }
    }

    @FXML
    void editarCamion(ActionEvent event) {
        String textoIdCamion = idEntrada.getText();
        String nomCamion = nombreEntrada.getText();
        String matriculaCamion = matriculaEntrada.getText();
        Camion camion = obtenerCamion();
        if (textoIdCamion.equals("")) {
            System.out.println("Error no se puede editar, selecciona un camion de la lista");
            mesageError.setText("Error no se puede editar, selecciona un camion de la lista");
        } else {
            camion.setCamionNombre(nomCamion);
            camion.setCamionMatricula(matriculaCamion);
            controller.actualizarEntidad(camion);
            idEntrada.setText("");
            nombreEntrada.setText("");
            matriculaEntrada.setText("");
            mesageError.setText("Camion editado");
        }
        cargarDatos();
    }

    @FXML
    void introduceCamion(MouseEvent event) {
        Camion camion = lista.getSelectionModel().getSelectedItem();
        if (camion != null) {
            idEntrada.setText(camion.getCamionId().toString());
            nombreEntrada.setText(camion.getCamionNombre());
            matriculaEntrada.setText(camion.getCamionMatricula());
            mesageError.setText("Camion insertado");
        }
    }

    private Camion obtenerCamion() {
        String idEntradaText = idEntrada.getText();
        Camion camion = lista.getSelectionModel().getSelectedItem();
        if (idEntradaText != null && !idEntradaText.equals("")) {
            if (camion == null) {
                camion = controller.obtenerEntidadID(Integer.parseInt(idEntradaText), Camion.class);
            }
            if (Integer.parseInt(idEntradaText) != camion.getCamionId()) {
                camion = controller.obtenerEntidadID(Integer.parseInt(idEntradaText), Camion.class);
            }
        }
        return camion;
    }

    @FXML
    void irBolver(ActionEvent event) {
        HelloApplication.volverPanelPrincipal(2, 2);
    }
}
