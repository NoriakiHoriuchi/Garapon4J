package me.gizio.garapon4j.json;

import java.util.ArrayList;

import me.gizio.garapon4j.other.GaraponSettings;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class Program {
	private String gtvid;
	private DateTime startdate;
	private Duration duration;
	private int ch;
	private String title;
	private String description;
	private int favorite;
	private String genre1;
	private String genre2;
	private ArrayList<String> genre;
	private String bc;
	private String bc_tags;
	private int ts;
	private int caption_hit;
	private Caption caption;

	private GaraponSettings settings;

	public Program() {

	}

	public Program(String gtvid, DateTime startdate, Duration duration,
			int ch, String title, String description, int favorite,
			ArrayList<String> genre, String bc, String bc_tags, int ts,
			int caption_hit, Caption caption) {
		super();
		this.gtvid = gtvid;
		this.startdate = startdate;
		this.duration = duration;
		this.ch = ch;
		this.title = title;
		this.description = description;
		this.favorite = favorite;
		this.genre = genre;
		this.bc = bc;
		this.bc_tags = bc_tags;
		this.ts = ts;
		this.caption_hit = caption_hit;
		this.caption = caption;
	}

	public String getGtvid() {
		return gtvid;
	}

	public DateTime getStartdate() {
		return startdate;
	}

	public Duration getDuration() {
		return duration;
	}

	public int getCh() {
		return ch;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public int getFavorite() {
		return favorite;
	}

	public String getGenre1() {
		return genre1;
	}

	public String getGenre2() {
		return genre2;
	}

	public ArrayList<String> getGenre() {
		return genre;
	}

	public String getBc() {
		return bc;
	}

	public String getBc_tags() {
		return bc_tags;
	}

	public int getTs() {
		return ts;
	}

	public int getCaption_hit() {
		return caption_hit;
	}

	public Caption getCaption() {
		return caption;
	}

	public void setGtvid(String gtvid) {
		this.gtvid = gtvid;
	}

	public void setStartdate(String startdateString) {
		this.startdate = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
				.parseDateTime(startdateString);
	}

	public void setDuration(String durationString) {
		// hh:mm:ss => milliseconds

		long hour = Integer.parseInt(durationString.substring(0, 1));
		long minute = Integer.parseInt(durationString.substring(3, 4));
		long second = Integer.parseInt(durationString.substring(6, 7));
		this.duration = new Duration(
				(hour * 3600 + minute * 60 + second) * 1000);
	}

	public void setCh(int ch) {
		this.ch = ch;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}

	public void setGenre1(String genre1) {
		this.genre1 = genre1;
	}

	public void setGenre2(String genre2) {
		this.genre2 = genre2;
	}

	public void setGenre(ArrayList<String> genre) {
		this.genre = genre;
	}

	public void setBc(String bc) {
		this.bc = bc;
	}

	public void setBc_tags(String bc_tags) {
		this.bc_tags = bc_tags;
	}

	public void setTs(int ts) {
		this.ts = ts;
	}

	public void setCaption_hit(int caption_hit) {
		this.caption_hit = caption_hit;
	}

	public void setCaption(Caption caption) {
		this.caption = caption;
	}

	public String getThumbUrl() {
		settings = GaraponSettings.getInstance();
		return getGaraponUrl() + "/thumbs/" + gtvid;
	}

	public String getRtmpUrl() {
		settings = GaraponSettings.getInstance();
		return getGaraponUrl() + "/" + gtvid + "-" + settings.getGtvSessionId();
	}

	public String getHttpUrl() {
		settings = GaraponSettings.getInstance();
		return getGaraponUrl() + "/cgi-bin/play/m3u8.cgi?" + gtvid + "-"
				+ settings.getGtvSessionId();
	}

	public String getSharingUrl() {
		return getSharingUrl(0);
	}

	public String getSharingUrl(int second) {
		return "http://garapon.info/play/" + gtvid + ":" + second;
	}

	private String getGaraponUrl() {
		return "http://" + settings.getIpAddress() + ":" + settings.getPort();
	}
	
	@JsonAnySetter
	  public void handleUnknown(String key, Object value) {
	    // do something: put to a Map; log a warning, whatever
	  }
}
