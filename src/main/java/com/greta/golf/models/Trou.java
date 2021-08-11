package com.greta.golf.models;


import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table
public class Trou {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String nom;

    @Basic
    @Column(nullable = false)
    private int par;
    @Basic
    @Column(nullable = false)
    private int numTrou;
    @ManyToOne
    private Parcours parcours;
    @OneToMany(mappedBy = "trou")
    private Collection<Ajustement>ajustements;

    public Trou() {
    }

    public Trou(int numTrou) {
        this.numTrou=numTrou;
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

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public int getNumTrou() {
        return numTrou;
    }

    public void setNumTrou(int num_trou) {
        this.numTrou = num_trou;
    }

    public Parcours getParcours() {
        return parcours;
    }

    public void setParcours(Parcours parcours) {
        this.parcours = parcours;
    }

    public Collection<Ajustement> getAjustements() {
        return ajustements;
    }

    public void setAjustements(Collection<Ajustement> ajustements) {
        this.ajustements = ajustements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trou trou = (Trou) o;
        return id == trou.id && par == trou.par && numTrou == trou.numTrou && nom.equals(trou.nom) && Objects.equals(parcours, trou.parcours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, par, numTrou);
    }

    @Override
    public String toString() {
        return "Trou{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", par=" + par +
                ", numTrou=" + numTrou +
                '}';
    }
}

