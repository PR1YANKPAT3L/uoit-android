package kenpu.learn.canvas;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class LearnCanvas extends Activity {
	public static String TAG = "updateUI";
	
	public MyState state;
	public Handler handler;
	public MyCanvas canvas;
	
	final Handler.Callback updateUI = new Handler.Callback() {

		
		public boolean handleMessage(Message msg) {
			/* Get the theta and dx and dy */
			Bundle bundle = msg.getData();
			int dx = bundle.getInt("dx", 0);
			int dy = bundle.getInt("dy", 0);
			double theta = bundle.getDouble("theta");
			Log.d(TAG, "theta="+theta);
			state.rotate(theta);
			state.translate(dx, dy);
			canvas.invalidate();
			return false;
		}
	}; 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        canvas = (MyCanvas)this.findViewById(R.id.mycanvas);
        this.state = new MyState();
        this.handler = new Handler(updateUI);
        canvas.state = state;
        Controller controller = new Controller(this.handler);
        controller.start();
    }
}