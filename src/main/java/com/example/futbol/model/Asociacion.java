package com.example.futbol.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "asociaciones")
public class Asociacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    private String pais;
    private String presidente;

    // Relación OneToMany con Club
    @OneToMany(mappedBy = "asociacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Club> clubes = new ArrayList<>();

    // 👇 Campo adicional (NO se guarda en la BD)
    @Transient
    private int numeroClubes;

    // Constructores
    public Asociacion() {}

    public Asociacion(String nombre, String pais, String presidente) {
        this.nombre = nombre;
        this.pais = pais;
        this.presidente = presidente;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getPresidente() { return presidente; }
    public void setPresidente(String presidente) { this.presidente = presidente; }

    public List<Club> getClubes() { return clubes; }
    public void setClubes(List<Club> clubes) { this.clubes = clubes; }

    public int getNumeroClubes() { return numeroClubes; }
    public void setNumeroClubes(int numeroClubes) { this.numeroClubes = numeroClubes; }

    @Override
    public String toString() {
        return "Asociacion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", presidente='" + presidente + '\'' +
                ", numeroClubes=" + numeroClubes +
                '}';
    }
}
