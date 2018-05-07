package be.pxl.ja2.beers.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

	public static EntityManagerFactory getEntityManagerFactory() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentDB");
		return emf;
	}
}
