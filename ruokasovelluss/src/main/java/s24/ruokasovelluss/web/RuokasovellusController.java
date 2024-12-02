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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RuokasovellusController {

    @Autowired
    private KategoriaRepository kategoriaRepository;

    @Autowired
    private AinesosaRepository ainesosaRepository;

    @Autowired
    private ReseptiRepository reseptiRepository;

    @GetMapping("/login")
    public String login() {
        return "login"; // Must correspond to src/main/resources/templates/login.html
    }

    @GetMapping("/index")
    public String showIndex(Model model) {
        return "index";
    }
    

    @GetMapping("/reseptit")
public String showReseptit(Model model) {
    model.addAttribute("resepti", new Resepti()); // For form binding
    model.addAttribute("ainesosat", ainesosaRepository.findAll()); // List of ingredients
    model.addAttribute("kategoriat", kategoriaRepository.findAll()); // List of categories
    model.addAttribute("reseptit", reseptiRepository.findAll()); // List of recipes
    return "reseptit";
}


@GetMapping("/kategoriat")
public String listKategoriat(Model model) {
    model.addAttribute("kategoriat", kategoriaRepository.findAll());
    return "kategoriat"; // Must match the name of the template file (kategoriat.html)
}


    @GetMapping("/ainesosat")
    public String listAinesosat(Model model) {
        model.addAttribute("ainesosat", ainesosaRepository.findAll());
        return "ainesosat";
    }
    /*
     * spring.application.name=ruokasovelluss
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.show-sql=true
server.port=8080

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
     */
}