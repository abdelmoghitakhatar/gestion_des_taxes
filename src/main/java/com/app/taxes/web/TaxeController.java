package com.app.taxes.web;

import com.app.taxes.Domain.Entreprise;
import com.app.taxes.Domain.Taxe;
import com.app.taxes.dao.EntrepriseRepository;
import com.app.taxes.dao.TaxeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TaxeController {

    private final EntrepriseRepository entrepriseRepository;
    private final TaxeRepository taxeRepository;

    public TaxeController(EntrepriseRepository entrepriseRepository, TaxeRepository taxeRepository) {
        this.entrepriseRepository = entrepriseRepository;
        this.taxeRepository = taxeRepository;
    }

    @GetMapping("/entreprises")
    public String index(Model model,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size,
                        @RequestParam(defaultValue = "") String motCle){
        Pageable pageable = PageRequest.of(page, size, Sort.by("code"));
        Page<Entreprise> entreprises = entrepriseRepository.findByNomSocieteContainsIgnoreCase(motCle, pageable);
        model.addAttribute("entrepriseList", entreprises.stream().toList());
        int[] pages = new int[entreprises.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        model.addAttribute("motCle", motCle);
        return "entreprises";
    }
}
