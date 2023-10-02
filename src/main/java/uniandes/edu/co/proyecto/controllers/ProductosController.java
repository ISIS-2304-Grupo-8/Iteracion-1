package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import oracle.jdbc.proxy.annotation.Post;
import uniandes.edu.co.proyecto.modelo.ProductoEntity;
import uniandes.edu.co.proyecto.repositories.ProductoRepository;

@Controller
public class ProductosController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/productos")
    public String productos(Model model) {
        model.addAttribute("productos", productoRepository.darProductos());
        return "productos";
    }

    @GetMapping("/productos/new")
    public String productoForm(Model model) {
        model.addAttribute("producto", new ProductoEntity());
        return "productoNew";
    }

    @PostMapping("/productos/new/save")
    public String productoSave(@ModelAttribute ProductoEntity producto) {
        productoRepository.crearProducto(producto.getNombre(), producto.getCosto(),
                producto.getPk().getTipos_servicio_tipo_servicio().getTipo_servicio());
        return "redirect:/productos";
    }

    @GetMapping("/productos/{id}/edit")
    public String prductoEditarFrom(@PathVariable("id_producto") Integer id_producto,
            @PathVariable("tipo_servicio") String tipo_servicio, Model model) {
        ProductoEntity producto = productoRepository.darProducto(id_producto, tipo_servicio);
        if (producto != null) {
            model.addAttribute("producto", producto);
            return "productoEdit";
        } else {
            return "redirect:/productos";
        }
    }

    @PostMapping("/productos/{id}/edit/save")
    public String productoEditarSave(@PathVariable("id_producto") Integer id_producto,
            @PathVariable("tipo_servicio") String tipo_servicio, @ModelAttribute ProductoEntity producto) {
        productoRepository.actualizarProducto(producto.getPk().getId_producto(), producto.getNombre(),
                producto.getCosto(), producto.getPk().getTipos_servicio_tipo_servicio().getTipo_servicio());
        return "redirect:/productos";
    }

    @GetMapping("/productos/{id}/delete")
    public String productoDelete(@PathVariable("id_producto") Integer id_producto,
            @PathVariable("tipo_servicio") String tipo_servicio) {
        productoRepository.eliminarProducto(id_producto, tipo_servicio);
        return "redirect:/productos";
    }
}
