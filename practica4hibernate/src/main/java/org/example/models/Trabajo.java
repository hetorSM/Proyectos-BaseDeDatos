package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "trabajos")
public class Trabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trabajo")
    private Integer trabajoId;

    @ManyToOne // Relación con Usuario
    @JoinColumn(name = "conductor", nullable = false) // Columna que referencia a Usuario
    private Usuario trabajoConductor;

    @Column(name = "cliente", nullable = false, length = 45)
    private String trabajoCliente;

    @ManyToOne // Relación con Camion
    @JoinColumn(name = "camion_trabajo", nullable = false) // Columna que referencia a Camion
    private Camion trabajoCamion;

    public Trabajo() {
    }

    public Trabajo(Usuario trabajoConductor, String trabajoCliente, Camion trabajoCamion) {
        this.trabajoConductor = trabajoConductor;
        this.trabajoCliente = trabajoCliente;
        this.trabajoCamion = trabajoCamion;
    }

    // Getters y Setters
    public Integer getTrabajoId() {
        return trabajoId;
    }

    public void setTrabajoId(Integer trabajoId) {
        this.trabajoId = trabajoId;
    }

    public Usuario getTrabajoConductor() {
        return trabajoConductor;
    }

    public void setTrabajoConductor(Usuario trabajoConductor) {
        this.trabajoConductor = trabajoConductor;
    }

    public String getTrabajoCliente() {
        return trabajoCliente;
    }

    public void setTrabajoCliente(String trabajoCliente) {
        this.trabajoCliente = trabajoCliente;
    }

    public Camion getTrabajoCamion() {
        return trabajoCamion;
    }

    public void setTrabajoCamion(Camion trabajoCamion) {
        this.trabajoCamion = trabajoCamion;
    }

    @Override
    public String toString() {
        return "Trabajo{ ID= " + trabajoId + ", Conductor= " + trabajoConductor.getUsuarioNombre()
                + ", Cliente= " + trabajoCliente + ", Camion= " + trabajoCamion + "} ";
    }
}
