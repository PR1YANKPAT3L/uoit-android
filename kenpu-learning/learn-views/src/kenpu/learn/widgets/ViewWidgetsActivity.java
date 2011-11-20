package kenpu.learn.widgets;

import kenpu.learn.views.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

public class ViewWidgetsActivity extends Activity implements OnClickListener, OnItemClickListener {
	private Integer[] imageIDs = {
		R.drawable.color1,
		R.drawable.color2,
		R.drawable.color3,
		R.drawable.color4,
		R.drawable.color5,
		R.drawable.color6,
		R.drawable.color7,
		R.drawable.color8
	};
	public static class ImageAdapter extends BaseAdapter {
		private ViewWidgetsActivity activity;
		public ImageAdapter(ViewWidgetsActivity activity) {
			this.activity = activity;
		}
		@Override
		public int getCount() {
			return this.activity.imageIDs.length;
		}

		@Override
		public Object getItem(int i) {
			return i;
		}

		@Override
		public long getItemId(int i) {
			return i;
		}

		@Override
		public View getView(int i, View view, ViewGroup parent) {
			// TODO learn how to do inflate
			ImageView imageView;
			imageView = new ImageView(this.activity);
			imageView.setLayoutParams(new Gallery.LayoutParams(150, 150));
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			imageView.setImageResource(this.activity.imageIDs[i]);
			return imageView;
		}
		
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widgets);
        this.findViewById(R.id.show_time).setOnClickListener(this);
        
        Gallery gallery = (Gallery)this.findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter(this));
        gallery.setOnItemClickListener(this);
    }

	@Override
	public void onClick(View v) {
		TimePicker picker = (TimePicker)this.findViewById(R.id.timepicker);
		DatePicker dpicker = (DatePicker)this.findViewById(R.id.datepicker);
		String msg = dpicker.getYear() + "/" 
		           + dpicker.getMonth() + "/" 
		           + dpicker.getDayOfMonth() + " " 
		           + picker.getCurrentHour() + ":" + picker.getCurrentMinute();
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onItemClick(AdapterView parent, View view, int i, long id) {
		ImageView v = (ImageView)this.findViewById(R.id.imageview);
		v.setImageResource(this.imageIDs[i]);
	}
}
