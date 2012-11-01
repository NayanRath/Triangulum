package edu.macalester;

import android.telephony.SmsManager;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: aaron
 * Date: 10/30/12
 * Time: 1:42 PM
 * To change this template use File | Settings | File Templates.
 */
/*
 * this is a comment to test if Kyle can commit
*/
public class textSender {


    public String mkTxt(List<String[]> modTxt){
        String msg = "";
        for (String[] i : modTxt){
            msg=msg+i;
        }
        return msg;
    }

    public void sendTxt(List<String[]> modTxt, String destNumber){
        String msg = mkTxt(modTxt);
        SmsManager smsMan = SmsManager.getDefault();
        smsMan.sendTextMessage(destNumber,null,msg,null,null);

    }
}
