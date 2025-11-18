package com.example.futbol.service;

import com.example.futbol.model.Club;
import com.example.futbol.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubService {
    
    @Autowired
    private ClubRepository clubRepository;
    
    public List<Club> findAll() {
        return clubRepository.findAll();
    }
    
    public Optional<Club> findById(Long id) {
        return clubRepository.findById(id);
    }
    
    public Club save(Club club) {
        return clubRepository.save(club);
    }
    
    public void deleteById(Long id) {
        clubRepository.deleteById(id);
    }
    
    public List<Club> findByNombre(String nombre) {
        return clubRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    public List<Club> findByCiudad(String ciudad) {
        return clubRepository.findByCiudad(ciudad);
    }
    
    public List<Club> findByAsociacionId(Long asociacionId) {
        return clubRepository.findByAsociacionId(asociacionId);
    }
    
    public Optional<Club> findByEntrenadorId(Long entrenadorId) {
        return clubRepository.findByEntrenadorId(entrenadorId);
    }
    
    public List<Club> findClubesWithMoreThanJugadores(int minJugadores) {
        return clubRepository.findClubesWithMoreThanJugadores(minJugadores);
    }
    
    public List<Object[]> countClubesByCiudad() {
        return clubRepository.countClubesByCiudad();
    }
}