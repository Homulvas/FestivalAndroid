package api.festival;

import java.io.Serializable;

public class Item implements Serializable, Comparable<Item>{
	private String title;
	private String url;
	
	public String getTitle() {
		return title;
	}
	
	public String getUrl() {
		return url;
	}

	public int compareTo(Item arg0) {
		return title.compareTo(arg0.getTitle());
	}
}
