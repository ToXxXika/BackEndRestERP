package com.example.backerp.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Logparticipation {
    private int idparticipation;
    private Integer idutilisateur;
    private Integer ideventfk;
    private Integer montantpaye;
    private Utilisateur utilisateurByIdutilisateur;
    private Evenement evenementByIdeventfk;

    public Logparticipation(int idutilisateur, int ideventfk, int montantpaye) {
        this.idutilisateur = idutilisateur;
        this.ideventfk = ideventfk;
        this.montantpaye = montantpaye;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idparticipation")
    public int getIdparticipation() {
        return idparticipation;
    }

    public void setIdparticipation(int idparticipation) {
        this.idparticipation = idparticipation;
    }

    @Basic
    @Column(name = "idutilisateur" )
    public Integer getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(Integer idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    @Basic
    @Column(name = "ideventfk" )
    public Integer getIdeventfk() {
        return ideventfk;
    }

    public void setIdeventfk(Integer ideventfk) {
        this.ideventfk = ideventfk;
    }

    @Basic
    @Column(name = "montantpaye")
    public Integer getMontantpaye() {
        return montantpaye;
    }

    public void setMontantpaye(Integer montantpaye) {
        this.montantpaye = montantpaye;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Logparticipation that = (Logparticipation) o;
        return idparticipation == that.idparticipation && Objects.equals(idutilisateur, that.idutilisateur) && Objects.equals(ideventfk, that.ideventfk) && Objects.equals(montantpaye, that.montantpaye);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idparticipation, idutilisateur, ideventfk, montantpaye);
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "idutilisateur", referencedColumnName = "id",insertable = false,updatable = false)
    public Utilisateur getUtilisateurByIdutilisateur() {
        return utilisateurByIdutilisateur;
    }

    public void setUtilisateurByIdutilisateur(Utilisateur utilisateurByIdutilisateur) {
        this.utilisateurByIdutilisateur = utilisateurByIdutilisateur;
    }

    @ManyToOne
    @JsonIgnore

    @JsonBackReference
    @JoinColumn(name = "ideventfk", referencedColumnName = "idevent",insertable = false,updatable = false)
    public Evenement getEvenementByIdeventfk() {
        return evenementByIdeventfk;
    }

    public void setEvenementByIdeventfk(Evenement evenementByIdeventfk) {
        this.evenementByIdeventfk = evenementByIdeventfk;
    }
}
