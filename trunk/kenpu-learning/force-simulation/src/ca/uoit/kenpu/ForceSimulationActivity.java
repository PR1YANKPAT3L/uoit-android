package ca.uoit.kenpu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ForceSimulationActivity extends Activity {
	public static String TAG = "ForceSimulationActivity";
	public World w;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Starting...");
        setContentView(R.layout.main);
        
        
    }
}