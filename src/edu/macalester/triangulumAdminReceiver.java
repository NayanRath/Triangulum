package edu.macalester;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * This is a stub class to receive admin rights
 * (required for lock module)
 */
public class triangulumAdminReceiver extends DeviceAdminReceiver {  
	/**
	 * Empty method. Required to receive admin rights
	 */
	public void onReceive(Context context, Intent intent) {
    }
}
