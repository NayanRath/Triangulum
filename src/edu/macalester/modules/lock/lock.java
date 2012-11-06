package edu.macalester.modules.lock;

import android.content.Context;
import android.os.PowerManager;
import edu.macalester.modules.triangulumModule;

public class lock extends triangulumModule {

	public String getTxt(){
        PowerManager manager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		manager.goToSleep(0);
		String txt = "success, but you can't see this cause the phone is locked";
		
		return txt;

	}
}
