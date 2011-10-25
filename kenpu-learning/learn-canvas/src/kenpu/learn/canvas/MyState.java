package kenpu.learn.canvas;

import android.graphics.Point;

public class MyState {
	public static final int N = 6;
	public Point c;
	public Point[] satellites;
	public int R = 50;
	public int r = 50;
	public double d = 200;
	public double theta0 = 0;
	
	public MyState() {
		c = new Point(300, 300);
		satellites = new Point[N];
		theta0 = 0;
		this.rotate(0);
	}
	
	public void rotate(double theta0) {
		this.theta0 = theta0;
		for(int i=0; i < N; i++) {
			double theta = (double)i/N * 2 * Math.PI + theta0;
			satellites[i] = new Point(c.x + (int)(d*Math.cos(theta)), c.y+(int)(d*Math.sin(theta)));
		}
	}
	
	public void translate(float dx, float dy) {
		c.x += dx;
		c.y += dy;
		this.rotate(this.theta0);
	}
}
