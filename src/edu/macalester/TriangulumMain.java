package edu.macalester;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import edu.macalester.modules.locationFetcher.locationFetcher;
import edu.macalester.modules.triangulumModule;

import java.util.LinkedList;
import java.util.List;


public class TriangulumMain extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView meh = (TextView) findViewById(R.id.meh);

        List<String[]> txts=runModules(getModuleNames());
        String t= txts.get(0)[1];

        meh.setText(t);
    }

    public List<String> getModuleNames(){
        List<String> mods= new LinkedList<String>();
        mods.add("edu.macalester.modules.locationFetcher.locationFetcher");
        return mods;
    }

    public List<String[]> runModules(List<String> modnames){
        String txt;
        List<String[]> out= new LinkedList<String[]>();
        for(String modname : modnames){
            try{
                triangulumModule mod = (locationFetcher) Class.forName(modname).newInstance();
                mod.setContext(this);
                txt = mod.getTxt();
            } catch (Exception e){
                txt = e.toString();
            }
            out.add(new String[]{modname,txt});
        }
        return out;
    }

    public void sendTxt(List<String[]> txtData){
        textSender tSend = new textSender();
    }

}
