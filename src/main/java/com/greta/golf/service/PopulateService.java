package com.greta.golf.service;

import com.greta.golf.models.*;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;
import java.util.Random;

@Service
public class PopulateService {

    private GolfService golfService;

    @Autowired
    PopulateService(GolfService golfService) {
        this.golfService = golfService;
    }



    public Golf createGolf(String gps,String nom) {
        Golf golf = new Golf();
        golf.setNom(nom);
        golf.setCoordGPS(gps);
        System.out.println(golf);
      golf= golfService.saveGolf(golf);
        System.out.println(golf);
        return golf;
    }
    public void populate() {
     Golf golf=   createGolf("48.4596"+" 2.5324","Cely");
        createParcours(golf, "principal");

    }

    public void createParcours(Golf golf, String nom) {
        Parcours parcours = new Parcours();
        parcours.setNom(nom);
        parcours.setGolf(golf);
        golfService.saveParcours(parcours);
        Date date=new Date();
      Tour tour=  createTournoi(parcours,"Boissy",1,3,date);

        int [] array = {5,4,4,4,3,5,3,4,4,5,4,3,4,4,5,4,3,4};
        for (int i = 1; i <= 18; i++) {

                createTrou(parcours, i,array[i-1],tour);

            }
        }


    public void createTrou(Parcours parcours, int i,int j,Tour tour) {
        Trou trou = new Trou();
        trou.setNumTrou(i);
        trou.setPar(j);
        trou.setParcours(parcours);
        golfService.saveTrou(trou);
        createAjustement(trou,tour);

    }

    public void createAjustement(Trou trou,Tour tour) {
            Ajustement ajustement = new Ajustement();
            Random r = new Random();
            ajustement.setTempsAjuste(1+ r.nextInt(3));
            ajustement.setTour(tour);
            ajustement.setTrou(trou);
            golfService.saveAjustement(ajustement);


    }

    public Tour createTour(Tournoi tournoi, Date date, int numtour, int intervalle, LocalTime heure){
        Tour tour=new Tour();
        tour.setDate(date);
        tour.setTournoi(tournoi);
        tour.setNumTour(numtour);
        tour.setNbJoueursPartie(45);
        tour.setHeurePremierDepart(heure);
        tour.setIntervalleDepart(intervalle);
       return golfService.saveTour(tour);

    }


    public Tour createTournoi(Parcours parcours,String nom,int numtour,int nbTour,Date date){
        Tournoi tournoi=new Tournoi();
        tournoi.setNom(nom);
        tournoi.setParcours(parcours);
        tournoi.setNbTours(nbTour);
        tournoi.setDateDebut(date);
        tournoi.setUser(tournoi.getUser());
        golfService.saveTournoi(tournoi);
        return createTour(tournoi,date,numtour,11,LocalTime.now());

    }


    public void createUser(String email,boolean active,String login,String password){
        User user=new User();
        user.setRoles(user.getRoles());
        user.setRoles(user.getRoles());
        user.setEmail(email);
        user.setActive(active);
        user.setLogin(login);
        user.setPassword(password);
        golfService.saveUser(user);
        createRole(user,"Olivier","Admin");
    }

    public void createRole(User user,String nom,String nomRole){
        Role role=new Role();
        role.setNom(nom);
        role.setRole(nomRole);
        role.setUsers(role.getUsers());
        golfService.saveRole(role);
        createUser("moha94470",true,"momo","94470M");

    }


}
