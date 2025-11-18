package com.example.futbol.service;

import com.example.futbol.model.Jugador;
import com.example.futbol.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorService {
    
    @Autowired
    private JugadorRepository jugadorRepository;
    
    public List<Jugador> findAll() {
        return jugadorRepository.findAll();
    }
    
    public Optional<Jugador> findById(Long id) {
        return jugadorRepository.findById(id);
    }
    
    public Jugador save(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }
    
    public void deleteById(Long id) {
        jugadorRepository.deleteById(id);
    }
    
    public List<Jugador> findByClubId(Long clubId) {
        return jugadorRepository.findByClubId(clubId);
    }
    
    public List<Jugador> findByPosicion(String posicion) {
        return jugadorRepository.findByPosicion(posicion);
    }
    
    public List<Jugador> findByNombreOrApellido(String texto) {
        return jugadorRepository.findByNombreOrApellidoContaining(texto);
    }
    
    public List<Jugador> findJugadoresSinClub() {
        return jugadorRepository.findJugadoresSinClub();
    }
    
    public List<Object[]> countJugadoresByPosicion() {
        return jugadorRepository.countJugadoresByPosicion();
    }
}