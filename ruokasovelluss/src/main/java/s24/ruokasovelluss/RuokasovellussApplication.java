package s24.ruokasovelluss;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import s24.ruokasovelluss.domain.Category;
import s24.ruokasovelluss.domain.CategoryR;
import s24.ruokasovelluss.domain.IngredientR;
import s24.ruokasovelluss.domain.RecipeR;
import s24.ruokasovelluss.domain.User;
import s24.ruokasovelluss.domain.UserR;

@SpringBootApplication
public class RuokasovellussApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuokasovellussApplication.class, args);
    }
    @Bean
    public CommandLineRunner demo(RecipeR recipeR, IngredientR ingredientR,
                                  CategoryR categoryR, UserR userR) {
        return (args) -> {
            // Create initial categories
            Category category1 = new Category();
            category1.setName("Desserts");
            Category category2 = new Category();
            category2.setName("Main Courses");

            categoryR.save(category1);
            categoryR.save(category2);

            User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			userR.save(user1);
			userR.save(user2);

            System.out.println("Data initialized!");
        };
    }
}