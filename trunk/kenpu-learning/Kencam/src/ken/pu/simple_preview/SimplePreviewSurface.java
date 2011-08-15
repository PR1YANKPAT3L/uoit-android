package ken.pu.simple_preview;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SimplePreviewSurface extends SurfaceView {
	
	public static String TAG = "SimplePreviewSurface";
	public int width;
	public int height;
	public Camera camera;
	public SurfaceHolder mHolder;
	
	public SimplePreviewSurface(Context context, int w, int h) {
		super(context);
		this.width = w;
		this.height = h;
		mHolder = this.getHolder();
		mHolder.addCallback(new CameraPreviewCallback(this));
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		Log.d(TAG, "created");
	}
	
	@Override
	protected void onMeasure(int w, int h) {
		this.setMeasuredDimension(this.width, this.height);
	}
}
