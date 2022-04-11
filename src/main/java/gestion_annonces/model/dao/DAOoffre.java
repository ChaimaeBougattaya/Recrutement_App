package gestion_annonces.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import gestion_annonces.model.bo.*;

public class DAOoffre implements IDAOoffre{

	@Override
	public boolean create(Offre o) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory=UtilHibernate.getSession();
		Session session=sessionFactory.openSession();
				
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			session.save(o);
			
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
	public boolean delete(Offre o) {
			SessionFactory sessionFactory=UtilHibernate.getSession();
			Session session=sessionFactory.openSession();
			
			Transaction tx=null;
			try {
				tx=session.beginTransaction();
				session.delete(o);
				tx.commit();
				session.close();
				System.out.println("Offre deleted successsfuly ");
				return true;
				
			}catch(Exception exp) {
				tx.rollback();
				exp.printStackTrace();
				System.out.println("EROR while deleting");
				return false;
			}
	}
	@Override
	public Collection<Offre> retrieve() {
		
		/*SessionFactory sessionFactory=UtilHibernate.getSession();
		Session session=sessionFactory.openSession();
		return session.createQuery("from Offre").list();*/
		
		SessionFactory sessionFactory=UtilHibernate.getSession();
		Session session;
		if(sessionFactory.isOpen())
		session=sessionFactory.getCurrentSession();
		else
		 session=sessionFactory.openSession();
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			List<Offre> offres=  session.createQuery("from Offre").list();
			tx.commit();
			session.close();
			if(offres.isEmpty())
				return null;
			return offres;
		}catch(Exception exp) {
			tx.rollback();
			System.out.println("EROR while retrieve"+exp);
			return null;
		}
	}
	

	@Override
	public boolean update(Offre o) {
			SessionFactory sessionFactory=UtilHibernate.getSession();
			Session session=sessionFactory.openSession();
	
			Transaction tx=null;
			try {
				tx=session.beginTransaction();
				
				session.update(o);;
				tx.commit();
				session.close();
				System.out.println("Contact updated successsfuly ");
				return true;
				
			}catch(Exception exp) {
				tx.rollback();
				System.out.println("EROR while updating");
				return false;
			}
	}
	@Override
	public Offre retrieveOne(long id) {
		SessionFactory sessionFactory=UtilHibernate.getSession();
		System.out.println("iciiiiiii");
		Session session;
		if(sessionFactory.isOpen())
		session=sessionFactory.getCurrentSession();
		else
		 session=sessionFactory.openSession();
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			List<Offre> offres=  session.createQuery("from Offre o where o.id = :i").setParameter("i",id).list();
			tx.commit();
			session.close();
			if(offres.isEmpty())
				return null;
			return offres.get(0);
			
		}catch(Exception exp) {
			tx.rollback();
			System.out.println("EROR while retrieve"+exp);
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		//for(Offre o : new DAOoffre().retrieve())
			//System.out.println(o);
		//
		
		/*Recruteur r = new DAORecruteur().retrieveOne("TK27579");
		
		System.out.println(r);
		Offre o = new Offre();
		o.setId(43);
		 o.setRecruteur(r);
		 
		 System.out.println(o);
		 new DAOoffre().delete(o);
		
		for(Offre o : new DAOoffre().retrieve())
			System.out.println(o);*/
		long id =3;
		System.out.println(new DAOoffre().retrieveOne(id));
		
	}

}
