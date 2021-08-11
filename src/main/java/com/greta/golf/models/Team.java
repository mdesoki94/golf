package com.greta.golf.models;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import java.util.ArrayList;
import java.util.List;

public class Team {
    @CsvBindByPosition(position = 0)
    private String num;
    @CsvBindByPosition(position = 1)
    private String start;
    private List<Player> players;
    private List<Rate> rates;

    public Team() {
    }

    public Team(String num, String start,List<Player> players) {
        this.num = num;
        this.start = start;
        this.players=players;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }


}
