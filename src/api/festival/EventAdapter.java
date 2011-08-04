package api.festival;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

public class EventAdapter extends ArrayAdapter<Event>{

	public EventAdapter(Context context, int textViewResourceId,
			List<Event> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

}
