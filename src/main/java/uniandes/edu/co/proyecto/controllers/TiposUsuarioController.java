package uniandes.edu.co.proyecto.controllers;

import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.TipoUsuarioEntity;
import uniandes.edu.co.proyecto.repositories.TipoUsuarioRepository;

@RestController
public class TiposUsuarioController {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @GetMapping("/tiposusuario")
    public ResponseEntity<List<TipoUsuarioEntity>> tiposUsuario() {
    Collection<TipoUsuarioEntity> tiposUsuarioCollection = tipoUsuarioRepository.darTiposUsuario();
    List<TipoUsuarioEntity> tiposUsuario = new ArrayList<>(tiposUsuarioCollection);
    return new ResponseEntity<>(tiposUsuario, HttpStatus.OK);
}

    @GetMapping("/tiposusuario/{id_usuario}")
    public ResponseEntity<Object> tipoUsuario(@PathVariable("id_usuario") int id_usuario) {
        TipoUsuarioEntity tipoUsuario = tipoUsuarioRepository.darTipoUsuario(id_usuario);
        if (tipoUsuario != null) {
            return new ResponseEntity<>(Collections.singletonMap("tipoUsuario", tipoUsuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("mensaje", "Tipo de usuario no encontrado"),
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tiposusuario/new")
    public String tipoUsuarioForm(Model model) {
        model.addAttribute("tipousuario", new TipoUsuarioEntity());
        return "tipoUsuarioNew";
    }

    @PostMapping("/tiposusuario/new/save")
    public String tipoUsuarioSave(@ModelAttribute TipoUsuarioEntity tipoUsuario) {
        tipoUsuarioRepository.crearTipoUsuario(tipoUsuario.getRol());
        return "redirect:/tiposusuario";
    }

    @GetMapping("/tiposusuario/{id}/edit")
    public String tipoUsuarioEditarFrom(@PathVariable("id_usuario") Integer id_usuario, Model model) {
        TipoUsuarioEntity tipoUsuario = tipoUsuarioRepository.darTipoUsuario(id_usuario);
        if (tipoUsuario != null) {
            model.addAttribute("tipousuario", tipoUsuario);
            return "tipoUsuarioEdit";
        } else {
            return "redirect:/tiposusuario";
        }
    }

    @PostMapping("/tiposusuario/{id}/edit/save")
    public String tipoUsuarioEditarSave(@PathVariable("id_usuario") Integer id_usuario,
            @ModelAttribute TipoUsuarioEntity tipoUsuario) {
        tipoUsuarioRepository.actualizarTipoUsuario(tipoUsuario.getId_usuario(), tipoUsuario.getRol());
        return "redirect:/tiposusuario";
    }

    @GetMapping("/tiposusuario/{id}/delete")
    public String tipoUsuarioDelete(@PathVariable("id_usuario") Integer id_usuario) {
        tipoUsuarioRepository.eliminarTipoUsuario(id_usuario);
        return "redirect:/tiposusuario";
    }

}
