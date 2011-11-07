package ca.uoit.kenpu;

import java.util.List;

import android.graphics.PointF;

public class Particle {
	public static final float dt = 0.1f;
	public float m;   // mass
	public PointF p1; // Current position
	public PointF p0; // Previous position
	public List<Spring> conn; // Spring connections
	public boolean fixed = false;
	public float r;
	
	public Particle(float m, float r, PointF p0, PointF v0) {
		this.p0 = new PointF(p0.x, p0.y);
		this.p1 = new PointF(p0.x + v0.x*dt, p0.y + v0.y*dt);
		this.m = m;
		this.r = r;
	}
	
	public void set(PointF p) {
		this.p1.set(p);
	}
	
	
}
