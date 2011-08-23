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
	
	@SuppressWarnings("unchecked")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.festivals);
		
		list = (ListView) findViewById(R.id.festival_list);
		list.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent eventIntent = new Intent(arg1.getContext(), EventActivity.class);
				String id = ((Item) list.getItemAtPosition((int) arg3)).getUrl();
				Event eve = new API("3FPE9X151AMKIqrv", "V6KLOjmYaz8r_cYWTKIfVPfkHIiIj7Ha").getEventFromUrl(id);
				
				if (eve != null) {
				eventIntent.putExtra("event", eve);
                startActivityForResult(eventIntent, 0);
				} else {
					Toast.makeText(FestivalsActivity.this, "The event information could not be downloaded", 100).show();
				}
			}
			
		});
		list.setAdapter(new ItemAdapter(this, 0, (ArrayList<Item>) getIntent().getSerializableExtra("items"), (ArrayList<String>) getIntent().getSerializableExtra("dates"),getIntent().getSerializableExtra("performances")));
	}

}
