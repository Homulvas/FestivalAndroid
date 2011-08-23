package api.festival;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<Item> {

	private ArrayList<Item> items;
	private ArrayList<String> dates;
	private Context context;
	private boolean performances;

	public ItemAdapter(Context context, int textViewResourceId,
			ArrayList<Item> objects, ArrayList<String> dates, Serializable serializable) {
		super(context, textViewResourceId, objects);
		items = objects;
		this.dates = dates;
		this.context = context;
		performances = (Boolean) serializable;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			if (performances) {
				v = vi.inflate(R.layout.date_list, null);
			} else {
				v = vi.inflate(R.layout.list, null);
			}
		}
		Item e = items.get(position);
		if (e != null) {
			TextView nameText = (TextView) v.findViewById(R.id.event);

			if (nameText != null) {
				nameText.setText(e.getTitle());
			}
			if (performances) {
				TextView date = (TextView) v.findViewById(R.id.date);
				
				if (date != null) {
					date.setText(dates.get(position));
					
				}
			}
		}
		return v;
	}

}
