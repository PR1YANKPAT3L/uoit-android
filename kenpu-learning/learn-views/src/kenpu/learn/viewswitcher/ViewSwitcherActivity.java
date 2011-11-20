package kenpu.learn.viewswitcher;

import kenpu.learn.views.R;
import android.app.Activity;
import android.os.Bundle;

public class ViewSwitcherActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewswitcher);
    }
}
