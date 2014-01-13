package me.gizio.garapon4j.json;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class Caption {
	private String caption_text;
	private String caption_time;

	public Caption(){
		
	}
	
	public Caption(String captionText, String captionTime) {
		this.caption_text = captionText;
		this.caption_time = captionTime;
	}


	public String getCaption_text() {
		return caption_text;
	}

	public String getCaption_time() {
		return caption_time;
	}

	public void setCaption_text(String caption_text) {
		this.caption_text = caption_text;
	}

	public void setCaption_time(String caption_time) {
		this.caption_time = caption_time;
	}

	@JsonAnySetter
	  public void handleUnknown(String key, Object value) {
	    // do something: put to a Map; log a warning, whatever
	  }
}
