package s24.ruokasovelluss.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Resepti {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "resepti_id")
private Long reseptiId;

@NotBlank(message = "Anna nimi")
@Column(name = "nimi")
private String nimi;

@ManyToOne
@JoinColumn(name = "ainesosa_id", nullable = false)
private Ainesosa ainesosa;

@ManyToOne
@JoinColumn(name = "kategoria_id", nullable = false)
private Kategoria kategoria;

@Column(name = "ohje")
private String ohje;

public Resepti () {}

    public Resepti (String nimi, Ainesosa ainesosa, Kategoria kategoria, String ohje ) {
        super();
        this.nimi=nimi;
        this.ainesosa=ainesosa;
        this.kategoria=kategoria;
        this.ohje=ohje;
    }

public Long getReseptiId() {
    return reseptiId;
}
public void setReseptiId(Long reseptiId){
    this.reseptiId = reseptiId;
}

public String getNimi(){
    return nimi;
}
public void setNimi(String nimi){
    this.nimi = nimi;
}

public Ainesosa getAinesosa() {
    return ainesosa;
}
public void setAinesosa(Ainesosa ainesosa) {
    this.ainesosa = ainesosa;
}

public Kategoria getKategoria(){
    return kategoria;
}
public void setKategoria(Kategoria kategoria){
    this.kategoria = kategoria;
}

public String getOhje(){
    return ohje;
}
public void setOhje(String ohje){
    this.ohje = ohje;
}
@Override
public String toString() {
    return "Resepti [resepti_id=" + reseptiId +
    ", nimi=" + nimi +
    ", ainesosa=" + ainesosa + 
    ", kategoria=" + kategoria +
    ", ohje=" + ohje + "]";
}
}