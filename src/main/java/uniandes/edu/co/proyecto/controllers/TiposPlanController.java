package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.TipoPlanEntity;
import uniandes.edu.co.proyecto.repositories.TipoPlanRepository;

@RestController
public class TiposPlanController {
    @Autowired
    private TipoPlanRepository tipoPlanRepository;

    @GetMapping("/tipos_planes")
    public String tiposPlan(Model model) {
        model.addAttribute("tipos_planes", tipoPlanRepository.darTiposPlan());
        return model.toString();
    }

    @GetMapping("/tipos_planes/new")
    public String tiposPlanForm(Model model) {
        model.addAttribute("tipo_plan", new TipoPlanEntity());
        return "tipos_plan_new";
    }

    @PostMapping("/tipos_planes/new/save")
    public String tiposPlanSave(@ModelAttribute TipoPlanEntity tipoPlanEntity) {
        tipoPlanRepository.crearTipoPlan(tipoPlanEntity.getTipo_plan(), tipoPlanEntity.getVigencia());
        return "redirect:/tipos_plan";
    }

    @GetMapping("/tipos_planes/{tipo_plan}/edit")
    public String tiposPlanEditarForm(@PathVariable("tipo_plan") String tipo_plan, Model model) {
        TipoPlanEntity tipoPlan = tipoPlanRepository.darTipoPlan(tipo_plan);
        if (tipoPlan != null) {
            model.addAttribute("tipo_plan", tipoPlan);
            return "tipo_plan_editar";
        } else {
            return "redirect:/tipos_plan";

        }

    }

    @PostMapping("/tipos_plan/{tipo_plan}/edit/save")
    public String tiposPlanEditarSave(@PathVariable("tipo_plan") String tipo_plan,
            @ModelAttribute TipoPlanEntity tipoPlanEntity) {
        tipoPlanRepository.actualizarTipoPlan(tipo_plan, tipoPlanEntity.getVigencia());
        return "redirect:/tipos_plan";
    }

    @GetMapping("/tipos_plan/{tipo_plan}/delete")
    public String tiposPlanDelete(@PathVariable("tipo_plan") String tipo_plan) {
        tipoPlanRepository.eliminarTipoPlan(tipo_plan);
        return "redirect:/tipos_plan";
    }

}
