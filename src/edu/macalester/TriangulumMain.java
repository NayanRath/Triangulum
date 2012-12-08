package edu.macalester;

import android.app.Service;
import android.content.*;
import android.os.IBinder;
import edu.macalester.modules.triangulumModule;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
/**
 * This is the main class that takes care of most of the heavy lifting.
 * It is a service rather than an activity, allowing it to run in the background
 * and while phone is asleep.
 */
public class TriangulumMain extends Service {
    
	public static SharedPreferences settings;
    public HashMap<String, String> allMods;
    IntentFilter intentFilter;
    
    /**
     * This is the main class that takes care of most of the heavy lifting.
     * It is a service rather than an activity, allowing it to run in the background
     * and while phone is asleep.
     */
    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            onTxtStart((String[]) intent.getExtras().get("txtWords"),(String) intent.getExtras().get("from"));
        }
    };

    @Override
    /**
     * Prevents binding
     */
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
        SharedPreferences.Editor editor = settings.edit();
        editor.commit();
    }

    /**
     * Creates a list of all available modules
     */
    public List<String> getAllModuleNames(){
        List<String> mods=new LinkedList<String>();
        for(String val : allMods.keySet()){
            mods.add(val);
        }
        return mods;
    }

    /**
     * Activates the modules that are available
     */
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

    /**
     * Sends a response text based on the commands of the user
     */
    public void sendTxt(String dest, List<String[]> txtData){
        textSender tSend = new textSender();
        tSend.sendTxt(txtData,dest);
    }

    /**
     * Gets incoming parsed text and the number of 
     * who it's from.
     */
    public void onTxtStart(String[] txt, String frm){
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
    /**
     * Checks preferences to see which modules are enabled. Sends a list of
     * enabled modules to getAllMods.
     */
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
    /**
     * Takes list of enabled mods as parameter from setAllMods
     * and activates the module classes for each one by putting
     * the module name in a HashMap with the module name as a key and
     * the class name as the value.
     */
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
