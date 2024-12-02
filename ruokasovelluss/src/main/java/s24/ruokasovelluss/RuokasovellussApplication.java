package s24.ruokasovelluss;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import s24.ruokasovelluss.domain.Kategoria;
import s24.ruokasovelluss.domain.KategoriaRepository;
import s24.ruokasovelluss.domain.Ainesosa;
import s24.ruokasovelluss.domain.AinesosaRepository;
import s24.ruokasovelluss.domain.Resepti;
import s24.ruokasovelluss.domain.ReseptiRepository;
import s24.ruokasovelluss.domain.AppUser;
import s24.ruokasovelluss.domain.AppUserRepository;

import java.util.Arrays;


@SpringBootApplication
public class RuokasovellussApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuokasovellussApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ReseptiRepository reseptiRepository, AinesosaRepository ainesosaRepository,
                                  KategoriaRepository kategoriaRepository, AppUserRepository userRepository) {
        return (args) -> {
            // Create initial categories
            Kategoria kategoria1 = new Kategoria("Jälkiruoat"); // Desserts
            Kategoria kategoria2 = new Kategoria("Pääruoat"); // Main Courses

            kategoriaRepository.saveAll(Arrays.asList(kategoria1, kategoria2));

            // Create some ingredients
            Ainesosa flour = new Ainesosa("Vehnäjauho");
            Ainesosa sugar = new Ainesosa("Sokeri");
            Ainesosa butter = new Ainesosa("Voi");
            Ainesosa egg = new Ainesosa("Kananmuna");
            Ainesosa chicken = new Ainesosa("Kananrinta");
            Ainesosa rice = new Ainesosa("Riisi");
            Ainesosa soySauce = new Ainesosa("Soijakastike");

            ainesosaRepository.saveAll(Arrays.asList(flour, sugar, butter, egg, chicken, rice, soySauce));

            // Create some recipes
            Resepti pancakeRecipe = new Resepti("Pannukakku", egg, kategoria1, "Sekoita kulhossa kuivat ainekset, lado pellille ja paista uunissa.");

            Resepti chickenStirFry = new Resepti("Kana Stir Fry", chicken, kategoria2,  "Maukas ja terveellinen kanaruoka");

            reseptiRepository.saveAll(Arrays.asList(pancakeRecipe, chickenStirFry));

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Create default users with encoded passwords
        AppUser user1 = new AppUser("user", encoder.encode("password"), "USER");
        AppUser user2 = new AppUser("admin", encoder.encode("admin"), "ADMIN");

        userRepository.saveAll(Arrays.asList(user1, user2));
        System.out.println("Users created with encoded passwords!");

            System.out.println("Data initialized!");
        };
    }
}