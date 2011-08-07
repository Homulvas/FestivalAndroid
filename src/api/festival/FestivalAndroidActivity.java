package api.festival;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class FestivalAndroidActivity extends Activity {
	private Button ok, art, book, international, jazz, mela, tattoo;
	private EditText entry;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ok = (Button) findViewById(R.id.ok);
		entry = (EditText) findViewById(R.id.entry);
		ok.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				if (entry.getText().length() != 0) {
					((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
							.hideSoftInputFromWindow(entry.getWindowToken(), 0);
					
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

}