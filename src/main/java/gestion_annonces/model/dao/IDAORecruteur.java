package gestion_annonces.model.dao;

import java.util.Collection;

import gestion_annonces.model.bo.Offre;
import gestion_annonces.model.bo.Recruteur;

public interface IDAORecruteur {
	public boolean create(Recruteur r);
	public Recruteur retrieveOne(String username);
	public Collection<Recruteur> retrieve();
}
