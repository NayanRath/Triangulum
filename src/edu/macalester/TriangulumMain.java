package edu.macalester;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import edu.macalester.modules.triangulumModule;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class TriangulumMain extends Activity {

    public TextView view;
    public HashMap<String, String> allMods;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAllMods();
        setContentView(R.layout.main);
        view = (TextView) findViewById(R.id.meh);
        //onTxtStart(new String[0], "8675309");
        //To run individual module:
        String[] tst = {"menu"};
        onTxtStart(tst, "18675309");
    }

    public List<String> getAllModuleNames(){
        List<String> mods=new LinkedList<String>();
        for(String val : allMods.keySet()){
            mods.add(val);
        }
        return mods;
    }

    public List<String[]> runModules(HashMap<String,String> mods){
        String txt;
        List<String[]> out= new LinkedList<String[]>();
        for(String modname : mods.keySet()){
            try{
                triangulumModule mod = (triangulumModule) Class.forName(allMods.get(mods.get(modname))).newInstance();
                mod.setContext(this);
                txt = mod.getTxt();
            } catch (Exception e){
                txt = e.toString();
            }
            out.add(new String[]{modname,txt});
        }
        return out;
    }

    public void sendTxt(String dest, List<String[]> txtData){
        textSender tSend = new textSender();
        tSend.sendTxt(txtData,dest);
    }


    public void onTxtStart(String[] txt, String frm){
        HashMap<String, String> modnames=new HashMap<String,String>();
        for (String s : txt){
            if (allMods.keySet().contains(s)){
                modnames.put(s,allMods.get(s));
            }
        }
        if (modnames.isEmpty()){
            modnames.put("menu",allMods.get("menu"));
        }

        List<String[]> txts = runModules(modnames);
        String t= txts.get(0)[1];

        view.setText(t);
    }

    public void setAllMods(){
        allMods = new HashMap<String, String>();
        allMods.put("location","edu.macalester.modules.locationFetcher.locationFetcher");
        allMods.put("menu","edu.macalester.modules.menu.menu");
        //allMods.put("alert","edu.macalester.modules.alert.alert");
    }

    public static HashMap<String, String> getAllMods(){
        HashMap<String, String> modMap = new HashMap<String, String>();
        modMap.put("location","edu.macalester.modules.locationFetcher.locationFetcher");
        modMap.put("menu","edu.macalester.modules.menu.menu");
        return modMap;
    }
}
