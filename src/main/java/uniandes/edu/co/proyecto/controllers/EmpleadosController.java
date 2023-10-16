package uniandes.edu.co.proyecto.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import uniandes.edu.co.proyecto.modelo.EmpleadoEntity;
import uniandes.edu.co.proyecto.repositories.EmpleadoRepository;

@Controller
public class EmpleadosController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping("/empleados")
    public ResponseEntity<List<EmpleadoEntity>> empleados(){
        Collection<EmpleadoEntity> empleadosCollection = empleadoRepository.darEmpleados();
        List<EmpleadoEntity> empleados = new ArrayList<>(empleadosCollection);
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    // @GetMapping("/empleados/new")
    // public String empleadoForm(Model model){
    //     model.addAttribute("empleado", new EmpleadoEntity());
    //     return "empleadoNuevo";
    // }

    // @PostMapping("/empleados/new/save")
    // public String empleadoGuardar(@ModelAttribute EmpleadoEntity empleado){
    //     empleadoRepository.insertarEmpleado(empleado.getNum_doc(), empleado.getNombre(), empleado.getEmail(), empleado.getTipo_doc(), empleado.getRol());
    //     return "redirect:/empleados";
    // }

    //test for creating new 'empleado'
    @PostMapping("/empleados/new")
    public ResponseEntity<Map<String, String>> createEmpleado(@RequestBody EmpleadoEntity empleado) {
    Map<String, String> response = new HashMap<>();
    try {
        empleadoRepository.insertarEmpleado(empleado.getNum_doc(), empleado.getNombre(),
                empleado.getEmail(), empleado.getTipo_doc(), empleado.getRol());
        response.put("message", "Empleado created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    } catch (Exception e) {
        response.put("message", "Failed to create empleado");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

     @GetMapping("/empleados/{num_doc}")
    public ResponseEntity<Object> empleadoDetalle(@PathVariable("num_doc") int num_doc){
        EmpleadoEntity empleado = empleadoRepository.darEmpleado(num_doc);
        if(empleado != null){
            return new ResponseEntity<>(Collections.singletonMap("empleado", empleado), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("mensaje", "Empleado no encontrado"), HttpStatus.NOT_FOUND);
        }
}

}
