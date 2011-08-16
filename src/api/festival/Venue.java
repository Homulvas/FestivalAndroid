package api.festival;

import java.io.Serializable;

public class Venue implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8007684469598698816L;
	private String address;
	public String getAddress() {
		return address;
	}
	public String getDescription() {
		return description;
	}
	public String getName() {
		return name;
	}
	public String getPhone() {
		return phone;
	}
	public String getPostCode() {
		return post_code;
	}
	private String description;
	private String name;
	private String phone;
	private String post_code;
}
