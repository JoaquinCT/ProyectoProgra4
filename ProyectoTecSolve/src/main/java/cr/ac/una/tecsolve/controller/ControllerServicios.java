/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.controller;

import com.example.proyecto.data.ServiciosData;
import com.example.proyecto.domain.Servicios;
import java.util.LinkedList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Usuario
 */
@Controller 
@RequestMapping("/Servicios")
public class ControllerServicios {
       @GetMapping 
    public String Servicios(Model model){
        
         LinkedList<Servicios> espacios = new ServiciosData().getEspacios();
        
        model.addAttribute("titulo","Servicios");
        model.addAttribute("nombre",espacios.getFirst());
        model.addAttribute("servicios",espacios);
             
        return "Servicios";
    }
     @GetMapping("/insertarServicios")
    public String guardarInventario(){
         
        return "insertarServicios";
    }
     @GetMapping ("/guardarServicio")
    public String guardarServicio(@RequestParam String nombreServicio,@RequestParam String descripcion,@RequestParam String horario,@RequestParam float precio,@RequestParam String encargado){
        
        Servicios servicio = new Servicios(nombreServicio,descripcion,horario,precio,encargado);
       
         new ServiciosData().insertar(servicio);
        return "redirect:/servicios";
    }
         @GetMapping("/eliminarServicio")
    public String eliminarServicio(@RequestParam int id, Model model){
        System.out.println("Eliminar id "+id);
    
      new ServiciosData().eliminar(id);
      return "redirect:/servicios";
    }
    
       @GetMapping("/actualizarServicio/{id}")
    public String mostrarDatosSER(@PathVariable int id, Model model){
        
        int numero =  id;
        LinkedList<Servicios> con = new LinkedList<Servicios>();
        ServiciosData dc = new ServiciosData();
        con = dc.mostrarDatos(numero);
        model.addAttribute("lista",con);
        
        return "./editarServicios";
    }
    
   @GetMapping("/actualizarServicio/ActualizarSer")
public String actualizarServicio(@RequestParam("id") int id, @RequestParam("nombreServicio") String nombreServicio, @RequestParam("descripcion") String descripcion, @RequestParam("horario") String horario,@RequestParam("precio")float precio ,@RequestParam("encargado") String encargado) {
    Servicios servicio = new Servicios(nombreServicio, descripcion,horario, precio, encargado);
   
    new ServiciosData().actualizar(servicio, id);
    return "redirect:/servicios";
}

///FILTRO PRUEBA 

@GetMapping("/BuscarServicio/{nombre}")
    public String buscarSER(@PathVariable String nombre, Model model){
        
        LinkedList<Servicios> con = new LinkedList<Servicios>();
        ServiciosData dc = new ServiciosData();
        con = dc.BuscarDatos(nombre);
        model.addAttribute("lista",con);
        
        return "./BuscarServicios";
    }

}
