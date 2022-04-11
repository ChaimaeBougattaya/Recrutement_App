package gestion_annonces.model.dao;

import java.util.Collection;

import gestion_annonces.model.bo.*;

public class TEST {
	public static void main(String[] args) {
		/*
		 * 
		 * for(Contrat c : new DAOcontrat().retrieve())
			System.out.println(c);
		 
		Collection<Recruteur > list = new DAORecruteur().retrieve();
		for(Recruteur r : list)
			System.out.println(r.toString());*/
		
		for(Offre o : new DAOoffre().retrieve())
			System.out.println(o);
		
	}

}
