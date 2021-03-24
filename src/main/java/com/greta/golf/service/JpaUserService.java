package com.greta.golf.service;

import com.greta.golf.dao.GroupDao;
import com.greta.golf.dao.UserDao;
import com.greta.golf.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;

public class JpaUserService {
    private UserDao userDao;
    private GroupDao groupDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public JpaUserService(UserDao userDao,
                          GroupDao groupDao,
                          BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userDao = userDao;
        this.groupDao = groupDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void save(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(groupDao.findAll()));
        userDao.save(user);
    }

    public User findByUserName(String userName){
        return userDao.findByLogin(userName);
    }

}