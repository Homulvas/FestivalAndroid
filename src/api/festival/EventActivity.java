package api.festival;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class EventActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event);
		
		final Event event = (Event) getIntent().getSerializableExtra("event");
		if (event != null) {
		Performances[] performances = event.getPerformances();
		
		View headerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.event_footer, null, false); 
		ListView performanceList = (ListView) findViewById(R.id.performanceList);
		
		PerformancesAdapter adapter = new PerformancesAdapter(this,
				R.layout.performance_list, performances);
		performanceList.addHeaderView(headerView);
		Button showMap = (Button) findViewById(R.id.show_map);
		
		final float lon = Float.parseFloat(event.getLongitude());
		final float lat = Float.parseFloat(event.getLatitude());
		if (lon != 0 || lat != 0) {
			final String title = event.getTitle();
		showMap.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				Intent mapIntent = new Intent(EventActivity.this, EventMap.class);
				mapIntent.putExtra("longitude", lon);
				mapIntent.putExtra("latitude", lat);
				mapIntent.putExtra("title", title);
                startActivityForResult(mapIntent, 0);
			}
			
		});
		} else {
			showMap.setVisibility(8);
		}
		performanceList.setAdapter(adapter);
		
		TextView festival = (TextView) findViewById(R.id.festival);
		TextView description = (TextView) findViewById(R.id.description);
		festival.setText(event.getTitle());
		description.setText(event.getDescription());
		}
	}
}
