package ca.uoit.kenpu;

import java.util.List;
import java.util.Vector;

import android.graphics.PointF;

public class Particle {
	public float m;   // mass
	public float m_inverse; // 1/mass
	public PointF p; // Current position
	public PointF pp; // Previous position
	public PointF pn; // next position
	public List<Spring> conn; // Spring connections
	public boolean fixed = false;
	public float r;
	
	public Particle(float m, float r, PointF p0, PointF v0) {
		float dt = World.dt;
		this.pp = new PointF(p0.x, p0.y);
		this.p = new PointF(p0.x + v0.x*dt, p0.y + v0.y*dt);
		this.pn = new PointF(0,0);
		this.m = m;
		this.m_inverse = 1/m;
		this.r = r;
		this.conn = new Vector<Spring>();
	}
	
	public void set(PointF p) {
		this.p.set(p);
	}
	
	/**
	 * Uses Verlet integral
	 * pn = 2*p - pp + a*dt*dt
	 */
	public void next() throws SimulationException {
		pn.set(p);
		if(this.fixed)
			return;
		
		Subtract(Multiply(pn, 2), pp);
		PointF accel = new PointF(0, 0);
		for(Spring s: conn)
			Add(accel, s.forceOn(this));
		Multiply(accel, this.m_inverse);
	}
	
	/**
	 * Update current position and previous position
	 */
	public void update() {
		p.set(pn);
		pp.set(p);
	}
	
	public static float distance(Particle x1, Particle x2) {
		float dx = x1.p.x - x2.p.x;
		float dy = x1.p.y - x2.p.y;
		return (float)Math.sqrt(dx*dx + dy*dy);
	}
	public static PointF unit(Particle x1, Particle x2) {
		float dx = x1.p.x - x2.p.x;
		float dy = x1.p.y - x2.p.y;
		float d = (float)Math.sqrt(dx*dx + dy*dy);
		if(d < 0.5) d = 0.5f; // For the sake of numerical stability
		return new PointF(dx/d, dy/d);
	}
	
	// In-place editing
	public static PointF Multiply(PointF x, float v) {
		x.set(x.x*v, x.y*v);
		return x;
	}
	public static PointF Subtract(PointF x, PointF y) {
		x.set(x.x - y.x, x.y - y.y);
		return x;
	}
	public static PointF Add(PointF x, PointF y) {
		x.set(x.x + y.x, x.y + y.y);
		return x;
	}
	
}
