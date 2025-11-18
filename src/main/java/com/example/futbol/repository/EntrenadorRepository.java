package com.example.futbol.repository;

import com.example.futbol.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {
    
    // Buscar entrenadores por nombre (ignorando mayúsculas/minúsculas)
    List<Entrenador> findByNombreContainingIgnoreCase(String nombre);
    
    // Buscar entrenadores por apellido (ignorando mayúsculas/minúsculas)
    List<Entrenador> findByApellidoContainingIgnoreCase(String apellido);
    
    // Buscar entrenadores por nacionalidad
    List<Entrenador> findByNacionalidad(String nacionalidad);
    
    // Buscar entrenadores por rango de edad
    List<Entrenador> findByEdadBetween(int edadMin, int edadMax);
    
    // Buscar entrenadores sin club
    @Query("SELECT e FROM Entrenador e WHERE e.club IS NULL")
    List<Entrenador> findEntrenadoresSinClub();
    
    // Buscar entrenadores por nombre o apellido
    @Query("SELECT e FROM Entrenador e WHERE LOWER(e.nombre) LIKE LOWER(CONCAT('%', :texto, '%')) OR LOWER(e.apellido) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<Entrenador> findByNombreOrApellidoContaining(@Param("texto") String texto);
    
    // Contar entrenadores por nacionalidad
    @Query("SELECT e.nacionalidad, COUNT(e) FROM Entrenador e WHERE e.nacionalidad IS NOT NULL GROUP BY e.nacionalidad")
    List<Object[]> countEntrenadoresByNacionalidad();
}