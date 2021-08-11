package com.greta.golf.dao;

import com.greta.golf.models.Tour;
import com.greta.golf.models.Trou;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface TourRepository extends CrudRepository<Tour,Long> {
    List<Tour>findByDate(Date date);
    List<Tour> findByTournoiId(long id);
}
