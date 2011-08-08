package api.festival;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class EventActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event);
		
		Event event = (Event) getIntent().getSerializableExtra("event");
		if (event != null) {
		Performances[] performances = event.getPerformances();
		
		View headerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.event_footer, null, false); 
		ListView performanceList = (ListView) findViewById(R.id.performanceList);
		
		PerformancesAdapter adapter = new PerformancesAdapter(this,
				R.layout.performance_list, performances);
		performanceList.addHeaderView(headerView);
		performanceList.setAdapter(adapter);
		
		TextView festival = (TextView) findViewById(R.id.festival);
		TextView description = (TextView) findViewById(R.id.description);
		festival.setText(event.getTitle());
		description.setText(event.getDescription());
		}
	}
}
