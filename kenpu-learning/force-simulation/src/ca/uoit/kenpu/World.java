package ca.uoit.kenpu;

import java.util.List;
import java.util.Vector;

import android.graphics.PointF;

public class World {
	public static final float dt = 0.1f;
	public List<Particle> particles;
	public List<Spring> springs;
	public PointF size;
	
	public World() {
		int n = 10;
		size = new PointF(800, 600);
		particles = new Vector<Particle>();
		springs = new Vector<Spring>();
		float k = 1;
		float l = size.y/n;
		float r = 5;
		Particle[][] grid = new Particle[n][n];
		for(int i=0; i < n; i++)
			for(int j=0; j< n; j++) {
				Particle p = new Particle(r, 1.0f, new PointF(size.x/n*i, size.y/n*j), new PointF(0,0));
				particles.add(grid[i][j] = p);
				if(i==0 || j==0)
					p.fixed = true;
			}
		for(int i=0; i < n-1; i++)
			for(int j=0; j < n-1; j++) {
				Particle p1 = grid[i][j];
				Particle p2 = grid[i+1][j];
				Particle p3 = grid[i][j+1];
				Spring s1 = new Spring(k, l).connect(p1, p2);
				Spring s2 = new Spring(k, l).connect(p1, p3);
				springs.add(s1);
				springs.add(s2);
			}
		springs.add(new Spring(k,l).connect(grid[n-1][n-1], grid[n-1][n-2]));
		springs.add(new Spring(k,l).connect(grid[n-1][n-1], grid[n-2][n-2]));
	}
	
	public void step(int iteration) throws SimulationException {
		for(int i=0; i < iteration; i++) {
			// Compute the new positions
			for(Particle x : particles) x.next();
			// Update the current positions
			for(Particle x : particles) x.update();
			// Update the spring forces
			for(Spring s : springs) s.update();
		}
	}
}
