package ken.pu.filtered_view;

import ken.pu.misc.Util;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.View;

public class FilteredMonitor extends View implements Camera.PreviewCallback {
	private static String TAG = "FilteredMonitor";
	int width;
	int height;
	int cameraWidth;
	int cameraHeight;
	int pixels[];
	Matrix matrix;
	
	public FilteredMonitor(Context context) {
		super(context);
		width = FilteredView.displayWidth;
		height = FilteredView.displayHeight;
		this.pixels = null;
	}
	
	public void allocatePixels(Size size) {
		this.cameraHeight = size.height;
		this.cameraWidth = size.width;
		this.pixels = new int[size.height * size.width];
		this.matrix = new Matrix();
		this.matrix.setRectToRect(new RectF(0, 0, this.cameraWidth, this.cameraHeight), new RectF(0, 0, this.width, this.height), Matrix.ScaleToFit.CENTER);
		this.matrix.postRotate(90, this.width/2.0f, this.height/2.0f);
		
	}
	
	@Override
	protected void onMeasure(int w, int h) {
		this.setMeasuredDimension(this.width, this.height);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(FilteredView.bitmap, this.matrix, null);
		Log.d(TAG, "onDraw");
//		canvas.drawARGB(0x88, 0x88, 0x88, 0x88);
	}
	
	@Override
	public void onPreviewFrame(byte[] data, Camera camera) {
		Log.d(TAG, "onPreviewFrame");
		int w = FilteredView.bitmap.getWidth();
		int h = FilteredView.bitmap.getHeight();
		Util.decodeYUV420SP(pixels, data, w, h);
		for(int i=0; i < pixels.length; i++) {
			int r = (pixels[i] >> 16) & 0xff;
			int g = (pixels[i] >> 8) & 0xff;
			int b = (pixels[i] & 0xff);
//			int x = (r > g) ? r : ((g > b) ? g : b); // Take the maximum of (R, G, B)
			int x = g;
			pixels[i] = 0xff000000 | (x << 16) | (x << 8) | x;
		}
		FilteredView.bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
		this.invalidate();
	}
	
}
