package com.app.taxes.dao;

import com.app.taxes.Domain.Entreprise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {

    Page<Entreprise> findByNomSocieteContainsIgnoreCase(String text, Pageable pageable);
}
