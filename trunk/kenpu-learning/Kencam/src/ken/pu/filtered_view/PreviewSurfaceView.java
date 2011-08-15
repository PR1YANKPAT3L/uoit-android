package ken.pu.filtered_view;

import java.io.IOException;
import java.util.List;

import ken.pu.misc.Util;
import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PreviewSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
	
	public static String TAG = "PreviewSurfaceView";
	public int width;
	public int height;
	
	public PreviewSurfaceView(Context context) {
		super(context);
		this.width = FilteredView.displayWidth;
		this.height = FilteredView.displayHeight;
		SurfaceHolder holder = this.getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	@Override
	protected void onMeasure(int w, int h) {
		this.setMeasuredDimension(this.width, this.height);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		FilteredView.camera = Camera.open();
		Util.configCamera(FilteredView.camera, width, height);
		Size resolution = FilteredView.camera.getParameters().getPreviewSize();
		FilteredView.bitmap = Bitmap.createBitmap(resolution.width, resolution.height, Bitmap.Config.ARGB_8888);
		FilteredView.filteredMonitor.allocatePixels(resolution);
		try {
			FilteredView.camera.setPreviewDisplay(holder);
			FilteredView.camera.setPreviewCallback(FilteredView.filteredMonitor);
			FilteredView.camera.startPreview();
		} catch(IOException e) {
			Log.d(TAG, "surfaceChanged() error: " + e.getMessage());
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.d(TAG, "surfaceCreated()");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if(FilteredView.camera != null) {
			FilteredView.camera.setPreviewCallback(null);
			FilteredView.camera.stopPreview();
			FilteredView.camera.release();
			FilteredView.camera = null;
		}
	}
	
	

}
