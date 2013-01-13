package it.unitn.logic;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import it.unitn.boundary.AbstractFacade;
import it.unitn.entities.DistrictEB;
import it.unitn.entities.RegionEB;

@Stateless
public class DistrictEBFacade extends AbstractFacade<DistrictEB> {

	private static Logger log = Logger.getLogger(DistrictEBFacade.class
			.getName());

	@PersistenceContext(unitName = "MyUniShop")
	private EntityManager em;


	public DistrictEBFacade() {
		// TODO Auto-generated constructor stub
		super(DistrictEB.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

	public void createDistrict(DistrictEB districtEB) {

		em.persist(districtEB);
		em.flush();

	}

	public Integer getLastDistricInsertedID() {
		Query query = em.createNamedQuery("DistrictEB.findLastInsertedID");
		return (Integer) query.getSingleResult();
		/*
		 * List<Integer> resultList = query.getResultList(); Iterator<Integer>
		 * it = resultList.iterator(); Integer object = 0; while (it.hasNext())
		 * { object = it.next(); System.out.println(" My Max ID RegionEB " +
		 * object); } return object;
		 */
	}

	public DistrictEB getIdDistrictByName(String name) {
		Query query = em.createNamedQuery("DistrictEB.findByName");
		query.setParameter("name", name);
		DistrictEB districtEB = new DistrictEB();
		districtEB = (DistrictEB) query.getSingleResult();
		return districtEB;
	}

	public List<DistrictEB> getDistrictsByRegionId(Integer idRegion) {
		log.info("STRART: Get all districs by region id");

		// Query query = em.createNamedQuery("DistrictEB.findByIddistrict");
		Query query = em.createNamedQuery("RegionEB.findByIdregion");
		query.setParameter("idregion", idRegion);
		RegionEB regionEB = (RegionEB) query.getSingleResult();

		List<DistrictEB> resultList = regionEB.getDistrictEBList();
		log.info("END: Get all districs by region id");

		return resultList;
		// TODO Auto-generated method stub

	}

}
