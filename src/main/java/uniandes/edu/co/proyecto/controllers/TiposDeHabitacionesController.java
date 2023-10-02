package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.proyecto.repositories.TipoHabitacionRepository;

@Controller
public class TiposDeHabitacionesController {
    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    @GetMapping("/tipos_de_habitaciones")
    public String Tipos_habitacion(Model model){
        model.addAttribute("Tipos_habitacion", tipoHabitacionRepository.darTiposDeHabitaciones());
    return "Tipos_habitacion";
    }
}
