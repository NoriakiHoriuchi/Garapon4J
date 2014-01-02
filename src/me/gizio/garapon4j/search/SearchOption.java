package me.gizio.garapon4j.search;

import java.util.ArrayList;

import org.joda.time.DateTime;

public class SearchOption {
	private int n;
	private int p;
	private String s;
	private String key;
	private ArrayList<String> gtvids;
	private String genre0;
	private String genre1;
	private String ch;
	private String dt;
	private DateTime sdate;
	private DateTime edate;
	private String rank;
	private String sort;
	private String video;
	
	public int getN() {
		return n;
	}
	public int getP() {
		return p;
	}
	public String getS() {
		return s;
	}
	public String getKey() {
		return key;
	}
	public ArrayList<String> getGtvids() {
		return gtvids;
	}
	public String getGenre0() {
		return genre0;
	}
	public String getGenre1() {
		return genre1;
	}
	public String getCh() {
		return ch;
	}
	public String getDt() {
		return dt;
	}
	public DateTime getSdate() {
		return sdate;
	}
	public DateTime getEdate() {
		return edate;
	}
	public String getRank() {
		return rank;
	}
	public String getSort() {
		return sort;
	}
	public String getVideo() {
		return video;
	}
	public void setN(int n) {
		this.n = n;
	}
	public void setP(int p) {
		this.p = p;
	}
	public void setS(String s) {
		this.s = s;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setGtvids(ArrayList<String> gtvids) {
		this.gtvids = gtvids;
	}
	public void setGenre0(String genre0) {
		this.genre0 = genre0;
	}
	public void setGenre1(String genre1) {
		this.genre1 = genre1;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public void setSdate(DateTime sdate) {
		this.sdate = sdate;
	}
	public void setEdate(DateTime edate) {
		this.edate = edate;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	
	
}
