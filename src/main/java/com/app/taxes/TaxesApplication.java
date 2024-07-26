package com.app.taxes;

import com.app.taxes.Domain.Entreprise;
import com.app.taxes.Domain.IR;
import com.app.taxes.Domain.TVA;
import com.app.taxes.Domain.Taxe;
import com.app.taxes.dao.EntrepriseRepository;
import com.app.taxes.dao.TaxeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class TaxesApplication implements CommandLineRunner {

    private final EntrepriseRepository entrepriseRepository;
    private final TaxeRepository taxeRepository;

    public TaxesApplication(EntrepriseRepository entrepriseRepository, TaxeRepository taxeRepository) {
        this.entrepriseRepository = entrepriseRepository;
        this.taxeRepository = taxeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TaxesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Entreprise e1 = entrepriseRepository.save(
                new Entreprise("email1", "nom1", "raison1")
        );
        Entreprise e2 = entrepriseRepository.save(
                new Entreprise("email2", "nom2", "raison2")
        );
        Taxe t1 = taxeRepository.save(
                new TVA("tva1", new Date(), 1000, e1)
        );
        Taxe t2 = taxeRepository.save(
                new TVA("tva2", new Date(), 2000, e2)
        );
        Taxe t3 = taxeRepository.save(
                new IR("ir1", new Date(), 3000, e2)
        );
        Taxe t4 = taxeRepository.save(
                new IR("ir2", new Date(), 4000, e1)
        );
        Entreprise e3 = entrepriseRepository.save(
                new Entreprise("email3", "nom3", "raison3")
        );
        Entreprise e4 = entrepriseRepository.save(
                new Entreprise("email4", "nom4", "raison4")
        );
        Taxe t5 = taxeRepository.save(
                new TVA("tva1", new Date(), 1000, e3)
        );
        Taxe t6 = taxeRepository.save(
                new TVA("tva2", new Date(), 2000, e4)
        );
        Taxe t7 = taxeRepository.save(
                new IR("ir1", new Date(), 3000, e4)
        );
        Taxe t8 = taxeRepository.save(
                new IR("ir2", new Date(), 4000, e3)
        );
    }
}
