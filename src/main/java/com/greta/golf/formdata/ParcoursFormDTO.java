package com.greta.golf.formdata;

import com.greta.golf.dao.GolfRepository;
import com.greta.golf.models.Golf;
import com.greta.golf.models.Tournoi;
import com.greta.golf.models.Trou;
import com.greta.golf.service.GolfService;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;
import java.util.Collection;
import java.util.List;

public class ParcoursFormDTO {
    private Long id;
    private String nom;
    private Long golfid;
    private List<Trou> trous;
    private List<Tournoi>tournoi;


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

    public Long getGolf() {
        return golfid;
    }

    public void setGolf(Long golf) {
        this.golfid = golf;
    }

    public List<Trou> getTrous() {
        return trous;
    }

    public void setTrous(List<Trou> trous) {
        this.trous = trous;
    }

    public Long getGolfid() {
        return golfid;
    }

    public void setGolfid(Long golfid) {
        this.golfid = golfid;
    }

    public List<Tournoi> getTournoi() {
        return tournoi;
    }

    public void setTournoi(List<Tournoi> tournoi) {
        this.tournoi = tournoi;
    }
}
