/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.controller;

import com.example.proyecto.data.InventarioData;
import com.example.proyecto.domain.Inventario;
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
@RequestMapping("/Inventario")
public class ControllerInventario {
     @GetMapping 
    public String index(Model model){
         LinkedList<Inventario> espacios = new InventarioData().getEspacios();
        
        model.addAttribute("titulo","Inventario");
        model.addAttribute("nombre",espacios.getFirst());
        model.addAttribute("inventario",espacios);
             
        return "inventarioT";
    }
    
     
     @GetMapping("/insertarInventario")
    public String guardar(){
         
        return "insertarInventario";
    }
     @GetMapping ("/guardarInventario")
    public String guardarInventario(@RequestParam String categoria,@RequestParam String clasificacion,@RequestParam String nombreProducto,@RequestParam int cantidad,@RequestParam float precio){
        
        Inventario inventario = new Inventario(categoria,clasificacion,nombreProducto,cantidad,precio);
       
         new InventarioData().insertar(inventario);
        return "redirect:/inventario";
    }
    

       @GetMapping("/eliminarInventario")
    public String eliminar(@RequestParam int id, Model model){
        System.out.println("Eliminar id "+id);
    
      new InventarioData().eliminar(id);
      return "redirect:/inventario";
    }

    
      @GetMapping("/actualizarInventario/{id}")
    public String mostrarDatos(@PathVariable int id, Model model){
        
        int numero =  id;
        LinkedList<Inventario> con = new LinkedList<Inventario>();
        InventarioData dc = new InventarioData();
        con = dc.mostrarDatos(numero);
        model.addAttribute("lista",con);
        
        return "./editarInventario";
    }
    
   @GetMapping("/actualizarInventario/ActualizarInventario")
public String actualizarInventario(@RequestParam("id") int id, @RequestParam("clasificacion") String clasificacion, @RequestParam("categoria") String categoria, @RequestParam("nombreProducto") String nombreProducto,@RequestParam("cantidad") int cantidad,@RequestParam("precio") float precio) {
    Inventario inventario = new Inventario(categoria, clasificacion,nombreProducto, cantidad, precio);
   
    new InventarioData().actualizar(inventario, id);
    return "redirect:/inventario";
}
 
}
