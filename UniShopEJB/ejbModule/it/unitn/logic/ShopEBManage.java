package it.unitn.logic;

import java.util.ArrayList;
import java.util.List;

import it.unitn.dto.Shop;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean; 

/**
 * 
 * @author waseem
 */
@Stateless
@LocalBean
@ManagedBean(name = "ShopEBManage")
public class ShopEBManage {

	@EJB
	ShopEBFacade shopEBFacade = new ShopEBFacade();

	public ShopEBManage() {
		// TODO Auto-generated constructor stub
	}

	public void addShop(Shop shop) {
		shopEBFacade.createShop(shop);
	}

	public Integer getlastIdShop() {

		return shopEBFacade.getLastShopInserted();

	}

	public Shop getShopEBByName(String name) {

		return shopEBFacade.getShopEBIdByName(name);
	}

	public List<Shop> getAllShops() {
		
		List<Shop> lstShop = new ArrayList<Shop>();
		lstShop = shopEBFacade.getAllShops();
		return lstShop;
	}

	public List<Shop> getShopsByRegion(String region) {
		List<Shop> lstShop = new ArrayList<Shop>();
		Integer idregion=Integer.valueOf(region);
		lstShop = shopEBFacade.getShopsByRegion(idregion);
		return lstShop;
	}

	public List<Shop> getShopsByDistrict(String region,String district) {
		// TODO Auto-generated method stub
		
		List<Shop> lstShop = new ArrayList<Shop>();
		Integer idregion=Integer.valueOf(region);
		Integer iddistrict=Integer.valueOf(district);
		lstShop = shopEBFacade.getShopsByDistrict(idregion, iddistrict);
		return lstShop;
	}
	

	public List<Shop> getShopsByMunicipal(String municipal) {

		List<Shop> lstShop = new ArrayList<Shop>();
		Integer idmunicipal=Integer.valueOf(municipal);
		lstShop = shopEBFacade.getShopsByMunicipal(idmunicipal);
		return lstShop;
	}

	public Shop getShopByIdShop(Integer idshop) {
		// TODO Auto-generated method stub
		return  shopEBFacade.EBToDTO(shopEBFacade.getShopById(idshop));
	}

	public void modifyShop(Shop shop) {
		// TODO Auto-generated method stub
		shopEBFacade.modifyShop(shop);
	}

	public List<Shop> getShopByAuthor(String username) {
		
		List<Shop> lstShop = new ArrayList<Shop>();
		lstShop=shopEBFacade.getShopByAuthor(username);
		return lstShop;
	}

	public void deleteShopById(Integer idshop) {
		// TODO Auto-generated method stub
		shopEBFacade.delteShopById(idshop);
	}

}
