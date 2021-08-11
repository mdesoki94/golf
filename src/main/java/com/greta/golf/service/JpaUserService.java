package com.greta.golf.service;

import com.greta.golf.dao.RoleRepository;
import com.greta.golf.dao.UserRepository;
import com.greta.golf.models.Role;
import com.greta.golf.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JpaUserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public JpaUserService(UserRepository userRepository,
                          RoleRepository roleRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void save(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    public User findByUserName(String userName){
        return userRepository.findByLogin(userName);
    }

    public void addUser(String name ,String email, String mdp,Long idGroup){
        Role role = this.roleRepository.findById(idGroup).get();
        User user = new User(name,mdp,email);
        Set<Role> roles = new HashSet<Role>();
        System.out.println(role.getRole());
        user.setRoles(roles);
        roles.add(role);
        this.save(user);
    }
}