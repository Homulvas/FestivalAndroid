package api.festival;

import java.util.ArrayList;

import android.content.Context;
import android.widget.ArrayAdapter;

public class PerformancesAdapter extends ArrayAdapter<Performances>{

	public PerformancesAdapter(Context context, int textViewResourceId,
			ArrayList<Performances> objects) {
		super(context, textViewResourceId, objects);
	}

}
