package com.greta.golf.models;

import java.util.Date;

public class Rate {
    private Ajustement ajustement;
    private Team team;
    private Date date;

    public Rate() {
    }

    public Rate(Ajustement ajustement, Date date) {
        this.ajustement = ajustement;
        this.date = date;
    }

    public Rate(Ajustement ajustement, Team team, Date date) {
        this.ajustement = ajustement;
        this.team = team;
        this.date = date;
    }

    public Ajustement getAttunement() {
        return ajustement;
    }

    public void setAttunement(Ajustement ajustement) {
        this.ajustement = ajustement;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
