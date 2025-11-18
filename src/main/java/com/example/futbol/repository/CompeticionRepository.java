package com.example.futbol.repository;

import com.example.futbol.model.Competicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CompeticionRepository extends JpaRepository<Competicion, Long> {
    
    // Buscar competiciones por nombre (ignorando mayúsculas/minúsculas)
    List<Competicion> findByNombreContainingIgnoreCase(String nombre);
    
    // Buscar competiciones por monto de premio mayor que
    List<Competicion> findByMontoPremioGreaterThan(int montoPremio);
    
    // Buscar competiciones por rango de montos de premio
    List<Competicion> findByMontoPremioBetween(int montoMin, int montoMax);
    
    // Buscar competiciones activas (fecha actual entre inicio y fin)
    @Query("SELECT c FROM Competicion c WHERE :fecha BETWEEN c.fechaInicio AND c.fechaFin")
    List<Competicion> findCompeticionesActivas(@Param("fecha") LocalDate fecha);
    
    // Buscar competiciones futuras
    @Query("SELECT c FROM Competicion c WHERE c.fechaInicio > :fecha")
    List<Competicion> findCompeticionesFuturas(@Param("fecha") LocalDate fecha);
    
    // Buscar competiciones pasadas
    @Query("SELECT c FROM Competicion c WHERE c.fechaFin < :fecha")
    List<Competicion> findCompeticionesPasadas(@Param("fecha") LocalDate fecha);
    
    // Buscar competiciones por club participante
    @Query("SELECT c FROM Competicion c JOIN c.clubes club WHERE club.id = :clubId")
    List<Competicion> findByClubId(@Param("clubId") Long clubId);
    
    // Contar competiciones por año de inicio
    @Query("SELECT YEAR(c.fechaInicio), COUNT(c) FROM Competicion c GROUP BY YEAR(c.fechaInicio)")
    List<Object[]> countCompeticionesByYear();
    
    // Buscar competiciones ordenadas por monto de premio descendente
    List<Competicion> findByOrderByMontoPremioDesc();
}