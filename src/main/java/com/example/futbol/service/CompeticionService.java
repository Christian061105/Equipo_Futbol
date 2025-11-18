package com.example.futbol.service;

import com.example.futbol.model.Competicion;
import com.example.futbol.repository.CompeticionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompeticionService {
    
    @Autowired
    private CompeticionRepository competicionRepository;
    
    public List<Competicion> findAll() {
        return competicionRepository.findAll();
    }
    
    public Optional<Competicion> findById(Long id) {
        return competicionRepository.findById(id);
    }
    
    public Competicion save(Competicion competicion) {
        return competicionRepository.save(competicion);
    }
    
    public void deleteById(Long id) {
        competicionRepository.deleteById(id);
    }
    
    public List<Competicion> findByNombre(String nombre) {
        return competicionRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    public List<Competicion> findByMontoPremioGreaterThan(int monto) {
        return competicionRepository.findByMontoPremioGreaterThan(monto);
    }
    
    public List<Competicion> findByOrderByMontoPremioDesc() {
        return competicionRepository.findByOrderByMontoPremioDesc();
    }
}