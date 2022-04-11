package gestion_annonces.model.bo;

import java.util.ArrayList;
import java.util.Collection;

public class Annonces {
	Collection<Offre> offres;

	public Annonces() {
		this.offres=  new ArrayList<Offre>();
	}
	
	public Collection<Offre> getOffres() {
		return offres;
	}
	public void setOffres(Collection<Offre> offres) {
		this.offres = offres;
	}
	
	public void addOffre(Offre o)
	{
		this.offres.add(o);
	}
}
