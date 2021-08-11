package com.greta.golf.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table
public class Tournoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;
    private String nom;
    private Integer nbTours;
    private String commentaire;
    private Integer nbJoueurParties;
    @OneToMany(mappedBy = "tournoi")
    private Collection<Tour>tour;
    @ManyToOne
    private User user;
    @ManyToOne
    private Parcours parcours;

    public Collection<Tour> getTour() {
        return tour;
    }

    public void setTour(Collection<Tour> tour) {
        this.tour = tour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getNbTours() {
        return nbTours;
    }

    public void setNbTours(Integer nbTours) {
        this.nbTours = nbTours;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Parcours getParcours() {
        return parcours;
    }

    public void setParcours(Parcours parcours) {
        this.parcours = parcours;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Integer getNbJoueurParties() {
        return nbJoueurParties;
    }

    public void setNbJoueurParties(Integer nbJoueurParties) {
        this.nbJoueurParties = nbJoueurParties;
    }
}
