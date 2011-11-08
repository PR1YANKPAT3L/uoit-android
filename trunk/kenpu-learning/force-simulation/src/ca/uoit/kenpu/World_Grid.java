package ca.uoit.kenpu;

import java.util.List;

public class World_Grid extends World {
	
	private List<Particle> P;
	private List<Spring> S;
	
	public World_Grid() {
		Particle[][] grid = new Particle[10][10];
		for(int i=0; i < 10; i++) {
			for(int j=0; j < 10; j++) {
				Particle p = new Particle(1.0f, 20.0f);
				P.add(p);
			}
		}
		
	}

	@Override
	public List<Particle> particles() {
		return null;
	}

	@Override
	public List<Spring> springs() {
		return null;
	}

}
