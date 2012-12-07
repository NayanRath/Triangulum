package edu.macalester;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created with IntelliJ IDEA.
 * User: aaron
 * Date: 11/27/12
 * Time: 2:03 PM
 * To change this template use File | Settings | File Templates.
 */

//recieves BOOT_COMPLETED broadcast and starts app at bootup
public class bootLauncher extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Intent mServiceIntent = new Intent();
        mServiceIntent.setAction("edu.macalester.TriangulumMain");
        context.startService(mServiceIntent);
    }
}
