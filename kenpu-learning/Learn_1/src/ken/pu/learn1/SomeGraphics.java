package ken.pu.learn1;

import ken.pu.learn1.views.MyCanvas;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class SomeGraphics extends Activity implements OnClickListener, Runnable {
	private LinearLayout rootLayout;
	private Button quitButton;
	private MyCanvas mCanvas;
	private Handler mHandler;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.rootLayout = new LinearLayout(this);
		this.rootLayout.setOrientation(LinearLayout.VERTICAL);
		this.quitButton = new Button(this);
		this.quitButton.setText("Close this activity");
		this.mCanvas = new MyCanvas(this);
		this.rootLayout.addView(this.quitButton);
		this.rootLayout.addView(this.mCanvas);
		this.setContentView(this.rootLayout);
		
		this.quitButton.setOnClickListener(this);
		Log.d("SomeGraphics", "creating the handler");
		mHandler = new Handler();
		mHandler.post(this);
	}
	@Override
	public void onClick(View v) {
		this.finish();
	}
	@Override
	public void run() {
		Log.d("SomeGraphics", "updating canvas");
		this.mCanvas.update();
		this.mHandler.postDelayed(this, 100);
	}	
}
