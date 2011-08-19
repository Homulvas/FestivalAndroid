package api.festival;

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
	private ProgressDialog dialog;
	private API api;
	private Button ok, art, book, international, jazz, mela, tattoo, upcoming;
	private EditText entry;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		api = new API("3FPE9X151AMKIqrv", "V6KLOjmYaz8r_cYWTKIfVPfkHIiIj7Ha");

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
				new EventThread("festival", "art", false).start();
			}

		});

		book = (Button) findViewById(R.id.book);
		book.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				dialog = ProgressDialog.show(FestivalAndroidActivity.this, "",
						"Loading. Please wait...", true);
				new EventThread("festival", "book", false).start();
			}

		});

		international = (Button) findViewById(R.id.international);
		international.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				dialog = ProgressDialog.show(FestivalAndroidActivity.this, "",
						"Loading. Please wait...", true);
				new EventThread("festival", "international", false).start();
			}

		});

		jazz = (Button) findViewById(R.id.jazz);
		jazz.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				dialog = ProgressDialog.show(FestivalAndroidActivity.this, "",
						"Loading. Please wait...", true);
				new EventThread("festival", "jazz", false).start();
			}

		});

		mela = (Button) findViewById(R.id.mela);
		mela.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				dialog = ProgressDialog.show(FestivalAndroidActivity.this, "",
						"Loading. Please wait...", true);
				new EventThread("festival", "mela", false).start();
			}

		});

		tattoo = (Button) findViewById(R.id.tattoo);
		tattoo.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				dialog = ProgressDialog.show(FestivalAndroidActivity.this, "",
						"Loading. Please wait...", true);
				new EventThread("festival", "tattoo", false).start();
			}

		});

		upcoming = (Button) findViewById(R.id.upcoming);
		upcoming.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				dialog = ProgressDialog.show(FestivalAndroidActivity.this, "", "Loading. Please wait...", true);
				Calendar cal = Calendar.getInstance();
				new EventThread("date_from", cal.get(Calendar.YEAR) + "-"
						+ (cal.get(Calendar.MONTH) + 1) + "-"
						+ cal.get(Calendar.DAY_OF_MONTH) + " "
						+ cal.get(Calendar.HOUR_OF_DAY) + ":"
						+ cal.get(Calendar.MINUTE) + ":"
						+ cal.get(Calendar.SECOND), true).start();
			}
		});

	}

	private class EventThread extends Thread {

		private ArrayList<Item> names;
		private String key, value;
		private boolean performances;

		public EventThread(String key, String value, boolean performances) {
			this.key = key;
			this.value = value;
			this.performances = performances;
		}

		public void run() {
			value = value.replace("\n", " ");
			value = value.trim();

			HashMap<String, String> map = new HashMap<String, String>();
			map.put(key, value);
			map.put("size", Integer.toString(size));
			int from = 0;
			map.put("from", Integer.toString(from));
			Item[] items = api.getEvents(map);

			names = new ArrayList<Item>();

			if (performances) {
				while (items.length != 0) {
					for (Item item : items) {
						Item temp = item;
						Performances[] list = item.getPerformances();
						for (int a = 0; a < list.length; a++) {
							String date = list[a].getStart();
							if (date.compareTo(value) < 0) {
								temp.setStart(date);
								names.add(temp);
							}
						}
					}
//					from += size;
//					map.put("from", Integer.toString(from));
//					items = api.getEvents(map);
					items = new Item[0];
				}
				Collections.sort(names, new PerformanceComparator());
			} else {
				while (items.length != 0) {
					for (Item item : items) {
						names.add(item);
					}
					from += size;
					map.put("from", Integer.toString(from));
					items = api.getEvents(map);
				}
				Collections.sort(names, new ItemComparator());
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