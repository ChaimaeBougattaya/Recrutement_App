package gestion_annonces.model.bo;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="offre")
public class Offre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	@ManyToOne()
	@JoinColumn(name="typecontrat")
	public Contrat typecontrat;
	
	@ManyToOne(optional = true)
	@JoinColumn(name="recruteur",nullable=false)//
	private Recruteur recruteur;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Postulation",joinColumns = {
			@JoinColumn(name="offre")
	},inverseJoinColumns = {
			@JoinColumn(name="candidat")
	})
	
	private Set<Candidat> candidats;
	public String titre;
	public String description;
	public String profile;
	public String Datepub;
	
	public Set<Candidat> getCandidats() {
		return candidats;
	}
	public void setCandidats(Set<Candidat> candidats) {
		this.candidats = candidats;
	}
	public Recruteur getRecruteur() {
		return recruteur;
	}
	public void setRecruteur(Recruteur recruteur) {
		this.recruteur = recruteur;
	}
	public Offre() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Offre(long id, String titre, String description, Contrat typecontrat, String profile, String datepub) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.typecontrat = typecontrat;
		this.profile = profile;
		Datepub = datepub;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Contrat getTypecontrat() {
		return typecontrat;
	}
	public void setTypecontrat(Contrat typecontrat) {
		this.typecontrat = typecontrat;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getDatepub() {
		return Datepub;
	}
	public void setDatepub(String datepub) {
		Datepub = datepub;
	}
	@Override
	public String toString() {
		String rs = "Offre [id=" + id + ", typecontrat=" + typecontrat + ", recruteur=" + recruteur.getNom() 
				 + ", titre=" + titre + ", description=" + description + ", profile=" + profile
				+ ", Datepub=" + Datepub + "]";
		if(this.candidats!=null)
		for(Candidat c : this.candidats)
			rs+=c.toString();
		return rs;
	}




}
