package ken.pu.learn2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class LotsOfViews extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/*************************
		 * Let's build the UI
		 *************************/
		
		// Top level container
		LinearLayout topLayout = new LinearLayout(this);
		topLayout.setBackgroundColor(0xff333355);
		topLayout.setOrientation(LinearLayout.VERTICAL);
		topLayout.setGravity(Gravity.CENTER);
		topLayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		
		// Vertical scrollable
		ScrollView scrollView = new ScrollView(this);
		scrollView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		topLayout.addView(scrollView);
		
		// Horizontal scrollable
		HorizontalScrollView hScrollView = new HorizontalScrollView(this);
		hScrollView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		scrollView.addView(hScrollView);
		
		
		/*
		 * Inner layout
		 * This is necessary because scroll view is a frame layout, so it can
		 * only hold one thing.
		 */
		TableLayout innerLayout = new TableLayout(this);
		innerLayout.setOrientation(TableLayout.VERTICAL);
		innerLayout.setGravity(Gravity.CENTER);
		innerLayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		hScrollView.addView(innerLayout);
		int m = 10, n = 10;
		for(int i=0; i < m; i++) {
			TableRow row = new TableRow(this);
			innerLayout.addView(row);
			for(int j=0; j < n; j++) {
				Button b = new Button(this);
				b.setText("(" + i + ", " + j + ")");
				b.setMinHeight(200);
				b.setMinimumWidth(200);
				b.setTextScaleX(2.0f);
				row.addView(b);
			}
		}
		
		this.setContentView(topLayout);
	}
}
