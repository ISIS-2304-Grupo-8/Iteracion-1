package uniandes.edu.co.proyecto.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.proyecto.modelo.HabitacionEntity;
import uniandes.edu.co.proyecto.repositories.HabitacionRepository;
import uniandes.edu.co.proyecto.repositories.HabitacionRepository.RespuestaReq1;
import uniandes.edu.co.proyecto.repositories.HabitacionRepository.RespuestaOcupacion;


@Controller
public class HabitacionesController {

    @Autowired
    private HabitacionRepository habitacionRepository;


    @GetMapping("/habitaciones")
    public ResponseEntity<Page<HabitacionEntity>> habitaciones(Pageable pageable){
    Page<HabitacionEntity> habPage = habitacionRepository.findAll(pageable);
    return new ResponseEntity<>(habPage, HttpStatus.OK);
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
        habitacionRepository.insertarHabitacion(habitacion.getConsumo_acumulado(), habitacion.getDisponibilidad(), habitacion.getTipos_habitacion_id_tipo().getId_tipo());
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
        habitacionRepository.actualizarHabitacion(id_habitacion, habitacion.getConsumo_acumulado(), habitacion.getDisponibilidad(), habitacion.getTipos_habitacion_id_tipo().getId_tipo());
        return "redirect:/habitaciones";
    }

    @GetMapping("/habitaciones/{id_habitacion}/delete")
    public String habitacionEliminar(@PathVariable("id_habitacion") Integer id_habitacion){
        habitacionRepository.eliminarHabitacion(id_habitacion);
        return "redirect:/habitaciones";
    }

    // @GetMapping("/habitaciones/dinero_recolectado")
    // public String habitacionDineroRecolectado(Model model){
    //     Collection<RespuestaReq1> informacion = habitacionRepository.darDineroRecolectadoServiciosPorHabitacion();
    //     model.addAttribute("habitaciones", informacion.iterator().next().getID_HABITACION());
    //     model.addAttribute("dinero_recolectado", informacion.iterator().next().getDINERO_RECOLECTADO());
    //     return "habitaciones_dinero_recolectado";
    // }

    @GetMapping("/habitaciones/dinero_recolectado")
    public ResponseEntity<Collection<RespuestaReq1>> habitacionesDinero(Pageable pageable){
    Collection<RespuestaReq1> habPage = habitacionRepository.darDineroRecolectadoServiciosPorHabitacion();
    return new ResponseEntity<>(habPage, HttpStatus.OK);
    }

    @GetMapping("/habitaciones/indice_ocupacion")
    public ResponseEntity<Collection<RespuestaOcupacion>> obtenerIndiceOcupacion(@RequestParam(required = false) Integer size, @RequestParam(required = false) Integer offset) {
        System.out.println("EL tamaño escogido es: " + size);
        System.out.println("EL offset escogido es: " + offset);
        Collection<RespuestaOcupacion> resultado = habitacionRepository.obtenerIndiceOcupacionPorHabitacion(size, offset);
    return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    
}
