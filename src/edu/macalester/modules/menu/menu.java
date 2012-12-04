package edu.macalester.modules.menu;

import edu.macalester.TriangulumMain;
import edu.macalester.modules.triangulumModule;

/**
 * Created with IntelliJ IDEA.
 * User: aaron
 * Date: 11/4/12
 * Time: 7:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class menu extends triangulumModule {

    public String getTxt(){
        String txt="hello";
        /*
        for (String s : TriangulumMain.getAllMods().keySet()){
            txt =txt + s + " ";
        }
        */
        return txt;
    }
}
