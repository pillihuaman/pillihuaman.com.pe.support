package pillihuaman.com.model.request;
import com.googlecode.jmapper.annotations.JGlobalMap;

import lombok.Getter;
import lombok.Setter;


@JGlobalMap
@Getter
@Setter
public class ReqUser {
	
	private int idUser;
	private String alias;
	private String apiPassword;
	private int id_System;
	private String mail;
	private String mobilPhone;
	private String password;
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getApiPassword() {
		return apiPassword;
	}
	public void setApiPassword(String apiPassword) {
		this.apiPassword = apiPassword;
	}
	public int getId_System() {
		return id_System;
	}
	public void setId_System(int id_System) {
		this.id_System = id_System;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMobilPhone() {
		return mobilPhone;
	}
	public void setMobilPhone(String mobilPhone) {
		this.mobilPhone = mobilPhone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalPassword() {
		return salPassword;
	}
	public void setSalPassword(String salPassword) {
		this.salPassword = salPassword;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTypeDocument() {
		return typeDocument;
	}
	public void setTypeDocument(String typeDocument) {
		this.typeDocument = typeDocument;
	}
	public String getNumTypeDocument() {
		return numTypeDocument;
	}
	public void setNumTypeDocument(String numTypeDocument) {
		this.numTypeDocument = numTypeDocument;
	}
	private String salPassword;
	private String user;
	private String username;
	private String  typeDocument;
	private String   numTypeDocument;
}


