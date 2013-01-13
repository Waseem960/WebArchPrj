/**
 * 
 */
package it.unitn.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import it.unitn.boundary.AbstractFacade;
import it.unitn.dto.Shop;
import it.unitn.entities.MunicipalEB;
import it.unitn.entities.ShopEB;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * 
 * @author waseem
 */
@Stateless
public class ShopEBFacade extends AbstractFacade<ShopEB> {
	private static Logger log = Logger.getLogger(ShopEBFacade.class.getName());

	public ShopEBFacade() {
		super(ShopEB.class);
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext(unitName = "MyUniShop")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@EJB
	MunicipalEBManage municipalEBManage = new MunicipalEBManage();

	public void createShop(Shop shop) {
		log.info("START: Inserting new shop");
		ShopEB shopEB = new ShopEB();
		shopEB = DTOtoEB(shop);
		em.persist(shopEB);
		em.flush();
		log.info("END: Inserting new shop");
	}

	private ShopEB DTOtoEB(Shop shop) {
		ShopEB shopEB = new ShopEB();
		MunicipalEB municipalEB = municipalEBManage.getMunicipalByID(shop
				.getIdCity());
		shopEB.setName(shop.getName());
		shopEB.setDescription(shop.getDescription());
		shopEB.setAddress(shop.getAddress());
		shopEB.setIdcity(municipalEB);
		shopEB.setAuthor(shop.getAuthor());
		shopEB.setIdshop(getLastShopInserted() + 1);
		return shopEB;
	}

	public Integer getLastShopInserted() {

		// TODO Auto-generated method stub
		log.info("START: Last shop inserted");
		Query query = em.createNamedQuery("ShopEB.findMaxIdShop");
		log.info("END: Last shop inserted");		
		Integer ris=(Integer) query.getSingleResult();
		if(ris==null)
			return 0;
		return ris;		 
	}

	public Shop getShopEBIdByName(String name) {
		// TODO Auto-generated method stub
		log.info("START: Getting shop by name");
		Shop shop = new Shop();
		ShopEB shopEB = new ShopEB();
		Query query = em.createNamedQuery("ShopEB.findByName");
		query.setParameter("name", name);
		shopEB = (ShopEB) query.getSingleResult();
		if (shopEB != null)
			shop = EBToDTO(shopEB);
		else
			shop = null;
		log.info("END: Getting shop by name");
		return shop;
	}

	public Shop EBToDTO(ShopEB shopEB) {
		Shop shop = new Shop();
		shop.setIdshop(shopEB.getIdshop());
		shop.setName(shopEB.getName());
		shop.setDescription(shopEB.getDescription());
		shop.setAddress(shopEB.getAddress());
		shop.setIdCity(shopEB.getIdcity().getIdmunicipal());
		shop.setAuthor(shopEB.getAuthor());
		Integer totalComm = getTotalCommentsByIdShop(shopEB);
		log.info("Total number of comments= " + totalComm);
		shop.setNumberComments(totalComm);
		return shop;
	}

	public Integer getTotalCommentsByIdShop(ShopEB shopEB) {
		log.info("START: Getting total number of comments for a shop");
		Query query = em.createNamedQuery("CommentEB.countNumberOfComments");
		query.setParameter("idshop", shopEB);
		log.info("END: Getting total number of comments for a shop "
				+ query.getSingleResult());
		long res = (Long) query.getSingleResult();
		return (int) res;
		// return (Integer) query.getSingleResult();
		/*
		 * if (query.getResultList().size() == 0) return 0; else return
		 * query.getResultList().size();
		 */
		// return (Integer) query.getSingleResult();
	}

	public ShopEB getShopById(Integer idshop) {
		// TODO Auto-generated method stub
		log.info("START: Getting shop by ID");
		ShopEB shopEB = new ShopEB();

		Query query = em.createNamedQuery("ShopEB.findByIdshop");
		query.setParameter("idshop", idshop);
		shopEB = (ShopEB) query.getSingleResult();
		log.info("END: Getting shop by ID");
		return shopEB;
	}

	public List<Shop> getAllShops() {
		// TODO Auto-generated method stub
		log.info("START: Getting all shops");
		List<Shop> lstShops = new ArrayList<Shop>();
		Shop shop = new Shop();
		ShopEB shopEB = new ShopEB();
		Query query = em.createNamedQuery("ShopEB.findAll");
		List<ShopEB> resultList = query.getResultList();
		Iterator<ShopEB> it = resultList.iterator();
		while (it.hasNext()) {
			shopEB = (ShopEB) it.next();
			shop = EBToDTO(shopEB);
			lstShops.add(shop);
		}
		log.info("END: Getting all shops ->size: " + lstShops.size());
		return lstShops;
	}

	public List<Shop> getShopsByRegion(Integer idregion) {
		// TODO Auto-generated method stub
		log.info("START: Getting shops by region");
		List<Shop> lstShops = new ArrayList<Shop>();
		Shop shop = new Shop();
		ShopEB shopEB = new ShopEB();
		Query query = em.createNamedQuery("ShopEB.findByRegionID");
		query.setParameter("idregion", idregion);
		List<ShopEB> resultList = query.getResultList();
		Iterator<ShopEB> it = resultList.iterator();
		while (it.hasNext()) {
			shopEB = (ShopEB) it.next();
			shop = EBToDTO(shopEB);
			lstShops.add(shop);
		}
		log.info("END: Getting shops by region ->size: " + lstShops.size());
		return lstShops;
	}

	public List<Shop> getShopsByDistrict(Integer idregion, Integer iddistrict) {

		log.info("START: Getting Getting shops by district");
		List<Shop> lstShops = new ArrayList<Shop>();
		Shop shop = new Shop();
		ShopEB shopEB = new ShopEB();
		Query query = em.createNamedQuery("ShopEB.findByDistrictID");
		query.setParameter("idregion", idregion);
		query.setParameter("iddistrict", iddistrict);
		List<ShopEB> resultList = query.getResultList();
		Iterator<ShopEB> it = resultList.iterator();
		while (it.hasNext()) {
			shopEB = (ShopEB) it.next();
			shop = EBToDTO(shopEB);
			lstShops.add(shop);
		}
		log.info("END: Getting shops by district ->size: " + lstShops.size());
		return lstShops;
	}

	public List<Shop> getShopsByMunicipal(Integer idmunicipal) {
		// TODO Auto-generated method stub
		log.info("START: Getting shops by municipal");
		List<Shop> lstShops = new ArrayList<Shop>();
		Shop shop = new Shop();
		ShopEB shopEB = new ShopEB();
		Query query = em.createNamedQuery("ShopEB.findByMunicipalID");
		query.setParameter("idmunicipal", idmunicipal);
		List<ShopEB> resultList = query.getResultList();
		Iterator<ShopEB> it = resultList.iterator();
		while (it.hasNext()) {
			shopEB = (ShopEB) it.next();
			shop = EBToDTO(shopEB);
			lstShops.add(shop);
		}
		log.info("END: Getting shops by municipal ->size: " + lstShops.size());
		return lstShops;
	}

	public void modifyShop(Shop shop) {
		// TODO Auto-generated method stub
		log.info("START: Modifiying shop");
		ShopEB shopEB = new ShopEB();
		//shopEB = DTOtoEB(shop);
		shopEB = em.find(ShopEB.class,shop.getIdshop());
		shopEB.setName(shop.getName());
		shopEB.setAddress(shop.getAddress());
		shopEB.setDescription(shop.getDescription());		
		MunicipalEB municipalEB = municipalEBManage.getMunicipalByID(shop
				.getIdCity());
		shopEB.setIdcity(municipalEB);
		em.flush();
		log.info("END: Modifiying shop");
	}

	public List<Shop> getShopByAuthor(String username) {
		// TODO Auto-generated method stub
		log.info("START: Getting shops by Author");
		List<Shop> lstShops = new ArrayList<Shop>();
		Shop shop = new Shop();
		ShopEB shopEB = new ShopEB();
		Query query = em.createNamedQuery("ShopEB.findByAuthor");
		query.setParameter("author", username);
		List<ShopEB> resultList = query.getResultList();
		Iterator<ShopEB> it = resultList.iterator();
		while (it.hasNext()) {
			shopEB = (ShopEB) it.next();
			shop = EBToDTO(shopEB);
			lstShops.add(shop);
		}
		log.info("END: Getting shops by Author ->size: " + lstShops.size());
		return lstShops;
	}

	public void delteShopById(Integer idshop) {
		// TODO Auto-generated method stub
		log.info("START: Deleting shop by idshop");
		ShopEB shopEB=em.find(ShopEB.class,idshop);
		em.remove(shopEB);
		em.flush();
		log.info("START: Deleting shop by idshop");
		
	}
}
