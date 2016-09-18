package com.example.manuel.miniproyecto;

/**
 * @author Manuel on 28/12/2015.
 */
public class Semanal {

    private String imagen;
    private String nombre;
    private String dia_semana;
    private String fecha;
    private String temp_max;
    private String temp_min;
    private String descripcion;

    public Semanal(String nombre) {
        this.nombre = nombre;
    }

    public Semanal(String imagen, String dia_semana, String fecha, String temp_max, String temp_min) {
        this.imagen = imagen;
        this.dia_semana = dia_semana;
        this.fecha = fecha;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
    }

    public Semanal(String imagen, String dia_semana, String fecha, String temp_max, String temp_min, String descripcion) {
        this.imagen = imagen;
        this.dia_semana = dia_semana;
        this.fecha = fecha;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
        this.descripcion = descripcion;
    }


    public String getDia_semana() {
        return dia_semana;
    }

    public void setDia_semana(String dia_semana) {
        this.dia_semana = dia_semana;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}