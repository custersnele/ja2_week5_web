package be.pxl.ja2.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import be.pxl.ja2.dao.BrewerDao;
import be.pxl.ja2.dao.EntityManagerUtil;
import be.pxl.ja2.data.Brewer;

@Path("brewers")
public class BrewersRest {
	
	@GET
	@Path("city/{city}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Brewer> getBrewersByCity(@PathParam("city") String city) {
		EntityManager em = EntityManagerUtil.createEntityManager();
		BrewerDao brewerDao = new BrewerDao(em);
		System.out.println("QueryParam: " + city);
		System.out.println("BrewerDao: " + brewerDao);
		List<Brewer> brewers = brewerDao.findByCity(city);
		System.out.println("Aantal brouwers: " + brewers.size() + " for city " + city);
		em.close();
		return brewers;
	}
	

}
