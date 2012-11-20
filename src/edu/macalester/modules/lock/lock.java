package edu.macalester.modules.lock;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import edu.macalester.modules.triangulumModule;
import edu.macalester.triangulumAdminReceiver;

public class lock extends triangulumModule {
    private DevicePolicyManager manager;
    ComponentName devAdm;
	public String getTxt(){
        //PowerManager manager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		//manager.goToSleep(0);
		//String txt = "success, but you can't see this cause the phone is locked";

        //DevicePolicyManager manager = new DevicePolicyManager();
        manager=(DevicePolicyManager)context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        devAdm = new ComponentName(context, triangulumAdminReceiver.class);
        //DeviceAdminReceiver adRec = new DeviceAdminReceiver();


        manager.lockNow();

/*        KeyguardManager mgr = (KeyguardManager)context.getSystemService(context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock lock = mgr.newKeyguardLock(Context.KEYGUARD_SERVICE);
        lock.disableKeyguard();
        lock.reenableKeyguard();*/

        String txt = "worked?";
		return txt;

	}
}
