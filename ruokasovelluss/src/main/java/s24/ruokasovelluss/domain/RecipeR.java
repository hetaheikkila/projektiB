package s24.ruokasovelluss.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RecipeR extends CrudRepository<Recipe, Long> {
    List<Recipe> findByName(String name);

    List<Recipe> findByNameContainingIgnoreCaseOrCategoryNameContainingIgnoreCase(String query, String query2);

}
