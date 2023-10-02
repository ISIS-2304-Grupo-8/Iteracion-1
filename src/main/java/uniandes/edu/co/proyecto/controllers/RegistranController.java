package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.ClienteEntity;
import uniandes.edu.co.proyecto.modelo.EmpleadoEntity;
import uniandes.edu.co.proyecto.modelo.Registran;
import uniandes.edu.co.proyecto.modelo.RegistranPK;
import uniandes.edu.co.proyecto.repositories.ClienteRepository;
import uniandes.edu.co.proyecto.repositories.EmpleadoRepository;
import uniandes.edu.co.proyecto.repositories.RegistranRepository;

@Controller
public class RegistranController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RegistranRepository registranRepository;

    @GetMapping("/registran/new")
    public String registranForm(Model model){
        model.addAttribute("empleados", empleadoRepository.darEmpleados());
        model.addAttribute("clientes", clienteRepository.darClientes());
        return "registranNuevo";
    }

    @PostMapping("/registran/new/save")
    public String registranGuardar(@ModelAttribute("empleados_num_doc") int num_doc_empleado, @ModelAttribute("clientes_num_doc") int num_doc_cliente){
        EmpleadoEntity empleado =  empleadoRepository.darEmpleado(num_doc_empleado);
        ClienteEntity cliente = clienteRepository.darCliente(num_doc_cliente);
        RegistranPK pk = new RegistranPK(cliente, empleado);
        Registran registran =  new Registran();
        registran.setPk(pk);
        registranRepository.insertarRegistran(registran.getPk().getId_cliente().getNum_doc(), registran.getPk().getId_empleado().getNum_doc(), 0);
        return "redirect:/empleados";
    }
    
}
