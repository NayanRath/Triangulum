package edu.macalester;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import edu.macalester.modules.triangulumModule;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class TriangulumMain extends Service {
    public static SharedPreferences settings;
	private SharedPreferences sharedPreferences;

   // public TextView view;
    public HashMap<String, String> allMods;
    IntentFilter intentFilter;
    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            onTxtStart((String[]) intent.getExtras().get("txtWords"),(String) intent.getExtras().get("from"));
        }
    };

    @Override
    public IBinder onBind(final Intent intent) {
        return null;
    }
    
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        intentFilter=new IntentFilter();
        intentFilter.addAction("SMS_RECEIVED_ACTION");
        registerReceiver(intentReceiver,intentFilter);
        setAllMods();
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
        Log.d("hello", "world");
    	setAllMods();
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

//        view.setText(t);
        if (!frm.isEmpty()){
            textSender ts = new textSender();
            ts.sendTxt(t,frm);
            ts = null;
        }
    }

    public void setAllMods(){
    	Toast.makeText(this, "It was seen.", Toast.LENGTH_SHORT).show();
    	settings = getSharedPreferences("SharedPrefs", 0);
    	List<String> enablers = new LinkedList<String>();
    	
    	boolean bLocation = settings.getBoolean("PrefBool1", true);
        boolean bAlert = settings.getBoolean("PrefBool2", true);
        boolean bLock = settings.getBoolean("PrefBool3", true);
        
        if (bLocation) enablers.add("location");
        if (bAlert) enablers.add("alert");
        if (bLock) enablers.add("lock");
    	allMods=getAllMods(enablers);

    }

    public static HashMap<String, String> getAllMods(List<String> enablers){
        //TODO: fetch from database?
        HashMap<String, String> modMap = new HashMap<String, String>();
        if (enablers.contains("location")) {
        	modMap.put("location","edu.macalester.modules.locationFetcher.locationFetcher");
        }
        if (enablers.contains("lock")) {
            modMap.put("lock","edu.macalester.modules.lock.lock");       
        }
        if (enablers.contains("lock")) {
            modMap.put("alert","edu.macalester.modules.alert.alert");       
        }
        modMap.put("menu","edu.macalester.modules.find.find");        
        modMap.put("menu","edu.macalester.modules.menu.menu");
        return modMap;
    }
}
