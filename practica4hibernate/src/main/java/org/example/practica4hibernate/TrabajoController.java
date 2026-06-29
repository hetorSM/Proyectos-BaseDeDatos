package org.example.practica4hibernate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.example.controllers.TransporteController;
import org.example.models.Camion;
import org.example.models.Trabajo;
import org.example.models.Usuario;

import java.util.List;

public class TrabajoController {
    private static TransporteController controller = new TransporteController();
    private List<Usuario> usuarios;
    private List<Camion> camiones;

    @FXML
    private Button addBoton;

    @FXML
    private Button borrarBoton;

    @FXML
    private ComboBox<Camion> camionBox;

    @FXML
    private TextField clienteEntrada;

    @FXML
    private ComboBox<String> conductorBox;

    @FXML
    private Button ediatrBoton;

    @FXML
    private TextField idEntrada;

    @FXML
    private ListView<Trabajo> lista;

    @FXML
    private Label mesageError;

    @FXML
    private Button volverBoton;

    @FXML
    public void initialize() {
        cargarConductores();
        cargarCamiones();
        cargarTrabajos();
    }

    private void cargarConductores() {
        conductorBox.getItems().clear();
        usuarios = controller.getListEntidades(Usuario.class);
        for (Usuario user : usuarios) conductorBox.getItems().add(user.getUsuarioNombre());
    }

    private void cargarCamiones() {
        camionBox.getItems().clear();
        camiones = controller.getListEntidades(Camion.class);
        camionBox.getItems().addAll(camiones);
    }

    private void limpiarTextos() {
        idEntrada.setText("");
        clienteEntrada.setText("");
        conductorBox.setValue(usuarios.get(0).getUsuarioNombre());
        camionBox.setValue(camiones.get(0));
    }

    private void cargarTrabajos() {
        lista.getItems().clear();
        lista.getItems().addAll(controller.getListEntidades(Trabajo.class));
    }

    @FXML
    void addTrabajo(ActionEvent event) {
        String cliente = clienteEntrada.getText();
        if (cliente.isEmpty()) {
            mesageError.setText("El cliente no puede estar vacío.");
        } else {
            controller.insertarEntidad(new Trabajo(controller.buscarUsuario(conductorBox.getValue()), cliente, camionBox.getValue()));
            mesageError.setText("Trabajo agregado exitosamente.");
            cargarTrabajos();
            limpiarTextos();
        }
    }

    @FXML
    void borrarTrabajo(ActionEvent event) {
        Trabajo trabajo = obtenerTrabajo();
        if (trabajo != null) {
            controller.borrarEntidad(trabajo);
            cargarTrabajos();
            limpiarTextos();
            mesageError.setText("Trabajo eliminado exitosamente.");
        } else mesageError.setText("Trabajo no se ha podido eliminar.");
    }

    @FXML
    void editarTrabajo(ActionEvent event) {
        Usuario usuario = controller.buscarUsuario(conductorBox.getValue());
        Camion camion = camionBox.getValue();
        String cliente = clienteEntrada.getText();
        Trabajo trabajo = obtenerTrabajo();
        if (cliente.isEmpty()) {
            mesageError.setText("El cliente no puede estar vacío.");
        } else if (trabajo != null) {
            trabajo.setTrabajoConductor(usuario);
            trabajo.setTrabajoCamion(camion);
            trabajo.setTrabajoCliente(cliente);
            controller.actualizarEntidad(trabajo);
            mesageError.setText("Trabajo editado exitosamente.");
            cargarTrabajos();
            limpiarTextos();
        } else mesageError.setText("Trabajo no se ha podido editar.");
    }

    @FXML
    void introduceTrabajo(MouseEvent event) {
        Trabajo trabajo = lista.getSelectionModel().getSelectedItem();
        if (trabajo != null) {
            idEntrada.setText(trabajo.getTrabajoId().toString());
            conductorBox.setValue(trabajo.getTrabajoConductor().getUsuarioNombre());
            camionBox.setValue(trabajo.getTrabajoCamion());
            clienteEntrada.setText(trabajo.getTrabajoCliente());
            mesageError.setText("Trabajo seleccionado.");
        } else mesageError.setText("No se ha encontrado ningun trabajo para seleccionar.");
    }

    private Trabajo obtenerTrabajo() {
        String idEntradaText = idEntrada.getText();
        Trabajo trabajo = lista.getSelectionModel().getSelectedItem();
        if (idEntradaText != null && !idEntradaText.equals("")) {
            if (trabajo == null) {
                trabajo = controller.obtenerEntidadID(Integer.parseInt(idEntradaText), Trabajo.class);
            }
            if (Integer.parseInt(idEntradaText) != trabajo.getTrabajoId()) {
                trabajo = controller.obtenerEntidadID(Integer.parseInt(idEntradaText), Trabajo.class);
            }
        }
        return trabajo;
    }

    @FXML
    void irBolver(ActionEvent event) {
        HelloApplication.volverPanelPrincipal(2, 2);
    }

}
