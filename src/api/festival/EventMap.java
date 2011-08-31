package api.festival;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class EventMap extends MapActivity {
	private static final int E06 = 1000000;
	private MyLocationOverlay locationOverlay;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);

		float longitude = (Float) getIntent().getSerializableExtra("longitude")
				* E06;
		float latitude = (Float) getIntent().getSerializableExtra("latitude")
				* E06;

		GeoPoint point = new GeoPoint((int) latitude, (int) longitude);

		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.pointer);
		MapOverlay itemizedOverlay = new MapOverlay(drawable, this);

		locationOverlay = new MyLocationOverlay(this,
				mapView);

		locationOverlay.enableMyLocation();

		OverlayItem overlayitem = new OverlayItem(point, (String) getIntent()
				.getSerializableExtra("title"), null);

		itemizedOverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedOverlay);
		mapOverlays.add(locationOverlay);
	}

	public void onPause() {
		super.onPause();
		locationOverlay.disableMyLocation();
	}
	
	public void onResume() {
		super.onResume();
		locationOverlay.enableMyLocation();
	}

	protected boolean isRouteDisplayed() {
		return false;
	}
}
