/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Caso1_JosueAlfaroCamacho.controller;


import Caso1_JosueAlfaroCamacho.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping("/")
    public String mostrarInicio(Model modelo) {
        modelo.addAttribute("servicios", servicioService.getServicios());
        return "index";
    }
}