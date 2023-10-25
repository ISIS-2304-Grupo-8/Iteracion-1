package uniandes.edu.co.proyecto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping("/administradorDelSistema.html")
    public String admin() {
        return "administradorDelSistema";
    }

    @RequestMapping("/recepcionista.html")
    public String recepcionist() {
        return "recepcionista";
    }

    @RequestMapping("/administrador.html")
    public String administrador() {
        return "administrador";
    }

    @RequestMapping("/cliente.html")
    public String cliente() {
        return "cliente";
    }

    @RequestMapping("/gerente.html")
    public String gerente() {
        return "gerente";
    }
    

    //administrador.html" "recepcionista.html" "recepcionista.html"
}

