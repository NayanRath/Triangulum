package edu.macalester;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Starts the app when phone starts up
 */
public class bootLauncher extends BroadcastReceiver {
	/**
	 * Receives BOOT_COMPLETED broadcast and starts app at bootup
	 */
	public void onReceive(Context context, Intent intent) {
        Intent mServiceIntent = new Intent();
        mServiceIntent.setAction("edu.macalester.TriangulumMain");
        context.startService(mServiceIntent);
    }
}