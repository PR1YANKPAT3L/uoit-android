package ca.uoit.kenpu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ForceSimulationActivity extends Activity implements OnClickListener {
	private static String TAG = "Activity";
	public ForceSimulationController controller;
	public Button quit;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Starting...");
        setContentView(R.layout.main);
        CanvasView v = (CanvasView)this.findViewById(R.id.canvas);
        quit = (Button)this.findViewById(R.id.quit);
        quit.setOnClickListener(this);
        controller = new ForceSimulationController(v);        
        controller.start();
    }

    @Override
    public void onClick(View v) {
    	controller.interrupt();
    	this.finish();
    }
}