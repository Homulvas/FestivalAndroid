package api.festival;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

@SuppressWarnings("rawtypes")
public class MapOverlay extends ItemizedOverlay {
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context context;
	
	public MapOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		this.context = context;
	}
	
	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}

	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	public int size() {
		return mOverlays.size();
	}

	protected boolean onTap(int index) {
		  OverlayItem item = mOverlays.get(index);
		  String title = item.getTitle();
		  if (title != null) {
		  AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		  dialog.setTitle(title);
		  dialog.setMessage(item.getSnippet());
		  dialog.show();
		  return true;
		  } else {
			  return false;
		  }
		}
}
