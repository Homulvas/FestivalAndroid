package api.festival;

import java.util.Comparator;

public class PerformanceComparator implements Comparator<Item>{

	public int compare(Item arg0, Item arg1) {
		return arg0.getStart().compareTo(arg1.getStart());
	}

}
