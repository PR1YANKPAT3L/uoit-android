package ken.pu.simple_preview;

import java.io.IOException;
import java.util.List;

import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;

public class CameraPreviewCallback implements SurfaceHolder.Callback {
	public static String TAG = "CameraPreviewCallback";
	public SimplePreviewSurface surface;
	
	public CameraPreviewCallback(SimplePreviewSurface surface) {
		this.surface = surface;
		Log.d(TAG, "created.");
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.d(TAG, "surfaceCreated");
		try {
			if(surface.camera == null) {
				surface.camera = Camera.open();
			}
			if(surface.camera != null) {
				surface.camera.setPreviewDisplay(holder);
			}
		} catch(IOException e) {
			Log.d(TAG, "cannot set the preview display: " + e.getMessage());
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Log.d(TAG, "surfaceChanged");
		if(surface.camera == null)
			Log.d(TAG, "camera is empty!");
		else {
			Camera.Parameters params = surface.camera.getParameters();
			List<Size> sizes = params.getSupportedPreviewSizes();
			Size size = sizes.get(0);
			params.setPreviewSize(size.width, size.height);
			Log.d(TAG, "setPreviewSize(" + size.width + "x" + size.height + ")");
			params.setFocusMode(Camera.Parameters.FOCUS_MODE_INFINITY);
			this.surface.camera.setParameters(params);
			this.surface.camera.setDisplayOrientation(90);
			this.surface.camera.startPreview();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "surfaceDestroyed");
		if(surface.camera != null) {
			surface.camera.stopPreview();
			surface.camera.release();
			surface.camera = null;
		}
	}

}
