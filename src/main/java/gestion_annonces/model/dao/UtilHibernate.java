package gestion_annonces.model.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mysql.cj.util.Util;

public class UtilHibernate {
private static SessionFactory session;

	static {
		try {
			Configuration config=new Configuration();
			config.configure("/gestion_annonces/model/dao/hibernate.cfg.xml");
			session=config.buildSessionFactory();
			
			}catch(HibernateException exep) {
				System.out.println("error");
				exep.printStackTrace();
		}

	}
	public static SessionFactory getSession() {
		return session;
	}

	public static void setSession(SessionFactory session) {
		UtilHibernate.session = session;
	}
	public static void main(String[] args) {
		UtilHibernate.getSession();
	}
}
