package ken.pu.learn1.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.Log;
import android.view.View;

public class MyCanvas extends View {
	ShapeDrawable drawable;
	int i;
	public MyCanvas(Context ctx) {
		super(ctx);
		drawable = new ShapeDrawable(new OvalShape());
		drawable.getPaint().setColor(0xff740000);
		drawable.setBounds(10, 10, 300, 250);
		i = 0;
	}
	protected void onDraw(Canvas canvas) {
		this.drawable.draw(canvas);
	}
	public void update() {
		i += 1;
		int x = i * 5 + 10;
		int y = i * 5 + 10;
		this.drawable.setBounds(x, y, x+200, y+150);
		if(i % 10 == 0) {
			Log.d("MyCanvas.update", "updating...");
		}
		this.invalidate();
	}
}
