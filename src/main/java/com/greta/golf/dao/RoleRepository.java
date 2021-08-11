package com.greta.golf.dao;

import com.greta.golf.models.Golf;
import com.greta.golf.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
    Role getRoleByNom(String name);
}
