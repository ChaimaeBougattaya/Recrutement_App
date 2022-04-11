package gestion_annonces.model.dao;

import java.util.Collection;

import gestion_annonces.model.bo.Contrat;

public interface IDAOcontrat {
	public boolean create(Contrat o);
	public Collection<Contrat> retrieve();

}
