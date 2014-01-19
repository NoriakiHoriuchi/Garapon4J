package me.gizio.garapon4j.other;

public class GaraponSettings {

	private static GaraponSettings instance = new GaraponSettings();

	private GaraponSettings() {
	}

	public static GaraponSettings getInstance() {
		return instance;
	}

	private String user = null;
	private String md5Password = null;
	private String devId = null;

	private String ipAddress = null;
	private String privateIpAddress = null;
	private String grobalIpAddress = null;
	private String port = null;
	private String port2 = null;
	private String gtvApiVer = null;

	private String gtvSessionId = null;
	private String gtvFarmwareVer = null;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMD5Password() {
		return md5Password;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getPrivateIpAddress() {
		return privateIpAddress;
	}

	public void setPrivateIpAddress(String privateIpAddress) {
		this.privateIpAddress = privateIpAddress;
	}

	public String getGrobalIpAddress() {
		return grobalIpAddress;
	}

	public void setGrobalIpAddress(String grobalIpAddress) {
		this.grobalIpAddress = grobalIpAddress;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getPort2() {
		return port2;
	}

	public void setPort2(String port2) {
		this.port2 = port2;
	}

	public String getGtvVer() {
		return gtvApiVer;
	}

	public void setGtvVer(String gtvVer) {
		this.gtvApiVer = gtvVer;
	}

	public String getMd5Password() {
		return md5Password;
	}

	public String getGtvApiVer() {
		return gtvApiVer;
	}

	public String getGtvSessionId() {
		return gtvSessionId;
	}

	public String getGtvFarmwareVer() {
		return gtvFarmwareVer;
	}

	public void setMD5Password(String md5Password) {
		this.md5Password = md5Password;
	}

	public void setGtvApiVer(String gtvApiVer) {
		this.gtvApiVer = gtvApiVer;
	}

	public void setGtvSessionId(String gtvSessionId) {
		this.gtvSessionId = gtvSessionId;
	}

	public void setGtvFarmwareVer(String gtvFarmwareVer) {
		this.gtvFarmwareVer = gtvFarmwareVer;
	}

	public void cleanAll() {

		user = null;
		md5Password = null;
		devId = null;

		ipAddress = null;
		privateIpAddress = null;
		grobalIpAddress = null;
		port = null;
		port2 = null;
		gtvApiVer = null;

		gtvSessionId = null;
		gtvFarmwareVer = null;
	}
}
