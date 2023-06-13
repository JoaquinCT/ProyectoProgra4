
/**
 *
 * @author kinco
 */
package cr.ac.una.tecsolve.controller;

import cr.ac.una.tecsolve.domain.Proveedor;
import cr.ac.una.tecsolve.logic.LogicaProveedor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/proveedores")
public class ControllerProveedor {

    LogicaProveedor logicaProveedor = new LogicaProveedor();

    @GetMapping("/listaProveedores")
    public String obtenerProveedores(Model model, @RequestParam(name="pageNumber",defaultValue = "1") int numeroPagina, @RequestParam(name="pageSize", defaultValue = "1") int pageSize) {
        
        int numeroTotalProveedores = logicaProveedor.getTotalProveedores();
        int numeroTotalPaginas = (int) Math.ceil((double) numeroTotalProveedores / pageSize);
        model.addAttribute("proveedores", logicaProveedor.getListaProveedoresPorPaginacion(numeroPagina, pageSize));
        model.addAttribute("pageNumber", numeroPagina);
        model.addAttribute("totalPages", numeroTotalPaginas);
        model.addAttribute("pageSize", pageSize);
        return "listarProveedor";
    }

    @GetMapping("/formGuardarProveedor")
    public String formGuardarProveedor(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "formProveedor";
    }

    @PostMapping("/saveProveedor")
    public String guardarProveedor(@ModelAttribute Proveedor proveedor, HttpServletRequest request) {
        String paginaAnterior = request.getParameter("paginaAnterior");
        if (proveedor.getId() == 0) {
            logicaProveedor.insertarProveedor(proveedor);
            return "redirect:/proveedores/listaProveedores";
        } else {
            logicaProveedor.actualizarProveedor(proveedor);
            return "redirect:"+paginaAnterior;
        }
        
    }

    @GetMapping("/editar/{id}")
    public String formEditarProveedor(@PathVariable int id, Model model, HttpServletRequest request) {
        String paginaAnterior = request.getHeader("referer");
        model.addAttribute("paginaAnterior", paginaAnterior);
        model.addAttribute("proveedor", logicaProveedor.getProveedorPorId(id));
        return "formProveedor";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProveedor(@PathVariable int id, HttpServletRequest request) {
        String paginaAnterior = request.getHeader("referer");
        logicaProveedor.eliminarProveedor(id);
        return "redirect:"+paginaAnterior;
    }
}
