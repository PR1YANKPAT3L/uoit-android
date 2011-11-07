package ca.uoit.kenpu;

import java.util.List;

import android.graphics.PointF;

public class World {
	public List<Particle> particles;
	public PointF size;
	
	public World() {
		int n = 10;
		size = new PointF(600, 400);
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
				Spring s1 = new Spring(k, l);
				Spring s2 = new Spring(k, l);
				s1.connect(p1, p2);
				s2.connect(p1, p3);
			}
		(new Spring(k,l)).connect(grid[n-1][n-1], grid[n-1][n-2]);
		(new Spring(k,l)).connect(grid[n-1][n-1], grid[n-2][n-2]);
	}
	
}
