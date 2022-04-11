package gestion_annonces.model.bo;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Candidat")
public class Candidat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nom;
	private String prenom;
	private String username;
	private String password;
	private byte[] cv;
	private byte[] lettreM;
	
	@ManyToMany(fetch = FetchType.EAGER,mappedBy = "candidats")
	private Set<Offre> condidatures;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Offre> getCondidatures() {
		return condidatures;
	}
	public void setCondidatures(Set<Offre> condidatures) {
		this.condidatures = condidatures;
	}
	public Candidat() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		String res = "Candidat [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", username=" + username + ", password="
				+ password + ", cv=" + cv + ", lettreM=" + lettreM + "]";
		if(this.getCondidatures()!=null)
		for(Offre o : this.getCondidatures())
		res+=o.getId() + " "+ o.getTitre();
		return res;
	}
	public byte[] getCv() {
		return cv;
	}
	public void setCv(byte[] cv) {
		this.cv = cv;
	}
	public byte[] getLettreM() {
		return lettreM;
	}
	public void setLettreM(byte[] lettreM) {
		this.lettreM = lettreM;
	}
	public Candidat(long id, String nom, String prenom, String username, String password, byte[] cv, byte[] lettreM,
			Set<Offre> condidatures) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.password = password;
		this.cv = cv;
		this.lettreM = lettreM;
		this.condidatures = condidatures;
	}
	
	
}
