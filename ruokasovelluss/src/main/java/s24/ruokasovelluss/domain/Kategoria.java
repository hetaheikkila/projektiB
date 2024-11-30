package s24.ruokasovelluss.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Kategoria {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "kategoria_id")
private Long kategoriaId;

@NotBlank(message = "Nimi tarvitaan.")
@Column(name = "kategoria_nimi")
private String kategoriaNimi;

  @JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kategoria")
	private List<Resepti> reseptit;


public Kategoria() {}

public Kategoria(String kategoriaNimi){
    super();
    this.kategoriaNimi=kategoriaNimi;

}
public Long getKategoriaId() {
    return kategoriaId;
}

public void setKategoriaId(long kategoriaId) {
    this.kategoriaId = kategoriaId;
}

public String getKategoriaNimi() {
    return kategoriaNimi;
}

public void setKategoriaNimi(String kategoriaNimi) {
    this.kategoriaNimi = kategoriaNimi;
}
public List<Resepti> getReseptit() {
    return reseptit;
}

public void setReseptit(List<Resepti> reseptit) {
    this.reseptit = reseptit;
}

@Override
	public String toString() {
		return kategoriaNimi;
	}
}
