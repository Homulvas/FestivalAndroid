package api.festival;

import java.io.Serializable;

public class Performances implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2562907135678223983L;
	private String concession;
	private String end;
	private String price;
	private String start;
	
	public String getPrice(){
		if (price.equals("0")) {
			return "Free";
		}
		return price;
	}

	public String getConcession() {
		return concession;
	}

	public CharSequence getStart() {
		return start;
	}

	public CharSequence getEnd() {
		return end;
	}
}
