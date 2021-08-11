package com.greta.golf.dao;

import com.greta.golf.models.Golf;
import com.greta.golf.models.Parcours;
import com.greta.golf.models.Trou;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GolfRepository extends CrudRepository<Golf, Long> {
    List<Golf> findGolfByNom(String nom);
    Golf findByNom(String name);

}
