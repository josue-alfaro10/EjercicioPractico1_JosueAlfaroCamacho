/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Caso1_JosueAlfaroCamacho.controller;

import Caso1_JosueAlfaroCamacho.domain.Categoria;
import Caso1_JosueAlfaroCamacho.service.CategoriaService;
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
@RequestMapping("/categoria")
public class CategoriaController {
 
    @Autowired
    private CategoriaService categoriaService;
 
    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("categorias", categoriaService.getCategorias());
        model.addAttribute("totalCategorias", categoriaService.getCategorias().size());
        return "categoria/listado";
    }
 
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria/modifica";
    }
 
    @PostMapping("/guardar")
    public String guardar(@Valid Categoria categoria, RedirectAttributes ra) {
        try {
            categoriaService.guardar(categoria);
            ra.addFlashAttribute("todoOk", "Categoría guardada correctamente.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/categoria/listado";
    }
 
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        Optional<Categoria> opt = categoriaService.getCategoria(id);
        if (opt.isEmpty()) {
            ra.addFlashAttribute("error", "Categoría no encontrada.");
            return "redirect:/categoria/listado";
        }
        model.addAttribute("categoria", opt.get());
        return "categoria/modifica";
    }
 
    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            categoriaService.eliminar(id);
            ra.addFlashAttribute("todoOk", "Categoría eliminada correctamente.");
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("error", "La categoría no existe.");
        } catch (IllegalStateException e) {
            ra.addFlashAttribute("error", "No se puede eliminar. Tiene servicios asociados.");
        }
        return "redirect:/categoria/listado";
    }
}
