package com.greta.golf.controller;

import com.greta.golf.formdata.GolfFormDTO;
import com.greta.golf.models.*;
import com.greta.golf.service.GolfService;
import com.greta.golf.service.PopulateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller

public class GolfController {
    private GolfService golfService;
    private PopulateService populateService;

    @Autowired
    public GolfController(GolfService golfService, PopulateService populateService){this.golfService=golfService; this.populateService=populateService;}

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("trous",golfService.getTrous());
        return "index";
    }

    @GetMapping("/admin/groupe")
    public String listeGroupe(Model model){
        model.addAttribute("groupes",golfService.getGroupes());
        return "listGroupe";
    }

    @GetMapping("/admin/groupe/delete/{id}")
    public String deleteGroupe(@PathVariable (name = "id")Long id){
        golfService.deleteGroupe(id);
        return "redirect:/";
    }

    @GetMapping("/admin/test")
    public String celyController(Model model){
//    model.addAttribute("golfs",golfService.GolfByCategory("Coudray"));
//    model.addAttribute("trous",golfService.TrouByNum(1));

        return "admin";
    }
    @PostMapping("/admin/test")
    public String addGolf(){
        populateService.populate();
        return "redirect:/admin/test"; //Redirection pour eviter le renvoi du formulaire une seconde fois !!
    }



        @GetMapping("/login")
        public String login(){
            return "login";
        }




}
