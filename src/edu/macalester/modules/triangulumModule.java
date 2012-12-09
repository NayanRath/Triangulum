package edu.macalester.modules;

import android.content.Context;

/**
 * Abstract class that gets extended in each of the modules
 */
public abstract class triangulumModule {

	/**
	 * returns text to be sent back to user
	 */
	abstract public String getTxt();

	/**
	 * housekeeping
	 */
    public Context context;
    public void setContext(Context c){
        context=c;
    }

}
