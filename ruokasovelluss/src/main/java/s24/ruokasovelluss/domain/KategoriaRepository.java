package s24.ruokasovelluss.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface KategoriaRepository extends CrudRepository<Kategoria, Long>{
    List<Kategoria> findByKategoriaId(Long kategoriaId);
    List<Kategoria> findByKategoriaNimi(String kategoriaNimi);
    
}
