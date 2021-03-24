package com.greta.golf.formdata;

import com.greta.golf.models.Parcours;

import java.util.Collection;

public class GolfFormDTO {
    private long id;
    private String nom;
    private Collection<Parcours> parcours;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Collection<Parcours> getParcours() {
        return parcours;
    }

    public void setParcours(Collection<Parcours> parcours) {
        this.parcours = parcours;
    }
}
