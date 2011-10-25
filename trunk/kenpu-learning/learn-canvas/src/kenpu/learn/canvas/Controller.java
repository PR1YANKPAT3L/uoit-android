package kenpu.learn.canvas;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class Controller extends Thread {
	public Handler handler;
	public Controller(Handler handler) {
		this.handler = handler;
	}
	
	public void run() {
		Bundle bundle = new Bundle();
		for(int i = 0; i < 100; i++) {
			try {
				bundle.clear();
				bundle.putInt("dx", 0);
				bundle.putInt("dy", 0);
				bundle.putDouble("theta", i * 1.0 * Math.PI / 180);
				Message m = new Message();
				m.setData(bundle);
				handler.sendMessage(m);
				Thread.sleep(100);
			} catch(InterruptedException e) {
				break;
			}
		}
	}
}
