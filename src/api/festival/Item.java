package api.festival;

import android.os.Parcel;
import android.os.Parcelable;

public class Item extends Event implements Parcelable, Comparable<Item>,
		Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8173428604769963697L;
	private String title;
	private String url;
	private String start;

	public Item(Parcel arg0) {
		String[] data = new String[3];

		arg0.readStringArray(data);
		this.title = data[0];
		this.url = data[1];
		this.start = data[2];
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

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

	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeStringArray(new String[] { this.title, this.url, this.start });
	}

	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

		public Item createFromParcel(Parcel arg0) {
			return new Item(arg0);
		}

		public Item[] newArray(int arg0) {
			return new Item[arg0];
		}

	};

}
