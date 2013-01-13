/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn;

import it.unitn.dto.Region;
import it.unitn.dto.User;
import it.unitn.entities.DistrictEB;
import it.unitn.entities.MunicipalEB;
import it.unitn.entities.RegionEB;
import it.unitn.logic.DistrictEBManage;
import it.unitn.logic.MunicipalEBManage;
import it.unitn.logic.RegionEBFacade;
import it.unitn.logic.RegionEBManage;
import it.unitn.logic.UserManage;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;

import java.util.logging.Logger;

/**
 * 
 * @author waseem
 */
@ManagedBean
@SessionScoped
public class UserB implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String surname;
	private String username;
	private String password;
	private String email;
	private byte[] foto;

	private boolean skip;

	private static Logger log = Logger.getLogger(UserB.class.getName());

	private UploadedFile uploadedFile;

	@EJB
	UserManage userManage;
	
	public UserB() {
		
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String addUser() {
		User user = new User();
		user.setName(name);
		user.setSurname(surname);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setFoto(foto);
		userManage.InsertUser(user);
		return "Login";
	}
	
	public String LastUserID() {
		userManage.lastRecordInsert();
		return null;
	}

	public String onFlowProcess(FlowEvent event) {
		log.info("Current wizard step:" + event.getOldStep());
		log.info("Next step:" + event.getNewStep());

		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}
	public void handleFileUpload(FileUploadEvent event) {
		// add facesmessage to display with growl
		log.info("start uploading file");
		uploadedFile = event.getFile();
		//String fileName = FilenameUtils.getName(uploadedFile.getFileName());
		byte[] bytes = uploadedFile.getContents();
		foto = bytes;
		log.info("end uploading file");
	}

}
