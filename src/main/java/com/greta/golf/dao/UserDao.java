package com.greta.golf.dao;

import com.greta.golf.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByLogin(String name);
}
