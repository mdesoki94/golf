package com.greta.golf.formdata;

import com.greta.golf.models.Parcours;
import org.jsoup.Jsoup;

public class TrouFormDTO {
    private Long id;
    private String nom;
    private int par;
    private int numTrou;
    private long ajustement_id;
    private long parcours_id;

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
        this.nom = Jsoup.parse(nom).text();
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

    public void setNumTrou(int numTrou) {
        this.numTrou = numTrou;
    }


    public long getParcours_id() {
        return parcours_id;
    }

    public void setParcours_id(long parcours_id) {
        this.parcours_id = parcours_id;
    }

    public long getAjustement_id() {
        return ajustement_id;
    }

    public void setAjustement_id(long ajustement_id) {
        this.ajustement_id = ajustement_id;
    }
}
