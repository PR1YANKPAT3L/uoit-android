package ken.pu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	private static String TAG = "Main";
	private Button mButton_SimplePreview;
	private Button mButton_FilteredPreview;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main);
        
        this.mButton_FilteredPreview = (Button)this.findViewById(R.id.goto_filtered_preview);
        this.mButton_SimplePreview = (Button)this.findViewById(R.id.goto_simple_preview);
        this.mButton_FilteredPreview.setOnClickListener(this);
        this.mButton_SimplePreview.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		if(v == this.mButton_SimplePreview) {
			startActivity(new Intent(this, SimplePreviewActivity.class));
		} else if(v == this.mButton_FilteredPreview) {
			startActivity(new Intent(this, FilteredViewActivity.class));
		}
	}
}