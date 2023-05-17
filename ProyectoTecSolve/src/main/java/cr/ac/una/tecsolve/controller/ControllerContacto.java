/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tecsolve.controller;

import cr.ac.una.proyecto.data.DataContacto;
import cr.ac.una.proyecto.domain.Contacto;
import java.sql.Date;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ControllerContacto {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {

        return "indexContacto";
    }

    @GetMapping("/")
    public String inicio() {

        return "indexContacto";
    }

    @GetMapping("/listar")
    public String getContacto(Model model) {
        DataContacto dataCon = new DataContacto();
        model.addAttribute("lista", dataCon.listarContactos());
        return "listacontacto";

    }
    
    @GetMapping("/insertar")
    public String insertar(Model model) {
        model.addAttribute("contacto", new Contacto());
        return "form_contacto";
    }

    @PostMapping("/guardar")
    public String guardarEspacio(@ModelAttribute Contacto contacto) {
        
        DataContacto dataCon = new DataContacto();
        dataCon.insertarContacto(contacto);
        return "redirect:/listar";
    }

    @GetMapping(value = "/guardarEdit", params = {"id", "numeroWhatsapp", "numeroTelefono", "facebook", "correo", "instagram"})
    public String guardarEdit(int id, int numeroWhatsapp, int numeroTelefono, String facebook, String correo, String instagram) {

        DataContacto dataCon = new DataContacto();
        contacto con = new Contacto(id, numeroWhatsapp, numeroTelefono, facebook, correo, instagram);
        dataCon.editarDatosContacto(con);

        return "redirect:/listar";
    }

    @GetMapping("/eliminar/{numero}")
    public String eliminar(@PathVariable int numero) {

        DataContacto dataCon = new DataContacto();
        dataCon.eliminarNumero(numero);

        return "redirect:/listar";

    }

    @GetMapping("/editar/{id}/{numeroWhatsapp}/{numeroTelefono}/{facebook}/{correo}/{instagram}")
    public String editar(@PathVariable int id, @PathVariable int numeroWhatsapp, @PathVariable int numeroTelefono, @PathVariable String facebook, @PathVariable String correo, @PathVariable String instagram, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("numeroWhatsapp", numeroWhatsapp);
        model.addAttribute("numeroTelefono", numeroTelefono);
        model.addAttribute("facebook", facebook);
        model.addAttribute("correo", correo);
        model.addAttribute("instagram", instagram);
        return "form_ContactoEdit";
    }

}
