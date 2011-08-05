package api.festival;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class EventActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event);
		
		TextView festival = (TextView) findViewById(R.id.festival);
		TextView description = (TextView) findViewById(R.id.description);
		
		Event event = (Event) getIntent().getSerializableExtra("event");
		Performances[] performances = event.getPerformances();
		
		festival.setText(event.getTitle());
		description.setText(event.getDescription());
		
		ListView performanceList = (ListView) findViewById(R.id.performanceList);
		PerformancesAdapter adapter = new PerformancesAdapter(this,
				R.layout.performance_list, performances);
		performanceList.setAdapter(adapter);
	}
}
