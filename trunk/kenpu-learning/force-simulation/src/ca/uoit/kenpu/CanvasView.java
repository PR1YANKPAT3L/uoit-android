package ca.uoit.kenpu;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;

public class CanvasView extends View {
	
	private World world;
	private Paint bgPaint = new Paint();
	private Paint particlePaint = new Paint();
	private Paint springPaint = new Paint();
	private Map<Integer, ShapeDrawable> particles = new HashMap<Integer, ShapeDrawable>();
	
	public CanvasView(Context ctx, AttributeSet attrs) {
		super(ctx, attrs);
		world = null;
		bgPaint.setColor(0xFFFFFFFF);
		particlePaint.setARGB(200, 255, 0, 0);
		particlePaint.setAntiAlias(true);
		springPaint.setARGB(100, 0, 0, 255);
		springPaint.setStrokeWidth(5);
		springPaint.setAntiAlias(true);
	}
	
	public void setWorld(World w) {
		this.world = w;
		for(Particle x: w.particles()) {
			ShapeDrawable y = new ShapeDrawable(new OvalShape());
			y.getPaint().set(particlePaint);
			particles.put(x.id, moveShapeDrawable(y, x));
		}
	}
	
	private ShapeDrawable moveShapeDrawable(ShapeDrawable y, Particle x) {
		y.setBounds(Math.round(x.p.x - x.r), Math.round(x.p.y - x.r), Math.round(x.p.x+x.r), Math.round(x.p.y+x.r));		
		return y;
	}
	
	/**
	 * Updates the whole view
	 */
	@Override
	protected void onDraw(Canvas c) {
		if(world != null) clear(c).drawWorld(c);
	}
	
	/**
	 * Erases everyone
	 * @param c
	 * @return
	 */
	private CanvasView clear(Canvas c) {
		c.drawColor(0xFFFFFF);
		return this;
	}
	
	/**
	 * Draws the world
	 * @param c
	 * @return
	 */
	private CanvasView drawWorld(Canvas c) {
		for(Particle x : world.particles()) {
			moveShapeDrawable(particles.get(x.id), x).draw(c);
		}
		for(Spring s : world.springs()) {
			c.drawLine(s.x1.p.x, s.x1.p.y, s.x2.p.x, s.x2.p.y, springPaint);
		}
		return this;
	}

}
