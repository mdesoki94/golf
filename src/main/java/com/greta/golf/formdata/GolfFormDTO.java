package com.greta.golf.formdata;

import com.greta.golf.models.Parcours;
import org.jsoup.Jsoup;

import java.util.Collection;

public class GolfFormDTO {
    private long id;
    private String nom;
    private String latitude;
    private String longitude;
    private Collection<Parcours> parcours;

    public GolfFormDTO() {
    }

    public GolfFormDTO(long id, String nom, String latitude, String longitude) {
        this.id = id;
        this.nom = nom;
        this.latitude=latitude;
        this.longitude=longitude;
    }

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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Collection<Parcours> getParcours() {
        return parcours;
    }

    public void setParcours(Collection<Parcours> parcours) {
        this.parcours = parcours;
    }

}
