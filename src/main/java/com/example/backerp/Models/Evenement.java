package com.example.backerp.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Evenement {
    private int idevent;

    private String description;
    private Integer details;
    private Collection<Logparticipation> logparticipationsByIdevent;
    private String codevenement;
    private String refconv;
    private Conventions conventionsByRefconv;
    private Detailevenement detailevenementByDetails;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idevent")
    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "details")
    public Integer getDetails() {
        return details;
    }

    public void setDetails(Integer details) {
        this.details = details;
    }

    @OneToMany(mappedBy = "evenementByIdeventfk")
    public Collection<Logparticipation> getLogparticipationsByIdevent() {
        return logparticipationsByIdevent;
    }

    public void setLogparticipationsByIdevent(Collection<Logparticipation> logparticipationsByIdevent) {
        this.logparticipationsByIdevent = logparticipationsByIdevent;
    }

    @Basic
    @Column(name = "codevenement")
    public String getCodevenement() {
        return codevenement;
    }

    public void setCodevenement(String codevenement) {
        this.codevenement = codevenement;
    }

    @Basic
    @Column(name = "refconv")
    public String getRefconv() {
        return refconv;
    }

    public void setRefconv(String refconv) {
        this.refconv = refconv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evenement evenement = (Evenement) o;
        return Objects.equals(refconv, evenement.refconv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refconv);
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "refconv", referencedColumnName = "referenceconv",updatable = false,insertable = false)
    public Conventions getConventionsByRefconv() {
        return conventionsByRefconv;
    }

    public void setConventionsByRefconv(Conventions conventionsByRefconv) {
        this.conventionsByRefconv = conventionsByRefconv;
    }

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "details", referencedColumnName = "idetail",insertable = false,updatable = false)
    public Detailevenement getDetailevenementByDetails() {
        return detailevenementByDetails;
    }

    public void setDetailevenementByDetails(Detailevenement detailevenementByDetails) {
        this.detailevenementByDetails = detailevenementByDetails;
    }

}
