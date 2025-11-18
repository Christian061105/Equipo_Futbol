package com.example.futbol.repository;

import com.example.futbol.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
    
    // Buscar jugadores por club
    List<Jugador> findByClubId(Long clubId);
    
    // Buscar jugadores por posición
    List<Jugador> findByPosicion(String posicion);
    
    // Buscar jugadores por nombre (ignorando mayúsculas/minúsculas)
    List<Jugador> findByNombreContainingIgnoreCase(String nombre);
    
    // Buscar jugadores por apellido (ignorando mayúsculas/minúsculas)
    List<Jugador> findByApellidoContainingIgnoreCase(String apellido);
    
    // Buscar jugadores por número
    List<Jugador> findByNumero(int numero);
    
    // Buscar jugadores por rango de números
    List<Jugador> findByNumeroBetween(int numeroInicio, int numeroFin);
    
    // Buscar jugadores sin club
    @Query("SELECT j FROM Jugador j WHERE j.club IS NULL")
    List<Jugador> findJugadoresSinClub();
    
    // Buscar jugadores por posición y club
    List<Jugador> findByPosicionAndClubId(String posicion, Long clubId);
    
    // Contar jugadores por posición
    @Query("SELECT j.posicion, COUNT(j) FROM Jugador j WHERE j.posicion IS NOT NULL GROUP BY j.posicion")
    List<Object[]> countJugadoresByPosicion();
    
    // Buscar jugadores con nombre o apellido que contenga el texto
    @Query("SELECT j FROM Jugador j WHERE LOWER(j.nombre) LIKE LOWER(CONCAT('%', :texto, '%')) OR LOWER(j.apellido) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<Jugador> findByNombreOrApellidoContaining(@Param("texto") String texto);
}