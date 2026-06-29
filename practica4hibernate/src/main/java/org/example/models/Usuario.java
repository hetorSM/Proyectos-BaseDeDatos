package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer usuarioId;

    @Column(name = "nombre_usuario", nullable = false, length = 45)
    private String usuarioNombre;

    @Column(name = "password", nullable = false, length = 45)
    private String usuarioPassword;

    @Column(name = "permiso", nullable = false, length = 45)
    private String usuarioPermiso;

    public Usuario() {
    }

    public Usuario(String usuarioNombre, String usuarioPassword, String usuarioPermiso) {
        this.usuarioNombre = usuarioNombre;
        this.usuarioPassword = usuarioPassword;
        this.usuarioPermiso = usuarioPermiso;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getUsuarioPassword() {
        return usuarioPassword;
    }

    public void setUsuarioPassword(String usuarioPassword) {
        this.usuarioPassword = usuarioPassword;
    }

    public String getUsuarioPermiso() {
        return usuarioPermiso;
    }

    public void setUsuarioPermiso(String usuarioPermiso) {
        this.usuarioPermiso = usuarioPermiso;
    }

    @Override
    public String toString() {
        return "Usuario{ ID=" + usuarioId + ", Nombre= " + usuarioNombre
                + ", Password= " + usuarioPassword + ", Permiso= " + usuarioPermiso + "} ";
    }
}
