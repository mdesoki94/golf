package com.greta.golf.dao;

import com.greta.golf.models.Ajustement;
import com.greta.golf.models.Trou;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AjustementRepository extends CrudRepository<Ajustement,Long> {

    List<Ajustement> findByTourId(Long id);

    List<Ajustement> findByTrouId(long id);
}
