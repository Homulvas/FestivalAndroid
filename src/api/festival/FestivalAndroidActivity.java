package api.festival;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class FestivalAndroidActivity extends Activity {
	static final int size = 10;
	private ProgressDialog dialog;
	private API api;
	private Button ok, art, book, international, jazz, mela, tattoo;
	private EditText entry;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		api = new API("3FPE9X151AMKIqrv", "V6KLOjmYaz8r_cYWTKIfVPfkHIiIj7Ha");
		
		ok = (Button) findViewById(R.id.ok);
		entry = (EditText) findViewById(R.id.entry);
		ok.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				if (entry.getText().length() != 0) {
					((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
							.hideSoftInputFromWindow(entry.getWindowToken(), 0);
//					Intent festivalsIntent = new Intent(arg0.getContext(), FestivalsActivity.class);
//					festivalsIntent.putExtra("title", entry.getText());
//	                startActivityForResult(festivalsIntent, 0);
					dialog = ProgressDialog.show(FestivalAndroidActivity.this,
							"", "Loading. Please wait...", true);
//					new EventThread(list).start();
				}
			}

		});
		
		art = (Button) findViewById(R.id.art);
		art.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		book = (Button) findViewById(R.id.book);
		book.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		international = (Button) findViewById(R.id.international);
		international.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		jazz = (Button) findViewById(R.id.jazz);
		jazz.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		mela = (Button) findViewById(R.id.mela);
		mela.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		tattoo = (Button) findViewById(R.id.tattoo);
		tattoo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}

	private class EventThread extends Thread {

		private ListView listView;
		private EventAdapter adapter;

		public EventThread(ListView listView) {
			this.listView = listView;
		}

		public void run() {
			String entryText = "joe";
			entryText = entryText.replace("\n", " ");
			entryText = entryText.trim();

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("title", entryText);
			map.put("size", Integer.toString(size));
			int from = 0;
			map.put("from", Integer.toString(from));
			Event[] events = api.getEvents(map);

			ArrayList<Event> names = new ArrayList<Event>();

			while (events.length != 0) {
				for (Event event : events) {
					names.add(event);
				}
				from += size;
				map.put("from", Integer.toString(from));
				events = api.getEvents(map);
			}
			Collections.sort(names);
			adapter = new EventAdapter(listView.getContext(),
					R.layout.list, names);
			handler.sendEmptyMessage(0);
		}

		private Handler handler = new Handler() {

			public void handleMessage(Message msg) {
				listView.setAdapter(adapter);
				dialog.dismiss();
			}
		};
	}
}