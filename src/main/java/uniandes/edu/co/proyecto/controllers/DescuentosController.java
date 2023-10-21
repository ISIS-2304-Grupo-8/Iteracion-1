package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.DescuentoEntity;
import uniandes.edu.co.proyecto.repositories.DescuentoRepository;
import uniandes.edu.co.proyecto.repositories.TipoPlanRepository;

@RestController
public class DescuentosController {
    @Autowired
    private DescuentoRepository descuentoRepository;

    @Autowired
    private TipoPlanRepository tipoPlanRepository;

    @GetMapping("/descuentos")
    public String descuentos(Model model) {
        model.addAttribute("descuentos", descuentoRepository.darDescuentos());
        model.addAttribute("tipos_plan", tipoPlanRepository.darTiposPlan());
        return model.toString();
    }

    @GetMapping("/descuentos/new")
    public String descuentoForm(Model model) {
        model.addAttribute("descuento", new DescuentoEntity());
        return "descuentoNew";
    }

    @PostMapping("/descuentos/new/save")
    public String descuentoSave(@ModelAttribute DescuentoEntity descuento) {
        descuentoRepository.insertarDescuento(descuento.getPk().getServicio_descuentado(),
                descuento.getPk().getTipos_plan_tipo_plan().getTipo_plan(), descuento.getDescuento());
        return "redirect:/descuentos";
    }

    @GetMapping("/descuentos/{id}/edit")
    public String descuentoEdit(@PathVariable("servicio_descuentado") String servicio_descuentado,
            @PathVariable("tipo_plan") String tipo_plan, Model model) {
        DescuentoEntity descuento = descuentoRepository.darDescuento(servicio_descuentado, tipo_plan);
        if (descuento != null) {
            model.addAttribute("descuento", descuento);
            return "descuentoEdit";
        } else {
            return "redirect:/descuentos";
        }
    }

    @PostMapping("/descuentos/{id}/edit/save")
    public String descuentoUpdate(@PathVariable("servicio_descuentado") String servicio_descuentado,
            @PathVariable("tipo_plan") String tipo_plan, @ModelAttribute DescuentoEntity descuento) {
        descuentoRepository.actualizarDescuento(descuento.getPk().getServicio_descuentado(),
                descuento.getPk().getTipos_plan_tipo_plan().getTipo_plan(), descuento.getDescuento());
        return "redirect:/descuentos";
    }

    @GetMapping("/descuentos/{id}/delete")
    public String descuentoDelete(@PathVariable("servicio_descuentado") String servicio_descuentado,
            @PathVariable("tipo_plan") String tipo_plan) {
        descuentoRepository.eliminarDescuento(servicio_descuentado, tipo_plan);
        return "redirect:/descuentos";
    }
}
