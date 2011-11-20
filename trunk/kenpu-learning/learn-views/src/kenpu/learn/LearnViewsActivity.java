package kenpu.learn;

import kenpu.learn.views.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class LearnViewsActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.findViewById(R.id.launch_widget).setOnClickListener(this);
        this.findViewById(R.id.quit).setOnClickListener(this);
    }

	@Override
	public void onClick(View button) {
		switch(button.getId()) {
		case R.id.launch_widget:
			Toast.makeText(this, "Widgets", Toast.LENGTH_SHORT).show();
			Intent i = new Intent(this, kenpu.learn.widgets.ViewWidgetsActivity.class);
			this.startActivity(i);
			break;
		case R.id.quit:
			this.finish();
		}
	}
}