package com.example.backerp.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.extern.java.Log;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class Utilisateur implements UserDetails {
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
    @OneToMany(mappedBy = "utilisateurByIdutilisateur")
    @JsonIgnore
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

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Transient
    @Override
    public String getUsername() {
        return mail;
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return true;
    }
    @Transient
    @Override
    public String getPassword() {
        return password;
    }
}
