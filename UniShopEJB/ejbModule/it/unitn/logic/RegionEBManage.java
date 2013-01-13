package it.unitn.logic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
 
import it.unitn.entities.RegionEB;

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
@ManagedBean(name = "RegionEBManage")
public class RegionEBManage {

	@EJB
	private RegionEBFacade regionEBFacade = new RegionEBFacade();

	public RegionEBManage() {
		// TODO Auto-generated constructor stub
	}

	public void addRegion(RegionEB region) {
		regionEBFacade.createRegion(region);
	}

	public Integer getlastIdRegiong() {

		return regionEBFacade.getLastRegionEBInserted();

	}

	public RegionEB getRegionByName(String name) {

		return regionEBFacade.getIdRegionByName(name);
	}

	public Map<String, Integer> getAllRegion() {
		
		Map<String, Integer> regionMap = new HashMap<String, Integer>();
		List<RegionEB> regionEBlist = regionEBFacade.getAllRegionEB();
		Iterator<RegionEB> iterator = regionEBlist.iterator();
		RegionEB regionEB;
		while (iterator.hasNext()) {			
			regionEB = (RegionEB) iterator.next();
			regionMap.put(regionEB.getName(),regionEB.getIdregion());
		}
		
		Map<String, Integer> treeMap = new TreeMap<String, Integer>(regionMap);
		return treeMap;
	}

}
