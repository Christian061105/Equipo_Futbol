package com.example.futbol.controller;

import com.example.futbol.model.Asociacion;
import com.example.futbol.service.AsociacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/asociaciones")
public class AsociacionController {
    
    @Autowired
    private AsociacionService asociacionService;
    
    // Listar todas las asociaciones
    @GetMapping
    public String listarAsociaciones(Model model) {
        List<Asociacion> asociaciones = asociacionService.findAll();
        
        // Calcular número de clubes antes de enviar a la vista
        asociaciones.forEach(a -> {
            try {
                if (a.getClubes() != null) {
                    a.setNumeroClubes(a.getClubes().size());
                } else {
                    a.setNumeroClubes(0);
                }
            } catch (Exception e) {
                a.setNumeroClubes(0);
            }
        });
        
        model.addAttribute("asociaciones", asociaciones);
        return "asociaciones/listar";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("asociacion", new Asociacion());
        return "asociaciones/formulario";
    }
    
    @PostMapping("/guardar")
    public String guardarAsociacion(@ModelAttribute Asociacion asociacion) {
        asociacionService.save(asociacion);
        return "redirect:/asociaciones";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Asociacion> asociacion = asociacionService.findById(id);
        if (asociacion.isPresent()) {
            model.addAttribute("asociacion", asociacion.get());
            return "asociaciones/formulario";
        }
        return "redirect:/asociaciones";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarAsociacion(@PathVariable Long id) {
        asociacionService.deleteById(id);
        return "redirect:/asociaciones";
    }
    
    @GetMapping("/ver/{id}")
    public String verAsociacion(@PathVariable Long id, Model model) {
        Optional<Asociacion> asociacion = asociacionService.findById(id);
        if (asociacion.isPresent()) {
            model.addAttribute("asociacion", asociacion.get());
            return "asociaciones/ver";
        }
        return "redirect:/asociaciones";
    }
}
