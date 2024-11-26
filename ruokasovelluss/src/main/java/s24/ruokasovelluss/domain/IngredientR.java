package s24.ruokasovelluss.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface IngredientR extends CrudRepository<Ingredient, Long> {
    Ingredient findByNameIgnoreCase(String name);

    List<Ingredient> findByNameContainingIgnoreCase(String query);

    Ingredient findByName(String name);
}
