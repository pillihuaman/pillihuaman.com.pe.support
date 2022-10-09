package pillihuaman.com.model.response;
import com.googlecode.jmapper.annotations.JGlobalMap;

import lombok.Getter;
import lombok.Setter;


@JGlobalMap
@Getter
@Setter
public class RespUser {
	
	private  int id_user;
	private String alias;
	private  int id_system;
	private String mail;
	private String mobil_Phone;
	private String user;
	private String username;
	private String api_Password;
	private String password;
	private String sal_Password;
	private   int enabled;
	private int id_rol;
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public int getId_system() {
		return id_system;
	}
	public void setId_system(int id_system) {
		this.id_system = id_system;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMobil_Phone() {
		return mobil_Phone;
	}
	public void setMobil_Phone(String mobil_Phone) {
		this.mobil_Phone = mobil_Phone;
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
	public String getApi_Password() {
		return api_Password;
	}
	public void setApi_Password(String api_Password) {
		this.api_Password = api_Password;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSal_Password() {
		return sal_Password;
	}
	public void setSal_Password(String sal_Password) {
		this.sal_Password = sal_Password;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public int getId_rol() {
		return id_rol;
	}
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}

}


