package edu.macalester;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.util.Arrays;

/**
 * Class responsible for listening to incoming texts
 * and parses the text if it is a command for the app.
 * 
 */
public class textParser extends BroadcastReceiver {

    //public String activationCode = "qwertyuiop";
    //public TriangulumMain tMain;
    private Context context;


    /**
     * Gets the activation passcode from the user preferences
     * @return activation passcode
     */
    public String getActivationCode(){
        SharedPreferences settings = context.getSharedPreferences(Prefs.PREFS_NAME,Context.MODE_PRIVATE);
        String pass = settings.getString(Prefs.PREFPASS,Prefs.PREFSPASSDEF);
        return pass;
        //return "asdf";
    }

    /**
     * Listens for incoming texts, takes the text message and splits
     * it on spaces, and passes it on to testAndSplitString to
     * see if first word is the correct passcode. If it is, testAndSplitString
     * returns an array of module commands after the passcode and onReceive sends 
     * the module array through a broadcast intent which is picked up by
     * TriangulumMain.
     */    
    public void onReceive(Context con, Intent intent) {
        context =con;
        Bundle bun = intent.getExtras();
        SmsMessage[] msgs;
        //String frm;
        String content;
        if (bun != null){
            Object[] pdus = (Object[]) bun.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i=0; i<msgs.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                String[] c = testAndSplitString(msgs[i].getMessageBody());
                if (c!=null){

                    String frm=msgs[i].getOriginatingAddress();
                    //tMain = new TriangulumMain();

                    //tMain.onTxtStart(c, frm);
                    Intent broadcastIntent = new Intent();
                    broadcastIntent.setAction("SMS_RECEIVED_ACTION");
                    broadcastIntent.putExtra("txtWords",c);
                    broadcastIntent.putExtra("from",frm);
                    context.sendBroadcast(broadcastIntent);
                    //tMain.onTxtStart(c);
                    //Toast.makeText(context, frm, Toast.LENGTH_SHORT).show();
                    //tMain=null;
                }
            }
        }
    }
    /**
     * Takes SMS message string and checks to see if first word
     * is the passcode set by the user in preferences. If it matches
     * it returns an array of modules sent by user.
     * @param c
     * @return
     */
    public String[] testAndSplitString(String c){
        String s[] = c.split("\\s+");
        //return s;
        if (s[0].equals(getActivationCode())){
            return Arrays.copyOfRange(s, 1, s.length);
        }
        return null;
    }
}
