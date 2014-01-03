package me.gizio.garapon4j.auth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import me.gizio.garapon4j.exception.GaraponException;
import me.gizio.garapon4j.exception.UnknownDeveloperException;
import me.gizio.garapon4j.exception.UnknownIPAddressException;
import me.gizio.garapon4j.exception.UnknownRegistkeyException;
import me.gizio.garapon4j.exception.UnknownUserException;
import me.gizio.garapon4j.exception.WrongPasswordException;
import me.gizio.garapon4j.json.LoginInfo;
import me.gizio.garapon4j.other.GaraponSettings;
import me.gizio.garapon4j.other.MyConstants;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MyAuthImpl implements MyAuth {

	GaraponSettings settings;

	private static MyAuthImpl instance = new MyAuthImpl();

	private MyAuthImpl() {
	}

	public static MyAuthImpl getInstance() {
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
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String devId = settings.getDevId();
		if (devId == null) {
			// fail
		} else {
			HttpPost httpPost = new HttpPost(
					"http://garagw.garapon.info/getgtvaddress");
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(MyConstants.USER_KEY_WEB,
					settings.getUser()));
			params.add(new BasicNameValuePair(MyConstants.MD5PSWD_KEY_WEB,
					settings.getMD5Password()));
			params.add(new BasicNameValuePair(MyConstants.DEV_ID_KEY, settings
					.getDevId()));
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(params));
				CloseableHttpResponse response = httpclient.execute(httpPost);
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
				if (body.startsWith("1")) {
					if (body.contains("wrong password")) {
						throw new WrongPasswordException(
								substringAfterSemicolon(body));
					} else if (body.contains("unknown user")) {
						throw new UnknownUserException(
								substringAfterSemicolon(body));
					} else if (body.contains("unknown registerkey")) {
						throw new UnknownRegistkeyException(
								substringAfterSemicolon(body));
					} else if (body.contains("unknown ip address")) {
						throw new UnknownIPAddressException(
								substringAfterSemicolon(body));
					} else if (body.contains("unknown developer")) {
						throw new UnknownDeveloperException(
								substringAfterSemicolon(body));
					} else {
						// throw new Garapon4jException(body);
					}
				} else if (body.startsWith("0")) {
					// 0;success
					String[] str = body.split("\n");
					// String message = substringAfterSemicolon(str[0]);
					settings.setIpAddress(substringAfterSemicolon(str[1]));
					settings.setPrivateIpAddress(substringAfterSemicolon(str[2]));
					settings.setGrobalIpAddress(substringAfterSemicolon(str[3]));
					settings.setPort(substringAfterSemicolon(str[4]));
					settings.setPort2(substringAfterSemicolon(str[5]));
					settings.setGtvVer(substringAfterSemicolon(str[6]));
					System.out
							.println("------------ executedWebAuth -----------");
					return true;
				} else {
					// throw new Garapon4jException(body);
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// response.close();

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
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String devId = settings.getDevId();
		if (devId == null) {
			// fail
		} else {
			// success
			try {
				// Port No. is Required (I don't know why it is.)
				HttpPost httpPost = new HttpPost("http://"
						+ settings.getIpAddress() + ":" + settings.getPort()
						+ "/gapi/v3/auth?dev_id=" + settings.getDevId());
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair(
						MyConstants.USER_KEY_TERMINAL, settings.getUser()));
				params.add(new BasicNameValuePair(
						MyConstants.MD5PSWD_KEY_TERMINAL, settings
								.getMD5Password()));
				params.add(new BasicNameValuePair(MyConstants.AUTHTYPE_KEY,
						MyConstants.LOGIN_KEY));
				try {
					httpPost.setEntity(new UrlEncodedFormEntity(params));
					CloseableHttpResponse response = httpclient
							.execute(httpPost);
					ResponseHandler<String> handler = new BasicResponseHandler();
					String body = handler.handleResponse(response);
					// returns json
					System.out.println(body);
					ObjectMapper mapper = new ObjectMapper();
					LoginInfo info = mapper.readValue(body, LoginInfo.class);
					settings.setGtvSessionId(info.getGtvsession());
					settings.setGtvFarmwareVer(info.getVersion());

					System.out
							.println("------------ executedTerminalAuth -----------");
					return true;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					// response.close();

				}
			} catch (Exception e) {
				// TODO
			}
		}
		return false;
	}

	private static String substringAfterSemicolon(String str) {
		int semicolon = str.indexOf(";");
		return str.substring(semicolon + 1);
	}
}
