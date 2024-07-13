package com.app.taxes.Domain;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String email;

    private String nomSociete;

    private String raisonSociale;

    @OneToMany(mappedBy = "entreprise", fetch = FetchType.LAZY)
    private Collection<Taxe> taxes;

    public Entreprise(String email, String nomSociete, String raisonSociale) {
        this.email = email;
        this.nomSociete = nomSociete;
        this.raisonSociale = raisonSociale;
    }

    public Entreprise() {
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public Collection<Taxe> getTaxes() {
        return taxes;
    }

    public void setTaxes(Collection<Taxe> taxes) {
        this.taxes = taxes;
    }
}
