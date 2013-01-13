package it.unitn.logic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.unitn.entities.MunicipalEB;
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
@ManagedBean(name = "MunicipalEBManage")
public class MunicipalEBManage {

	@EJB
	private MunicipalEBFacade municipalEBFacade = new MunicipalEBFacade();

	public void addMunicipal(MunicipalEB municipalEB) {
		municipalEBFacade.createMunicipalEB(municipalEB);
	}

	public Integer getlastIdMunicipal() {

		return municipalEBFacade.getLastMunicipalInsertedID();

	}

	public Integer getIDMunicipalByName(String name) {

		return municipalEBFacade.getIdMunicipalByName(name);
	}

	public Map<String, Integer> getMunicipalByDistrict(String district) {
		Map<String, Integer> municipalMap = new HashMap<String, Integer>();

		Integer idDistrict = Integer.valueOf(district);
		List<MunicipalEB> municipalList = municipalEBFacade
				.getMunicipalsByDistrictid(idDistrict);
		Iterator<MunicipalEB> iterator = municipalList.iterator();
		// System.out.println(" municipalList size " + municipalList.size());
		MunicipalEB municipalEB;

		while (iterator.hasNext()) {
			municipalEB = (MunicipalEB) iterator.next();
			municipalMap.put(municipalEB.getName(),
					municipalEB.getIdmunicipal());
		}

		Map<String, Integer> treeMap = new TreeMap<String, Integer>(
				municipalMap);
		// System.out.println(" municipalMap size " + municipalMap.size());
		return treeMap;
	}

	public MunicipalEB getMunicipalByID(Integer idCity) {
		// TODO Auto-generated method stub
		MunicipalEB municipalEB;
		municipalEB = municipalEBFacade.getMunicipalByID(idCity);
		return municipalEB;
	}

	public Integer getMunicipalByName(String name) {
		// TODO Auto-generated method stub
		Integer myint = municipalEBFacade.getIdMunicipalByName(name);
		return myint;
	}

	public String getMunicipalNameByIdCity(Integer idCity) {
		return municipalEBFacade.getMunicipalNameByIdCity(idCity);
	}
}
