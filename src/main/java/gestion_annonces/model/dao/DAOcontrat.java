package gestion_annonces.model.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import gestion_annonces.model.bo.Contrat;
import gestion_annonces.model.bo.Offre;

public class DAOcontrat implements IDAOcontrat {

	@Override
	public boolean create(Contrat c) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory=UtilHibernate.getSession();
		Session session=sessionFactory.openSession();
				
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			session.save(c);
			
			tx.commit();
			
			session.close();
			
			return true;
		}
		catch(Exception exp) {
			tx.rollback();
			return false;
		}
	}

	@Override
	public Collection<Contrat> retrieve() {
		// TODO Auto-generated method stub
		Collection<Contrat> types = new ArrayList<Contrat>();
		
		SessionFactory sessionFactory=UtilHibernate.getSession();
		Session session=sessionFactory.openSession();
		
		types= session.createQuery("from Contrat").list();
		return types;
	}

	
}
