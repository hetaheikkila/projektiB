package s24.ruokasovelluss.domain;

import org.springframework.data.repository.CrudRepository;

public interface CategoryR extends CrudRepository<Category, Long> {
    Category findByName(String name);
}
