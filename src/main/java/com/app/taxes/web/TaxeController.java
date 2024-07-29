package com.app.taxes.web;

import com.app.taxes.Domain.Entreprise;
import com.app.taxes.Domain.Taxe;
import com.app.taxes.dao.EntrepriseRepository;
import com.app.taxes.dao.TaxeRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TaxeController {

    private final TaxeRepository taxeRepository;
    private final EntrepriseRepository entrepriseRepository;

    public TaxeController(TaxeRepository taxeRepository, EntrepriseRepository entrepriseRepository) {
        this.taxeRepository = taxeRepository;
        this.entrepriseRepository = entrepriseRepository;
    }

    @GetMapping("/taxes")
    public String getEntrepriseTaxes(Model model,
                                     @RequestParam(defaultValue = "-1") long code,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        List<Entreprise> entreprises = entrepriseRepository.findAll();
        Page<Taxe> taxes;
        if (code < 0){
            taxes = taxeRepository.findAll(pageable);
        } else {
            taxes = taxeRepository.findAllByEntrepriseCode(code, pageable);
        }
        int[] pages = new int[taxes.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("entreprises", entreprises);
        model.addAttribute("taxes", taxes.stream().toList());
        model.addAttribute("code", code);
        model.addAttribute("currentPage", page);
        return "taxes";
    }
}
