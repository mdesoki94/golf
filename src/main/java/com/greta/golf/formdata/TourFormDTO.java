package com.greta.golf.formdata;

import com.greta.golf.models.Ajustement;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class TourFormDTO {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Integer nbJoueursPartie;
    private Integer intervalleDepart;
    private long idTournois;
    private List<Ajustement>ajustements;

    private MultipartFile file;

    public TourFormDTO() {
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

    public Integer getIntervalleDepart() {
        return intervalleDepart;
    }

    public void setIntervalleDepart(Integer intervalleDepart) {
        this.intervalleDepart = intervalleDepart;
    }

    public long getIdTournois() {
        return idTournois;
    }

    public void setIdTournois(long idTournois) {
        this.idTournois = idTournois;
    }

    public List<Ajustement> getAjustements() {
        return ajustements;
    }

    public void setAjustements(List<Ajustement> ajustements) {
        this.ajustements = ajustements;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
