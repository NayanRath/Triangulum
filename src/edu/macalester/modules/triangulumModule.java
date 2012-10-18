package edu.macalester.modules;

import android.content.Context;

/**
 * Created with IntelliJ IDEA.
 * User: aaron
 * Date: 10/16/12
 * Time: 3:44 PM
 * To change this template use File | Settings | File Templates.
 */

//generic triangulum module template, extend in modules

public abstract class triangulumModule {

    //Extend these in modules
    abstract public String getTxt();//returns text to be sent back to user

    //houskeeping
    public Context context;
    public void setContext(Context c){
        context=c;
    }

}
