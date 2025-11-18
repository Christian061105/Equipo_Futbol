package com.example.futbol.service;

import com.example.futbol.model.Asociacion;
import com.example.futbol.repository.AsociacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsociacionService {
    
    @Autowired
    private AsociacionRepository asociacionRepository;
    
    public List<Asociacion> findAll() {
        return asociacionRepository.findAll();
    }
    
    public Optional<Asociacion> findById(Long id) {
        return asociacionRepository.findById(id);
    }
    
    public Asociacion save(Asociacion asociacion) {
        return asociacionRepository.save(asociacion);
    }
    
    public void deleteById(Long id) {
        asociacionRepository.deleteById(id);
    }
    
    public List<Asociacion> findByNombre(String nombre) {
        return asociacionRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    public List<Asociacion> findByPais(String pais) {
        return asociacionRepository.findByPais(pais);
    }
    
    public List<Object[]> countAsociacionesByPais() {
        return asociacionRepository.countAsociacionesByPais();
    }
}