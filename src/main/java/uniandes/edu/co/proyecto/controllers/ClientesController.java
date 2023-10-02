package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Collections;

import uniandes.edu.co.proyecto.modelo.ClienteEntity;
import uniandes.edu.co.proyecto.repositories.ClienteRepository;
import uniandes.edu.co.proyecto.repositories.ReservasRepository;

@Controller
public class ClientesController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ReservasRepository reservasRepository;

    @GetMapping("/clientes")
    public String clientes(Model model){
        model.addAttribute("clientes", clienteRepository.darClientes());
        return "clientes";
    }
    
    @GetMapping("/clientes/new")
    public String clienteForm(Model model){
        model.addAttribute("cliente", new ClienteEntity());
        model.addAttribute("reservas",reservasRepository.darReservas());
        return "clienteNuevo";
    }

    @PostMapping("/clientes/new/save")
    public String clienteGuardar(@ModelAttribute ClienteEntity cliente){
        clienteRepository.insertarCliente(cliente.getNum_doc(), cliente.getNombre(), cliente.getEmail(), cliente.getTipo_doc(), cliente.getRol_cliente(), cliente.getReserva().getId_reserva());
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/{num_doc}/edit")
    public String clienteEditarForm(@PathVariable("num_doc") int num_doc, Model model){
        ClienteEntity cliente = clienteRepository.darCliente(num_doc);
        if(cliente != null){
            model.addAttribute("cliente", cliente);
            model.addAttribute("reservas", reservasRepository.darReservas());
            return "clienteEditar";
        } else {
            return "redirect:/clientes";
        }
    }

    @PostMapping("/clientes/{num_doc}/edit/save")
    public String clienteEditarGuardar(@PathVariable("num_doc") int num_doc, @ModelAttribute ClienteEntity cliente){
        clienteRepository.actualizarCliente(num_doc, cliente.getNombre(), cliente.getEmail(), cliente.getTipo_doc(), cliente.getRol_cliente(), cliente.getReserva().getId_reserva());
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/{num_doc}/delete")
    public String clienteEliminar(@PathVariable("num_doc") int num_doc){
        clienteRepository.eliminarCliente(num_doc);
        return "redirect:/clientes";

    }

    @GetMapping("/clientes/{num_doc}")
    public ResponseEntity<Object> clienteDetalle(@PathVariable("num_doc") int num_doc) {
        ClienteEntity cliente = clienteRepository.darCliente(num_doc);
        if(cliente != null) {
            return new ResponseEntity<>(Collections.singletonMap("cliente", cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("mensaje", "Cliente no encontrado"), HttpStatus.NOT_FOUND);
        }
    }
}
