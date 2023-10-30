package uniandes.edu.co.proyecto.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.proyecto.modelo.ClienteEntity;
import uniandes.edu.co.proyecto.modelo.ServicioEntity;
import uniandes.edu.co.proyecto.repositories.ClienteRepository.ResponseGoodClients;
import uniandes.edu.co.proyecto.repositories.ServicioRepository;
import uniandes.edu.co.proyecto.repositories.ServicioRepository.ResponseMasPopulares;

@Controller
public class ServiciosController {

    @Autowired
    private ServicioRepository servicioRepository;

    @GetMapping("/servicios/20_populares")
    public ResponseEntity<Collection<ResponseMasPopulares>> habitacionesDinero(Pageable pageable,
            @RequestParam("f_in") String f_in, @RequestParam("f_fin") String f_fin,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Integer offset) {

        Collection<ResponseMasPopulares> serPage = servicioRepository.dar20ServiciosMasPopulares(f_in, f_fin, size,
                offset);
        return new ResponseEntity<>(serPage, HttpStatus.OK);
    }

}
