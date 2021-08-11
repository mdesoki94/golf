package com.greta.golf.models;

import com.opencsv.bean.CsvBindByPosition;

public class Player {
    @CsvBindByPosition(position = 0)
    private String numTeam;

    @CsvBindByPosition(position = 1)
    private String start;
    @CsvBindByPosition(position = 2)
    private String name;
    @CsvBindByPosition(position = 3)
    private String club;
    @CsvBindByPosition(position = 4)
    private String nat;
    @CsvBindByPosition(position = 5)
    private String index;
    @CsvBindByPosition(position = 6)
    private String serie;
    @CsvBindByPosition(position = 7)
    private String color;
    private Team team;

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public String getNumTeam() {
        return numTeam;
    }

    public void setNumTeam(String numTeam) {
        this.numTeam = numTeam;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
