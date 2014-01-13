package me.gizio.garapon4j.auth;

import java.util.ArrayList;
import java.util.List;

import me.gizio.garapon4j.exception.GaraponException;
import me.gizio.garapon4j.exception.UnknownDeveloperException;
import me.gizio.garapon4j.exception.UnknownIPAddressException;
import me.gizio.garapon4j.exception.UnknownRegistkeyException;
import me.gizio.garapon4j.exception.UnknownUserException;
import me.gizio.garapon4j.exception.WrongPasswordException;
import me.gizio.garapon4j.other.GaraponConnection;
import me.gizio.garapon4j.other.GaraponSettings;
import me.gizio.garapon4j.other.GaraponConstants;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class GaraponAuthImpl implements GaraponAuth {

	GaraponSettings settings;

	private static GaraponAuthImpl instance = new GaraponAuthImpl();

	private GaraponAuthImpl() {
	}

	public static GaraponAuthImpl getInstance() {
		return instance;
	}

	@Override
	public boolean execute() {
		try {
			boolean resultWebAuth = executeWebAuth();
			if (resultWebAuth == true) {
				boolean resultTerminalAuth = executeTerminalAuth();
				if (resultTerminalAuth == true) {
					return true;
				}
			}
		} catch (GaraponException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean executeWebAuth() throws GaraponException {
		settings = GaraponSettings.getInstance();
		String devId = settings.getDevId();
		if (devId == null) {
			// fail
		} else {
			String url = "http://garagw.garapon.info/getgtvaddress";
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(GaraponConstants.USER_KEY_WEB,
					settings.getUser()));
			params.add(new BasicNameValuePair(GaraponConstants.MD5PSWD_KEY_WEB,
					settings.getMD5Password()));
			params.add(new BasicNameValuePair(GaraponConstants.DEV_ID_KEY, settings
					.getDevId()));

			String body = GaraponConnection.post(url, params);
			if (body.startsWith("1")) {
				throwException(body);
			} else if (body.startsWith("0")) {
				setGaraponVariables(body);
			} else {
				// throw new Garapon4jException(body);
			}
		}
		return false;
	}

	/**
	 * 
	 */
	@Override
	public boolean executeTerminalAuth() throws GaraponException {
		System.out.println("------------ executingTerminalAuth -----------");
		settings = GaraponSettings.getInstance();
		String devId = settings.getDevId();
		if (devId == null) {
			// fail
		} else {
			// success
			// Port No. is Required (I don't know why it is.)
			String url = "http://" + settings.getIpAddress() + ":"
					+ settings.getPort() + "/gapi/v3/auth?dev_id="
					+ settings.getDevId();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(GaraponConstants.USER_KEY_TERMINAL,
					settings.getUser()));
			params.add(new BasicNameValuePair(GaraponConstants.MD5PSWD_KEY_TERMINAL,
					settings.getMD5Password()));
			params.add(new BasicNameValuePair(GaraponConstants.AUTHTYPE_KEY,
					GaraponConstants.LOGIN_KEY));

			String body = GaraponConnection.post(url, params);
			if (body.startsWith("1")) {
				throwException(body);
			} else if (body.startsWith("0")) {
				setGaraponVariables(body);
				return true;
			} else {
				// throw new Garapon4jException(body);
			}
		}
		return false;
	}

	private static void throwException(String body) throws GaraponException {
		if (body.contains("wrong password")) {
			throw new WrongPasswordException(substringAfterSemicolon(body));
		} else if (body.contains("unknown user")) {
			throw new UnknownUserException(substringAfterSemicolon(body));
		} else if (body.contains("unknown registerkey")) {
			throw new UnknownRegistkeyException(substringAfterSemicolon(body));
		} else if (body.contains("unknown ip address")) {
			throw new UnknownIPAddressException(substringAfterSemicolon(body));
		} else if (body.contains("unknown developer")) {
			throw new UnknownDeveloperException(substringAfterSemicolon(body));
		} else {
			// throw new Garapon4jException(body);
		}
	}

	private void setGaraponVariables(String body) {
		// 0;success
		String[] str = body.split("\n");
		// String message = substringAfterSemicolon(str[0]);
		settings.setIpAddress(substringAfterSemicolon(str[1]));
		settings.setPrivateIpAddress(substringAfterSemicolon(str[2]));
		settings.setGrobalIpAddress(substringAfterSemicolon(str[3]));
		settings.setPort(substringAfterSemicolon(str[4]));
		settings.setPort2(substringAfterSemicolon(str[5]));
		settings.setGtvVer(substringAfterSemicolon(str[6]));
	}

	private static String substringAfterSemicolon(String str) {
		int semicolon = str.indexOf(";");
		return str.substring(semicolon + 1);
	}
}
