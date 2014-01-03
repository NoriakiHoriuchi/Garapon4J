package me.gizio.garapon4j;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.gizio.garapon4j.auth.MyAuthImpl;
import me.gizio.garapon4j.favorite.FavoriteResult;
import me.gizio.garapon4j.json.ProgramInfo;
import me.gizio.garapon4j.other.GaraponSettings;
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
	public static ObjectMapper mapper;
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

	public ArrayList<ProgramInfo> search() {
		return search(null);
	}

	public ArrayList<ProgramInfo> search(Map<String, String> map) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (map != null) {
			setParams(map, params);
		}
		SearchResult search = null;
		ArrayList<ProgramInfo> result = null;
		settings = GaraponSettings.getInstance();
		String devId = settings.getDevId();
		if (devId == null) {
			// fail
		} else {
			// success
			String url = "http://" + settings.getIpAddress() + ":"
					+ settings.getPort() + "/gapi/v3" + "/search?dev_id="
					+ settings.getDevId() + "&gtvsession="
					+ settings.getGtvSessionId();
			String body = post(url, params);
			getObjectMapper();
			try {
				search = mapper.readValue(body.getBytes(), SearchResult.class);
				result = search.getProgram();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	private static String post(String url, List<NameValuePair> params) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		if (params != null) {
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(params));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
			ResponseHandler<String> handler = new BasicResponseHandler();
			return handler.handleResponse(response);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	private void setParams(Map<String, String> map, List<NameValuePair> params) {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			if (key.equals("n")) {
				params.add(new BasicNameValuePair(key, (String) entry
						.getValue()));
			} else if (key.equals("p")) {
				params.add(new BasicNameValuePair(key, (String) entry
						.getValue()));
			} else if (key.equals("s")) {
				params.add(new BasicNameValuePair(key, (String) entry
						.getValue()));
			} else if (key.equals("key")) {
				params.add(new BasicNameValuePair(key, (String) entry
						.getValue()));
			} else if (key.equals("gtvid")) {
				params.add(new BasicNameValuePair(key, (String) entry
						.getValue()));
			} else if (key.equals("gtvidlist")) {
				params.add(new BasicNameValuePair(key, (String) entry
						.getValue()));
			} else if (key.equals("genre0")) {
				params.add(new BasicNameValuePair(key, (String) entry
						.getValue()));
			} else if (key.equals("genre1")) {
				params.add(new BasicNameValuePair(key, (String) entry
						.getValue()));
			} else if (key.equals("ch")) {
				params.add(new BasicNameValuePair(key, (String) entry
						.getValue()));
			} else if (key.equals("dt")) {
				params.add(new BasicNameValuePair(key, (String) entry
						.getValue()));
			} else if (key.equals("sdate")) {
				params.add(new BasicNameValuePair(key, (String) entry
						.getValue()));
			} else if (key.equals("edate")) {
				params.add(new BasicNameValuePair(key, (String) entry
						.getValue()));
			} else if (key.equals("rank")) {
				params.add(new BasicNameValuePair(key, (String) entry
						.getValue()));
			} else if (key.equals("sort")) {
				params.add(new BasicNameValuePair(key, (String) entry
						.getValue()));
			} else if (key.equals("video")) {
				params.add(new BasicNameValuePair(key, (String) entry
						.getValue()));
			}
		}
	}

	public SearchBuilder getSearchBuilder() {
		return SearchBuilder.newInstance();
	}

	@Override
	public boolean favorite(String gtvid, String rank) {
		settings = GaraponSettings.getInstance();
		String devId = settings.getDevId();
		if (devId == null) {
			// fail
		} else {
			// success
			String url = getBaseUrl() + "/favorite?dev_id="
					+ settings.getDevId() + "&gtvsession="
					+ settings.getGtvSessionId();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			String body = post(url, params);
			getObjectMapper();
			try {
				FavoriteResult favorite = null;
				favorite = mapper.readValue(body.getBytes(),
						FavoriteResult.class);
				if (favorite.getStatus().equals("1")) {
					return true;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public String channel() {
		settings = GaraponSettings.getInstance();
		String devId = settings.getDevId();
		if (devId == null) {
			// fail
			return null;
		} else {
			// success
			String url = getBaseUrl() + "/channel?dev_id="
					+ settings.getDevId() + "&gtvsession="
					+ settings.getGtvSessionId();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			return post(url, params);
		}
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

	private static void getObjectMapper() {
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

		// XXX 動作確認済
		/**
		 * 1ページあたりの結果取得数を指定します。デフォルトは20。最大で100。
		 * 
		 * @param n
		 * @return
		 */
		public SearchBuilder setN(String n) {
			params.add(new BasicNameValuePair("n", n));
			return this;
		}

		// XXX 動作確認済
		/**
		 * 検索結果のページ番号を指定します。デフォルトは1。
		 * 
		 * @param p
		 * @return
		 */
		public SearchBuilder setP(String p) {
			params.add(new BasicNameValuePair("p", p));
			return this;
		}

		// XXX 動作確認済
		/**
		 * 
		 * @param s
		 *            "e"（EPG）または"c"（字幕）
		 * @return
		 */
		public SearchBuilder setS(String s) {
			params.add(new BasicNameValuePair("s", s));
			return this;
		}

		/*
		 * FIXME 症状：文字列検索ができない 全角文字が2文字以上だとヒットしない
		 */
		/**
		 * 文字列で検索します。
		 * 
		 * @param key
		 *            String
		 * @return
		 */
		public SearchBuilder setKey(String key) {
			params.add(new BasicNameValuePair("key", key));
			return this;
		}

		// XXX 動作確認済
		/**
		 * GTV IDを直接指定して検索します。
		 * 
		 * @param gtvid
		 * @return
		 */
		public SearchBuilder setGtvid(String gtvid) {
			params.add(new BasicNameValuePair("gtvid", gtvid));
			return this;
		}

		/**
		 * GTV IDを直接指定して検索します。
		 * 
		 * @param gtvid
		 * @return
		 */
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

		// FIXME ProgramInfoでparseに失敗する
		public SearchBuilder setGenre0(String genre0) {
			params.add(new BasicNameValuePair("genre0", genre0));
			return this;
		}

		// FIXME ProgramInfoでparseに失敗する
		public SearchBuilder setGenre1(String genre1) {
			params.add(new BasicNameValuePair("genre1", genre1));
			return this;
		}

		// XXX 動作確認済
		public SearchBuilder setCh(String ch) {
			params.add(new BasicNameValuePair("ch", ch));
			return this;
		}

		// XXX 動作確認済
		/**
		 * sdateとedateで設定した範囲が「番組開始時間の範囲」なのか「番組終了時間の範囲」なのかを設定します。
		 * 
		 * @param dt
		 *            番組開始時間の範囲：s、番組終了時間の範囲：e
		 * @return
		 */
		public SearchBuilder setDt(String dt) {
			params.add(new BasicNameValuePair("dt", dt));
			return this;
		}

		// XXX 動作確認済
		public SearchBuilder setSdate(DateTime sdate) {
			params.add(new BasicNameValuePair("sdate", sdate
					.toString("yyyy-MM-dd hh:mm:ss")));
			return this;
		}

		// XXX 動作確認済
		public SearchBuilder setEdate(DateTime edate) {
			params.add(new BasicNameValuePair("edate", edate
					.toString("yyyy-MM-dd hh:mm:ss")));
			return this;
		}

		// XXX 動作確認済
		public SearchBuilder setRank(String rank) {
			params.add(new BasicNameValuePair("rank", rank));
			return this;
		}

		// XXX 動作確認済
		public SearchBuilder setSort(String sort) {
			params.add(new BasicNameValuePair("sort", sort));
			return this;
		}

		public SearchBuilder setVideo(String video) {
			params.add(new BasicNameValuePair("video", video));
			return this;
		}

		public ArrayList<ProgramInfo> execute() {
			SearchResult search = new SearchResult();
			ArrayList<ProgramInfo> result = new ArrayList<ProgramInfo>();

			GaraponSettings settings = GaraponSettings.getInstance();
			String devId = settings.getDevId();
			if (devId == null) {
				// fail
			} else {
				// success
				String url = "http://" + settings.getIpAddress() + ":"
						+ settings.getPort() + "/gapi/v3" + "/search?dev_id="
						+ settings.getDevId() + "&gtvsession="
						+ settings.getGtvSessionId();
				String body = post(url, params);
				getObjectMapper();
				try {
					search = mapper.readValue(body.getBytes(),
							SearchResult.class);
					result = search.getProgram();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return result;
		}
	}
}
