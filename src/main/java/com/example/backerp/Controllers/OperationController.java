package com.example.backerp.Controllers;


import com.example.backerp.Models.*;
import com.example.backerp.Repositories.*;
import com.example.backerp.Resolvers.QueryResolver;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileWriter;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/operation")
public class OperationController {
    private final QueryResolver queryResolver;


    @Autowired
    private AssociationsRepository AR;
    @Autowired
    private ConventionsRepository CR;
    @Autowired
    private DetailevenementRepository ER;
    @Autowired
    private EvenementRepository EE;
    @Autowired
    private UtilisateurRepository UR;

    public OperationController(QueryResolver queryResolver) {
        this.queryResolver = queryResolver;
    }


    public boolean AddConvention(Conventions C) {
        System.out.println("AddConvention");
        try {
            CR.save(C);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean AddAssociation(Associations A) {
        System.out.println("Adding Association");
        // display A in console
        System.out.println("Libelle" + A.getLibelle() + " Reference" + A.getReference());
        boolean Res = false;

        try {
            this.AR.save(A);
            System.out.println("Association Saved");
            Res = true;
        } catch (Exception E) {
            System.out.println(E.getMessage());
        }
        return Res;
    }

    public boolean AddDetailEvent(Detailevenement Detail) {
        boolean Res = false;
        try {
            ER.save(Detail);
            System.out.println("Detail Evenement Saved");
            Res = true;
        } catch (Exception E) {
            System.out.println("Detail Evenement non trouvée" + E.getMessage());
        }
        return Res;
    }

    public boolean AddEvent(Evenement E) {
        boolean Res = false;
        try {
            EE.save(E);
            System.out.println("Evenement Saved");
            Res = true;
        } catch (Exception E1) {
            System.out.println("Evenement non trouvée");
        }
        return Res;
    }

    //add user to the database
    public boolean AddUser(Utilisateur U) {
        boolean Res = false;
        try {
            UR.save(U);
            System.out.println("Utilisateur Saved");
            Res = true;
        } catch (Exception E) {
            System.out.println("Utilisateur non trouvée");
        }
        return Res;
    }
    @PostMapping("/loadEventFile")
    public void excelReader2(@RequestParam("file") MultipartFile excel) throws IOException {
        int i = 1;
        XSSFWorkbook workbook = new XSSFWorkbook(excel.getInputStream());
        XSSFSheet sheet1 = workbook.getSheetAt(0);
        while (i < sheet1.getPhysicalNumberOfRows()) {
            System.out.println("Loading Row " + i);
            XSSFRow row = sheet1.getRow(i);
            if (AR.findById(row.getCell(0).toString()).isEmpty()) {
                String Association = row.getCell(0).toString();
                String Reference = row.getCell(1).toString();
                String referenceConvention = row.getCell(2).toString();
                String EvenementDescription = row.getCell(3).toString();
                System.out.println("********************************************");
                System.out.println("Evenement Description" + EvenementDescription);
                Associations A = new Associations();
                A.setLibelle(Association);
                A.setReference(Reference);
                if (AddAssociation(A)) {
                    Conventions C = new Conventions();
                    C.setIdassoc(A.getReference());
                    C.setReferenceconv(referenceConvention);
                    if (AddConvention(C)) {
                        System.out.println("Convention Saved");
                        XSSFSheet EventSheet = workbook.getSheetAt(1);
                        for (int k = 1; k < EventSheet.getPhysicalNumberOfRows(); k++) {
                            XSSFRow row2 = EventSheet.getRow(k);
                            if (row2.getCell(0).toString().equals(EvenementDescription)) {
                                System.out.println("Evemenement Found");
                                String CodeEvenement = row2.getCell(1).toString();
                                int Prix = 150;
                                int places = 10;
                                String Localisation = row2.getCell(4).toString();
                                int promotion = 20;
                                String DateDebut = "12/12/2019";
                                String DateFin = "12/12/2019";
                                int Det = (int) (Math.random() * 999999);
                                Detailevenement DE = new Detailevenement(Det, Localisation, Prix, promotion, places, DateDebut, DateFin);
                                if (AddDetailEvent(DE)) {
                                    System.out.println("Detail Evenement Ajouté");
                                    Evenement E = new Evenement();
                                    E.setDetails(DE.getIdetail());
                                    E.setRefconv(C.getReferenceconv());
                                    E.setCodevenement(CodeEvenement);
                                    E.setDescription(EvenementDescription);
                                    if (AddEvent(E)) {
                                        System.out.println("Evenement Ajouté");
                                        System.out.println("Finished");
                                    }
                                }
                            }
                        }
                    }
                    i++;
                }
            } else {
                System.out.println("Association déjà existante");
                i++;
            }
        }
    }

    public void DataExport() {
        // export all my database into a csv file
        DataFormatter df = new DataFormatter();
        StringBuilder sb = new StringBuilder();
        sb.append("Associations");
        sb.append("\n");
        for (Associations A : AR.findAll()) {
            sb.append(A.getLibelle());
            sb.append(",");
            sb.append(A.getReference());
            sb.append("\n");
        }
        sb.append("Conventions");
        sb.append("\n");
        for (Conventions C : CR.findAll()) {
            sb.append(C.getIdassoc());
            sb.append(",");
            sb.append(C.getReferenceconv());
            sb.append("\n");
        }
        sb.append("Detail Evenements");
        sb.append("\n");
        for (Detailevenement DE : ER.findAll()) {
            sb.append(DE.getIdetail());
            sb.append(",");
            sb.append(DE.getLocalisation());
            sb.append(",");
            sb.append(DE.getPrix());
            sb.append(",");
            sb.append(DE.getPromotion());
            sb.append(",");
            sb.append(DE.getPlaces());
            sb.append(",");
            sb.append(DE.getDatedeb());
            sb.append(",");
            sb.append(DE.getDatefin());
            sb.append("\n");
        }
        sb.append("Evenements");
        sb.append("\n");
        for (Evenement E : EE.findAll()) {
            sb.append(E.getCodevenement());
            sb.append(",");
            sb.append(E.getDescription());
            sb.append(",");
            sb.append(E.getRefconv());
            sb.append(",");
            sb.append(E.getDetails());
            sb.append("\n");
        }
        System.out.println(sb.toString());
        try {
            FileWriter writer = new FileWriter("C:\\Users\\HP\\Documents\\NetBeansProjects\\Association\\src\\main\\resources\\export.csv");
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    //

    @PostMapping("/uploadfile")
    public void exceluserReader(@RequestParam("file") MultipartFile excel) throws IOException {

        // read a csv file and add it into the database
        XSSFWorkbook workbook = new XSSFWorkbook(excel.getInputStream());
        XSSFSheet sheet1 = workbook.getSheetAt(0);
        int i = 1;
        while (i < sheet1.getPhysicalNumberOfRows()) {
            System.out.println("Loading Row " + i);
            XSSFRow row = sheet1.getRow(i);
            String Nom = row.getCell(0).toString();
            String Prenom = row.getCell(1).toString();
            String Email = row.getCell(3).toString();
            String Password = row.getCell(4).toString();
            String Cin = row.getCell(2).toString();
            Utilisateur U = new Utilisateur();
            U.setNom(Nom);
            U.setPrenom(Prenom);
            U.setMail(Email);
            U.setPassword(Password);
            U.setCin(Cin);
            if (AddUser(U)) {
                System.out.println("User Saved");
                i++;
            }

            if (i == sheet1.getPhysicalNumberOfRows()) {
                System.out.println("Finished");
            }
        }
    }

    @GetMapping("/loadAll")
    public boolean ExtractAll(@RequestParam("file") MultipartFile excel) throws IOException {
        return false;
    }

    @GetMapping("/getAllusers")

    public List<Utilisateur> getAllusers(){
        return queryResolver.getAllusers();
    }
    @GetMapping("/getUserByCin")

    public Utilisateur getUserByCin(@RequestParam(name = "cin") String cin){
        return queryResolver.getUserByCin(cin);
    }
    @GetMapping("getAllEvents")

    public List<Evenement> getAllEvents(){
        return queryResolver.getAllEvents();
    }
    @GetMapping("/getAllAssociations")
    public List<Associations> getAllAssociations(){
        return queryResolver.getAllAssociations();
    }

    @GetMapping("/getAllConventions")
    public List<Conventions> getAllConventions(){
        return queryResolver.getAllConventions();
    }

    @PostMapping("/addEvent")
    public boolean addEvent(@RequestBody Evenement evenement){
        return queryResolver.AddEvent(evenement);
    }
    @PostMapping("/addUser")
    public boolean addUser(@RequestBody Utilisateur utilisateur){
        return queryResolver.AddUser(utilisateur);
    }

    @GetMapping("/login")
    public Principal login(Principal p){
        return queryResolver.login(p);
    }
    @GetMapping("/deleteUserByCin")
    public boolean deleteUserByCin(@RequestParam(name = "cin") String cin){
        return queryResolver.DeleteUser(cin);
    }

    @PostMapping("/addDetailEvent")
    public boolean addDetailEvent(@RequestBody Detailevenement detailEvenement){
        return queryResolver.addDetailEveent(detailEvenement);
    }
    @PostMapping("/participate")
    public boolean participate(@RequestBody Logparticipation lp,@RequestParam(name = "places") int places){
        return queryResolver.participate(lp,places);
    }
    @PostMapping("/savelog")
    public boolean savelog(@RequestBody Logparticipation lp){
        return queryResolver.saveLog(lp);
    }

}