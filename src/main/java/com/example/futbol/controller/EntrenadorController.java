package com.example.futbol.controller;

import com.example.futbol.model.Entrenador;
import com.example.futbol.service.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/entrenadores")
public class EntrenadorController {
    
    @Autowired
    private EntrenadorService entrenadorService;
    
    // Listar todos los entrenadores
    @GetMapping
    public String listarEntrenadores(Model model) {
        List<Entrenador> entrenadores = entrenadorService.findAll();
        model.addAttribute("entrenadores", entrenadores);
        return "entrenadores/listar";  // ← CAMBIADO: "entrenadores/listar" (plural)
    }
    
    // Mostrar formulario para crear nuevo entrenador
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        return "entrenadores/formulario";  // ← CAMBIADO: "entrenadores/formulario" (plural)
    }
    
    // Guardar nuevo entrenador
    @PostMapping("/guardar")
    public String guardarEntrenador(@ModelAttribute Entrenador entrenador) {
        entrenadorService.save(entrenador);
        return "redirect:/entrenadores";
    }
    
    // Mostrar formulario para editar entrenador
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Entrenador> entrenador = entrenadorService.findById(id);
        if (entrenador.isPresent()) {
            model.addAttribute("entrenador", entrenador.get());
            return "entrenadores/formulario";  // ← CAMBIADO: "entrenadores/formulario" (plural)
        }
        return "redirect:/entrenadores";
    }
    
    // Eliminar entrenador
    @GetMapping("/eliminar/{id}")
    public String eliminarEntrenador(@PathVariable Long id) {
        entrenadorService.deleteById(id);
        return "redirect:/entrenadores";
    }
    
    // Ver detalles del entrenador
    @GetMapping("/ver/{id}")
    public String verEntrenador(@PathVariable Long id, Model model) {
        Optional<Entrenador> entrenador = entrenadorService.findById(id);
        if (entrenador.isPresent()) {
            model.addAttribute("entrenador", entrenador.get());
            return "entrenadores/ver";  // ← CAMBIADO: "entrenadores/ver" (plural)
        }
        return "redirect:/entrenadores";
    }
}