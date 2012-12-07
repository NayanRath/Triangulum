package edu.macalester;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: aaron
 * Date: 11/1/12
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class textParser extends BroadcastReceiver {

    //public String activationCode = "qwertyuiop";
    //public TriangulumMain tMain;
    private Context context;



    public String getActivationCode(){
        SharedPreferences settings = context.getSharedPreferences(Prefs.PREFS_NAME,Context.MODE_PRIVATE);
        String pass = settings.getString(Prefs.PREFPASS,Prefs.PREFSPASSDEF);
        return pass;
        //return "asdf";
    }

    //runs on incoming texts
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

    public String[] testAndSplitString(String c){
        String s[] = c.split("\\s+");
        //return s;
        if (s[0].equals(getActivationCode())){
            return Arrays.copyOfRange(s, 1, s.length);
        }
        return null;
    }
}
