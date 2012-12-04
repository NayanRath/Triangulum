package edu.macalester;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Prefs extends Activity {
   	private static final String OPT_LOCATION = "location";
   	private static final boolean OPT_LOCATION_DEF = true;
   	private static final String OPT_ALERT = "alert";
   	private static final boolean OPT_ALERT_DEF = true;
   	private static final String OPT_LOCK = "lock";
   	private static final boolean OPT_LOCK_DEF = true;
   	
   	public static final String PREFS_NAME= "SharedPrefs";
   	public static final String PREF_STRING= "PrefString";
   	public static final String PREF_BOOL1= "PrefBool1";
   	public static final String PREF_BOOL2= "PrefBool2";
   	public static final String PREF_BOOL3= "PrefBool3";
   	
   	private SharedPreferences mPrefs;
   	private EditText pCode;
   	private CheckBox pLocation;
   	private CheckBox pAlert;
   	private CheckBox pLock;
   	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mPrefs = getSharedPreferences(PREFS_NAME, 0);
		
		pCode = (EditText)findViewById(R.id.passcode);
		pLocation = (CheckBox)findViewById(R.id.location);
		pAlert = (CheckBox)findViewById(R.id.alert);
		pLock = (CheckBox)findViewById(R.id.lock);
		
	}
	
	@Override
	protected void onResume() {
		pCode.setText(mPrefs.getString(PREF_STRING, "qwertyuiop"));
		pLocation.setChecked(mPrefs.getBoolean(PREF_BOOL1,  false));
		pAlert.setChecked(mPrefs.getBoolean(PREF_BOOL2,  false));
		pLock.setChecked(mPrefs.getBoolean(PREF_BOOL3,  false));
		super.onResume();

	}
	
	
	@Override
	protected void onPause() {
		Editor e = mPrefs.edit();
		e.putString(PREF_STRING, pCode.getText().toString());
		e.putBoolean(PREF_BOOL1, pLocation.isChecked());
		e.putBoolean(PREF_BOOL2, pAlert.isChecked());
		e.putBoolean(PREF_BOOL3, pLock.isChecked());
		e.commit();

		Toast.makeText(this, "Settings Saved.", Toast.LENGTH_SHORT).show();
		super.onPause();
	}
	
	//Get the current value of the location option
	public static boolean locationPref(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(OPT_LOCATION, OPT_LOCATION_DEF);
	}
	
	//Get the current value of the alert option
		public static boolean alertPref(Context context) {
			return PreferenceManager.getDefaultSharedPreferences(context)
					.getBoolean(OPT_ALERT, OPT_ALERT_DEF);
		}
		
	//Get the current value of the lock option
	public static boolean lockPref(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(OPT_LOCK, OPT_LOCK_DEF);
	}
	
	
}