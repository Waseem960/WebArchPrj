package it.unitn.logic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.unitn.entities.DistrictEB;

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
@ManagedBean(name = "DistrictEBManage")
public class DistrictEBManage {

	@EJB
	private DistrictEBFacade districtEBFacade = new DistrictEBFacade();

	public void addDistrict(DistrictEB districtEB) {
		districtEBFacade.createDistrict(districtEB);

	}

	public Integer getlastIdDistrict() {

		return districtEBFacade.getLastDistricInsertedID();

	}

	public DistrictEB getDistrictByName(String name) {

		return districtEBFacade.getIdDistrictByName(name);
	}

	public Map<String, Integer> getDistrictByRegion(String region) {
		// TODO Auto-generated method stu
		Map<String, Integer> districtMap = new HashMap<String, Integer>();
		
		Integer idregion= Integer.valueOf(region);
		
		List<DistrictEB> districtList = districtEBFacade
				.getDistrictsByRegionId(idregion);
		
		Iterator<DistrictEB> iterator = districtList.iterator();
		//System.out.println("districtList size "+ districtList.size());
		DistrictEB districtEB;
		
		while (iterator.hasNext()) {
			districtEB = (DistrictEB) iterator.next();
			districtMap.put(districtEB.getName(), districtEB.getIddistrict());
		}
		Map<String, Integer> treeMap = new TreeMap<String, Integer>(districtMap);
		
		return treeMap;
	}
}
