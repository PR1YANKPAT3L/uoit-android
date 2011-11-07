package ca.uoit.kenpu;

public class Spring {
	public float k;
	public float l0;
	public float l; // Current length at t
	public Particle x1, x2;
	
	public Spring(float k, float l0) {
		this.k = k;
		this.l0 = l0;
	}
	
	public void connect(Particle x1, Particle x2) {
		this.x1 = x1;
		this.x2 = x2;
		this.x1.conn.add(this);
		this.x2.conn.add(this);
	}
}
