package me.gizio.garapon4j.search;

import java.util.ArrayList;

import me.gizio.garapon4j.json.ProgramInfo;

public class SearchResult {
	private String status;
	private int hit;
	private String version;
	private ArrayList<ProgramInfo> program;

	public SearchResult() {
	}

	public SearchResult(String status, int hit, String version,
			ArrayList<ProgramInfo> program) {
		super();
		this.status = status;
		this.hit = hit;
		this.version = version;
		this.program = program;
	}

	public String getStatus() {
		return status;
	}

	public int getHit() {
		return hit;
	}

	public String getVersion() {
		return version;
	}

	public ArrayList<ProgramInfo> getProgram() {
		return program;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setPrograms(ArrayList<ProgramInfo> program) {
		this.program = program;
	}

}
