package com.greta.golf.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table
public class Parcours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String nom;
    @ManyToOne
    private Golf golf;
    @OneToMany(mappedBy = "parcours")
    private Collection <Trou> trous;

    public Collection<Trou> getTrous() {
        return trous;
    }

    public void setTrous(Collection<Trou> trous) {
        this.trous = trous;
    }

    public Collection<Tournoi> getTournoi() {
        return tournoi;
    }

    public void setTournoi(Collection<Tournoi> tournoi) {
        this.tournoi = tournoi;
    }

    @OneToMany(mappedBy = "parcours")
    private Collection<Tournoi>tournoi;

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

    public Golf getGolf() {
        return golf;
    }

    public void setGolf(Golf golf) {
        this.golf = golf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcours parcours = (Parcours) o;
        return id == parcours.id && nom.equals(parcours.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom);
    }

    @Override
    public String toString() {
        return "Parcours{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }

    public int getPar() {
        int par=0;
        for (Trou t:this.trous)
              {
            par+=t.getPar();
        }
        return par;
    }
}
