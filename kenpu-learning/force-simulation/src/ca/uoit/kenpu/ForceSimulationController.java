package ca.uoit.kenpu;

import android.util.Log;

/**
 * 
 * @author kenpu
 * Model and controller
 */
public class ForceSimulationController extends Thread {
	public  static float dt = 0.1f;

	private static final String TAG = "Controller";
	private static long delay = 20;
	private World world;
	private CanvasView view;
	private Particle heldParticle = null;
	
	public ForceSimulationController(CanvasView canvasView) {
		view = canvasView;
	}
	public ForceSimulationController setWorld(World w) {
		this.world = w;
		this.view.setWorld(w);
		return this;
	}
	public void run() {
		for(int i=0; true; i++) {
			if(this.isInterrupted()) break;
			try {
				this.world.step(1);
				Thread.sleep(delay);
			} catch(InterruptedException e) {
				break;
			}
			this.view.postInvalidate();
		}
	}
	
	/**
	 * Hold on to a particle if possible
	 * @param x
	 * @param y
	 */
	public void holdParticle(float x, float y) {
		releaseParticle();
		this.heldParticle = world.find(x, y);
		if(this.heldParticle != null) {
			this.heldParticle.hold = true;
		}
	}
	
	/**
	 * Drag a particle
	 * @param x
	 * @param y
	 */
	public void moveParticle(float x, float y) {
		if(this.heldParticle != null)
			this.heldParticle.set(x, y);
		else
			holdParticle(x, y);
	}
	
	/**
	 * Release a particle
	 */
	public void releaseParticle() {
		if(this.heldParticle != null) {
			this.heldParticle.hold = false;
			this.heldParticle = null;
		}
	}

}
