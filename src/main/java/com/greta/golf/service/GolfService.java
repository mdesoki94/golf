package com.greta.golf.service;

import com.google.common.collect.Lists;
import com.greta.golf.formdata.RoleFormDTO;
import com.greta.golf.formdata.UserFormDTO;
import com.greta.golf.dao.*;
import com.greta.golf.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class GolfService {
GolfRepository golfRepository;
ParcoursRepository parcoursRepository;
TrouRepository trouRepository;
RoleRepository roleRepository;
UserRepository userRepository;
TournoiRepository tournoiRepository;
TourRepository tourRepository;
AjustementRepository ajustementRepository;

@Autowired
    public GolfService(GolfRepository golfRepository,
                       ParcoursRepository parcoursRepository,
                       TrouRepository trouRepository,RoleRepository roleRepository,
                       UserRepository userRepository,TournoiRepository tournoiRepository,
                       TourRepository tourRepository,AjustementRepository ajustementRepository){
    this.golfRepository=golfRepository;
    this.parcoursRepository=parcoursRepository;
    this.trouRepository=trouRepository;
    this.roleRepository=roleRepository;
    this.userRepository= userRepository;
    this.tournoiRepository=tournoiRepository;
    this.tourRepository=tourRepository;
    this.ajustementRepository=ajustementRepository;
}

public List<Trou> getTrous(){
    return Lists.newArrayList(trouRepository.findAll());
}

public List<Role>getGroupes(){
    return Lists.newArrayList(roleRepository.findAll());
}

    public Role getGroupe(Long id){
        return roleRepository.findById(id).orElse(new Role());
    }

    public void deleteGroupe(long id){
        roleRepository.deleteById(id);
    }

    public void saveRole(Role dto){
        Role roleDB = roleRepository.findById(dto.getId()).orElse(new Role());
        roleDB.setNom(dto.getNom());
        roleDB.setRole(dto.getRole());
        roleRepository.save(roleDB);
    }

    public List<User>getUsers(){
    return Lists.newArrayList(userRepository.findAll());
    }

    public User getUser(Long id){
        return userRepository.findById(id).orElse(new User());
    }

    public void deleteUser(long id){
        userRepository.deleteById(id);
    }

    public void saveUser(User user){
        User userDB = userRepository.findById(user.getId()).orElse(new User());
        userDB.setLogin(user.getLogin());
        userDB.setPassword(user.getPassword());
        userDB.setEmail(user.getEmail());
        userDB.setActive(user.isActive());

        userRepository.save(userDB);
    }

    public List<Golf>getGolfs(){return Lists.newArrayList(golfRepository.findAll());}

    public Golf getGolf(Long id){return golfRepository.findById(id).orElse(new Golf());}

    //GOLF
    public Golf saveGolf(Golf golf){
       return golfRepository.save(golf);
    }

    public List<Golf>GolfByCategory(String idCateg){
        return golfRepository.findGolfByNom(idCateg);
    }

    public void createGolfByName(String name) {
    golfRepository.save(new Golf(name));
    }

    public Golf getGolfByName(String name) {
    return golfRepository.findByNom(name);
    }
//TROU
    public void saveTrou(Trou trou){
            trouRepository.save(trou);
    }
    public List<Trou>TrouByNum(Integer num){
        return trouRepository.findTrouByNumTrou(num);
    }

    //TOURNOI
    public void saveTournoi(Tournoi tournoi){
        tournoiRepository.save(tournoi);
        }

    public List<Tournoi>NomTournoi(String nomTournoi){
        return tournoiRepository.findByNom(nomTournoi);
    }

    //TOUR
    public Tour saveTour(Tour tour){
    return tourRepository.save(tour);
    }

    //PARCOURS
    public void saveParcours(Parcours parcours){
        parcoursRepository.save(parcours);
    }

    //AJUSTEMENT
    public void saveAjustement(Ajustement ajustement){

            ajustementRepository.save(ajustement);
        }
    }



