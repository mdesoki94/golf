package com.greta.golf.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table
public class Golf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(nullable = false,unique = false)
    private String nom;
    private String coordGPS;



    @OneToMany(mappedBy = "golf")
    private Collection <Parcours> parcours;

    public Collection<Parcours> getParcours() {
        return parcours;
    }

    public void setParcours(Collection<Parcours> parcours) {
        this.parcours = parcours;
    }

    public Golf(){

    }
    public Golf(String name) {
        this.nom=name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCoordGPS() {
        return coordGPS;
    }

    public void setCoordGPS(String coordGPS) {
        this.coordGPS = coordGPS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Golf golf = (Golf) o;
        return id == golf.id && Objects.equals(nom, golf.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom);
    }

    @Override
    public String toString() {
        return "Golf{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
