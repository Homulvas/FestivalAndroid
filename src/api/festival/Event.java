package api.festival;

import java.io.Serializable;

public class Event implements Serializable {
	private String age_category;
	private String artist;
	private String code;
	private String description;
	private String festival;
	private String festival_id;
	private String genre;
	private String latitude;
	private String longitude;
	private String title;
	private String updated;
	private String url;
	private String website;
	private Venue venue;
	private Performances[] performances;

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Performances[] getPerformances() {
		return performances;
	}
}
