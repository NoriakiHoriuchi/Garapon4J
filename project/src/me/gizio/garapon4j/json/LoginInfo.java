package me.gizio.garapon4j.json;

public class LoginInfo {
	private int status;
	private int login;
	private String gtvsession;
	private String version;

	public LoginInfo() {
	}

	public LoginInfo(int status, int login, String gtvsession, String version) {
		this.status = status;
		this.login = login;
		this.gtvsession = gtvsession;
		this.version = version;
	}

	public int getStatus() {
		return status;
	}

	public int getLogin() {
		return login;
	}

	public String getGtvsession() {
		return gtvsession;
	}

	public String getVersion() {
		return version;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setLogin(int login) {
		this.login = login;
	}

	public void setGtvsession(String gtvsession) {
		this.gtvsession = gtvsession;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
