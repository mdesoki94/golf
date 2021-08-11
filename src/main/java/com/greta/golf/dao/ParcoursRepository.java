package com.greta.golf.dao;

import com.greta.golf.models.Parcours;
import com.greta.golf.models.Trou;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ParcoursRepository extends CrudRepository<Parcours, Long> {
    List<Parcours>findByNom(String nomParcours);
    List<Parcours>findParcoursByGolf_Id(Long id);
}
