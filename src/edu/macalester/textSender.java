package edu.macalester;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: aaron
 * Date: 10/30/12
 * Time: 1:42 PM
 * To change this template use File | Settings | File Templates.
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
        //TODO: send txt message here
    }
}
