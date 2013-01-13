/**
 * 
 */
package it.unitn.logic;

import java.util.List;
import java.util.logging.Logger;

import it.unitn.boundary.AbstractFacade;
import it.unitn.dto.Region;
import it.unitn.entities.RegionEB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * 
 * @author waseem
 */
@Stateless
public class RegionEBFacade extends AbstractFacade<RegionEB> {
	private static Logger log = Logger.getLogger(RegionEBFacade.class.getName());
	
	public RegionEBFacade() {
		super(RegionEB.class);
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext(unitName = "MyUniShop")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public void createRegion(RegionEB regionEB) {

		// RegionEB regionEB;
		// regionEB=EBtoDTO(region);
		em.persist(regionEB);
	}

	private RegionEB EBtoDTO(Region region) {
		RegionEB regionEB = new RegionEB();
		return regionEB;
	}

	public Integer getLastRegionEBInserted() {
		Query query = em.createNamedQuery("RegionEB.findLastInsertID");
		return (Integer) query.getSingleResult();
		/*
		 * List<Integer> resultList = query.getResultList(); Iterator<Integer>
		 * it = resultList.iterator(); Integer object = 0; while (it.hasNext())
		 * { object = it.next(); System.out.println(" My Max ID RegionEB " +
		 * object); } return object;
		 */
	}

	public RegionEB getIdRegionByName(String name) {
		Query query = em.createNamedQuery("RegionEB.findByName");
		query.setParameter("name", name);
		RegionEB regionEB = new RegionEB();
		regionEB = (RegionEB) query.getSingleResult();
		return regionEB;

	}

	public List<RegionEB> getAllRegionEB() {
	    log.info("START: get all regions");
		Query query = em.createNamedQuery("RegionEB.findAll");
		List<RegionEB> resultList = query.getResultList();
		log.info("END: get all regions");
		return resultList;
	}
}
