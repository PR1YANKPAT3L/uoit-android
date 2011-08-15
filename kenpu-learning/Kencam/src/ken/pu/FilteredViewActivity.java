package ken.pu;

import ken.pu.filtered_view.FilteredMonitor;
import ken.pu.filtered_view.FilteredView;
import ken.pu.filtered_view.PreviewSurfaceView;
import android.app.Activity;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.widget.FrameLayout;

public class FilteredViewActivity extends Activity {
	
	FrameLayout topLayout;
	FrameLayout bottomLayout;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.filtered_preview);
		
		this.topLayout = (FrameLayout)this.findViewById(R.id.filtered_preview_top);
		this.bottomLayout = (FrameLayout)this.findViewById(R.id.simple_preview_bottom);
		FilteredView.displayWidth = 400;
		FilteredView.displayHeight = 300;
		FilteredView.previewSurfaceView = new PreviewSurfaceView(this);
		FilteredView.filteredMonitor = new FilteredMonitor(this);
		this.topLayout.addView(FilteredView.previewSurfaceView);
		this.bottomLayout.addView(FilteredView.filteredMonitor);
	}

}
