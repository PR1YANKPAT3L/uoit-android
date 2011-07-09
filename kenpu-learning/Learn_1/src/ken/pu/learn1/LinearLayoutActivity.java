package ken.pu.learn1;

import ken.pu.learn1.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class LinearLayoutActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("LinearLayout", "started the activity...");
		this.setContentView(R.layout.linear_layout);
	}
}
