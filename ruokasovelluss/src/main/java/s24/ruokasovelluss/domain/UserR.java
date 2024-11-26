package s24.ruokasovelluss.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserR extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
