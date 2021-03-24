package com.greta.golf.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table
public class Ajustement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer tempsAjuste;

    @ManyToOne
    private Tour tour;

    @ManyToOne
    private Trou trou;

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }


    public Trou getTrou() {
        return trou;
    }

    public void setTrou(Trou trou) {
        this.trou = trou;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTempsAjuste() {
        return tempsAjuste;
    }

    public void setTempsAjuste(Integer tempsAjuste) {
        this.tempsAjuste = tempsAjuste;
    }


}
