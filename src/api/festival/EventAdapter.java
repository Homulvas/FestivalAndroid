package api.festival;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EventAdapter extends ArrayAdapter<Event>{
	
	private ArrayList<Event> events;
	private Context context;
	
	public EventAdapter(Context context, int textViewResourceId,
			ArrayList<Event> objects) {
		super(context, textViewResourceId, objects);
		events = objects;
		this.context = context;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list, null);
        }
        Event e = events.get(position);
        if (e != null) {
        	TextView nameText = (TextView) v.findViewById(R.id.event);

            if (nameText != null) {
                nameText.setText(e.getTitle());
            }
        }
        return v;
	}

}
