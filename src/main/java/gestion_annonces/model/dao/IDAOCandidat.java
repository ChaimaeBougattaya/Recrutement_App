package gestion_annonces.model.dao;

import java.util.Collection;

import gestion_annonces.model.bo.Candidat;
import gestion_annonces.model.bo.Contrat;

public interface IDAOCandidat {
	public boolean create(Candidat c);
	public Collection<Candidat> retrieve();
	public boolean update(Candidat c);
	public Candidat retrieveOne(String username);
	public Candidat retrieveOne(long id);
	public Candidat retrieveOne(String username,String password);
	
}
