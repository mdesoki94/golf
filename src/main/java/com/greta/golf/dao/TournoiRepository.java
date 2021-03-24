package com.greta.golf.dao;

import com.greta.golf.models.Tournoi;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TournoiRepository extends CrudRepository<Tournoi,Long> {
    List <Tournoi>findByNom(String nom);
}
