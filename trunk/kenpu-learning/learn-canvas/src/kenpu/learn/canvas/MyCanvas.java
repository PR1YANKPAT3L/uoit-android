package kenpu.learn.canvas;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;


public class MyCanvas extends View {
	public Paint paint;
	public MyState state;
	public ShapeDrawable c;
	public ShapeDrawable[] s;
	
	public MyCanvas(Context ctx, AttributeSet attrs) {
		super(ctx, attrs);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		c = new ShapeDrawable(new OvalShape());
		s = new ShapeDrawable[state.N];
		for(int i=0; i < state.N; i++) {
			s[i] = new ShapeDrawable(new OvalShape());
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		if(state != null) {
			paint.setColor(0xffffffff);
			c.setBounds(state.c.x - state.R, state.c.y - state.R, state.c.x+state.R, state.c.y+state.R);
			c.getPaint().set(paint);
			c.draw(canvas);
			for(int i=0; i < state.N; i++) {
				s[i].setBounds(state.satellites[i].x - state.r, state.satellites[i].y-state.r, state.satellites[i].x+state.r, state.satellites[i].y+state.r);
				s[i].getPaint().set(paint);
				s[i].draw(canvas);
			}
		}
	}
}