package com.greta.golf.dao;

import com.greta.golf.models.Trou;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrouRepository extends CrudRepository <Trou, Long>  {

    List<Trou>findTrouByNumTrou(Integer numTrou);
    Trou findTrouByParcours(Integer parcours);


}
