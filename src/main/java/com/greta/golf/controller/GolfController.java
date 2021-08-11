package com.greta.golf.controller;

import com.greta.golf.formdata.GolfFormDTO;
import com.greta.golf.formdata.ParcoursFormDTO;
import com.greta.golf.formdata.TourFormDTO;
import com.greta.golf.formdata.TournoisFormDTO;
import com.greta.golf.models.*;
import com.greta.golf.service.GolfService;
import com.greta.golf.service.JpaUserService;
import com.itextpdf.text.Document;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.IOUtils;

import java.awt.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Controller

public class GolfController {
    private GolfService golfService;


    @Autowired
    public GolfController(GolfService golfService) {
        this.golfService = golfService;
    }

    ////////////////////////////////GOLFS///////////////////////////////////////////////////

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("golfs", golfService.getGolfs());
        model.addAttribute("parcours", golfService.getParcours());
        ParcoursFormDTO dto=new ParcoursFormDTO();
        model.addAttribute("parcours", dto);
        model.addAttribute("trous",golfService.getTrous());
        return "index";
    }

    @GetMapping("/admin/golf/delete/{id}")
    public String deleteGolf(@PathVariable(name = "id") long id) {
        golfService.deleteGolf(id);

        return "redirect:/";
    }


    @GetMapping("/admin/golf/{id}")
    public String golf(Model model, @PathVariable(name = "id") Long id) {
        Golf golf = golfService.getGolf(id);
        GolfFormDTO dto = new GolfFormDTO();
        dto.setId(golf.getId());
        dto.setNom(golf.getNom());
        dto.setLatitude(golf.getLatitude());
        dto.setLongitude(golf.getLongitude());
        model.addAttribute("golf", dto);
        return "formgolf";
    }

    @PostMapping("/golf")
    public String postGolf(@ModelAttribute(name = "golf") GolfFormDTO golf) {
        golfService.saveGolfs(golf);
        return "redirect:/";
    }

    @GetMapping("/golf/add")
    public String addGolf(Model model) {
        model.addAttribute("golf", new GolfFormDTO());
        return "addgolf";
    }

    /////////////////////////////////////////PARCOURS//////////////////////////////////////////////
    @GetMapping("/parcours/add/{id}")
    public String addParcours(Model model, @PathVariable(name = "id") Long id) {
        ParcoursFormDTO dto = new ParcoursFormDTO();
        dto.setGolf(id);
        List<Trou> trous = new ArrayList<Trou>();
        for (int i = 0; i < 18; i++) {
//            Trou trou = new Trou();
//            trou.setNumTrou(i);
            trous.add(new Trou(i+1));
        }
        dto.setTrous(trous);
        model.addAttribute("parcours", dto);

        return "addparcours";
    }

    @PostMapping("/parcours")
    public String postParcours(ParcoursFormDTO parcours) {
        golfService.saveParcours(parcours);
        return "redirect:/";
    }

    @GetMapping("/admin/parcours/delete/{id}")
    public String deleteParcours(@PathVariable(name = "id") long id) {
        golfService.deleteParcours(id);
        return "redirect:/";
    }

    @GetMapping("/admin/parcours/{id}")
    public String parcours(Model model, @PathVariable(name = "id") Long id) {
        Parcours parcours = golfService.getParcours(id);
        ParcoursFormDTO dto = new ParcoursFormDTO();
        dto.setId(parcours.getId());
        dto.setNom(parcours.getNom());
        dto.setGolfid(parcours.getGolf().getId());
        dto.setTrous(parcours.getTrous());
        model.addAttribute("parcours", dto);
        return "formparcours";
    }
///////////////////////////////////TOURNOIS////////////////////////////////////////////////////

    @GetMapping("/arbitre/tournois")
    public String tournois(Model model) {
        model.addAttribute("parcours", golfService.getParcours());
        TournoisFormDTO dto=new TournoisFormDTO();
        model.addAttribute("tournois", dto);
        model.addAttribute("golfs", golfService.getGolfs());
        return "tournoi";
    }

    @GetMapping("/arbitre/tournois/add/{idParcours}")
    public String addTournois(Model model,@PathVariable Long idParcours) {
        TournoisFormDTO tournoisFormDTO=new TournoisFormDTO();
        tournoisFormDTO.setParcours_id(idParcours);
        model.addAttribute("tournois",  tournoisFormDTO);
        return "addtournois";
    }

    @PostMapping("/arbitre/tournois")
    public String postTournois(@ModelAttribute(name = "tournois") TournoisFormDTO tournois) {
        golfService.saveTournois(tournois);
        return "redirect:/arbitre/tournois";
    }

    @GetMapping("/arbitre/tournois/delete/{id}")
    public String deleteTournois(@PathVariable(name = "id") long id) {
        golfService.deleteTournois(id);
        return "redirect:/arbitre/tournois";
    }

    @GetMapping("/arbitre/tournois/{idTournois}")
    public String tournois(Model model,@PathVariable Long idTournois) {
        Tournoi tournoi= golfService.getTournoisByID(idTournois);
        TournoisFormDTO tournoisFormDTO=new TournoisFormDTO(tournoi);
        model.addAttribute("tournois",tournoisFormDTO);
        return "formtournois";
    }

/////////////////////////////////////////LES TOURS//////////////////////////////////////////////////////


    @GetMapping("/arbitre/tours/{idTournois}")
    public String tour(Model model,@PathVariable long idTournois) {
        Tournoi tournoi= golfService.getTournoisByID(idTournois);
        model.addAttribute("tournois",tournoi);
        return "tourlist";
    }

    @GetMapping("/arbitre/tours/update/{id}")
    public String tourUpdate(Model model,@PathVariable(name = "id") Long id){
        Tour tour = golfService.getTour(id);
        TourFormDTO tourFormDTO = new TourFormDTO();
        tourFormDTO.setId(tour.getId());
        tourFormDTO.setDate(tour.getDate());
        tourFormDTO.setNbJoueursPartie(tour.getNbJoueursPartie());
        tourFormDTO.setIdTournois(tour.getTournoi().getId());
        List<Tournoi> tournoi = golfService.getTounois();
        List<Ajustement> ajustements = (List<Ajustement>) tour.getAjustement();
        tourFormDTO.setAjustements(ajustements);
        model.addAttribute("tournois",tournoi);
        model.addAttribute("tours",tourFormDTO);
        return "formtour";
    }

    @PostMapping(value = "/arbitre/tours", produces = MediaType.APPLICATION_PDF_VALUE)
    public String postTours(@ModelAttribute TourFormDTO tourFormDTO, Model model) {
        Document pdf=new Document();
        try (Reader reader = new BufferedReader(new InputStreamReader(tourFormDTO.getFile().getInputStream(),"UTF-16"))) {
            // create csv bean reader
            CsvToBean<Player> csvToBean = new CsvToBeanBuilder(reader).
                    withType(Player.class).
                    withIgnoreLeadingWhiteSpace(true).
                    withIgnoreQuotations(true).
                    withSkipLines(1).
                    build();

            List<Player> players = csvToBean.parse();
            String numEquipe="";
            String heure="";
            List<Team> teams=new ArrayList<Team>();
            Team team=new Team();
            for (Player player: players)
            {
                if (!player.getNumTeam().equals(""))
                {
                    numEquipe=player.getNumTeam();
                    heure=player.getStart();
                    team=new Team(numEquipe,heure,new ArrayList<Player>());
                    teams.add(team);
                }
                player.setNumTeam(numEquipe);
                player.setStart(heure);
                team.getPlayers().add(player);
            }
          Tour tour= golfService.saveTours(tourFormDTO);

            for (Team aTeam:teams) {
                golfService.generateCadence(aTeam,tour);
            }
            pdf=golfService.generatePDF(teams,tour);
        }
        catch (Exception ex) {
            model.addAttribute("message", "An error occurred while processing the CSV file.");
            System.out.println("An error occurred while processing the CSV file.");
            ex.printStackTrace();
            model.addAttribute("status", false);

        }

        return "redirect:/arbitre/tournois";
    }


    @GetMapping("/arbitre/tours/add/{idTournois}")
    public String addTours(Model model,@PathVariable Long idTournois) {
        TourFormDTO tourFormDTO=new TourFormDTO();
        tourFormDTO.setIdTournois(idTournois);
        List<Ajustement>ajustements=new ArrayList<Ajustement>();
        Parcours parcours=golfService.getTournoisByID(idTournois).getParcours();
        for (Trou trou:parcours.getTrous()) {
            ajustements.add(new Ajustement(trou));
        }
        tourFormDTO.setAjustements(ajustements);
        model.addAttribute("tours",  tourFormDTO);
        return "formtour";
    }

    @GetMapping("/arbitre/tours/delete/{id}")
    public String deleteTours(@PathVariable(name = "id") long id) {
        golfService.deleteTours(id);
        return "redirect:/arbitre/tournois";
    }

////////////////////////////////////////////////////////////////////////////////////////////////

//    @GetMapping(value = "/pdf/{pdfName}", produces = MediaType.APPLICATION_PDF_VALUE)
//    public @ResponseBody
//    byte[]
//    pdf(@PathVariable("pdfName") String pdfName) {
//        //String imageName = vehiculeDao.findById(id).get().getImage1();
//        InputStream is = golfService.retreivepdf1(pdfName);
//        byte[] pdf = null;
//        try {
//            pdf = IOUtils.readNBytes(is,1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return pdf;
//    }

}

