package cr.ac.una.tecsolve.controller;

import cr.ac.una.tecsolve.data.DataFactura;
import cr.ac.una.tecsolve.domain.Factura;
import cr.ac.una.tecsolve.logic.LogicaFactura;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/facturas")
public class ControllerFactura {

    LogicaFactura logicaFactura = new LogicaFactura();

    @GetMapping("/listaFacturas")
    public String obtenerFacturas(Model model, @RequestParam(name = "pageNumber", defaultValue = "1") int numeroPagina, @RequestParam(name = "pageSize", defaultValue = "1") int pageSize) {

        int numeroTotalFacturas = logicaFactura.getNumeroTotalFacturas();
        int numeroTotalPaginas = (int) Math.ceil((double) numeroTotalFacturas / pageSize);
        model.addAttribute("facturas", logicaFactura.getListaFacturasPorPaginacion(numeroPagina, pageSize));
        model.addAttribute("pageNumber", numeroPagina);
        model.addAttribute("totalPages", numeroTotalPaginas);
        model.addAttribute("pageSize", pageSize);
        return "listaFacturas";
    }

    @GetMapping("/guardarFactura")
    public String formGuardarFactura(Model model) {
        model.addAttribute("factura", new Factura());
        return "formFactura";
    }

    @PostMapping("/saveFactura")
    public String guardarFactura(@Validated @ModelAttribute Factura factura, HttpServletRequest request) {
        String paginaAnterior = request.getParameter("paginaAnterior");
        if (factura.getId() == 0) {
            logicaFactura.insertarFactura(factura);
            return "redirect:/facturas/listaFacturas";
        } else {
            logicaFactura.actualizarFactura(factura);
            return "redirect:" + paginaAnterior;
        }

    }

    @GetMapping("/editar/{id}")
    public String formEditarFactura(@PathVariable int id, Model model, HttpServletRequest request) {
        String paginaAnterior = request.getHeader("referer");
        model.addAttribute("paginaAnterior", paginaAnterior);
        model.addAttribute("factura", logicaFactura.getFacturaPorId(id));
        return "formFactura";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarFactura(@PathVariable int id, HttpServletRequest request) {
        String paginaAnterior = request.getHeader("referer");
        logicaFactura.eliminarFactura(id);
        return "redirect:" + paginaAnterior;
    }
    
     @GetMapping("/BuscarFactura/{nombre}")
    public String buscarFactura(@PathVariable String nombre, Model model) {

        LinkedList<Factura> con = new LinkedList<Factura>();
         DataFactura dc = new DataFactura();
        con = dc.buscarDatos(nombre);
        model.addAttribute("facturas", con);

        return "./BuscarFactura";
    }
}
