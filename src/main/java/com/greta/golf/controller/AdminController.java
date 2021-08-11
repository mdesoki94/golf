package com.greta.golf.controller;

import com.greta.golf.service.GolfService;
import com.greta.golf.service.JpaUserService;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AdminController {
    @Autowired
    private JpaUserService userService;


    public AdminController() {

    }

    public AdminController(JpaUserService userService) {
        this.userService=userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin/utilisateurs")
    public String utilisateur() {
        return "utilisateur";
    }


    @PostMapping("admin/utilisateurs")
    public String postAdmin(Model model,
                            @RequestParam String login,
                            @RequestParam String email,
                            @RequestParam String pass,
                            @RequestParam String passConfirm,
                            @RequestParam Long role) {
        this.userService.addUser(Jsoup.parse(login).text(), Jsoup.parse(email).text(),  pass, role);
        return "redirect:/";
    }
}
