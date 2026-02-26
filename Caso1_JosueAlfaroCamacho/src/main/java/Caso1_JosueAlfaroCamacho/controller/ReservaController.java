/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Caso1_JosueAlfaroCamacho.controller;

import Caso1_JosueAlfaroCamacho.domain.Reserva;
import Caso1_JosueAlfaroCamacho.service.ReservaService;
import Caso1_JosueAlfaroCamacho.service.ServicioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
 

@Controller
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ServicioService servicioService;

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("reservas", reservaService.getReservas());
        return "reserva/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("servicios", servicioService.getServicios());
        return "reserva/modifica";
    }

    @PostMapping("/guardar")
    public String guardar(Reserva reserva) {
        reservaService.guardar(reserva);
        return "redirect:/reserva/listado";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Integer id, Model model) {
        Reserva reserva = reservaService.getReserva(id).orElse(null);
        model.addAttribute("reserva", reserva);
        model.addAttribute("servicios", servicioService.getServicios());
        return "reserva/modifica";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer id) {
        reservaService.eliminar(id);
        return "redirect:/reserva/listado";
    }
}
