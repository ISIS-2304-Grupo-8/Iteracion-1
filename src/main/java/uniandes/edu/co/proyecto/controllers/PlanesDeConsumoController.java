package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.PlanDeConsumoEntity;
import uniandes.edu.co.proyecto.repositories.PlanDeConsumoRepository;

@RestController
public class PlanesDeConsumoController {

    @Autowired
    private PlanDeConsumoRepository planesDeConsumoRepository;

    @GetMapping("/planes_de_consumo")
    public String planesDeConsumo(Model model) {
        model.addAttribute("planes_de_consumo", planesDeConsumoRepository.darPlanesDeConsumo());
        return model.toString();
    }

    @GetMapping("/planes_de_consumo/new")
    public String planDeConsumoForm(Model model) {
        model.addAttribute("plan_de_consumo", new PlanDeConsumoEntity());
        return "plan_de_consumo_new";
    }

    @PostMapping("/planes_de_consumo/new/save")
    public String planDeConsumoSave(@ModelAttribute PlanDeConsumoEntity planDeConsumoEntity) {
        planesDeConsumoRepository.crearPlanDeConsumo(planDeConsumoEntity.getNombre(),
                planDeConsumoEntity.getDescripcion(), planDeConsumoEntity.getTipos_plan_tipo_plan().getTipo_plan());
        return "redirect:/planes_de_consumo";
    }

    @GetMapping("/planes_de_consumo/{id_plan}/edit")
    public String planDeConsumoEditarForm(@PathVariable("id_plan") Integer id, Model model) {
        PlanDeConsumoEntity planDeConsumo = planesDeConsumoRepository.darPlanDeConsumo(id);
        if (planDeConsumo != null) {
            model.addAttribute("plan_de_consumo", planDeConsumo);
            return "plan_de_consumo_editar";
        } else {
            return "redirect:/planes_de_consumo";

        }

    }

    @PostMapping("/planes_de_consumo/{id_plan}/edit/save")
    public String planDeConsumoEditarSave(@PathVariable("id_plan") Integer id,
            @ModelAttribute PlanDeConsumoEntity planDeConsumoEntity) {
        planesDeConsumoRepository.actualizarPlanDeConsumo(id, planDeConsumoEntity.getNombre(),
                planDeConsumoEntity.getDescripcion());
        return "redirect:/planes_de_consumo";
    }

    @GetMapping("/planes_de_consumo/{id_plan}/delete")
    public String planDeConsumoDelete(@PathVariable("id_plan") Integer id) {
        planesDeConsumoRepository.eliminarPlanDeConsumo(id);
        return "redirect:/planes_de_consumo";
    }

}
