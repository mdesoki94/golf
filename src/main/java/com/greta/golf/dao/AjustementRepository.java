package com.greta.golf.dao;

import com.greta.golf.models.Ajustement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AjustementRepository extends CrudRepository<Ajustement,Long> {
    List<Ajustement>findByTempsAjuste(Integer tpsAjuste);
}
