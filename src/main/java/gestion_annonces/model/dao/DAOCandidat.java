package gestion_annonces.model.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import gestion_annonces.model.bo.Candidat;
import gestion_annonces.model.bo.Recruteur;

public class DAOCandidat implements IDAOCandidat{

	@Override
	public boolean create(Candidat c) {
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
	public Collection<Candidat> retrieve() {
		SessionFactory sessionFactory=UtilHibernate.getSession();
		Session session=sessionFactory.openSession();
		return session.createQuery("from Candidat").list();	
	}

	@Override
	public Candidat retrieveOne(String username) {
		
		SessionFactory sessionFactory=UtilHibernate.getSession();
		Session session;
		if(sessionFactory.isOpen())
			session=sessionFactory.getCurrentSession();
		else
			session=sessionFactory.openSession();

		Transaction tx=null;
		
		try {
			tx=session.beginTransaction();
			List<Candidat> candidats= session.createQuery("from Candidat where username = :t").setParameter("t", username).list();
			tx.commit();
			session.close();
			if(candidats.isEmpty())
				return null;
			return candidats.get(0);
			
		}catch(Exception exp) {
			tx.rollback();
			System.out.println("EROR while updating"+exp);
			return null;
		}
	
	}

	@Override
	public Candidat retrieveOne(String username, String password) {
		SessionFactory sessionFactory=UtilHibernate.getSession();
		Session session;
		if(sessionFactory.isOpen())
			session=sessionFactory.getCurrentSession();
		else
			session=sessionFactory.openSession();

		Transaction tx=null;
		
		try {
			tx=session.beginTransaction();
			List<Candidat> candidats= session.createQuery("from Candidat where username = :u and password = :p")
					.setParameter("u", username)
					.setParameter("p", password)
					.list();
			tx.commit();
			session.close();
			if(candidats.isEmpty())
				return null;
			return candidats.get(0);
			
		}catch(Exception exp) {
			tx.rollback();
			System.out.println("EROR while updating"+exp);
			return null;
		}
	}

	@Override
	public boolean update(Candidat c) {
		SessionFactory sessionFactory=UtilHibernate.getSession();
		
		Session session;
		if(sessionFactory.isOpen())
			session=sessionFactory.getCurrentSession();
		else
			session=sessionFactory.openSession();

		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			session.update(c);
			tx.commit();
			
			session.close();
			System.out.println("Contact updated successsfuly ");
			return true;
			
		}catch(Exception exp) {
			exp.printStackTrace();
			tx.rollback();
			System.out.println("EROR while updating"+exp);
			return false;
		}
	}
	public static void main(String[] args) {
		DAOCandidat dao = new DAOCandidat();
		Candidat c = dao.retrieveOne("chaimae");
		System.out.println(c);
		/*c.setPrenom("booooog");
		dao.update(c);*/
	}

	@Override
	public Candidat retrieveOne(long id) {
		SessionFactory sessionFactory=UtilHibernate.getSession();
		Session session;
		if(sessionFactory.isOpen())
			session=sessionFactory.getCurrentSession();
		else
			session=sessionFactory.openSession();

		Transaction tx=null;
		
		try {
			tx=session.beginTransaction();
			List<Candidat> candidats= session.createQuery("from Candidat where id = :u")
					.setParameter("u",id)
					.list();
			tx.commit();
			session.close();
			if(candidats.isEmpty())
				return null;
			return candidats.get(0);
			
		}catch(Exception exp) {
			tx.rollback();
			System.out.println("EROR while updating"+exp);
			return null;
		}
	}

}
