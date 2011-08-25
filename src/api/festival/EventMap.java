package api.festival;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class EventMap extends MapActivity{
	
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
	    
	    OverlayItem overlayitem = new OverlayItem(point, (String) getIntent().getSerializableExtra("title"), null);
	    
	    itemizedoverlay.addOverlay(overlayitem);
	    mapOverlays.add(itemizedoverlay);
	}
	
	protected boolean isRouteDisplayed() {
		return false;
	}
}
