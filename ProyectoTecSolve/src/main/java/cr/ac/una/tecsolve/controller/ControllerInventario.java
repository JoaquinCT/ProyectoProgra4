package cr.ac.una.tecsolve.controller;

import cr.ac.una.tecsolve.data.InventarioData;
import cr.ac.una.tecsolve.data.ServiciosData;
import cr.ac.una.tecsolve.domain.Inventario;
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
@RequestMapping("/Inventario")
public class ControllerInventario {

    @GetMapping
    public String index(Model model, @RequestParam(name="pageNumber",defaultValue = "1") int numeroPagina, @RequestParam(name="pageSize", defaultValue = "1") int pageSize) {
        
        int numeroTotalGastos = new InventarioData().getNumeroTotalInventarios();
        int numeroTotalPaginas = (int) Math.ceil((double) numeroTotalGastos / pageSize);
        LinkedList<Inventario> espacios = new InventarioData().getListaInventariosPorPaginacion(numeroPagina, pageSize);
        model.addAttribute("inventario", espacios);
        model.addAttribute("pageNumber", numeroPagina);
        model.addAttribute("totalPages", numeroTotalPaginas);
        model.addAttribute("pageSize", pageSize);
        return "inventarioT";
    }
    
    
    
    

    @GetMapping("/insertarInventario")
    public String guardar() {

        return "insertarInventario";
    }

    @GetMapping("/guardarInventario")
    public String guardarInventario(@RequestParam String categoria, @RequestParam String clasificacion, @RequestParam String nombreProducto, @RequestParam int cantidad, @RequestParam float precio) {

        Inventario inventario = new Inventario(categoria, clasificacion, nombreProducto, cantidad, precio);

        new InventarioData().insertar(inventario);
        return "redirect:/Inventario";
    }

    @GetMapping("/eliminarInventario")
    public String eliminar(@RequestParam int id, HttpServletRequest request) {
        String paginaAnterior = request.getHeader("referer");
        new InventarioData().eliminar(id);
        return "redirect:"+paginaAnterior;
    }

    @GetMapping("/actualizarInventario/{id}")
    public String mostrarDatos(@PathVariable int id, Model model, HttpServletRequest request) {
        
        String paginaAnterior = request.getHeader("referer");
        model.addAttribute("paginaAnterior", paginaAnterior);
        int numero = id;
        LinkedList<Inventario> con = new LinkedList<Inventario>();
        InventarioData dc = new InventarioData();
        con = dc.mostrarDatos(numero);
        model.addAttribute("lista", con);

        return "./editarInventario";
    }

    @GetMapping("/ActualizarInventario")
    public String actualizarInventario(HttpServletRequest request, @RequestParam("id") int id, @RequestParam("clasificacion") String clasificacion, @RequestParam("categoria") String categoria, @RequestParam("nombreProducto") String nombreProducto, @RequestParam("cantidad") int cantidad, @RequestParam("precio") float precio) {
        String paginaAnterior = request.getParameter("paginaAnterior");
        Inventario inventario = new Inventario(categoria, clasificacion, nombreProducto, cantidad, precio);
        new InventarioData().actualizar(inventario, id);
        return "redirect:"+paginaAnterior;
    }
    
    //Filtro 
     @GetMapping("/BuscarServicio/{nombre}")
    public String buscarSER(@PathVariable String nombre, Model model) {

        LinkedList<Inventario> con = new LinkedList<Inventario>();
        InventarioData dc = new InventarioData();
        con = dc.BuscarDatos(nombre);
        model.addAttribute("lista", con);

        return "./BuscarInventario";
    }
}
