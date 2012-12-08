package edu.macalester;

import android.app.Service;
import android.content.*;
import android.os.IBinder;
import edu.macalester.modules.triangulumModule;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//main class, takes care of the heavy lifting
public class TriangulumMain extends Service {
    public static SharedPreferences settings;
    public HashMap<String, String> allMods;  // map <modname: path>
    IntentFilter intentFilter;

    //receives intent from textParser
    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            onTxtStart((String[]) intent.getExtras().get("txtWords"),(String) intent.getExtras().get("from"));
        }
    };

    //prevents binding
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
        settings=getSharedPreferences("SharedPrefs",MODE_PRIVATE);

        //makes sure preferences exist (even if empty)
        SharedPreferences.Editor editor = settings.edit();
        editor.commit();
    }

    //returns a list of all module names
    public List<String> getAllModuleNames(){
        List<String> mods=new LinkedList<String>();
        for(String val : allMods.keySet()){
            mods.add(val);
        }
        return mods;
    }

    //given a list of module names,
    //instantiates modules and runs <Module>.getTxt
    //returns list of module response strings
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

    //sends a txt given a destination number and a list of strings
    public void sendTxt(String dest, List<String[]> txtData){
        textSender tSend = new textSender();
        tSend.sendTxt(txtData,dest);
    }


    //called by broadcast reciever on receipt of text data from textParser
    public void onTxtStart(String[] txt, String frm){
    	setAllMods();
        List<String> modnames=new LinkedList<String>();
        for (String s : txt){
            if (allMods.keySet().contains(s)){
                modnames.add(s);
            }
        }
        //if no argument in txt, reply w/ menu
        if (modnames.isEmpty()){
            modnames.add("menu");
        }

        List<String[]> txts = runModules(modnames);
        String t= txts.get(0)[1];

//        view.setText(t);

        //sends txt via txtSender
        if (!frm.isEmpty()){
            textSender ts = new textSender();
            ts.sendTxt(t,frm);
            ts = null;
        }
    }

    //updates allMods
    public void setAllMods(){
    	//Toast.makeText(this, "It was seen.", Toast.LENGTH_SHORT).show();
    	settings = getSharedPreferences("SharedPrefs", 0);
    	List<String> enablers = new LinkedList<String>();

    	boolean bLocation = settings.getBoolean(Prefs.PREFLOCATION, true);
        boolean bFind = settings.getBoolean(Prefs.PREFFIND, true);
    	boolean bAlert = settings.getBoolean(Prefs.PREFALERT, true);
        boolean bLock = settings.getBoolean(Prefs.PREFLOCK, true);

        if (bLocation) enablers.add("location");
        if (bFind) enablers.add("find");
        if (bAlert) enablers.add("alert");
        if (bLock) enablers.add("lock");
    	allMods=getAllMods(enablers);

    }

    //given a list of boolean toggles, update allMods
    public static HashMap<String, String> getAllMods(List<String> enablers){
        HashMap<String, String> modMap = new HashMap<String, String>();
        if (enablers.contains("location")) {
        	modMap.put("location","edu.macalester.modules.locationFetcher.locationFetcher");
        }
        if (enablers.contains("find")) {
            modMap.put("find","edu.macalester.modules.find.find");       
        }
        if (enablers.contains("lock")) {
            modMap.put("lock","edu.macalester.modules.lock.lock");       
        }
        if (enablers.contains("alert")) {
            modMap.put("alert","edu.macalester.modules.alert.alert");       
        }
        modMap.put("menu","edu.macalester.modules.menu.menu");
        return modMap;
    }
}
