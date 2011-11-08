package ca.uoit.kenpu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class ForceSimulationActivity extends Activity implements OnClickListener, OnTouchListener {
	private static String TAG = "Activity";
	public ForceSimulationController controller;
	public Button quit, orbit, vine, grid;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Starting...");
        setContentView(R.layout.main);
        CanvasView v = (CanvasView)this.findViewById(R.id.canvas);
        quit = (Button)this.findViewById(R.id.quit);
        orbit = (Button)this.findViewById(R.id.orbit);
        vine = (Button)this.findViewById(R.id.vine);
        grid = (Button)this.findViewById(R.id.grid);
        
        quit.setOnClickListener(this);
        orbit.setOnClickListener(this);
        vine.setOnClickListener(this);
        grid.setOnClickListener(this);
        
        v.setOnTouchListener(this);
        
        controller = new ForceSimulationController(v);
    }

    public void onClick(View v) {
    	if(v == quit) {
    		controller.interrupt();
    		this.finish();
    	} else if(v == orbit) {
    		controller.setWorld(new World_Orbit());
    		if(!controller.isAlive())
    			controller.start();
    	}
    }

	public boolean onTouch(View v, MotionEvent e) {
		float x = e.getX();
		float y = e.getY();
		switch(e.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			Log.d(TAG, "DOWN");
			controller.holdParticle(x, y);
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d(TAG, "MOVE");
			controller.moveParticle(x, y);
			break;
		case MotionEvent.ACTION_UP:
			Log.d(TAG, "UP");
			controller.releaseParticle(); 
			break;
		}
		return true;
	}
}