package uniandes.edu.co.proyecto.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import uniandes.edu.co.proyecto.modelo.ClienteEntity;
import uniandes.edu.co.proyecto.modelo.EmpleadoEntity;
import uniandes.edu.co.proyecto.repositories.ClienteRepository;
import uniandes.edu.co.proyecto.repositories.ReservasRepository;
import uniandes.edu.co.proyecto.repositories.ServicioRepository.ResponseMasPopulares;
import uniandes.edu.co.proyecto.repositories.ClienteRepository.ResponseGoodClients;

@Controller
public class ClientesController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ReservasRepository reservasRepository;

    //@GetMapping("/clientes")
    //public String clientes(Model model){
    //    model.addAttribute("clientes", clienteRepository.darClientes());
    //    return "clientes";
    //}

    @GetMapping("/clientes")
    public ResponseEntity<Page<ClienteEntity>> clientes(Pageable pageable){
    Page<ClienteEntity> clientesPage = clienteRepository.findAll(pageable);
    return new ResponseEntity<>(clientesPage, HttpStatus.OK);
    }

    //test for creating new 'empleado'
    @PostMapping("/clientes/new")
    public ResponseEntity<Map<String, String>> createCliente(@RequestBody ClienteEntity cliente) {
    Map<String, String> response = new HashMap<>();
    try {
        clienteRepository.insertarCliente(cliente.getNum_doc(), cliente.getNombre(),
                cliente.getEmail(), cliente.getTipo_doc(), cliente.getRol_cliente().getId_usuario());
        response.put("message", "Cliente created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    } catch (Exception e) {
        response.put("message", "Failed to create Cliente");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // @GetMapping("/clientes/new")
    // public String clienteForm(Model model){
    //     model.addAttribute("cliente", new ClienteEntity());
    //     model.addAttribute("reservas",reservasRepository.darReservas());
    //     return "clienteNuevo";
    // }

    // @PostMapping("/clientes/new/save")
    // public String clienteGuardar(@ModelAttribute ClienteEntity cliente){
    //     clienteRepository.insertarCliente(cliente.getNum_doc(), cliente.getNombre(), cliente.getEmail(), cliente.getTipo_doc(), cliente.getRol_cliente().getId_usuario());
    //     return "redirect:/clientes";
    // }

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
        clienteRepository.actualizarCliente(num_doc, cliente.getNombre(), cliente.getEmail(), cliente.getTipo_doc(), cliente.getRol_cliente().getId_usuario());
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

    @GetMapping("/clientes/buenos_clientes")
    public ResponseEntity<Collection<ResponseGoodClients>> buenosClientes() {
        Collection<ResponseGoodClients> buenosClientesPage = clienteRepository.darBuenosClientes();
        return new ResponseEntity<>(buenosClientesPage, HttpStatus.OK);
    }
}
