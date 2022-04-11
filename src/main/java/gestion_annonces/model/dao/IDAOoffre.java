package gestion_annonces.model.dao;
import java.util.Collection;

import gestion_annonces.model.bo.*;

public interface IDAOoffre {
	public boolean create(Offre o);
	public Collection<Offre> retrieve();
	public Offre retrieveOne(long id);
	public boolean delete(Offre o);
	boolean update(Offre o);
}
