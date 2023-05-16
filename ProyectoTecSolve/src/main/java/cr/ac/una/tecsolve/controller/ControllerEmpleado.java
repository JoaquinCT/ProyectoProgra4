package cr.ac.una.tecsolve.controller;

import cr.ac.una.tecsolve.domain.Empleado;
import cr.ac.una.tecsolve.logic.LogicaEmpleado;
import jakarta.servlet.http.HttpSession;
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
 * @author Juan Dolmus
 */
@Controller
@RequestMapping("empleados")
public class ControllerEmpleado {

    LogicaEmpleado logicE = new LogicaEmpleado();

    @GetMapping("/")
    public String init() {
        return "index";
    }

//    @GetMapping("/dashboard")
//    public String dashboard(Authentication authentication, Model model) {
//        String username = authentication.getName();
//        model.addAttribute("username", username);
//        return "redirect:/empleados/listarEmpleados";
//    }
    
//    @GetMapping("/")
//    public String init() {
//        return "index";
//    }
     
    @PostMapping("/showMenuEmpleado")
    public String mostrarMenuEmpleado(HttpSession session, @RequestParam("tipoUsuario") String tipoUsuario, @RequestParam String username, @RequestParam String password, Model model){
        
        if(logicE.searchCredenciales(username, password, tipoUsuario).equalsIgnoreCase("admin")){
            session.setAttribute("username", username);
            return "redirect:/empleados/dashboard";
        }else{
            if(logicE.searchCredenciales(username, password, tipoUsuario).equalsIgnoreCase("empleado")){
                return "redirect:/";
            }else{
                return "redirect:/";
            }
            
        }
    }
  
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        // Obtener el nombre de usuario almacenado en la variable de sesión
        String username = (String) session.getAttribute("username");
        if (username == null) {
            // Si el nombre de usuario no está definido, redirigir al formulario de inicio de sesión
            return "redirect:/";
        }
        // Si el nombre de usuario está definido, pasar la información a la vista
        model.addAttribute("username", username);
        return "redirect:/empleados/listarEmpleados";
    }

    @GetMapping("/listarEmpleados")
    public String getEmpleados(Model model, @RequestParam(name="pageNumber",defaultValue = "1") int numeroPagina, @RequestParam(name="pageSize", defaultValue = "1") int pageSize) {
        
        int numeroTotalGastos = logicE.getTotalGastos();
        int numeroTotalPaginas = (int) Math.ceil((double) numeroTotalGastos / pageSize);
        LinkedList<Empleado> empleados = logicE.getListaEmpleadosPorPaginacion(numeroPagina, pageSize);
        System.out.println(pageSize);
        model.addAttribute("empleados", empleados);
        model.addAttribute("pageNumber", numeroPagina);
        model.addAttribute("totalPages", numeroTotalPaginas);
        model.addAttribute("pageSize", pageSize);
        return "listaEmpleados";
    }

    @GetMapping("/formGuardarEmpleado")
    public String formGuardarEmpleado(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "formEmpleado";
    }

    @PostMapping("/saveEmpleado")
    public String guardarEmpleado(@ModelAttribute Empleado empleado) {

        logicE.guardarEmpleado(empleado);
        return "redirect:/empleados/listarEmpleados";
    }

    @GetMapping("/editar/{id}")
    public String getEditarRegistro(@PathVariable int id, Model model) {
        model.addAttribute("empleado", logicE.listarEmpleadoPorID(id));
        return "formEditarEmpleado";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable int id, @ModelAttribute Empleado empleado) {

        logicE.updateEmpleado(empleado, id);

        return "redirect:/empleados/listarEmpleados";
    }
    
    @GetMapping("delete/{id}")
    public String deleteEmpleado(@PathVariable int id){
        logicE.eliminarEmpleado(id);
        return "redirect:/empleados/listarEmpleados";
    }
}
