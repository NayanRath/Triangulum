package edu.macalester;

import android.telephony.SmsManager;

import java.util.List;

/**
 * Contains methods for constructing a text message to be
 * returned to the user after they send a command.
 * Also responsible for sending the text message.
 */
public class textSender {

	/**
	 * Makes a new text string and adds strings passed to it in a list
	 * 
	 * @return the constructed string for the message
	 */
    public String mkTxt(List<String[]> modTxt){
        String msg = "";
        for (String[] i : modTxt){
            msg=msg+i[0]+": "+i[1]+"; ";
        }
        return msg;
    }
	/**
	 * Sends a return SMS message containing response messages
	 * if there are multiple messages
	 */
    public void sendTxt(List<String[]> modTxt, String destNumber){
        String msg = mkTxt(modTxt);
        SmsManager smsMan = SmsManager.getDefault();
        smsMan.sendTextMessage(destNumber,null,msg,null,null);
    }
    
	/**
	 * Sends a return SMS message containing a response message
	 * if there is only one message.
	 */
    public void sendTxt(String msg, String dest){
        SmsManager smsMan = SmsManager.getDefault();
        smsMan.sendTextMessage(dest,null,msg,null,null);
    }
}
