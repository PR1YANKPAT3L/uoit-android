package ca.uoit.kenpu;

import android.util.Log;

/**
 * 
 * @author kenpu
 * Model and controller
 */
public class ForceSimulationController extends Thread {
	private static final String TAG = "Controller";
	private static long delay = 20;
	public World world;
	public CanvasView view;
	public ForceSimulationController(CanvasView canvasView) {
		this.world = new World();
		canvasView.setWorld(world);
		view = canvasView;
	}
	
	public void run() {
		for(int i=0; i < 1000; i++) {
			if(this.isInterrupted()) break;
			try {
				this.world.step(1);
				Thread.sleep(delay);
			} catch(SimulationException e) {
				Log.e(TAG, "Error: " + e);
			} catch(InterruptedException e) {
				break;
			}
			Log.d(TAG, "refreshing canvas");
			this.view.postInvalidate();
		}
	}

}
