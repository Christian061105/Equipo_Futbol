package com.example.futbol.repository;

import com.example.futbol.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    
    // Buscar club por nombre (ignorando mayúsculas/minúsculas)
    List<Club> findByNombreContainingIgnoreCase(String nombre);
    
    // Buscar clubes por ciudad
    List<Club> findByCiudad(String ciudad);
    
    // Buscar clubes por año de fundación
    List<Club> findByFundacion(int fundacion);
    
    // Buscar clubes por rango de años de fundación
    List<Club> findByFundacionBetween(int inicio, int fin);
    
    // Buscar clubes por asociación
    List<Club> findByAsociacionId(Long asociacionId);
    
    // Buscar clubes que tengan un entrenador específico
    @Query("SELECT c FROM Club c WHERE c.entrenador.id = :entrenadorId")
    Optional<Club> findByEntrenadorId(@Param("entrenadorId") Long entrenadorId);
    
    // Contar clubes por ciudad
    @Query("SELECT c.ciudad, COUNT(c) FROM Club c GROUP BY c.ciudad")
    List<Object[]> countClubesByCiudad();
    
    // Buscar clubes que participen en una competición específica
    @Query("SELECT c FROM Club c JOIN c.competiciones comp WHERE comp.id = :competicionId")
    List<Club> findByCompeticionId(@Param("competicionId") Long competicionId);
    
    // Buscar clubes con más de X jugadores
    @Query("SELECT c FROM Club c WHERE SIZE(c.jugadores) > :minJugadores")
    List<Club> findClubesWithMoreThanJugadores(@Param("minJugadores") int minJugadores);
}