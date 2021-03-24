package com.greta.golf.dao;

import com.greta.golf.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupDao extends JpaRepository<Role, Long> {
}

