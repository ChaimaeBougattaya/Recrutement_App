package gestion_annonces.model.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import gestion_annonces.model.bo.Offre;
import gestion_annonces.model.bo.Recruteur;
public class DAORecruteur implements IDAORecruteur {

	@Override
	public boolean create(Recruteur r) {

		SessionFactory sessionFactory=UtilHibernate.getSession();
		Session session=sessionFactory.openSession();
				
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			session.save(r);
			
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
	public Recruteur retrieveOne(String username) {
		SessionFactory sessionFactory=UtilHibernate.getSession();
		Session session=sessionFactory.openSession();
		
		return((Recruteur) session.createQuery("from Recruteur where username = :t").setParameter("t", username).list().get(0));
	
	}
	
	public Recruteur retrieveOne(String username,String password) {
		SessionFactory sessionFactory=UtilHibernate.getSession();
		Session session=sessionFactory.openSession();
		
		return((Recruteur) session.createQuery("from Recruteur where username = :u and password = :p")
				.setParameter("u", username)
				.setParameter("p", password)
				.list()
				.get(0));
	}

	@Override
	public Collection<Recruteur> retrieve() {
		SessionFactory sessionFactory=UtilHibernate.getSession();
		Session session=sessionFactory.openSession();
		return session.createQuery("from Recruteur").list();	
	}

}
