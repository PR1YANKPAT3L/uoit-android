package ca.uoit.kenpu;

import java.util.List;
import java.util.Vector;

import android.graphics.PointF;

public class Particle {
	public static int ID = 0;
	public int id;
	public float m;   // mass
	public float m_inverse; // 1/mass
	public PointF p; // Current position
	public PointF pp; // Previous position
	public PointF pn; // next position
	public List<Spring> conn; // Spring connections
	public boolean fixed = false;
	public boolean hold = false;
	public float r;
	private float dt = ForceSimulationController.dt;

	
	public Particle(float m, float r, PointF p0, PointF v0) {
		this.pp = new PointF(p0.x, p0.y);
		this.p = new PointF(p0.x + v0.x*dt, p0.y + v0.y*dt);
		this.pn = new PointF(0,0);
		this.m = m;
		this.m_inverse = 1/m;
		this.r = r;
		this.conn = new Vector<Spring>();
		this.id = (Particle.ID ++);
	}
	
	public Particle(float m, float r) {
		this(m, r, new PointF(0,0), new PointF(0,0));
	}
	
	public void set(float x, float y) {
		this.p.set(x, y);
	}
	
	/**
	 * Uses Verlet integral
	 * pn = 2*p - pp + a*dt*dt
	 */
	public void next() throws SimulationException {
		pn.set(p);
		if(this.fixed || this.hold)
			return;
		
		Subtract(Multiply(pn, 2), pp);
		PointF accel = new PointF(0, 0);
		for(Spring s: conn)
			Add(accel, s.forceOn(this));
		Multiply(accel, this.m_inverse * dt * dt);
		Add(pn, accel);
	}
	
	/**
	 * Update current position and previous position
	 */
	public void update() {
		pp.set(p);
		p.set(pn);
	}
	
	public static float distance(Particle x1, Particle x2) {
		float dx = x1.p.x - x2.p.x;
		float dy = x1.p.y - x2.p.y;
		return (float)Math.sqrt(dx*dx + dy*dy);
	}
	public static PointF unit(Particle x1, Particle x2) {
		float dx = x2.p.x - x1.p.x;
		float dy = x2.p.y - x1.p.y;
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
