package api.festival;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class EventActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event);
		
		TextView festival = (TextView) findViewById(R.id.festival);
		TextView description = (TextView) findViewById(R.id.description);
		
		festival.setText("Trolz");
		description.setText("herp derp derp");
	}
}
