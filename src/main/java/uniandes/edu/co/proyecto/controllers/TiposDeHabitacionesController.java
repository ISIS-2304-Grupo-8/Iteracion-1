package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.TipoHabitacionEntity;
import uniandes.edu.co.proyecto.repositories.TipoHabitacionRepository;

@RestController
public class TiposDeHabitacionesController {
    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    @GetMapping("/tipos_de_habitaciones")
    public String Tipos_habitacion(Model model){
        model.addAttribute("Tipos_habitacion", tipoHabitacionRepository.darTiposDeHabitaciones());
    return model.toString();
    }

    @GetMapping("/tipos_de_habitaciones/new")
    public String Tipo_habitacionForm(Model model){
        model.addAttribute("Tipo_habitacion", new TipoHabitacionEntity());
    return "Tipo_habitacionNuevo";
    }

    @PostMapping("/tipos_de_habitaciones/new/save")
    public String Tipo_habitacionGuardar(@ModelAttribute TipoHabitacionEntity Tipo_habitacion){
        tipoHabitacionRepository.insertarTipoDeHabitacion(Tipo_habitacion.getCosto(),
        Tipo_habitacion.getCapacidad(), Tipo_habitacion.getDescripcion(), Tipo_habitacion.getTipo());
    return "redirect:/tipos_de_habitaciones";
    }

    @GetMapping("/tipos_de_habitaciones/{id}/edit")
    public String Tipo_habitacionEditarForm(@PathVariable("id") int id, Model model){
        TipoHabitacionEntity tipoHabitacionEntity = tipoHabitacionRepository.darTipoDeHabitacion(id);
        if(tipoHabitacionEntity != null){
            model.addAttribute("Tipo_habitacion", tipoHabitacionEntity);
        } else {
            return "redirect:/tipos_de_habitaciones";
        }
    return "Tipo_habitacionEditar";
    }

    @PostMapping("/tipos_de_habitaciones/{id}/edit")
    public String Tipo_habitacionEditarGuardar(@PathVariable("id") int id, @ModelAttribute TipoHabitacionEntity tipoHabitacionEntity){
        tipoHabitacionRepository.actualizarTipoDeHabitacion(id, tipoHabitacionEntity.getCosto(), tipoHabitacionEntity.getCapacidad(),
         tipoHabitacionEntity.getDescripcion(), tipoHabitacionEntity.getTipo());
        return "redirect:/tipos_de_habitaciones";
    }

    @GetMapping("/tipos_de_habitaciones/{id}/delete")
    public String Tipo_habitacionEliminar(@PathVariable("id") int id){
        tipoHabitacionRepository.eliminarTipoDeHabitacion(id);
        return "redirect:/tipos_de_habitaciones";
    }
}
