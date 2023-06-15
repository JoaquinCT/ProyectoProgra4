package cr.ac.una.tecsolve.controller;

import cr.ac.una.tecsolve.data.DataContacto;
import cr.ac.una.tecsolve.domain.Contacto;
import cr.ac.una.tecsolve.logic.LogicaContactos;
import cr.ac.una.tecsolve.service.IContactoService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Maizeth Cisneros
 */
@Controller
@RequestMapping("/contactos")
public class ControllerContactos {

    LogicaContactos logicG = new LogicaContactos();

    @Autowired
    IContactoService service;

    @GetMapping("/paginacionContacto")
    public String paginacion() {
        return "paginacionContactos";
    }

    @GetMapping("/listaContactos")
    public String obtenerContactosPaginados(Model model, @RequestParam(name = "pageNumber", defaultValue = "1") int numeroPagina, @RequestParam(name = "pageSize", defaultValue = "1") int pageSize) {

        int numeroTotalContactos = logicG.getTotalContactos();
        int numeroTotalPaginas = (int) Math.ceil((double) numeroTotalContactos / pageSize);
        model.addAttribute("contactos", logicG.getListaContactosPorPaginacion(numeroPagina, pageSize));
        model.addAttribute("pageNumber", numeroPagina);
        model.addAttribute("totalPages", numeroTotalPaginas);
        model.addAttribute("pageSize", pageSize);
        return "listaContactos";
    }

    @GetMapping("/formGuardarContacto")
    public String formGuardarContacto(Model model) {
        model.addAttribute("contacto", new Contacto());
        return "formContacto";
    }

    @PostMapping("/saveContacto")
    public String guardarContacto(@ModelAttribute Contacto contacto, HttpServletRequest request) {
        String paginaAnterior = request.getParameter("paginaAnterior");
        if (contacto.getId() == 0) {
            //contacto.setNumeroWhatsapp(numeroWhaSelected);
            //logicG.insertarContacto(contacto);
            System.out.println("entro");
            service.save(contacto);
            return "redirect:/contactos/listaContactos";
        } else {
            //contacto.setNumeroTelefono(numeroTelSelected);
            service.save(contacto);
            return "redirect:" + paginaAnterior;
        }
    }

    @GetMapping("/editar/{id}")
    public String formEditContacto(@PathVariable int id, Model model, HttpServletRequest request) {
        String paginaAnterior = request.getHeader("referer");
        model.addAttribute("paginaAnterior", paginaAnterior);
        model.addAttribute("contacto", service.findById(id));
        System.out.println(service.findById(id).getNumeroWhatsapp());
        return "formContacto";
    }


    @GetMapping("/delete/{id}")
    public String deleteContacto(@PathVariable int id, HttpServletRequest request) {
        String paginaAnterior = request.getHeader("referer");
        logicG.eliminarContacto(id);
        return "redirect:"+paginaAnterior;
    }
    
    @GetMapping("/BuscarServicio/{nombre}")
    public String buscarSER(@PathVariable int nombre, Model model) {

        LinkedList<Contacto> con = new LinkedList<Contacto>();
        DataContacto dc = new DataContacto();
        con = dc.BuscarDatos(nombre);
        model.addAttribute("contactos", con);

        return "./BuscarContacto";
    }
}
