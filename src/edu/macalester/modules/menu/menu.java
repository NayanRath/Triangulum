package edu.macalester.modules.menu;

import android.content.SharedPreferences;
import edu.macalester.Prefs;
import edu.macalester.modules.triangulumModule;

import java.util.LinkedList;
import java.util.List;

/**
 * Creates a list of available commands that the user can send to the phone.
 */
public class menu extends triangulumModule {
	/**
	 * Looks at enabled modules in preferences and for each module, if it's enabled,
	 * adds it to a string that will be texted back to the user to let them know which
	 * commands are available.
	 */
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
        if (menu.equals("You have these commands enabled: ")){
            menu = "You don't have any commands enabled.";
        }

        return menu;
    }
}