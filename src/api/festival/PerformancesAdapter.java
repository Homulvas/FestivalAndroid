package api.festival;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PerformancesAdapter extends ArrayAdapter<Performances> {

	private Performances[] performances;
	private Context context;

	public PerformancesAdapter(Context context, int textViewResourceId,
			Performances[] performances2) {
		super(context, textViewResourceId, performances2);
		performances = performances2;
		this.context = context;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.performance_list, null);
		}
		Performances e = performances[position];
		if (e != null) {
			TextView price = (TextView) v.findViewById(R.id.price);

			if (price != null) {
				price.setText(e.getPrice());
			}

			TextView concession = (TextView) v.findViewById(R.id.concession);

			if (concession != null) {
				String conc = e.getConcession();
				if (conc.equals("0")) {
					v.findViewById(R.id.concession_text).setVisibility(4);
					concession.setVisibility(8);
				} else {
				concession.setText(conc);
				}
			}

			TextView start = (TextView) v.findViewById(R.id.start);

			if (start != null) {
				start.setText(e.getStart());
			}

			TextView end = (TextView) v.findViewById(R.id.end);

			if (end != null) {
				end.setText(e.getEnd());
			}
		}
		return v;
	}

}
