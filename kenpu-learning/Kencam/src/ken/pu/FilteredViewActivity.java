package ken.pu;

import ken.pu.filtered_view.FilteredMonitor;
import ken.pu.filtered_view.FilteredView;
import ken.pu.filtered_view.PreviewSurfaceView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class FilteredViewActivity extends Activity implements OnClickListener {
	
	FrameLayout topLayout;
	FrameLayout bottomLayout;
	Button originalButton;
	boolean originalVisible = true;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.filtered_preview);
		
		this.topLayout = (FrameLayout)this.findViewById(R.id.filtered_preview_top);
		this.bottomLayout = (FrameLayout)this.findViewById(R.id.simple_preview_bottom);
		FilteredView.displayWidth = 600;
		FilteredView.displayHeight = 800;
		FilteredView.previewSurfaceView = new PreviewSurfaceView(this);
		FilteredView.filteredMonitor = new FilteredMonitor(this);
		this.topLayout.addView(FilteredView.previewSurfaceView);
		this.topLayout.addView(FilteredView.filteredMonitor);
		
		this.originalButton = (Button)this.findViewById(R.id.original_button);
		this.originalButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v == this.originalButton) {
			this.originalVisible = ! this.originalVisible;

			if(this.originalVisible) {
				this.originalButton.setText("Hide original");
				if(FilteredView.filteredMonitor != null)
					FilteredView.filteredMonitor.alphaMask = 0x88000000;
			} else {
				this.originalButton.setText("Show original");
				if(FilteredView.filteredMonitor != null)
					FilteredView.filteredMonitor.alphaMask = 0xff000000;
			}
		}
	}

}
