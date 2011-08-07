package api.festival;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class FestivalsActivity extends Activity{
	private ListView list;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.festivals);
		
		list = (ListView) findViewById(R.id.festival_list);
		list.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent eventIntent = new Intent(arg1.getContext(), EventActivity.class);
				eventIntent.putExtra("event", (Event) list.getItemAtPosition((int) arg3));
                startActivityForResult(eventIntent, 0);
			}
			
		});
		ArrayList<Event> events = (ArrayList<Event>) getIntent().getSerializableExtra("events");
		list.setAdapter(new EventAdapter(this, 0, events));
	}
	
	

}
