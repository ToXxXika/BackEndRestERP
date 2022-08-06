package com.example.backerp.Resolvers;

import com.example.backerp.Models.*;
import com.example.backerp.Repositories.*;
import graphql.GraphQLException;
import graphql.GraphqlErrorException;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@Component
public class QueryResolver  {

     @Autowired
     private UtilisateurRepository UR ;
     @Autowired
     private EvenementRepository ER;
     @Autowired
     private AssociationsRepository AR ;
     @Autowired
     private ConventionsRepository CR ;
     @Autowired
     private DetailevenementRepository DER;
    @Autowired
    private LogParticipationRepository LPR ;
    @Autowired
    private PaieRepository PR ;


    public List<Utilisateur> getAllusers(){
         return (List<Utilisateur>) UR.findAll();
     }
     public Utilisateur getUserByCin(String cin){
          Optional<Utilisateur> utilisateur = UR.findByCin(cin);
          if(utilisateur.isPresent())
               return utilisateur.get();
          throw new GraphQLException("utilisateur non trouvé");

     }

        public List<Evenement> getAllEvents() {
           List<Evenement> evenements = (List<Evenement>) ER.findAll();
            if(evenements.isEmpty()){
                System.out.println("liste vide");
                return null;
           }
           else return evenements;
        }

        public List<Associations> getAllAssociations(){
            return (List<Associations>) AR.findAll();
        }
     //get all Conventions from the database and return them as a list of Conventions
        public List<Conventions> getAllConventions(){
            return (List<Conventions>) CR.findAll();
        }

        public boolean AddUser(Utilisateur user){
         try {
                UR.save(user);
                System.out.println("User added");
                return true;
            }
            catch (Exception e){
                System.out.println("User not added"+e.getMessage());
                return false;
         }
        }
        public boolean AddEvent(Evenement event, Detailevenement DE){
          boolean Res = false ;
            try{
                if(DER.save(DE) != null){
                    event.setDetails(DE.getIdetail());
                    ER.save(event);
                    Res=  true;
                }

            } catch (Exception e){
              System.out.println(e.getMessage());
            }
            return Res;
        }
     public Boolean deleteEvent(Integer idDetail,String codeevenment){
        Optional<Evenement>  Event= ER.deleteEvenementByCodevenement(codeevenment);
        if(Event.isPresent()){
            ER.delete(Event.get());
            DER.deleteById(idDetail);
            return true ;
        }else{
            return false ;
        }
    }
    public boolean updateplaces(Detailevenement DE,int NbrPlaces){
        boolean Res = false ;
        try {
            DE.setPlaces(DE.getPlaces()-NbrPlaces);
            DER.save(DE);
            Res=  true;
        }catch (Exception GEE){
            System.out.println(GEE.getMessage());
        }
        return Res ;
    }
    public Boolean participate(Logparticipation LP,Integer NbrPlaces){
        boolean Res = false ;
        Optional<Evenement> evenement = ER.findById(LP.getIdeventfk());
        if(evenement.isPresent()){
            Optional<Detailevenement> detailevenement = DER.findById(evenement.get().getDetails());
            if(detailevenement.isPresent()){
                if(detailevenement.get().getPlaces()>NbrPlaces){
                    try {
                        LPR.save(LP);
                        int montant= LP.getMontantpayé();
                        int idevent = LP.getIdeventfk();
                        Iterable<Paie> P = PR.findByRefconv(idevent);
                        if(P.iterator().hasNext()){
                            Paie paie = P.iterator().next();
                            paie.setMontanttotal(paie.getMontanttotal()+montant);
                            paie.setMontantpayeparagile(paie.getMontantpayeparagile()+((detailevenement.get().getPrix()) * (detailevenement.get().getPromotion()))/100);
                            if(updateplaces(detailevenement.get(),NbrPlaces)){
                                PR.save(paie);
                                Res=  true;
                            }
                        }else{
                            Paie paie = new Paie();
                            paie.setMontanttotal(montant);
                            paie.setRefconv(P.iterator().next().getRefconv());
                            paie.setMontantpayeparagile((detailevenement.get().getPrix()*detailevenement.get().getPromotion())/100);
                            PR.save(paie);
                            Res=  true;
                        }
                    }catch (GraphqlErrorException GEE){
                        System.out.println(GEE.getMessage());
                    }
                }
            }
        }
        return Res ;
    }

}
