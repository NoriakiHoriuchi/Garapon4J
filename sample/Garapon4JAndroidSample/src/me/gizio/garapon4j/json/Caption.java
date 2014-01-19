package me.gizio.garapon4j.json;

public class Caption {
	private String captionText;
	private String captionTime;

	public Caption(){
		
	}
	
	public Caption(String captionText, String captionTime) {
		this.captionText = captionText;
		this.captionTime = captionTime;
	}

	public String getCaptionText() {
		return captionText;
	}

	public String getCaptionTime() {
		return captionTime;
	}

	public void setCaptionText(String captionText) {
		this.captionText = captionText;
	}

	public void setCaptionTime(String captionTime) {
		this.captionTime = captionTime;
	}

}
