package it.unitn.logic;

import java.util.List;
import java.util.logging.Logger;

import it.unitn.boundary.AbstractFacade;
import it.unitn.entities.DistrictEB;
import it.unitn.entities.MunicipalEB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * 
 * @author waseem
 */
@Stateless
public class MunicipalEBFacade extends AbstractFacade<MunicipalEB> {

	private static Logger log = Logger.getLogger(MunicipalEBFacade.class
			.getName());

	@PersistenceContext(unitName = "MyUniShop")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

	public MunicipalEBFacade() {
		super(MunicipalEB.class);
	}

	public void createMunicipalEB(MunicipalEB municipalEB) {
		em.persist(municipalEB);
	}

	public Integer getLastMunicipalInsertedID() {
		log.info("START: Get Last inserted municipal");
		Query query = em.createNamedQuery("MunicipalEB.findLastInsertedID");
		log.info("END: Get Last inserted municipal");
		return (Integer) query.getSingleResult();

	}

	public Integer getIdMunicipalByName(String name) {
		log.info("START: Get municipal by name");
		Query query = em.createNamedQuery("MunicipalEB.findByName");
		query.setParameter("name", name);
		MunicipalEB municipalEB = new MunicipalEB();
		municipalEB = (MunicipalEB) query.getSingleResult();
		log.info("END: Get municipal by name");
		return municipalEB.getIdmunicipal();
	}

	public List<MunicipalEB> getMunicipalsByDistrictid(Integer idDistrict) {

		log.info("START: Get all municipals by distric id");

		Query query = em.createNamedQuery("DistrictEB.findByIddistrict");
		query.setParameter("iddistrict", idDistrict);
		DistrictEB districtEB = (DistrictEB) query.getSingleResult();
		List<MunicipalEB> resultList = districtEB.getMunicipalEBList();
		log.info("END: Get all municipals by distric id");

		return resultList;
	}

	public MunicipalEB getMunicipalByID(Integer idCity) {
		log.info("START: Get municipal by id " + idCity);
		Query query = em.createNamedQuery("MunicipalEB.findByIdmunicipal");
		// Query query =
		// em.createNamedQuery("SELECT m FROM MunicipalEB m WHERE m.idmunicipal = :idmunicipal");
		query.setParameter("idmunicipal", idCity);
		MunicipalEB municipalEB = new MunicipalEB();
		//List list = query.getResultList();
		municipalEB = (MunicipalEB) query.getSingleResult();
		//System.out.println("Size lst " + list.size());	
		log.info("END: Get municipal by id");
		return municipalEB;
	}

	public String getMunicipalNameByIdCity(Integer idCity) {
		log.info("START: Get municipal by id " + idCity);
		Query query = em.createNamedQuery("MunicipalEB.findByIdmunicipal");
		// Query query =
		// em.createNamedQuery("SELECT m FROM MunicipalEB m WHERE m.idmunicipal = :idmunicipal");
		query.setParameter("idmunicipal", idCity);
		MunicipalEB municipalEB = new MunicipalEB();
		//List list = query.getResultList();
		municipalEB = (MunicipalEB) query.getSingleResult();
		//System.out.println("Size lst " + list.size());	
		log.info("END: Get municipal by id");
		return municipalEB.getName();
	}
}
