package api.festival;

import java.util.Comparator;

public class PerformanceComparator implements Comparator<Performance>{

	public int compare(Performance arg0, Performance arg1) {
		return arg0.getStart().compareTo(arg1.getStart());
	}

}
