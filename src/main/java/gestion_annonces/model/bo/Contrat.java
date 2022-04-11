package gestion_annonces.model.bo;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="contrat")
public class Contrat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String typecontrat;
	@OneToMany(fetch=FetchType.LAZY,mappedBy="typecontrat")
	Set<Offre> offres;
	public Contrat() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTypecontrat() {
		return typecontrat;
	}

	public void setTypecontrat(String typecontrat) {
		this.typecontrat = typecontrat;
	}

	public Set<Offre> getOffres() {
		return offres;
	}

	public void setOffres(Set<Offre> offres) {
		this.offres = offres;
	}

	@Override
	public String toString() {
		return "Contrat [id=" + id + ", typecontrat=" + typecontrat + "]";
	}
	
	
}
