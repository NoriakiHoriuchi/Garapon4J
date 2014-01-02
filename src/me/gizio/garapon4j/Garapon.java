package me.gizio.garapon4j;

import me.gizio.garapon4j.GaraponImpl.SearchBuilder;

public interface Garapon {

	/**
	 * 
	 * @param user
	 * @param password
	 * @param dev_id
	 * @return IP Address
	 */
	public String initialize(String user, String md5Password, String dev_id);

	public String initializeByPlainPassword(String user, String password,
			String dev_id);

	public SearchBuilder getSearchBuilder();

	public String favorite(String gtvid, String rank);

	public String channel();

}
