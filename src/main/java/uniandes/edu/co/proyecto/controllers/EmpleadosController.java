package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.EmpleadoEntity;
import uniandes.edu.co.proyecto.repositories.EmpleadoRepository;

@Controller
public class EmpleadosController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping("/empleados")
    public String empleados(Model model){
        model.addAttribute("bares", empleadoRepository.darEmpleados());
        return "empleados";
    }

    @GetMapping("/empleados/new")
    public String empleadoForm(Model model){
        model.addAttribute("empleado", new EmpleadoEntity());
        return "empleadoNuevo";
    }

    @PostMapping("/empleados/new/save")
    public String empleadoGuardar(@ModelAttribute EmpleadoEntity empleado){
        empleadoRepository.insertarEmpleado(empleado.getNum_doc(), empleado.getNombre(), empleado.getEmail(), empleado.getTipo_doc(), empleado.getRol());
        return "redirect:/empleados";
    }


    @GetMapping("/empleados/{num_doc}/edit")
    public String empleadorEditarForm(@PathVariable("num_doc") int num_doc, Model model){
        EmpleadoEntity empleado = empleadoRepository.darEmpleado(num_doc);
        if(empleado != null){
            model.addAttribute("empleado", empleado);
            return "empleadoEditar";
        } else {
            return "redirect:/empleados";
        }
    }

    @PostMapping("/empleados/{num_doc}/edit/save")
    public String empleadoEditarGuardar(@PathVariable("num_doc") int num_doc, @ModelAttribute EmpleadoEntity empleado){
        empleadoRepository.actualizarEmpleado(num_doc, empleado.getNombre(), empleado.getEmail(), empleado.getTipo_doc(), empleado.getRol());
        return "redirect:/empleados";
    }
    
    @GetMapping("/empleados/{num_doc}/delete")
    public String empleadoEliminar(@PathVariable("num_doc") int num_doc){
        empleadoRepository.eliminarEmpleado(num_doc);
        return "redirect:/empleados";
    }
}
