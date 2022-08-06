package com.example.backerp.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Detailevenement {
    private Integer prix;
    private Integer places;
    private int idetail;
    private String localisation;
    private Integer promotion;
    private Evenement evenementsByIdetail;
    private String datedeb;
    private String datefin;

    public Detailevenement(int idetail,String localisation, int prix, int promotion, int places, String dateDebut, String dateFin) {
        this.idetail= idetail;
        this.localisation = localisation;
        this.prix = prix;
        this.promotion = promotion;
        this.places = places;
        this.datedeb = dateDebut;
        this.datefin = dateFin;
    }

    @OneToOne(mappedBy = "detailevenementByDetails")
    @JsonBackReference
    public Evenement getEvenementsByIdetail() {
        return evenementsByIdetail;
    }

    public void setEvenementsByIdetail(Evenement evenementsByIdetail) {
        this.evenementsByIdetail = evenementsByIdetail;
    }

    @Basic
    @Column(name = "prix")
    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    @Basic
    @Column(name = "places")
    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    @Id
    @Column(name = "idetail")
    public int getIdetail() {
        return idetail;
    }

    public void setIdetail(int idetail) {
        this.idetail = idetail;
    }

    @Basic
    @Column(name = "localisation")
    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    @Basic
    @Column(name = "promotion")
    public Integer getPromotion() {
        return promotion;
    }

    public void setPromotion(Integer promotion) {
        this.promotion = promotion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Detailevenement that = (Detailevenement) o;
        return idetail == that.idetail && Objects.equals(prix, that.prix) && Objects.equals(places, that.places) && Objects.equals(localisation, that.localisation) && Objects.equals(promotion, that.promotion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prix, places, idetail, localisation, promotion);
    }



    @Basic
    @Column(name = "datedeb")
    public String getDatedeb() {
        return datedeb;
    }

    public void setDatedeb(String datedeb) {
        this.datedeb = datedeb;
    }

    @Basic
    @Column(name = "datefin")
    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }
}
