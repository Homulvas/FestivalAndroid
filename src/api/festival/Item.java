package api.festival;

import java.io.Serializable;

public class Item extends Event implements Serializable, Comparable<Item>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8173428604769963697L;
	private String title;
	private String url;
	private String start;
	
	public String getTitle() {
		return title;
	}
	
	public String getUrl() {
		return url;
	}

	public int compareTo(Item arg0) {
		return title.compareTo(arg0.getTitle());
	}

	public String getStart() {
		return start;
	}
	
	public void setStart(String start) {
		this.start = start;
	}
}
