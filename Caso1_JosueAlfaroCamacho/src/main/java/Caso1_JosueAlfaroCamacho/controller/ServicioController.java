/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Caso1_JosueAlfaroCamacho.controller;

import Caso1_JosueAlfaroCamacho.domain.Servicio;
import Caso1_JosueAlfaroCamacho.service.CategoriaService;
import Caso1_JosueAlfaroCamacho.service.ServicioService;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
@Controller
@RequestMapping("/servicio")
public class ServicioController {
 
    @Autowired
    private ServicioService servicioService;
 
    @Autowired
    private CategoriaService categoriaService;
 
    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("servicios", servicioService.getServicios());
        model.addAttribute("totalServicios", servicioService.getServicios().size());
        model.addAttribute("categorias", categoriaService.getCategorias());
        return "servicio/listado";
    }
 
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("servicio", new Servicio());
        model.addAttribute("categorias", categoriaService.getCategorias());
        return "servicio/modifica";
    }
 
    @PostMapping("/guardar")
    public String guardar(@Valid Servicio servicio, RedirectAttributes ra) {
        try {
            servicioService.guardar(servicio);
            ra.addFlashAttribute("todoOk", "Servicio guardado correctamente.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/servicio/listado";
    }
 
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        Optional<Servicio> opt = servicioService.getServicio(id);
        if (opt.isEmpty()) {
            ra.addFlashAttribute("error", "Servicio no encontrado.");
            return "redirect:/servicio/listado";
        }
        model.addAttribute("servicio", opt.get());
        model.addAttribute("categorias", categoriaService.getCategorias());
        return "servicio/modifica";
    }
 
    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            servicioService.eliminar(id);
            ra.addFlashAttribute("todoOk", "Servicio eliminado correctamente.");
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("error", "El servicio no existe.");
        } catch (IllegalStateException e) {
            ra.addFlashAttribute("error", "No se puede eliminar. Tiene reservas asociadas.");
        }
        return "redirect:/servicio/listado";
    }
}