package com.example.backerp.Models;

import lombok.*;
import lombok.extern.java.Log;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Utilisateur {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nom")
    private String nom;
    @Basic
    @Column(name = "prenom")
    private String prenom;
    @Basic
    @Column(name = "cin")
    private String cin;
    @Basic
    @Column(name = "mail")
    private String mail;
    @Basic
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "utilisateurByIdutilisateur",targetEntity = Logparticipation.class,fetch =FetchType.EAGER)

    private Collection<Logparticipation> logparticipationsById;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Utilisateur that = (Utilisateur) o;
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Collection<Logparticipation> getLogparticipationsById() {
        return logparticipationsById;
    }

    public void setLogparticipationsById(Collection<Logparticipation> logparticipationsById) {
        this.logparticipationsById = logparticipationsById;
    }
}
