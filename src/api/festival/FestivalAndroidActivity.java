package api.festival;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FestivalAndroidActivity extends Activity {
	/** Called when the activity is first created. */
	static final String[] EVENTS = new String[] { "LeFag Event" };
	static final int size = 10;
	private ListView list;
	private Button ok;
	private TextView entry;
	private API api;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		api = new API("3FPE9X151AMKIqrv", "V6KLOjmYaz8r_cYWTKIfVPfkHIiIj7Ha");
		
		list = (ListView) findViewById(R.id.list);
		list.setAdapter(new ArrayAdapter<String>(this, R.layout.list, EVENTS));
		
		ok = (Button) findViewById(R.id.ok);
		entry = (TextView) findViewById(R.id.entry);
		ok.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				String entryText = entry.getText().toString();
				
				HashMap<String,String> map = new HashMap<String, String>();
				map.put("title", entryText);
				map.put("size", Integer.toString(size));
				int from = 0;
				map.put("from", Integer.toString(from));
				Event[] events = api.getEvents(map);
				
				ArrayList<String> names = new ArrayList<String>();
				
				while (events.length != 0) {
					for (Event event: events) {
						names.add(event.getTitle());
					}
					from += size;
					map.put("from", Integer.toString(from));
					events = api.getEvents(map);
				}
				
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(list.getContext(), R.layout.list, names);
				list.setAdapter(adapter);
			}
			
		});
	}
}