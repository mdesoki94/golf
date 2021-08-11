package com.greta.golf.formdata;

import com.greta.golf.models.Parcours;
import com.greta.golf.models.Tour;
import com.greta.golf.models.Tournoi;
import com.greta.golf.models.User;
import org.jsoup.Jsoup;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class TournoisFormDTO {
    private Long id;
    private String nom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;
    private String commentaire;
    private List<Tour> tour;
    private long parcours_id;
    public TournoisFormDTO() {
    }

    public TournoisFormDTO(Tournoi tournoi) {
        this.id = tournoi.getId();
        this.nom = tournoi.getNom();
        this.dateDebut = tournoi.getDateDebut();
        this.commentaire = tournoi.getCommentaire();
        this.parcours_id = tournoi.getParcours().getId();
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
        this.nom = Jsoup.parse(nom).text();
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = Jsoup.parse(commentaire).text();
    }

    public List<Tour> getTour() {
        return tour;
    }

    public void setTour(List<Tour> tour) {
        this.tour = tour;
    }

    public long getParcours_id() {
        return parcours_id;
    }

    public void setParcours_id(long parcours_id) {
        this.parcours_id = parcours_id;
    }
}
