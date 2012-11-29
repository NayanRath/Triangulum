package edu.macalester.modules.lock;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import edu.macalester.modules.triangulumModule;
import edu.macalester.triangulumAdminReceiver;

import java.util.Date;
import java.util.Random;

public class lock extends triangulumModule {
    private DevicePolicyManager manager;
    ComponentName devAdm;
    String newPass;
	public String getTxt(){
        //PowerManager manager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		//manager.goToSleep(0);
		//String txt = "success, but you can't see this cause the phone is locked";

        //DevicePolicyManager manager = new DevicePolicyManager();
        manager=(DevicePolicyManager)context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        devAdm = new ComponentName(context, triangulumAdminReceiver.class);
        //DeviceAdminReceiver adRec = new DeviceAdminReceiver();
        //manager.resetPassword(,0);

        Character[] validCharacters = {'q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z',
                'x','c','v','b','n','m','Q','W','E','R','T','Y','U','I','O','P','A','S','D','F','G','H','J','K','L','Z',
                'X','C','V','B','N','M','1','2','3','4','5','6','7','8','9','0'};
        String newPass="";
        Random rand = new Random((new Date()).getDate());
        for (int i=0;i<6;i++){
            newPass+=validCharacters[rand.nextInt(validCharacters.length)];
        }

        ///Note: uncomment the following line for hardcoded newPass
        newPass="asdf";
        String txt;
        if (manager.isAdminActive(devAdm)){
            manager.resetPassword(newPass, 0);
            manager.lockNow();

/*        KeyguardManager mgr = (KeyguardManager)context.getSystemService(context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock lock = mgr.newKeyguardLock(Context.KEYGUARD_SERVICE);
        lock.disableKeyguard();
        lock.reenableKeyguard();*/

        txt = "Device Locked: "+newPass;
        } else {
            txt = "no admin rights:(";
        }

        return txt;

	}
}
