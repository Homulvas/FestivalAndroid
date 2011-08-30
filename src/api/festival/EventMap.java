package api.festival;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class EventMap extends MapActivity{
	private LocationManager lMan;
	private LocationListener locationListener;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.map);
	    MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    
	    float longitude = (Float) getIntent().getSerializableExtra("longitude") * 1000000;
	    float latitude = (Float) getIntent().getSerializableExtra("latitude") * 1000000;
	    
	    GeoPoint point = new GeoPoint((int)latitude, (int)longitude);
	    
	    List<Overlay> mapOverlays = mapView.getOverlays();
	    Drawable drawable = this.getResources().getDrawable(R.drawable.maps);
	    MapOverlay itemizedoverlay = new MapOverlay(drawable, this);
	    
	    lMan = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
	    
	    locationListener = new LocationListener() {
	        public void onLocationChanged(Location location) {
	        	Toast.makeText(EventMap.this, "Trolololo", 100).show();
	        	GeoPoint loc = new GeoPoint((int) location.getLatitude() * 1000000, (int)location.getLongitude() * 1000000);
	        	OverlayItem pin = new OverlayItem(loc, null, null);
	        	itemizedoverlay.addOverlay(pin);
	        }

	        public void onStatusChanged(String provider, int status, Bundle extras) {}

	        public void onProviderEnabled(String provider) {}

	        public void onProviderDisabled(String provider) {}
	      };
	      
	    lMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 0, locationListener);
	    
	    OverlayItem overlayitem = new OverlayItem(point, (String) getIntent().getSerializableExtra("title"), null);
	    
	    itemizedoverlay.addOverlay(overlayitem);
	    mapOverlays.add(itemizedoverlay);
	}
	
	public void onPause() {
		super.onPause();
		lMan.removeUpdates(locationListener);
	}
	
	protected boolean isRouteDisplayed() {
		return false;
	}
}
