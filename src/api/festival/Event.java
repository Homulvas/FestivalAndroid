package api.festival;

import java.io.Serializable;

public class Event implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2396607897795865383L;
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
	
	public String getLatitude() {
		return latitude;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public String getAgeCategory() {
		return age_category;
	}

	public String getArtist() {
		return artist;
	}

	public String getCode() {
		return code;
	}

	public String getFestival() {
		return festival;
	}

	public String getFestivalId() {
		return festival_id;
	}

	public String getGenre() {
		return genre;
	}

	public String getUpdated() {
		return updated;
	}

	public String getUrl() {
		return url;
	}

	public String getWebsite() {
		return website;
	}

	public Venue getVenue() {
		return venue;
	}
	
	public String getAdress() {
		return venue.getAddress();
	}
}
