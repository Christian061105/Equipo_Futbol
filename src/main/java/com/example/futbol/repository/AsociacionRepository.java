package com.example.futbol.repository;

import com.example.futbol.model.Asociacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsociacionRepository extends JpaRepository<Asociacion, Long> {
    
    // Buscar asociaciones por nombre (ignorando mayúsculas/minúsculas)
    List<Asociacion> findByNombreContainingIgnoreCase(String nombre);
    
    // Buscar asociaciones por país
    List<Asociacion> findByPais(String pais);
    
    // Buscar asociaciones por presidente
    List<Asociacion> findByPresidenteContainingIgnoreCase(String presidente);
    
    // Buscar asociaciones por nombre o país
    @Query("SELECT a FROM Asociacion a WHERE LOWER(a.nombre) LIKE LOWER(CONCAT('%', :texto, '%')) OR LOWER(a.pais) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<Asociacion> findByNombreOrPaisContaining(@Param("texto") String texto);
    
    // Contar asociaciones por país
    @Query("SELECT a.pais, COUNT(a) FROM Asociacion a WHERE a.pais IS NOT NULL GROUP BY a.pais")
    List<Object[]> countAsociacionesByPais();
    
    // Buscar asociaciones con más de X clubes
    @Query("SELECT a FROM Asociacion a WHERE SIZE(a.clubes) > :minClubes")
    List<Asociacion> findAsociacionesWithMoreThanClubes(@Param("minClubes") int minClubes);
}