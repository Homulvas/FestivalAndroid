package api.festival;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
				
				SharedPreferences settings = getSharedPreferences("keys", MODE_PRIVATE);
				String key = settings.getString("key", null);
				String secret = settings.getString("secret", null);
				Event eve = new API(key, secret).getEventFromUrl(id);
				
				if (eve != null) {
				eventIntent.putExtra("event", eve);
                startActivityForResult(eventIntent, 0);
				} else {
					Toast.makeText(FestivalsActivity.this, "The event information could not be downloaded", 100).show();
				}
			}
			
		});
		list.setAdapter(new ItemAdapter(this, 0, (ArrayList<Item>) getIntent().getSerializableExtra("items") ,getIntent().getSerializableExtra("performances")));
	}

}
