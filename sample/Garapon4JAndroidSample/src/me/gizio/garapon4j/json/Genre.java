package me.gizio.garapon4j.json;

public class Genre {
	private String genre;
	private String genre0;
	private String genre1;

	public Genre() {
	}

	public Genre(String genre) {
		this.genre = genre;
		int slash = genre.indexOf("/");
		this.genre0 = genre.substring(0, slash - 1);
		this.genre1 = genre.substring(slash + 1);

	}

	public Genre(String genre0, String genre1) {
		this.genre = genre0 + "/" + genre1;
		this.genre0 = genre0;
		this.genre1 = genre1;

	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
		int slash = genre.indexOf("/");
		this.genre0 = genre.substring(0, slash - 1);
		this.genre1 = genre.substring(slash + 1);
	}

	public String getGenre0() {
		return genre0;
	}

	public String getGenre1() {
		return genre1;
	}
}
