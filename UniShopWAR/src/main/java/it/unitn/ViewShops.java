/**
 * 
 */
package it.unitn;

import it.unitn.dto.Shop;
import it.unitn.logic.DistrictEBManage;
import it.unitn.logic.MunicipalEBManage;
import it.unitn.logic.RegionEBManage;
import it.unitn.logic.ShopEBManage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author waseem
 * 
 */
@ManagedBean
@SessionScoped
public class ViewShops implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ViewShops.class.getName());

	private String region = null;
	private String district = null;
	private String municipal = null;
	private String shopName;
	private List<Shop> shops = new ArrayList<Shop>();

	private Map<String, Integer> regionMap = new HashMap<String, Integer>();
	private Map<String, Integer> districtMap = new HashMap<String, Integer>();
	private Map<String, Integer> municipalMap = new HashMap<String, Integer>();

	@EJB
	RegionEBManage regionManage;
	@EJB
	DistrictEBManage districtManage;
	@EJB
	MunicipalEBManage municipalEBManage;
	@EJB
	ShopEBManage shopEBManage = new ShopEBManage();

	public ViewShops() {
		// shops=shopEBManage.getAllShops();
		shops = new ArrayList<Shop>();
		// System.out.println("shops=new ArrayList<Shop>();");
		// shops=shopEBManage.getAllShops();
		// populateData();

	}

	/**
	 * @return the region
	 */
	public String getRegion() {

		// log.info(" getRegion: " + region);
		// log.info(" region= " + region + " district= " + district+
		// " municipal= " + municipal + " Shops= " + shops);
		// if ((shops == null)|| shops.isEmpty()) {
		if ((region == null) && (district == null) && (municipal == null)) {
			this.shops = shopEBManage.getAllShops();
		}
		// }
		return region;
	}

	/**
	 * @param region
	 *            the region to set
	 */
	public void setRegion(String region) {
		log.info(" setRegion: " + region);

		this.region = region;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the municipal
	 */
	public String getMunicipal() {
		return municipal;
	}

	/**
	 * @param municipal
	 *            the municipal to set
	 */
	public void setMunicipal(String municipal) {
		this.municipal = municipal;
	}

	/**
	 * @return the shopName
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * @param shopName
	 *            the shopName to set
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * @return the shops
	 */
	public List<Shop> getShops() {

		if ((shops == null)) {
			// System.out.println(" Shop Size shops.isEmpty() ");
			if (((region == "") && (district == "") && (municipal == ""))
					|| ((region == null) && (district == null) && (municipal == null))) {
				return shopEBManage.getAllShops();
			}
		}
		return shops;
	}

	/**
	 * @return the shops
	 */
	/*
	 * public List<Shop> getShops() { if (((region == "") && (district == "") &&
	 * (municipal == "")) || ((region == null) && (district == null) &&
	 * (municipal == null))) return shopEBManage.getAllShops(); return shops; }
	 */

	/**
	 * @param shops
	 *            the shops to set
	 */
	/*
	 * public void setShops(List<Shop> shops) { this.shops = shops; }
	 */
	/**
	 * @return the regionMap
	 */
	public Map<String, Integer> getRegionMap() {
		regionMap = regionManage.getAllRegion();
		return regionMap;
	}

	/**
	 * @param regionMap
	 *            the regionMap to set
	 */
	public void setRegionMap(Map<String, Integer> regionMap) {
		this.regionMap = regionMap;
	}

	/**
	 * @return the districtMap
	 */
	public Map<String, Integer> getDistrictMap() {
		return districtMap;
	}

	/**
	 * @param districtMap
	 *            the districtMap to set
	 */
	public void setDistrictMap(Map<String, Integer> districtMap) {
		this.districtMap = districtMap;
	}

	/**
	 * @return the municipalMap
	 */
	public Map<String, Integer> getMunicipalMap() {
		return municipalMap;
	}

	/**
	 * @param municipalMap
	 *            the municipalMap to set
	 */
	public void setMunicipalMap(Map<String, Integer> municipalMap) {
		this.municipalMap = municipalMap;
	}

	public List<Shop> populateData() {
		return shopEBManage.getAllShops();
	}

	public void handleRegionChange() {
		// E' un intero idregione
		// log.info(" handleRegionChange region :" + region);
		if (region != null && !region.equals("")) {
			// log.info(" region IF :" + region);
			districtMap = districtManage.getDistrictByRegion(region);
		} else {
			log.info(" handleRegionChange region ELSE :" + region);
			districtMap = new HashMap<String, Integer>();
			municipalMap = new HashMap<String, Integer>();
			this.shops = shopEBManage.getAllShops();
			district = null;
			municipal = null;
		}
	}

	public void handleDistrictChange() {
		// log.info("handleDistrictChange district :" + district);

		if (district != null && !district.equals(""))
			municipalMap = municipalEBManage.getMunicipalByDistrict(district);
		else {
			municipalMap = new HashMap<String, Integer>();
			municipal = null;
			district = null;
		}
	}

	public void handleChangeDatatableRegion() {

		log.info(" region= " + region + " district= " + district
				+ " municipal= " + municipal);
		if ((region != null && !region.equals(""))) {
			log.info(" Option 1");
			this.shops = shopEBManage.getShopsByRegion(region);
		}

	}

	public void handleChangeDatatableDistrict() {

		// log.info(" region= " + region + " district= " + district +
		// " municipal= " + municipal);
		if ((region != null && !region.equals(""))
				&& (district != null && !district.equals(""))) {
			log.info(" Option 2");
			this.shops = shopEBManage.getShopsByDistrict(region, district);
		} else if (region != null && !region.equals("")) {
			this.shops = shopEBManage.getShopsByRegion(region);
		}

	}

	public void handleChangeDatatableMunicipal() {
		log.info(" region= " + region + " district= " + district
				+ " municipal= " + municipal);
		if ((region != null && !region.equals(""))
				&& (district != null && !district.equals(""))
				&& (municipal != null && !municipal.equals(""))) {
			log.info(" Option 3");
			this.shops = shopEBManage.getShopsByMunicipal(municipal);
		} else {
			handleChangeDatatableDistrict();
		}
	}

	public void handleChangeDatatable() {

		// log.info(" region= " + region + " district= " + district+
		// " municipal= " + municipal);
		/*
		 * if (((region != "") && (district == "") && (municipal == "")) ||
		 * ((region != null) && (district == null) && (municipal == null))) {
		 */
		/*
		 * if ((region != null && !region.equals("")) && ((district == null ||
		 * district.equals(""))) && ((municipal == null ||
		 * municipal.equals("")))) {
		 */
		if ((region != null && !region.equals(""))) {
			// log.info(" Option 1");
			this.shops = shopEBManage.getShopsByRegion(region);

		} else if ((region != null && !region.equals(""))
				&& (district != null && !district.equals(""))
				&& (municipal == null || municipal.equals(""))) {
			// log.info(" Option 2");
			this.shops = shopEBManage.getShopsByDistrict(region, district);

		} else if ((region != null && !region.equals(""))
				&& (district != null && !district.equals(""))
				&& (municipal != null && !municipal.equals(""))) {
			// log.info(" Option 3");
			this.shops = shopEBManage.getShopsByMunicipal(municipal);

		}
	}
}
