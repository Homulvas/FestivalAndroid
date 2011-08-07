package api.festival;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FestivalsActivity extends Activity{
	private ListView list;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.festivals);
		
		list = new ListView(FestivalsActivity.this);
		list.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent eventIntent = new Intent(arg1.getContext(), EventActivity.class);
				eventIntent.putExtra("event", (Event) list.getItemAtPosition((int) arg3));
                startActivityForResult(eventIntent, 0);
			}
			
		});
	}
	
	

}
