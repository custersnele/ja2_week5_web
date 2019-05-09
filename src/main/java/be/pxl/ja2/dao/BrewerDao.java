package be.pxl.ja2.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import be.pxl.ja2.data.Brewer;

public class BrewerDao {
	private EntityManager entityManager;

	public BrewerDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Brewer> findByCity(String city) {
		TypedQuery<Brewer> findByCity = entityManager.createNamedQuery("findByCity", Brewer.class);
		System.out.println("findByCity" + findByCity);
		findByCity.setParameter("city", city);
		
		return findByCity.getResultList();
	}

}
