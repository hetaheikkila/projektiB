package s24.ruokasovelluss.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Ainesosa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ainesosa_id")
    private Long ainesosaId;

    @NotBlank(message = "ainesosan nimi pakollinen.")
    @Column(name = "ainesosa_nimi")
    private String ainesosaNimi;
  
    public Ainesosa() {}

    public Ainesosa(String ainesosaNimi) {
        super();
        this.ainesosaNimi = ainesosaNimi;
    }


    public Long getAinesosaId() {
        return ainesosaId;
    }

    public void setAinesosaId(Long ainesosaId) {
        this.ainesosaId = ainesosaId;
    }

    public String getAinesosaNimi() {
        return ainesosaNimi;
    }

    public void setAinesosaNimi(String ainesosaNimi) {
        this.ainesosaNimi = ainesosaNimi;
    }
    @Override
	public String toString() {
		return ainesosaNimi;
	}
}
