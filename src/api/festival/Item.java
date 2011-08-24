package api.festival;

import android.os.Parcel;
import android.os.Parcelable;

public class Item extends Event implements Parcelable, Comparable<Item>{
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
	
	public String getStart() {
		return start;
	}
	
	public void setStart(String string) {
		start = string;
	}

	public int compareTo(Item arg0) {
		return title.compareTo(arg0.getTitle());
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

}
