/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.domain;

import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class Servicios {
     private int id;
    private String nombreServicio;
    private String descripcion;
    private String horario;
    private Float precio;
    private String encargado;
    private LinkedList Servicios;

    
    public Servicios() {
    }

    public Servicios(LinkedList Servicios) {
        this.Servicios = Servicios;
    }

    public Servicios(String nombreServicio, String descripcion, String horario, Float precio, String encargado) {
        this.id=id;
        this.nombreServicio = nombreServicio;
        this.descripcion = descripcion;
        this.horario = horario;
        this.precio = precio;
        this.encargado = encargado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }
}
