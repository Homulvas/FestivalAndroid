package api.festival;

import java.util.Comparator;

public class ItemComparator implements Comparator<Item>{

	public int compare(Item arg0, Item arg1) {
		int result = arg0.getStart().compareTo(arg1.getStart());
		if (result == 0) {
			return arg0.getTitle().compareTo(arg1.getTitle());
		}
		return result;
	}
}
