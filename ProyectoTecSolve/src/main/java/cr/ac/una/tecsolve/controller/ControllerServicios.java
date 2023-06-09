package cr.ac.una.tecsolve.controller;

import cr.ac.una.tecsolve.data.ServiciosData;
import cr.ac.una.tecsolve.domain.Servicios;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/servicios")
public class ControllerServicios {

    @GetMapping
    public String Servicios(Model model, @RequestParam(name="pageNumber",defaultValue = "1") int numeroPagina, @RequestParam(name="pageSize", defaultValue = "2") int pageSize) {
        int numeroTotalGastos = new ServiciosData().getNumeroTotalServicios();
        int numeroTotalPaginas = (int) Math.ceil((double) numeroTotalGastos / pageSize);
        LinkedList<Servicios> espacios = new ServiciosData().getListaServiciosPorPaginacion(numeroPagina, pageSize);
        model.addAttribute("pageNumber", numeroPagina);
        model.addAttribute("totalPages", numeroTotalPaginas);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("servicios", espacios);

        return "Servicios";
    }

    @GetMapping("/insertarServicios")
    public String guardarInventario() {

        return "insertarServicios";
    }

    @GetMapping("/guardarServicio")
    public String guardarServicio(@RequestParam String nombreServicio, @RequestParam String descripcion, @RequestParam String horario, @RequestParam float precio, @RequestParam String encargado) {

        Servicios servicio = new Servicios(nombreServicio, descripcion, horario, precio, encargado);

        new ServiciosData().insertar(servicio);
        return "redirect:/servicios";
    }

    @GetMapping("/eliminarServicio")
    public String eliminarServicio(@RequestParam int id, HttpServletRequest request) {
        String paginaAnterior = request.getHeader("referer");
        new ServiciosData().eliminar(id);
        return "redirect:"+paginaAnterior;
    }

    @GetMapping("/actualizarServicio/{id}")
    public String mostrarDatosSER(@PathVariable int id, Model model, HttpServletRequest request) {
        String paginaAnterior = request.getHeader("referer");
        model.addAttribute("paginaAnterior", paginaAnterior);
        int numero = id;
        LinkedList<Servicios> con = new LinkedList<Servicios>();
        ServiciosData dc = new ServiciosData();
        con = dc.mostrarDatos(numero);
        model.addAttribute("lista", con);

        return "./editarServicios";
    }

    @GetMapping("/ActualizarSer")
    public String actualizarServicio(HttpServletRequest request, @RequestParam("id") int id, @RequestParam("nombreServicio") String nombreServicio, @RequestParam("descripcion") String descripcion, @RequestParam("horario") String horario, @RequestParam("precio") float precio, @RequestParam("encargado") String encargado) {
        String paginaAnterior = request.getParameter("paginaAnterior");
        Servicios servicio = new Servicios(nombreServicio, descripcion, horario, precio, encargado);
        new ServiciosData().actualizar(servicio, id);
        return "redirect:"+paginaAnterior;
    }

///FILTRO PRUEBA 
    @GetMapping("/BuscarServicio/{nombre}")
    public String buscarSER(@PathVariable String nombre, Model model) {

        LinkedList<Servicios> con = new LinkedList<Servicios>();
        ServiciosData dc = new ServiciosData();
        con = dc.BuscarDatos(nombre);
        model.addAttribute("lista", con);

        return "./BuscarServicios";
    }

}
