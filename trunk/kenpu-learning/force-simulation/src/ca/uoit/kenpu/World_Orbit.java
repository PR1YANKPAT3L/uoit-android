package ca.uoit.kenpu;

import java.util.List;
import java.util.Vector;

import android.graphics.PointF;

public class World_Orbit extends World {
		
	public List<Particle> P;
	public List<Spring> S;
	
	public World_Orbit() {
		int n = 10;
		P = new Vector<Particle>();
		S = new Vector<Spring>();
		float k = 10;
		float l = size.y/n;
		float m = 1.0f;
		float r = 10;
		
		Particle p1 = new Particle(m, 5*r, new PointF(400, 300), new PointF(0,0));
		Particle p2 = new Particle(m, 3*r, new PointF(200, 100), new PointF(0,-200));
		Particle p3 = new Particle(m/5, r, new PointF(100, 100), new PointF(0,10));
		p1.fixed = true;
		P.add(p1);
		P.add(p2);
		P.add(p3);
		S.add(new Spring(k, l).connect(p1, p2));
		S.add(new Spring(k, l/5).connect(p2, p3));
	}
	
	public List<Particle> particles() {
		return P;
	}
	public List<Spring> springs() {
		return S;
	}
	
}
