package edu.macalester;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * Created with IntelliJ IDEA.
 * User: aaron
 * Date: 11/1/12
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class textParser extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Bundle bun = intent.getExtras();
        SmsMessage[] msgs;
        if (bun != null){

        }
    }
}
