package com.example.futbol.service;

import com.example.futbol.model.Entrenador;
import com.example.futbol.repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrenadorService {
    
    @Autowired
    private EntrenadorRepository entrenadorRepository;
    
    public List<Entrenador> findAll() {
        return entrenadorRepository.findAll();
    }
    
    public Optional<Entrenador> findById(Long id) {
        return entrenadorRepository.findById(id);
    }
    
    public Entrenador save(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }
    
    public void deleteById(Long id) {
        entrenadorRepository.deleteById(id);
    }
    
    public List<Entrenador> findByNombre(String nombre) {
        return entrenadorRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    public List<Entrenador> findByNacionalidad(String nacionalidad) {
        return entrenadorRepository.findByNacionalidad(nacionalidad);
    }
    
    public List<Entrenador> findEntrenadoresSinClub() {
        return entrenadorRepository.findEntrenadoresSinClub();
    }
    
    public List<Object[]> countEntrenadoresByNacionalidad() {
        return entrenadorRepository.countEntrenadoresByNacionalidad();
    }
}