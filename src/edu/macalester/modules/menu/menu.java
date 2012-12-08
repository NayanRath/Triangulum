package edu.macalester.modules.menu;

import android.content.SharedPreferences;
import edu.macalester.Prefs;
import edu.macalester.modules.triangulumModule;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: aaron
 * Date: 11/4/12
 * Time: 7:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class menu extends triangulumModule {

    public String getTxt(){
        String menu="You have these commands enabled: ";
        SharedPreferences settings = context.getSharedPreferences("SharedPrefs", 0);
    	List<String> enablers = new LinkedList<String>();

    	boolean bLocation = settings.getBoolean(Prefs.PREFLOCATION, true);
        boolean bFind = settings.getBoolean(Prefs.PREFFIND, true);
    	boolean bAlert = settings.getBoolean(Prefs.PREFALERT, true);
        boolean bLock = settings.getBoolean(Prefs.PREFLOCK, true);

        if (bLocation) menu= menu+"location ";
        if (bFind) menu=menu+"find ";
        if (bAlert) menu=menu+"alert ";
        if (bLock) menu=menu+"lock";

        //menu = menu +"menu.";
        if (menu.equals("You have these commands enabled: ")){
            menu = "You don't have any commands enabled.";
        }

        return menu;
    }
}
