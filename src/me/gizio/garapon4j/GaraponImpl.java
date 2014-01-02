package me.gizio.garapon4j;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import me.gizio.garapon4j.auth.MyAuthImpl;
import me.gizio.garapon4j.json.ProgramInfo;
import me.gizio.garapon4j.other.GaraponSettings;
import me.gizio.garapon4j.other.MyConstants;
import me.gizio.garapon4j.search.SearchResult;

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
import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GaraponImpl implements Garapon {

	public GaraponSettings settings;
	public ObjectMapper mapper;
	private static GaraponImpl instance = new GaraponImpl();

	private GaraponImpl() {
	}

	public static GaraponImpl getInstance() {
		return instance;
	}

	@Override
	public String initializeByPlainPassword(String user, String password,
			String devId) {
		String md5Password = getMD5Text(password);
		return initialize(user, md5Password, devId);
	}

	public String initialize(String user, String md5Password, String devId) {
		settings = GaraponSettings.getInstance();
		settings.cleanAll();
		settings.setUser(user);
		settings.setMD5Password(md5Password);
		settings.setDevId(devId);
		MyAuthImpl auth = MyAuthImpl.getInstance();
		auth.execute();
		return null;
	}

	public ArrayList<ProgramInfo> search(String json) {
		SearchResult search = null;
		ArrayList<ProgramInfo> result = null;
		String resultJsonString = searchJson(json);
		getObjectMapper();
		try {
			search = mapper.readValue(resultJsonString.getBytes(),
					SearchResult.class);
			result = search.getProgram();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public SearchBuilder getSearchBuilder() {
		return SearchBuilder.newInstance();
	}

	private String searchJson(String json) {
		settings = GaraponSettings.getInstance();
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String devId = settings.getDevId();
		if (devId == null) {
			// fail
		} else {
			// success
			try {
				HttpPost httpPost = new HttpPost(getBaseUrl()
						+ "/search?dev_id=" + settings.getDevId()
						+ "&gtvsession=" + settings.getGtvSessionId());
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				// params.add(new BasicNameValuePair(
				// MyConstants.USER_KEY_TERMINAL, settings.getUser()));
				try {
					httpPost.setEntity(new UrlEncodedFormEntity(params));
					CloseableHttpResponse response = httpclient
							.execute(httpPost);
					ResponseHandler<String> handler = new BasicResponseHandler();
					String body = handler.handleResponse(response);
					// returns json

					return body;
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
		return null;
	}

	@Override
	public String favorite(String gtvid, String rank) {
		settings = GaraponSettings.getInstance();
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String devId = settings.getDevId();
		if (devId == null) {
			// fail
		} else {
			// success
			try {
				HttpPost httpPost = new HttpPost(getBaseUrl()
						+ "/favorite?dev_id=" + settings.getDevId()
						+ "&gtvsession=" + settings.getGtvSessionId());
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair(MyConstants.GTVID_KEY,
						settings.getUser()));
				params.add(new BasicNameValuePair("gtvid", gtvid));
				params.add(new BasicNameValuePair("rank", rank));
				try {
					httpPost.setEntity(new UrlEncodedFormEntity(params));
					CloseableHttpResponse response = httpclient
							.execute(httpPost);
					ResponseHandler<String> handler = new BasicResponseHandler();
					String body = handler.handleResponse(response);
					// returns json

					return body;
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
		return null;
	}

	@Override
	public String channel() {
		settings = GaraponSettings.getInstance();
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String devId = settings.getDevId();
		if (devId == null) {
			// fail
		} else {
			// success
			try {
				HttpPost httpPost = new HttpPost(getBaseUrl()
						+ "/channel?dev_id=" + settings.getDevId()
						+ "&gtvsession=" + settings.getGtvSessionId());
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

					return body;
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
		return null;
	}

	String getMD5Text(String text) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] md5passwdBytes = md.digest(text.getBytes());

			BigInteger bigInt = new BigInteger(1, md5passwdBytes);
			return bigInt.toString(16);
			// Now we need to zero pad it if you actually want the full 32
			// chars.
			// while(hashtext.length() < 32 ){
			// hashtext = "0"+hashtext;
			// }
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getBaseUrl() {
		return "http://" + settings.getIpAddress() + ":" + settings.getPort()
				+ "/gapi/v3";
	}

	private void getObjectMapper() {
		if (mapper == null) {
			mapper = new ObjectMapper();
		}
	}

	public static class SearchBuilder {
		private static List<NameValuePair> params;

		public static SearchBuilder newInstance() {
			params = new ArrayList<NameValuePair>();
			return new SearchBuilder();
		}

		public SearchBuilder setN(String n) {
			params.add(new BasicNameValuePair("n", n));
			return this;
		}

		public SearchBuilder setP(String p) {
			params.add(new BasicNameValuePair("p", p));
			return this;
		}

		public SearchBuilder setS(String s) {
			params.add(new BasicNameValuePair("s", s));
			return this;
		}

		public SearchBuilder setKey(String key) {
			params.add(new BasicNameValuePair("key", key));
			return this;
		}

		public SearchBuilder setGtvid(ArrayList<String> gtvid) {
			// FIXME
			if (gtvid.size() == 1) {
				params.add(new BasicNameValuePair("gtvid", gtvid.get(0)));
			} else if (gtvid.size() > 1) {
				String gtvidString = null;
				for (String id : gtvid) {
					if (gtvidString != null) {
						gtvidString += ",";
					}
					gtvidString += id;
				}
				params.add(new BasicNameValuePair("gtvlist", gtvidString));
			}
			return this;
		}

		public SearchBuilder setGtvid(String gtvid) {
			params.add(new BasicNameValuePair("gtvid", gtvid));
			return this;
		}

		public SearchBuilder setGenre0(String genre0) {
			params.add(new BasicNameValuePair("genre0", genre0));
			return this;
		}

		public SearchBuilder setGenre1(String genre1) {
			params.add(new BasicNameValuePair("genre1", genre1));
			return this;
		}

		public SearchBuilder setCh(String ch) {
			params.add(new BasicNameValuePair("ch", ch));
			return this;
		}

		public SearchBuilder setDt(String dt) {
			params.add(new BasicNameValuePair("dt", dt));
			return this;
		}

		public SearchBuilder setSdate(DateTime sdate) {
			params.add(new BasicNameValuePair("sdate", sdate
					.toString("yyyy-MM-DD hh:mm:ss")));
			return this;
		}

		public SearchBuilder setEdate(DateTime edate) {
			params.add(new BasicNameValuePair("edate", edate
					.toString("yyyy-MM-DD hh:mm:ss")));
			return this;
		}

		public SearchBuilder setRank(String rank) {
			params.add(new BasicNameValuePair("rank", rank));
			return this;
		}

		public SearchBuilder setSort(String sort) {
			params.add(new BasicNameValuePair("sort", sort));
			return this;
		}

		public SearchBuilder setVideo(String video) {
			params.add(new BasicNameValuePair("video", video));
			return this;
		}

		public ArrayList<ProgramInfo> execute() {
			SearchResult search = null;
			ArrayList<ProgramInfo> result = null;

			GaraponSettings settings = GaraponSettings.getInstance();
			CloseableHttpClient httpclient = HttpClients.createDefault();

			String resultJsonString = null;

			String devId = settings.getDevId();
			if (devId == null) {
				// fail
			} else {
				// success
				try {
					HttpPost httpPost = new HttpPost("http://"
							+ settings.getIpAddress() + ":"
							+ settings.getPort() + "/gapi/v3"
							+ "/search?dev_id=" + settings.getDevId()
							+ "&gtvsession=" + settings.getGtvSessionId());
					try {
						httpPost.setEntity(new UrlEncodedFormEntity(params));
						CloseableHttpResponse response = httpclient
								.execute(httpPost);
						ResponseHandler<String> handler = new BasicResponseHandler();
						String body = handler.handleResponse(response);
						// returns json

						resultJsonString = body;
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

			ObjectMapper mapper = GaraponImpl.getInstance().mapper;
			try {
				search = mapper.readValue(resultJsonString.getBytes(),
						SearchResult.class);
				result = search.getProgram();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
	}
}
