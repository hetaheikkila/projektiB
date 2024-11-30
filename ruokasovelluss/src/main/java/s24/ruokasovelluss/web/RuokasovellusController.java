package s24.ruokasovelluss.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import s24.ruokasovelluss.domain.Ainesosa;
import s24.ruokasovelluss.domain.AinesosaRepository;
import s24.ruokasovelluss.domain.Kategoria;
import s24.ruokasovelluss.domain.KategoriaRepository;
import s24.ruokasovelluss.domain.Resepti;
import s24.ruokasovelluss.domain.ReseptiRepository;

@Controller
public class RuokasovellusController {

    @Autowired
    private KategoriaRepository kategoriaRepository;

    @Autowired
    private AinesosaRepository ainesosaRepository;

    @Autowired
    private ReseptiRepository reseptiRepository;

    // Kategoriat Section
    @GetMapping("/")
    public String showIndexPage() {
        return "login"; // This should match the filename in templates (index.html)
    }

    @GetMapping("/kategoriat")
    public String listKategoriat(Model model) {
        model.addAttribute("kategoriat", kategoriaRepository.findAll());
        model.addAttribute("kategoria", new Kategoria());
        return "kategoriat";
    }

    @PostMapping("/kategoriat/save")
    public String saveKategoria(@Valid @ModelAttribute("kategoria") Kategoria kategoria, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("kategoriat", kategoriaRepository.findAll());
            return "kategoriat";
        }
        kategoriaRepository.save(kategoria);
        return "redirect:/kategoriat";
    }

    @GetMapping("/kategoriat/delete/{id}")
    public String deleteKategoria(@PathVariable Long id) {
        kategoriaRepository.deleteById(id);
        return "redirect:/kategoriat";
    }

    @GetMapping("/kategoriat/edit/{id}")
    public String editKategoria(@PathVariable Long id, Model model) {
        Kategoria kategoria = kategoriaRepository.findById(id).orElse(null);
        model.addAttribute("kategoria", kategoria);
        model.addAttribute("kategoriat", kategoriaRepository.findAll());
        return "kategoriat";
    }

    // Ainesosat Section
    @GetMapping("/ainesosat")
    public String listAinesosat(Model model) {
        model.addAttribute("ainesosat", ainesosaRepository.findAll());
        model.addAttribute("ainesosa", new Ainesosa());
        return "ainesosat";
    }

    @PostMapping("/ainesosat/save")
    public String saveAinesosa(@Valid @ModelAttribute("ainesosa") Ainesosa ainesosa, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("ainesosat", ainesosaRepository.findAll());
            return "ainesosat";
        }
        ainesosaRepository.save(ainesosa);
        return "redirect:/ainesosat";
    }

    @GetMapping("/ainesosat/delete/{id}")
    public String deleteAinesosa(@PathVariable Long id) {
        ainesosaRepository.deleteById(id);
        return "redirect:/ainesosat";
    }

    @GetMapping("/ainesosat/edit/{id}")
    public String editAinesosa(@PathVariable Long id, Model model) {
        Ainesosa ainesosa = ainesosaRepository.findById(id).orElse(null);
        model.addAttribute("ainesosa", ainesosa);
        model.addAttribute("ainesosat", ainesosaRepository.findAll());
        return "ainesosat";
    }

    // Reseptit Section
    @GetMapping("/reseptit")
public String showReseptit(Model model) {
    model.addAttribute("resepti", new Resepti()); // for the form
    model.addAttribute("ainesosat", ainesosaRepository.findAll()); // List of ainesosat
    model.addAttribute("kategoriat", kategoriaRepository.findAll()); // List of kategoriat
    model.addAttribute("reseptit", reseptiRepository.findAll()); // List of reseptit
    return "reseptit";
}


    @PostMapping("/reseptit/save")
    public String saveResepti(@Valid @ModelAttribute("resepti") Resepti resepti, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("reseptit", reseptiRepository.findAll());
            return "reseptit";
        }
        reseptiRepository.save(resepti);
        return "redirect:/reseptit";
    }

    @GetMapping("/reseptit/delete/{id}")
    public String deleteResepti(@PathVariable Long id) {
        reseptiRepository.deleteById(id);
        return "redirect:/reseptit";
    }
}