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
        onTxtStart(new String[0], "8675309");
/*        List<String[]> txts=runModules(getAllModuleNames());
        String t= txts.get(0)[1];

        view.setText(t);*/
    }

    public List<String> getAllModuleNames(){
        List<String> mods=new LinkedList<String>();
        for(String val : allMods.keySet()){
            mods.add(val);
        }
        return mods;
    }

    public List<String[]> runModules(List<String> modnames){
        String txt;
        List<String[]> out= new LinkedList<String[]>();
        for(String modname : modnames){
            try{
                triangulumModule mod = (triangulumModule) Class.forName(allMods.get(modname)).newInstance();
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
        List<String> modnames=new LinkedList<String>();
        for (String s : txt){
            if (allMods.keySet().contains(s)){
                modnames.add(s);
            }
        }
        if (modnames.isEmpty()){
            modnames.add("menu");
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
