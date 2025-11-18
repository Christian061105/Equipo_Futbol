package com.example.futbol.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clubes")
public class Club {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    private String ciudad;
    
    private int fundacion;
    
    // Relación OneToOne con Entrenador
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entrenador_id")
    private Entrenador entrenador;


    
    // Relación OneToMany con Jugador
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Jugador> jugadores = new ArrayList<>();
    
    // Relación ManyToOne con Asociacion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asociacion_id")
    private Asociacion asociacion;
    
    // Relación ManyToMany con Competicion
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "club_competicion",
        joinColumns = @JoinColumn(name = "club_id"),
        inverseJoinColumns = @JoinColumn(name = "competicion_id")
    )
    private List<Competicion> competiciones = new ArrayList<>();
    
    // Constructores
    public Club() {}
    
    public Club(String nombre, String ciudad, int fundacion) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.fundacion = fundacion;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    
    public int getFundacion() { return fundacion; }
    public void setFundacion(int fundacion) { this.fundacion = fundacion; }
    
    public Entrenador getEntrenador() { return entrenador; }
    public void setEntrenador(Entrenador entrenador) { this.entrenador = entrenador; }
    
    public List<Jugador> getJugadores() { return jugadores; }
    public void setJugadores(List<Jugador> jugadores) { this.jugadores = jugadores; }
    
    public Asociacion getAsociacion() { return asociacion; }
    public void setAsociacion(Asociacion asociacion) { this.asociacion = asociacion; }
    
    public List<Competicion> getCompeticiones() { return competiciones; }
    public void setCompeticiones(List<Competicion> competiciones) { this.competiciones = competiciones; }
    
    // Métodos helper para relaciones bidireccionales
    public void addJugador(Jugador jugador) {
        jugadores.add(jugador);
        jugador.setClub(this);
    }
    
    public void removeJugador(Jugador jugador) {
        jugadores.remove(jugador);
        jugador.setClub(null);
    }
    
    public void addCompeticion(Competicion competicion) {
        competiciones.add(competicion);
        competicion.getClubes().add(this);
    }
    
    public void removeCompeticion(Competicion competicion) {
        competiciones.remove(competicion);
        competicion.getClubes().remove(this);
    }
    
    @Override
    public String toString() {
        return "Club{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", fundacion=" + fundacion +
                '}';
    }
}