package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "camiones")
public class Camion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_camion")
    private Integer camionId;

    @Column(name = "nombre_camion", nullable = false, length = 45)
    private String camionNombre;

    @Column(name = "matricula", nullable = false, length = 45)
    private String camionMatricula;

    public Camion() {
    }

    public Camion(String camionNombre, String camionMatricula) {
        this.camionNombre = camionNombre;
        this.camionMatricula = camionMatricula;
    }

    public Integer getCamionId() {
        return camionId;
    }

    public void setCamionId(Integer camionId) {
        this.camionId = camionId;
    }

    public String getCamionNombre() {
        return camionNombre;
    }

    public void setCamionNombre(String camionNombre) {
        this.camionNombre = camionNombre;
    }

    public String getCamionMatricula() {
        return camionMatricula;
    }

    public void setCamionMatricula(String camionMatricula) {
        this.camionMatricula = camionMatricula;
    }

    @Override
    public String toString() {
        return "Camion{ ID= " + camionId + ", Nombre= " + camionNombre + ", Matricula= " + camionMatricula + "} ";
    }
}
