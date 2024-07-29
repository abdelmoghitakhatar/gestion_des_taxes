package com.app.taxes.dao;

import com.app.taxes.Domain.Entreprise;
import com.app.taxes.Domain.Taxe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxeRepository extends JpaRepository<Taxe, Long> {

    Page<Taxe> findAllByEntrepriseCode(Long code, Pageable pageable);
}
