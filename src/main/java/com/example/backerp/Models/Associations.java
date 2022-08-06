package com.example.backerp.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Associations {

    private String libelle;
    private String reference;

    private Collection<Conventions> conventionsByReference;

    @Basic
    @Column(name = "Libelle")
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }


    @Id
    @Basic
    @Column(name = "reference")
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Associations that = (Associations) o;
        return Objects.equals(reference, that.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }

    @OneToMany(mappedBy = "associationsByIdassoc")
    @JsonManagedReference
    public Collection<Conventions> getConventionsByReference() {
        return conventionsByReference;
    }

    public void setConventionsByReference(Collection<Conventions> conventionsByReference) {
        this.conventionsByReference = conventionsByReference;
    }
}
