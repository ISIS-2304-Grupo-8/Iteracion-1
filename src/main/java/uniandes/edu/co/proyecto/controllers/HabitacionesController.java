package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.HabitacionEntity;
import uniandes.edu.co.proyecto.repositories.HabitacionRepository;

public class HabitacionesController {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @GetMapping("/habitaciones")
    public String habitaciones(Model model){
        model.addAttribute("habitaciones", habitacionRepository.darHabitaciones());
        return "habitaciones";
    }

   @GetMapping("/habitaciones/{id_habitacion}")
    public String habitacion(Model model, @PathVariable("id_habitacion") Integer id_habitacion){
        HabitacionEntity habitacion = habitacionRepository.darHabitacion(id_habitacion);
        if(habitacion != null){
            model.addAttribute("habitacion", habitacion);
            return "habitacion";
        } else {
            return "redirect:/habitaciones";
        }
    }


    @GetMapping("/habitaciones/new")
    public String habitacionForm(Model model){
        model.addAttribute("habitacion", new HabitacionEntity());
        return "habitacion_new";
    }

    @PostMapping("/habitaciones/new/save")
    public String habitacionGuardar(@ModelAttribute HabitacionEntity habitacion){
        habitacionRepository.insertarHabitacion(habitacion.getConsumo_acumulado(), habitacion.getDisponibilidad(), habitacion.getReservas_id_reserva().getId_reserva(), habitacion.getTipos_habitacion_id_tipo().getId_tipo());
        return "redirect:/habitaciones";
    }

    @GetMapping("/habitaciones/{id_habitacion}/edit")
    public String habitacionEditar(Model model, @PathVariable("id_habitacion") Integer id_habitacion){
        HabitacionEntity habitacion = habitacionRepository.darHabitacion(id_habitacion);
        if(habitacion != null){
            model.addAttribute("habitacion", habitacion);
            return "habitacion_edit";
        } else {
            return "redirect:/habitaciones";
        }
    }

    @PostMapping("/habitaciones/{id_habitacion}/edit/save")
    public String habitacionEditarGuardar(@PathVariable("id_habitacion") Integer id_habitacion, @ModelAttribute HabitacionEntity habitacion){
        habitacionRepository.actualizarHabitacion(id_habitacion, habitacion.getConsumo_acumulado(), habitacion.getDisponibilidad(), habitacion.getReservas_id_reserva().getId_reserva(), habitacion.getTipos_habitacion_id_tipo().getId_tipo());
        return "redirect:/habitaciones";
    }

    @GetMapping("/habitaciones/{id_habitacion}/delete")
    public String habitacionEliminar(@PathVariable("id_habitacion") Integer id_habitacion){
        habitacionRepository.eliminarHabitacion(id_habitacion);
        return "redirect:/habitaciones";
    }

    @GetMapping("/habitaciones/dinero_recolectado")
    public String habitacionDineroRecolectado(Model model){
        model.addAttribute("habitaciones", habitacionRepository.darDineroRecolectadoServiciosPorHabitacion());
        return "habitaciones_dinero_recolectado";
    }
    
}
