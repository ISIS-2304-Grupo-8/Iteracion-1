package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.ReservaEntity;
import uniandes.edu.co.proyecto.repositories.ClienteRepository;
import uniandes.edu.co.proyecto.repositories.Estado_reservaRepository;
import uniandes.edu.co.proyecto.repositories.PlanDeConsumoRepository;
import uniandes.edu.co.proyecto.repositories.ReservasRepository;

@Controller
public class ReservasController {

    @Autowired
    private ReservasRepository reservasRepository;

    @Autowired
    private Estado_reservaRepository estado_reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PlanDeConsumoRepository planDeConsumoRepository;

    @GetMapping("/reservas")
    public String reservas(Model model){
        model.addAttribute("reserva", reservasRepository.darReservas());
        model.addAttribute("estados_reservas", estado_reservaRepository.darEstados_reserva());
        model.addAttribute("clientes", clienteRepository.darClientes());
        model.addAttribute("planes_de_consumo", planDeConsumoRepository.darPlanesDeConsumo());
        return "reservas";

    }

    @GetMapping("/reservas/new")
    public String reservasForm(Model model){
        model.addAttribute("reserva", new ReservaEntity());
        model.addAttribute("estados_reservas", estado_reservaRepository.darEstados_reserva());
        model.addAttribute("clientes", clienteRepository.darClientes());
        model.addAttribute("planes_de_consumo", planDeConsumoRepository.darPlanesDeConsumo());
        return "reservaNuevo";

    }

    @GetMapping("/reservas/{id_reserva}/edit")
    public String reservaEditarForm(@PathVariable("id_reserva") int id_reserva, Model model){
        ReservaEntity reserva = reservasRepository.darReserva(id_reserva);
        if(reserva != null){
            model.addAttribute("reserva", reserva);
            model.addAttribute("estados_reservas", estado_reservaRepository.darEstados_reserva());
            model.addAttribute("clientes", clienteRepository.darClientes());
            model.addAttribute("planes_de_consumo", planDeConsumoRepository.darPlanesDeConsumo());
            return "reservaEditar";

        } else {
            return "redirect:/reservas";
        }
    }

    //@PostMapping("/reservas/{id_reserva}/edit/save")
    //public String reservaEditarGuardar(@PathVariable("id_reserva") int id_reserva, @ModelAttribute ReservaEntity reserva){
        //reservasRepository.actualizarReserva(id_reserva, reserva.getNum_personas(), reserva.getEstado_reserva().getPk().getFecha_inicial(), reserva.getEstado_reserva().getPk().getFecha_final(), reserva.getId_plan_consumo(), reserva.getNum_personas());
    //}

    
    //@PostMapping("/reservas/{id_reserva}/delete")


    
}
