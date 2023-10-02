package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.ReservaEntity;
import uniandes.edu.co.proyecto.repositories.ReservasRepository;

@RestController
public class ReservasController {
    @Autowired
    private ReservasRepository reservasRepository;

    @GetMapping("/reservas")
    public String reservas(Model model) {
        model.addAttribute("reservas", reservasRepository.darReservas());
        return model.toString();
    }

    @GetMapping("/reservas/new")
    public String reservaForm(Model model) {
        model.addAttribute("reserva", new ReservaEntity());
        return "reservaNew";
    }

    @PostMapping("/reservas/new/save")
    public String reservaSave(@ModelAttribute ReservaEntity reserva) {
        reservasRepository.insertarReserva(reserva.getNum_personas(),
                reserva.getEstado_reserva().getPk().getFecha_inicial(),
                reserva.getEstado_reserva().getPk().getFecha_final(), reserva.getId_plan_consumo().getId_plan(),
                reserva.getClientes_num_doc().getNum_doc());
        return "redirect:/reservas";

    }

    @GetMapping("/reservas/{id}/edit")
    public String reservaEditarForm(@PathVariable("id_reserva") int id, Model model) {
        ReservaEntity reserva = reservasRepository.darReserva(id);
        if (reserva != null) {
            model.addAttribute("reserva", reserva);
            return "reservaEdit";
        } else {
            return "redirect:/reservas";
        }
    }

    @PostMapping("/reservas/{id}/edit/save")
    public String reservaEditarSave(@PathVariable("id_reserva") int id, @ModelAttribute ReservaEntity reserva) {
        reservasRepository.actualizarReserva(reserva.getId_reserva(), reserva.getNum_personas(),
                reserva.getEstado_reserva().getPk().getFecha_inicial(),
                reserva.getEstado_reserva().getPk().getFecha_final(), reserva.getId_plan_consumo().getId_plan(),
                reserva.getClientes_num_doc().getNum_doc());
        return "redirect:/reservas";

    }

    @GetMapping("/reservas/{id}/delete")
    public String reservaDelete(@PathVariable("id_reserva") int id) {
        reservasRepository.eliminarReserva(id);
        return "redirect:/reservas";
    }
}
