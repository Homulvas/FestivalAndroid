package api.festival;

import java.util.Comparator;

public class ItemComparator implements Comparator<Item>{

	public int compare(Item arg0, Item arg1) {
		return arg0.getTitle().compareTo(arg1.getTitle());
	}
}
