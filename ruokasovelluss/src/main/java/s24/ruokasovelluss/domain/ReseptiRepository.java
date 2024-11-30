package s24.ruokasovelluss.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface ReseptiRepository extends CrudRepository<Resepti, Long>{
    List<Resepti> findByAinesosa(Ainesosa ainesosa);
    List<Resepti> findByKategoria(Kategoria kategoria);
    List<Resepti> findByReseptiId(Resepti resepti);

}
