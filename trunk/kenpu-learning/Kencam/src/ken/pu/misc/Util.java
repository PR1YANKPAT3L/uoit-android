package ken.pu.misc;

import java.util.List;

import ken.pu.filtered_view.FilteredView;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.Size;

public class Util {
	public static void configCamera(Camera camera, int w, int h) {
		Camera.Parameters params = camera.getParameters();
		List<Size> sizes = params.getSupportedPreviewSizes();
		Size size = sizes.get(0);
		params.setPreviewSize(size.width, size.height);
		params.setFocusMode(params.FOCUS_MODE_AUTO);
		params.setPreviewFormat(ImageFormat.NV21);
		camera.setParameters(params);
		camera.setDisplayOrientation(90);
	}

	// Method from Ketai project! Not mine! See below...
	public static void decodeYUV420SP(int[] rgb, byte[] yuv420sp, int width,
			int height) {
		final int frameSize = width * height;
		for (int j = 0, yp = 0; j < height; j++) {
			int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
			for (int i = 0; i < width; i++, yp++) {
				int y = (0xff & ((int) yuv420sp[yp])) - 16;
				if (y < 0)
					y = 0;
				if ((i & 1) == 0) {
					v = (0xff & yuv420sp[uvp++]) - 128;
					u = (0xff & yuv420sp[uvp++]) - 128;
				}

				int y1192 = 1192 * y;
				int r = (y1192 + 1634 * v);
				int g = (y1192 - 833 * v - 400 * u);
				int b = (y1192 + 2066 * u);

				if (r < 0)
					r = 0;
				else if (r > 262143)
					r = 262143;
				if (g < 0)
					g = 0;
				else if (g > 262143)
					g = 262143;
				if (b < 0)
					b = 0;
				else if (b > 262143)
					b = 262143;

				rgb[yp] = 0xff000000 | ((r << 6) & 0xff0000)
						| ((g >> 2) & 0xff00) | ((b >> 10) & 0xff);
			}
		}
	}
}
