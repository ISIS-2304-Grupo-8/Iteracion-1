package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.IncluyenEntity;
import uniandes.edu.co.proyecto.modelo.IncluyenEntityPK;
import uniandes.edu.co.proyecto.modelo.PlanDeConsumoEntity;
import uniandes.edu.co.proyecto.modelo.ProductoEntity;
import uniandes.edu.co.proyecto.repositories.IncluyenRepository;
import uniandes.edu.co.proyecto.repositories.PlanDeConsumoRepository;
import uniandes.edu.co.proyecto.repositories.ProductoRepository;

@RestController
public class IncluyenController {
    @Autowired
    private IncluyenRepository incluyenRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PlanDeConsumoRepository planDeConsumoRepository;

    @GetMapping("/incluyen/new")
    public String incluyenForm(Model model) {
        model.addAttribute("productos", productoRepository.darProductos());
        model.addAttribute("planes", planDeConsumoRepository.darPlanesDeConsumo());
        return "incluyenNew";
    }

    @PostMapping("/incluyen/new/save")
    public String incluyenSave(@ModelAttribute("prod_id") Integer id_producto,
            @ModelAttribute("tipo_servicio") String tipo_servicio, @ModelAttribute("id_plan") Integer id_plan) {

        ProductoEntity producto = productoRepository.darProducto(id_producto, tipo_servicio);
        PlanDeConsumoEntity plan = planDeConsumoRepository.darPlanDeConsumo(id_plan);
        IncluyenEntityPK pk = new IncluyenEntityPK(plan, producto);
        IncluyenEntity incluyen = new IncluyenEntity();
        incluyen.setPk(pk);

        incluyenRepository.crearIncluyen(incluyen.getPk().getProducto().getPk().getId_producto(),
                incluyen.getPk().getPlanesdeconsumo_id_plan().getId_plan(),
                incluyen.getPk().getProducto().getPk().getTipos_servicio_tipo_servicio().getTipo_servicio());
        return "redirect:/planesDeConsumo";
    }
}
