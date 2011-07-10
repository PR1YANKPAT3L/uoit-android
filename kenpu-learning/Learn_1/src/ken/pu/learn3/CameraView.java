package ken.pu.learn3;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;

public class CameraView extends SurfaceView implements SurfaceHolder.Callback {
	
	static public String TAG = "CameraView";
	Camera mCamera;
	List<Size> mSupportedPreviewSizes;
	Size mPreviewSize;
	SurfaceHolder mHolder;
	
	CameraView(Context context) {
		super(context);
		this.setBackgroundColor(0xffff00ff);
		this.mHolder = this.getHolder();
		this.mHolder.addCallback(this);
		this.mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	public void setCamera(Camera cam) {
		this.mCamera = cam;
		if(this.mCamera != null) {
			this.mSupportedPreviewSizes = mCamera.getParameters().getSupportedPreviewSizes();
			for(Size s: this.mSupportedPreviewSizes) {
				Log.d(TAG, "supported preview size:" + s.width + "x" + s.height);
				this.mPreviewSize = s;
			}
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Log.d(TAG, "surfaceChanged(...)");
		try {
			Camera.Parameters camParameter = mCamera.getParameters();
			Size s = this.mSupportedPreviewSizes.get(this.mSupportedPreviewSizes.size()-1);
			camParameter.setPreviewSize(s.width, s.height);
			this.requestLayout();
			mCamera.setParameters(camParameter);
			mCamera.startPreview();
		} catch(Exception e) {
			Log.e(TAG, "Error: " + e);
		}
		Log.d(TAG, "done surfaceChanged(...)");
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			if(this.mCamera != null) {
				this.mCamera.setPreviewDisplay(this.mHolder);
			}
		} catch(IOException e) {
			Log.e(TAG, "IOException: " + e);
		}
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if(mCamera != null)
			mCamera.startPreview();
	}
	
}
