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
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class EventMap extends MapActivity {
	private static final int E06 = 1000000;
	private LocationManager lMan;
	private LocationListener locationListener;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);

		float longitude = (Float) getIntent().getSerializableExtra("longitude") * E06;
		float latitude = (Float) getIntent().getSerializableExtra("latitude") * E06;

		GeoPoint point = new GeoPoint((int) latitude, (int) longitude);

		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.maps);
		MapOverlay itemizedOverlay = new MapOverlay(drawable, this);

		lMan = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

		Drawable image = getResources().getDrawable(R.drawable.loc);
		final MapOverlay locationOverlay = new MapOverlay(image, this);

		locationListener = new LocationListener() {
			OverlayItem pin = null;

			public void onLocationChanged(Location location) {

				if (pin == null) {
					Location last = lMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
					GeoPoint loc = new GeoPoint((int) (last.getLatitude() * E06), (int) (last.getLongitude() * E06));
					pin = new OverlayItem(loc, null, null);
					locationOverlay.addOverlay(pin);
				} else {
					GeoPoint loc = new GeoPoint(
							(int) (location.getLatitude() * E06),
							(int) (location.getLongitude() * E06));
					pin = new OverlayItem(loc, null, null);
				}
			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}
		};

		lMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 0,
				locationListener);

		OverlayItem overlayitem = new OverlayItem(point, (String) getIntent()
				.getSerializableExtra("title"), null);

		itemizedOverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedOverlay);
		mapOverlays.add(locationOverlay);
	}

	public void onPause() {
		super.onPause();
		lMan.removeUpdates(locationListener);
	}

	protected boolean isRouteDisplayed() {
		return false;
	}
}
