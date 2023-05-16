
package cr.ac.una.tecsolve.controller;


import cr.ac.una.tecsolve.data.DataCliente;
import cr.ac.una.tecsolve.domain.Cliente;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/clientes")
public class ControllerCliente {
/*
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {

        return "indexCliente";
    }*/

    @GetMapping("/menuCliente")
    public String inicio() {

        return "indexCliente";
    }

    @GetMapping("/listar")
    public String getCliente(Model model, @RequestParam(name="pageNumber",defaultValue = "1") int numeroPagina, @RequestParam(name="pageSize", defaultValue = "1") int pageSize) {
        DataCliente dataC = new DataCliente();
        int numeroTotalClientes = dataC.getNumeroTotalclientes();
        int numeroTotalPaginas = (int) Math.ceil((double) numeroTotalClientes / pageSize);
        model.addAttribute("lista", dataC.getListaClientesPorPaginacion(numeroPagina, pageSize));
        model.addAttribute("pageNumber", numeroPagina);
        model.addAttribute("totalPages", numeroTotalPaginas);
        model.addAttribute("pageSize", pageSize);
        return "listacliente";

    }
    
    @GetMapping("/insertar")
    public String insertar(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "form_cliente";
    }

    @PostMapping("/guardar")
    public String guardarEspacio(@ModelAttribute Cliente cliente) {
        
        DataCliente dataC = new DataCliente();
       // System.out.println(cliente.getNombre());
        dataC.insertarCliente(cliente);
        return "redirect:/clientes/listar";
    }

//    @PostMapping(value="/guardar", params={"nombre","apellido","cedula","numero","correo"})
//    public String guardar(String nombre, String apellido, String cedula, int numero, String correo){
//        
//   
//       
//        DataCliente dataC = new DataCliente();
//        Cliente c = new
//        
//        dataC.insertarCliente(c);
//      
//      return "redirect:/listar";  
//    }
    @GetMapping(value = "/guardarEdit", params = {"id", "nombre", "apellido", "cedula", "telefono", "correo"})
    public String guardarEdit(int id, String nombre, String apellido, String cedula, String telefono, String correo) {

        DataCliente dataC = new DataCliente();
        Cliente c = new Cliente(id, nombre, apellido, cedula, telefono, correo);
        dataC.editarDatosCliente(c);

        return "redirect:/clientes/listar";
    }

    @GetMapping("/eliminar/{numero}")
    public String eliminar(@PathVariable int numero) {

        DataCliente dataC = new DataCliente();
        dataC.eliminarNumero(numero);

        return "redirect:/clientes/listar";

    }

    @GetMapping("/editar/{id}/{nombre}/{apellido}/{cedula}/{telefono}/{correo}")
    public String editar(@PathVariable int id, @PathVariable String nombre, @PathVariable String apellido, @PathVariable String cedula, @PathVariable String telefono, @PathVariable String correo, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("nombre", nombre);
        model.addAttribute("apellido", apellido);
        model.addAttribute("cedula", cedula);
        model.addAttribute("telefono", telefono);
        model.addAttribute("correo", correo);
        return "form_clienteEdit";
    }

}
