package api.festival;

public class Performance{
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
	
	public void setStart(String start) {
		this.start = start;
	}

	public int compareTo(Performance arg0) {
		return start.compareTo(arg0.getStart());
	}
}
