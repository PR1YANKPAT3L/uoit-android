package ken.pu.learn1;

import ken.pu.learn2.LotsOfViews;
import ken.pu.learn3.CameraActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	private Button startLinearLayout;
	private Button someGraphics;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.startLinearLayout = (Button)this.findViewById(R.id.linear_layout_button);
        if(this.startLinearLayout != null)
        	this.startLinearLayout.setOnClickListener(this);
        someGraphics = (Button)this.findViewById(R.id.some_graphics);
        if(this.someGraphics != null)
        	someGraphics.setOnClickListener(this);
        if(this.findViewById(R.id.lots_views) != null)
        	((Button)this.findViewById(R.id.lots_views)).setOnClickListener(this);
        if(this.findViewById(R.id.camera) != null)
        	((Button)this.findViewById(R.id.camera)).setOnClickListener(this);
    }
    
	@Override
	public void onClick(View v) {
		if(v == this.startLinearLayout) {
			Intent i = new Intent(this, LinearLayoutActivity.class);
			Log.d("Main", "made an intent.");
			startActivity(i);
		} else if(v == this.someGraphics) {
			startActivity(new Intent(this, SomeGraphics.class));
		} else if(v.getId() == R.id.lots_views) {
			startActivity(new Intent(this, LotsOfViews.class));
		} else if(v.getId() == R.id.camera) {
			startActivity(new Intent(this, CameraActivity.class));
		}
	}
}