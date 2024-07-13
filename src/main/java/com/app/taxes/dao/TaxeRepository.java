package com.app.taxes.dao;

import com.app.taxes.Domain.Taxe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxeRepository extends JpaRepository<Taxe, Long> {
}
