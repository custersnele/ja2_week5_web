package be.pxl.ja2.beers.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import be.pxl.ja2.beers.data.Brewer;

public class BrewerDao {
	private EntityManager em;
	
	public BrewerDao(EntityManager entityManager) {
		this.em = entityManager;
	}
	
	public List<Brewer> findByCity(String city) {
		TypedQuery<Brewer> findByCity = em.createNamedQuery("findByCity", Brewer.class);
		System.out.println("findByCity" + findByCity);
		findByCity.setParameter("city", city);
		
		return findByCity.getResultList();
	}

}
