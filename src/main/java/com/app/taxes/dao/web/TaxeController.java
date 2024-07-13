package com.app.taxes.dao.web;

import com.app.taxes.Domain.Entreprise;
import com.app.taxes.Domain.Taxe;
import com.app.taxes.dao.EntrepriseRepository;
import com.app.taxes.dao.TaxeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String index(Model model){
        List<Entreprise> entreprises = entrepriseRepository.findAll();
        model.addAttribute("entrepriseList", entreprises);
        return "entreprises";
    }

//    @GetMapping("/taxes")
//    public String taxes(Model model){
//        List<Taxe> taxes = taxeRepository.findAll();
//        model.addAttribute("taxeList", taxes);
//        return null;
//    }
}
