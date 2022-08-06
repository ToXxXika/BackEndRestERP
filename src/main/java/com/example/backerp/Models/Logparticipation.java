package com.example.backerp.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Logparticipation {
    private int idparticipation;
    private Integer idutilisateur;
    private Integer ideventfk;
    private Integer montantpayé;
    private Utilisateur utilisateurByIdutilisateur;
    private Evenement evenementByIdeventfk;

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
    @Column(name = "idutilisateur" ,insertable = false,updatable = false)
    public Integer getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(Integer idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    @Basic
    @Column(name = "ideventfk" ,insertable = false,updatable = false)
    public Integer getIdeventfk() {
        return ideventfk;
    }

    public void setIdeventfk(Integer ideventfk) {
        this.ideventfk = ideventfk;
    }

    @Basic
    @Column(name = "montantpayé")
    public Integer getMontantpayé() {
        return montantpayé;
    }

    public void setMontantpayé(Integer montantpayé) {
        this.montantpayé = montantpayé;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Logparticipation that = (Logparticipation) o;
        return idparticipation == that.idparticipation && Objects.equals(idutilisateur, that.idutilisateur) && Objects.equals(ideventfk, that.ideventfk) && Objects.equals(montantpayé, that.montantpayé);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idparticipation, idutilisateur, ideventfk, montantpayé);
    }

    @ManyToOne
    @JoinColumn(name = "idutilisateur", referencedColumnName = "id")
    public Utilisateur getUtilisateurByIdutilisateur() {
        return utilisateurByIdutilisateur;
    }

    public void setUtilisateurByIdutilisateur(Utilisateur utilisateurByIdutilisateur) {
        this.utilisateurByIdutilisateur = utilisateurByIdutilisateur;
    }

    @ManyToOne
    @JoinColumn(name = "ideventfk", referencedColumnName = "idevent")
    public Evenement getEvenementByIdeventfk() {
        return evenementByIdeventfk;
    }

    public void setEvenementByIdeventfk(Evenement evenementByIdeventfk) {
        this.evenementByIdeventfk = evenementByIdeventfk;
    }
}
