package com.example.colegiohectorsm;

public class Alumno {
    private String nombre, apellidos, nia, curso;

    public Alumno() {
    }

    public Alumno(String nombre, String apellidos, String nia, String curso) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nia = nia;
        this.curso = curso;
    }

    //Desmonta el fromato en el que se almaceno en un txt
    public boolean separarMeterTodo(String dato) {
        String lista[] = dato.split(";");
        if (lista.length == 4) {
            this.setTodo(lista[0], lista[1], lista[2], lista[3]);
            return true;
        }
        return false;
    }

    //Introduce todos los datos de golpe
    public void setTodo(String nombre, String apellidos, String nia, String curso) {
        this.setNombre(nombre);
        this.setApellidos(apellidos);
        this.setNia(nia);
        this.setCurso(curso);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNia() {
        return nia;
    }

    public void setNia(String nia) {
        this.nia = nia;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return nombre + ";" + apellidos + ";" + nia + ";" + curso;
    }
}
