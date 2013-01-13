/**
 * 
 */
package it.unitn;

import it.unitn.logic.DistrictEBManage;
import it.unitn.logic.MunicipalEBManage;
import it.unitn.logic.RegionEBManage;

import java.util.HashMap;
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
public class Location implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(Location.class.getName());

	private Map<String, Integer> regionMap = new HashMap<String, Integer>();
	private Map<String, Integer> districtMap = new HashMap<String, Integer>();
	private Map<String, Integer> municipalMap = new HashMap<String, Integer>();

	private String region;

	private String district;

	private String municipal;

	@EJB
	RegionEBManage regionManage;
	@EJB
	DistrictEBManage districtManage;
	@EJB
	MunicipalEBManage municipalEBManage;

	public Location() {

	}

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
	 * @return the region
	 */
	public String getRegion() {
		//System.out.println(" getRegion: " + region);
		return region;
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
	 * @param region
	 *            the region to set
	 */
	public void setRegion(String region) {
		//System.out.println(" setRegion: " + region);
		this.region = region;
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

	public void createRegionMap() {
		regionManage.getAllRegion();

	}

	public void handleRegionChange() {
		// E' un intero idregione
		//System.out.println(" region :" + region);
		if (region != null && !region.equals(""))
			districtMap = districtManage.getDistrictByRegion(region);
		else {
			districtMap = new HashMap<String, Integer>();
			district = null;
		}
	}

	public void handleDistrictChange() {
		
		//System.out.println(" district :" + district);
		if (district != null && !district.equals(""))
			municipalMap = municipalEBManage.getMunicipalByDistrict(district);
		else
			municipalMap = new HashMap<String, Integer>();
	}
	
}
