package api.festival;

import java.util.Comparator;

public class ItemComparator implements Comparator<Event>{

	public int compare(Event arg0, Event arg1) {
		return arg0.getTitle().compareTo(arg1.getTitle());
	}
}
