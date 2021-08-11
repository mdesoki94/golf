package com.greta.golf.service;

import com.google.common.collect.Lists;
import com.greta.golf.formdata.*;
import com.greta.golf.dao.*;
import com.greta.golf.models.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

@Service
public class GolfService {
//    @Value("${prestige.img.path}")
//    String path;

    GolfRepository golfRepository;
    ParcoursRepository parcoursRepository;
    TrouRepository trouRepository;
    RoleRepository roleRepository;
    UserRepository userRepository;
    TournoiRepository tournoiRepository;
    TourRepository tourRepository;
    AjustementRepository ajustementRepository;

    @Autowired
    public GolfService(GolfRepository golfRepository,
                       ParcoursRepository parcoursRepository,
                       TrouRepository trouRepository, RoleRepository roleRepository,
                       UserRepository userRepository, TournoiRepository tournoiRepository,
                       TourRepository tourRepository, AjustementRepository ajustementRepository) {
        this.golfRepository = golfRepository;
        this.parcoursRepository = parcoursRepository;
        this.trouRepository = trouRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.tournoiRepository = tournoiRepository;
        this.tourRepository = tourRepository;
        this.ajustementRepository = ajustementRepository;
    }

    //////////////////////////////////////////////GOLF//////////////////////////////////////////////////////////////////////////////////
    public void saveGolfs(GolfFormDTO golfDTO) {
        Golf golfDB = golfRepository.findById(golfDTO.getId()).orElse(new Golf());
        golfDB.setId(golfDTO.getId());
        golfDB.setNom(Jsoup.parse(golfDTO.getNom()).text());
        golfDB.setLatitude(Jsoup.parse(golfDTO.getLatitude()).text());
        golfDB.setLongitude(Jsoup.parse(golfDTO.getLongitude()).text());
        golfRepository.save(golfDB);
    }

    public List<Golf> getGolfs() {
        return Lists.newArrayList(golfRepository.findAll());
    }

    public Golf getGolf(Long id) {
        return golfRepository.findById(id).orElse(new Golf());
    }

    public void deleteGolf(long id) {
        List<Parcours> parcours = parcoursRepository.findParcoursByGolf_Id(id);
        for (Parcours parcour : parcours) {
            deleteParcours(parcour.getId());
        }
        golfRepository.deleteById(id);
    }

    public void deleteTrou(long id) {
        List<Ajustement> ajustement = ajustementRepository.findByTrouId(id);
        for (Ajustement ajustements : ajustement) {
            deleteAjustement(ajustements.getId());
        }
        trouRepository.deleteById(id);
    }

    public void deleteAjustement(long id) {
        ajustementRepository.deleteById(id);
    }

    ////////////////////////////////////////////////////PARCOURS///////////////////////////////////////////////////////////////////////////
    public void saveParcours(ParcoursFormDTO parcoursDTO) {
        Parcours parcours = new Parcours();
        parcours.setId(parcoursDTO.getId());
        parcours.setNom(parcoursDTO.getNom());
        parcours.setGolf(golfRepository.findById(parcoursDTO.getGolfid()).orElse(new Golf()));

        for (Trou trou : parcoursDTO.getTrous()
        ) {
            trou.setParcours(parcours);

        }
        parcours.setTrous(parcoursDTO.getTrous());
        parcoursRepository.save(parcours);
        trouRepository.saveAll(parcours.getTrous());
    }


    public Parcours getParcours(Long id) {
        return parcoursRepository.findById(id).orElse(new Parcours());
    }

    public List<Parcours> getParcours() {
        return Lists.newArrayList(parcoursRepository.findAll());
    }

    public void deleteParcours(Long id) {
        List<Tournoi> tournoi = tournoiRepository.findTournoiByParcours_Id(id);
        for (Tournoi tournois : tournoi) {
            deleteTournois(tournois.getId());
        }
        List<Trou> trou = trouRepository.findTrouByParcours_Id(id);
        for (Trou trous : trou) {
            deleteTrou(trous.getId());
        }

        parcoursRepository.deleteById(id);

    }
///////////////////////////////////////////////////////TROU/////////////////////////////////////////////////////////////
    public List<Trou> getTrous() {
    return Lists.newArrayList(trouRepository.findAll());
}

/////////////////////////////////////////////////////TOURNOIS///////////////////////////////////////////////////////////
public void saveTournois(TournoisFormDTO tournoisFormDTO) {

    Tournoi tournoiDB = new Tournoi();
    Parcours parcours=parcoursRepository.findById(tournoisFormDTO.getParcours_id()).orElse(new Parcours());
    tournoiDB.setParcours(parcours);
    tournoiDB.setId(tournoisFormDTO.getId());
    tournoiDB.setNom(tournoisFormDTO.getNom());
    tournoiDB.setDateDebut(tournoisFormDTO.getDateDebut());
    tournoiDB.setCommentaire(tournoisFormDTO.getCommentaire());
    tournoiRepository.save(tournoiDB);
}
    public List<Tournoi> getTounois() {
        return Lists.newArrayList(tournoiRepository.findAll());
    }

    public Tournoi getTournoi(Long id) {
        return tournoiRepository.findById(id).orElse(new Tournoi());
    }

    public void deleteTournois(long id) {
        List<Tour> tour = tourRepository.findByTournoiId(id);
        for (Tour tours : tour) {
            deleteTours(tours.getId());
        }
        tournoiRepository.deleteById(id);
    }

    public Tournoi getTournoisByID(long id){
        return tournoiRepository.findById(id).get();
    }

    public Tour saveTours (TourFormDTO tourFormDTO)
    {
        Tour tour= new Tour();
        tour.setId(tourFormDTO.getId());
//        tour.setAjustement(tourFormDTO.getAjustements());
        tour.setDate(tourFormDTO.getDate());
        tour.setNbJoueursPartie(tourFormDTO.getNbJoueursPartie());
//        tour.setIntervalleDepart(tourFormDTO.getIntervalleDepart());
        tour.setTournoi(tournoiRepository.findById(tourFormDTO.getIdTournois()).get());
        for (Ajustement ajustement:tourFormDTO.getAjustements()
        ) {
            ajustement.setTour(tour);
        }
        tour.setAjustement(tourFormDTO.getAjustements());
        tourRepository.save(tour);
        ajustementRepository.saveAll(tour.getAjustement());
        return tour;
    }
    public void deleteTours(long id) {
        List<Ajustement> ajustement = ajustementRepository.findByTourId(id);
        for (Ajustement ajustements : ajustement) {
            deleteAjustement(ajustements.getId());
        }
        tourRepository.deleteById(id);
    }

    public List<Rate> generateCadence( Team team, Tour tour)throws ParseException{
        List<Rate>rates=new ArrayList<>();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH'h'mm");
        Date jour=tour.getDate();
        Calendar cl = Calendar.getInstance();
        cl.setTime(jour);
        for (Ajustement ajustement:tour.getAjustement()) {
            Date d0;
            int tempsStandar;
            if (ajustement.getTrou().getPar()==3){
                tempsStandar=11;
            }
            else if (ajustement.getTrou().getPar()==4){
                tempsStandar=14;
            }
            else {
                tempsStandar=17;
            }
            if (rates.size()==0){
                d0=jour;
                Date time=simpleDateFormat.parse(team.getStart());
                cl.add(Calendar.HOUR,time.getHours());
                cl.add(Calendar.MINUTE,time.getMinutes()+ajustement.getTempsAjuste()+tempsStandar);
            }
            else {
                d0=rates.get(rates.size()-1).getDate();
                cl.add(Calendar.MINUTE,ajustement.getTempsAjuste()+tempsStandar);
            }
            Rate rate=new Rate(ajustement,cl.getTime());
            rates.add(rate);
        }
        team.setRates(rates);
        return rates;
    }

    public Document generatePDF(List<Team> teams, Tour tour)throws FileNotFoundException, DocumentException {
        Document document=new Document(PageSize.A2.rotate());
        PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/static/pdf/cadence"+tour.getId()+".pdf"));
        document.open();
        Font font= FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk=new Chunk(tour.getTournoi().getNom(),font);

        PdfPTable table=new PdfPTable(21);
        table.setWidthPercentage(100);
        addTableHeader(table);
        for (Team t:teams) {
            addRows(table,t);
        }
        document.add(chunk);
        document.add(table);
        document.close();
        return document;

    }



    private void addTableHeader(PdfPTable table){
        Stream.of("#","Départ","Joueurs", "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18")
                .forEach(columnTitle ->{
                    PdfPCell header =new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });


    }
    private void addRows(PdfPTable table, Team team) {
        table.addCell(team.getNum());
        table.addCell(team.getStart());
        PdfPTable tableNoms=new PdfPTable(1);
        for (Player p:team.getPlayers()) {
            tableNoms.addCell(p.getName());
        }
        table.addCell(tableNoms);
        DateFormat dateFormat=new SimpleDateFormat("hh:mm");
        for (Rate r:team.getRates()) {
            table.addCell(dateFormat.format(r.getDate()));
        }
    }
    public Tour getTour(Long id) {
        return tourRepository.findById(id).orElse(new Tour()) ;}

    public Role getRoleByName(String user) {
        return roleRepository.getRoleByNom(user);
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//    public int savePdf(TourFormDTO tourFormDTO, InputStream fi) {
////        tourFormDTO.setFile(save(fi));
//        return 0;
//    }
//
//    public InputStream retreivepdf1(String fileName){
//        return retreivepdf1( fileName);
//    }
//
//    private InputStream retreivepdf( String fileName){
//        InputStream is = null;
//        try {
//            is = new FileInputStream(path+ "/" +fileName);
//        } catch (FileNotFoundException fnfe) {
//            System.out.println("Erreur récupération du pdf "+fileName+" : "+fnfe.getMessage());
//        }
//        return is;
//    }
//
//    private String save( InputStream fi) {
//        String fileName = "";
//        try (DirectoryStream<Path> dir = Files.newDirectoryStream(Paths.get(path + "/" ),  "*")) {
//
//            for (Path file : dir
//            ) {
//                if (fileName.compareTo(file.getFileName().toString()) < 0) {
//                    fileName = file.getFileName().toString();
//                }
//            }
//            String numStr = fileName.substring(1, fileName.indexOf(".pdf"));
//
//            Integer num = Integer.parseInt(numStr);
//
//            numStr = String.format("%04d", num + 1);
//
//            fileName =  numStr + ".pdf";
//
//            String filePath = path + "/" + fileName;
//
//            Files.copy(fi, new File(filePath).toPath());
//
//        } catch (IOException ioe) {
//            System.out.println("Erreur sur nom du pdf : " + ioe.getMessage());
//        }
//        return fileName;
//    }
}



