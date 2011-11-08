package ca.uoit.kenpu;

import java.util.List;

public abstract class World {
	public abstract List<Particle> particles();
	public abstract List<Spring> springs();
	public void step(int iteration) throws SimulationException {
		for(int i=0; i < iteration; i++) {
			// Compute the new positions
			for(Particle x : particles()) x.next();
			// Update the current positions
			for(Particle x : particles()) x.update();
			// Update the spring forces
			for(Spring s : springs()) s.update();
		}
	}
	public Particle find(float x, float y) {
		for(Particle q : particles()) {
			float dx = (q.p.x - x);
			float dy = (q.p.y - y);
			if(dx*dx + dy*dy < q.r*q.r) {
				return q;
			}
		}
		return null;
	}
}
