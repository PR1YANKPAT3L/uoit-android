package ken.pu;

import ken.pu.simple_preview.SimplePreviewSurface;
import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class SimplePreviewActivity extends Activity {
	
	SimplePreviewSurface mPreviewSurface;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.preview);
		
		this.mPreviewSurface = new SimplePreviewSurface(this, 400, 300);
		((FrameLayout)this.findViewById(R.id.simple_preview_frame)).addView(mPreviewSurface);
	}
}
