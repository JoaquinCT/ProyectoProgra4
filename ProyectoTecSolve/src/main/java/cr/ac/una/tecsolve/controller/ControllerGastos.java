package cr.ac.una.tecsolve.controller;

import cr.ac.una.tecsolve.domain.Gasto;
import cr.ac.una.tecsolve.logic.LogicaGastos;
import jakarta.servlet.http.HttpServletRequest;
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
 * @author Juan Dolmus
 */
@Controller
@RequestMapping("/gastos")
public class ControllerGastos {

    LogicaGastos logicG = new LogicaGastos();

    @GetMapping("/paginacionGasto")
    public String paginacion() {
        return "paginacionGastos";
    }

    @GetMapping("/listaGastos")
    public String obtenerProductosPaginados(Model model, @RequestParam(name="pageNumber",defaultValue = "1") int numeroPagina, @RequestParam(name="pageSize", defaultValue = "1") int pageSize) {
                
        int numeroTotalGastos = logicG.getTotalGastos();
        int numeroTotalPaginas = (int) Math.ceil((double) numeroTotalGastos / pageSize);
        model.addAttribute("gastos", logicG.getListaGastosPorPaginacion(numeroPagina, pageSize));
        model.addAttribute("pageNumber", numeroPagina);
        model.addAttribute("totalPages", numeroTotalPaginas);
        model.addAttribute("pageSize", pageSize);
        return "listaGastos";
    }
    
    @GetMapping("/formGuardarGasto")
    public String formGuardarEmpleado(Model model) {
        model.addAttribute("gasto", new Gasto());
        return "formGasto";
    }

    @PostMapping("/saveGasto")
    public String guardarEmpleado(@ModelAttribute Gasto gasto, @RequestParam("categorias") String categSelected, HttpServletRequest request) {
        String paginaAnterior = request.getParameter("paginaAnterior");
        if (gasto.getId() == 0) {
            gasto.setCategoria(categSelected);
            logicG.insertarGasto(gasto);
            return "redirect:/gastos/listaGastos";
        } else {
            gasto.setCategoria(categSelected);
            logicG.actualizarGasto(gasto);
            return "redirect:"+paginaAnterior;
        }
    }

    @GetMapping("/editar/{id}")
    public String formEditGasto(@PathVariable int id, Model model, HttpServletRequest request) {
        String paginaAnterior = request.getHeader("referer");
        model.addAttribute("paginaAnterior", paginaAnterior);
        model.addAttribute("gasto", logicG.getGastoPorId(id));
        return "formGasto";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmpleado(@PathVariable int id) {
        logicG.eliminarGasto(id);
        return "redirect:/gastos/listaGastos";
    }
}
