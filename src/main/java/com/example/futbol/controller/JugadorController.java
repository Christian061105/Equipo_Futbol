package com.example.futbol.controller;

import com.example.futbol.model.Jugador;
import com.example.futbol.model.Club;
import com.example.futbol.service.JugadorService;
import com.example.futbol.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/jugadores")
public class JugadorController {
    
    @Autowired
    private JugadorService jugadorService;
    
    @Autowired
    private ClubService clubService;
    
    // Listar todos los jugadores
    @GetMapping
    public String listarJugadores(Model model) {
        List<Jugador> jugadores = jugadorService.findAll();
        model.addAttribute("jugadores", jugadores);
        return "jugadores/listar";  // ← CAMBIADO: "jugadores/listar" (plural)
    }
    
    // Mostrar formulario para crear nuevo jugador
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("jugador", new Jugador());
        model.addAttribute("clubes", clubService.findAll());
        return "jugadores/formulario";  // ← CAMBIADO: "jugadores/formulario" (plural)
    }
    
    // Guardar nuevo jugador
    @PostMapping("/guardar")
    public String guardarJugador(@ModelAttribute Jugador jugador) {
        jugadorService.save(jugador);
        return "redirect:/jugadores";
    }
    
    // Mostrar formulario para editar jugador
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Jugador> jugador = jugadorService.findById(id);
        if (jugador.isPresent()) {
            model.addAttribute("jugador", jugador.get());
            model.addAttribute("clubes", clubService.findAll());
            return "jugadores/formulario";  // ← CAMBIADO: "jugadores/formulario" (plural)
        }
        return "redirect:/jugadores";
    }
    
    // Eliminar jugador
    @GetMapping("/eliminar/{id}")
    public String eliminarJugador(@PathVariable Long id) {
        jugadorService.deleteById(id);
        return "redirect:/jugadores";
    }
    
    // Ver detalles del jugador
    @GetMapping("/ver/{id}")
    public String verJugador(@PathVariable Long id, Model model) {
        Optional<Jugador> jugador = jugadorService.findById(id);
        if (jugador.isPresent()) {
            model.addAttribute("jugador", jugador.get());
            return "jugadores/ver";  // ← CAMBIADO: "jugadores/ver" (plural)
        }
        return "redirect:/jugadores";
    }
}