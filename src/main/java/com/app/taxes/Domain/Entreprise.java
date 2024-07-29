package com.app.taxes.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Collection;

@Entity
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @Email
    @Size(min = 6, message = "la taille doit être 6 ou plus")
    private String email;

    @Size(min = 3, max = 30)
    private String nomSociete;

    @Size(min = 3, max = 30)
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
