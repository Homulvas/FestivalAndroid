package api.festival;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import android.widget.Toast;

public class FestivalAndroidActivity extends Activity {
	static final int size = 10;
	static final String festival = "festival";
	static final String key = "3FPE9X151AMKIqrv";
	static final String secret = "V6KLOjmYaz8r_cYWTKIfVPfkHIiIj7Ha";
	private ProgressDialog dialog;
	private API api;
	private Button ok, art, book, international, jazz, mela, tattoo, upcoming;
	private EditText entry;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		api = new API(key, secret);

		ok = (Button) findViewById(R.id.ok);
		entry = (EditText) findViewById(R.id.entry);
		ok.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				String text = entry.getText().toString();
				if (text.length() != 0) {
					((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
							.hideSoftInputFromWindow(entry.getWindowToken(), 0);
					dialog = ProgressDialog.show(FestivalAndroidActivity.this,
							"", "Loading. Please wait...", true);
					new EventThread("title", text, false).start();
				}
			}

		});

		art = (Button) findViewById(R.id.art);
		art.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				dialog = ProgressDialog.show(FestivalAndroidActivity.this, "",
						"Loading. Please wait...", true);
				new EventThread(festival, "art", false).start();
			}

		});

		book = (Button) findViewById(R.id.book);
		book.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				dialog = ProgressDialog.show(FestivalAndroidActivity.this, "",
						"Loading. Please wait...", true);
				new EventThread(festival, "book", false).start();
			}

		});

		international = (Button) findViewById(R.id.international);
		international.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				dialog = ProgressDialog.show(FestivalAndroidActivity.this, "",
						"Loading. Please wait...", true);
				new EventThread(festival, "international", false).start();
			}

		});

		jazz = (Button) findViewById(R.id.jazz);
		jazz.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				dialog = ProgressDialog.show(FestivalAndroidActivity.this, "",
						"Loading. Please wait...", true);
				new EventThread(festival, "jazz", false).start();
			}

		});

		mela = (Button) findViewById(R.id.mela);
		mela.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				dialog = ProgressDialog.show(FestivalAndroidActivity.this, "",
						"Loading. Please wait...", true);
				new EventThread(festival, "mela", false).start();
			}

		});

		tattoo = (Button) findViewById(R.id.tattoo);
		tattoo.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				dialog = ProgressDialog.show(FestivalAndroidActivity.this, "",
						"Loading. Please wait...", true);
				new EventThread(festival, "tattoo", false).start();
			}

		});

		upcoming = (Button) findViewById(R.id.upcoming);
		upcoming.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				dialog = ProgressDialog.show(FestivalAndroidActivity.this, "",
						"Loading. Please wait...", true);
				new EventThread("date_from", null, true).start();
			}
		});

	}

	private class EventThread extends Thread {

		private ArrayList<Item> names;
		private String key, value, value2;
		private boolean performances;

		public EventThread(String key, String value, boolean performances) {
			this.key = key;
			this.value = value;
			this.performances = performances;
			if (performances) {
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				this.value = sdf.format(cal.getTime());
				cal.add(Calendar.DAY_OF_MONTH, 3);
				value2 = sdf.format(cal.getTime());
			}
		}

		public void run() {
			value = value.replace("\n", " ");
			value = value.trim();

			HashMap<String, String> map = new HashMap<String, String>();
			map.put(key, value);
			map.put("size", Integer.toString(size));
			int from = 0;
			map.put("from", Integer.toString(from));
			if (performances) {
				map.put("date_to", value2);
			}
			Item[] items = api.getEvents(map);

			names = new ArrayList<Item>();

			while (items.length != 0) {
				for (Item item : items) {
					if (performances) {
						Performances[] list = item.getPerformances();
						for (Performances perf : list) {
							String date = perf.getStart();
							if (date.compareTo(value) > 0
									&& date.compareTo(value2) < 0) {
								Item current = (Item) item.clone();
								current.setStart(date);
								names.add(current);
							}
						}
					} else {
						names.add(item);
					}
				}
				from += size;
				map.put("from", Integer.toString(from));
				items = api.getEvents(map);
			}
			if (performances) {
			Collections.sort(names, new ItemComparator());
			} else {
			Collections.sort(names);
			}

			handler.sendEmptyMessage(0);
		}

		private Handler handler = new Handler() {

			public void handleMessage(Message msg) {
				dialog.dismiss();
				if (names.size() != 0) {
					Intent festivalsIntent = new Intent(
							FestivalAndroidActivity.this,
							FestivalsActivity.class);

					festivalsIntent.putExtra("items", names);
					festivalsIntent.putExtra("performances", performances);
					startActivityForResult(festivalsIntent, 0);
				} else {
					Toast.makeText(FestivalAndroidActivity.this,
							"The events could not be downloaded", 100).show();
				}
			}
		};
	}
}