package ca.uoit.kenpu;

import java.util.List;
import java.util.Vector;

import android.graphics.PointF;

public class World_Grid extends World {
	
	private List<Particle> P;
	private List<Spring> S;
	
	public World_Grid() {
		int n = 20;
		float r = 10.0f;
		P = new Vector<Particle>();
		S = new Vector<Spring>();
		Particle[][] grid = new Particle[n][n];
		float d = Math.min(size.x/n, size.y/n);
		for(int i=0; i < n; i++) {
			for(int j=0; j < n; j++) {
				Particle p = new Particle(1.0f, r, new PointF(i*d, j*d), new PointF(0,0));
				P.add(p);
				if(i==0 || j==0 || i==n-1 || j==n-1)
					p.fixed = true;
				grid[i][j] = p;
			}
		}
		for(int i=0; i < n-1; i++)
			for(int j=0; j < n-1; j++) {
				S.add(new Spring(1.0f, 10.0f).connect(grid[i][j], grid[i+1][j]));
				if(i > 0) S.add(new Spring(5.0f, d).connect(grid[i][j], grid[i][j+1]));
			}
	}

	@Override
	public List<Particle> particles() {
		return P;
	}

	@Override
	public List<Spring> springs() {
		return S;
	}

}
