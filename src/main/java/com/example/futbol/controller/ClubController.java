package com.example.futbol.controller;

import com.example.futbol.model.Club;
import java.util.Map;
import java.util.HashMap;
import com.example.futbol.model.Jugador;
import com.example.futbol.service.ClubService;
import com.example.futbol.service.JugadorService;
import com.example.futbol.service.EntrenadorService;
import com.example.futbol.service.AsociacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clubes")
public class ClubController {
    
    @Autowired
    private ClubService clubService;
    
    @Autowired
    private JugadorService jugadorService;
    
    @Autowired
    private EntrenadorService entrenadorService;
    
    @Autowired
    private AsociacionService asociacionService;
    
    // ✅ RUTA CORRECTA: /clubes
    @GetMapping
    public String listarClubes(Model model) {
        List<Club> clubes = clubService.findAll();

        // Mapa para guardar la cantidad de jugadores de cada club
        Map<Long, Integer> cantidadJugadores = new HashMap<>();

        for (Club club : clubes) {
            // Usa el servicio de jugadores para contar cuántos tiene cada club
            List<Jugador> jugadores = jugadorService.findByClubId(club.getId());
            cantidadJugadores.put(club.getId(), jugadores.size());
        }

        model.addAttribute("clubes", clubes);
        model.addAttribute("cantidadJugadores", cantidadJugadores);

        return "club/listar";
    }

    
    // ✅ RUTA CORRECTA: /clubes/nuevo
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("club", new Club());
        model.addAttribute("entrenadores", entrenadorService.findAll());
        model.addAttribute("asociaciones", asociacionService.findAll());
        return "club/formulario";
    }
    
    // ✅ RUTA CORRECTA: /clubes/guardar
    @PostMapping("/guardar")
    public String guardarClub(@ModelAttribute Club club) {
        // Si el entrenador ya existe, lo recuperamos del repositorio
        if (club.getEntrenador() != null && club.getEntrenador().getId() != null) {
            club.setEntrenador(entrenadorService.findById(club.getEntrenador().getId()).orElse(null));
        } else {
            club.setEntrenador(null);
        }

        // Si la asociación ya existe, también la traemos
        if (club.getAsociacion() != null && club.getAsociacion().getId() != null) {
            club.setAsociacion(asociacionService.findById(club.getAsociacion().getId()).orElse(null));
        } else {
            club.setAsociacion(null);
        }

        // Guardamos el club
        clubService.save(club);

        return "redirect:/clubes";
    }


    
    // ✅ RUTA CORRECTA: /clubes/editar/{id}
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Club> club = clubService.findById(id);
        if (club.isPresent()) {
            model.addAttribute("club", club.get());
            model.addAttribute("entrenadores", entrenadorService.findAll());
            model.addAttribute("asociaciones", asociacionService.findAll());
            return "club/formulario";
        }
        return "redirect:/clubes";
    }
    
    // ✅ RUTA CORRECTA: /clubes/eliminar/{id}
    @GetMapping("/eliminar/{id}")
    public String eliminarClub(@PathVariable Long id) {
        clubService.deleteById(id);
        return "redirect:/clubes";
    }
    
    // ✅ RUTA CORRECTA: /clubes/ver/{id}
    @GetMapping("/ver/{id}")
    public String verClub(@PathVariable Long id, Model model) {
        Optional<Club> club = clubService.findById(id);
        if (club.isPresent()) {
            model.addAttribute("club", club.get());
            List<Jugador> jugadores = jugadorService.findByClubId(id);
            model.addAttribute("jugadores", jugadores);
            return "club/ver";
        }
        return "redirect:/clubes";
    }
}