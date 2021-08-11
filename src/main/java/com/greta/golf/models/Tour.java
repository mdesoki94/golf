package com.greta.golf.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private LocalTime heurePremierDepart;
    private Integer intervalleDepart;
    private Integer nbJoueursPartie;
    private Integer numTour;
    @ManyToOne
    private Tournoi tournoi;
    @OneToMany(mappedBy = "tour")
    private List<Ajustement> ajustement;

    public LocalTime getHeurePremierDepart() {
        return heurePremierDepart;
    }

    public void setHeurePremierDepart(LocalTime heurePremierDepart) {
        this.heurePremierDepart = heurePremierDepart;
    }

    public Integer getIntervalleDepart() {
        return intervalleDepart;
    }

    public void setIntervalleDepart(Integer intervalleDepart) {
        this.intervalleDepart = intervalleDepart;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    public List<Ajustement> getAjustement() {
        return ajustement;
    }

    public void setAjustement(List<Ajustement> ajustement) {
        this.ajustement = ajustement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNbJoueursPartie() {
        return nbJoueursPartie;
    }

    public void setNbJoueursPartie(Integer nbJoueursPartie) {
        this.nbJoueursPartie = nbJoueursPartie;
    }

    public Integer getNumTour() {
        return numTour;
    }

    public void setNumTour(Integer numTour) {
        this.numTour = numTour;
    }

}

