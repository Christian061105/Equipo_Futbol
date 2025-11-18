package com.example.futbol.controller;

import com.example.futbol.model.Competicion;
import com.example.futbol.service.CompeticionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/competiciones")
public class CompeticionController {
    
    @Autowired
    private CompeticionService competicionService;
    
    // Listar todas las competiciones
    @GetMapping
    public String listarCompeticiones(Model model) {
        List<Competicion> competiciones = competicionService.findAll();
        model.addAttribute("competiciones", competiciones);
        return "competiciones/listar";  // ← CAMBIADO: "competiciones/listar" (plural)
    }
    
    // Mostrar formulario para crear nueva competición
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("competicion", new Competicion());
        return "competiciones/formulario";  // ← CAMBIADO: "competiciones/formulario" (plural)
    }
    
    // Guardar nueva competición
    @PostMapping("/guardar")
    public String guardarCompeticion(@ModelAttribute Competicion competicion) {
        competicionService.save(competicion);
        return "redirect:/competiciones";
    }
    
    // Mostrar formulario para editar competición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Competicion> competicion = competicionService.findById(id);
        if (competicion.isPresent()) {
            model.addAttribute("competicion", competicion.get());
            return "competiciones/formulario";  // ← CAMBIADO: "competiciones/formulario" (plural)
        }
        return "redirect:/competiciones";
    }
    
    // Eliminar competición
    @GetMapping("/eliminar/{id}")
    public String eliminarCompeticion(@PathVariable Long id) {
        competicionService.deleteById(id);
        return "redirect:/competiciones";
    }
    
    // Ver detalles de la competición
    @GetMapping("/ver/{id}")
    public String verCompeticion(@PathVariable Long id, Model model) {
        Optional<Competicion> competicion = competicionService.findById(id);
        if (competicion.isPresent()) {
            model.addAttribute("competicion", competicion.get());
            return "competiciones/ver";  // ← CAMBIADO: "competiciones/ver" (plural)
        }
        return "redirect:/competiciones";
    }
}