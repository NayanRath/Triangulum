package edu.macalester;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
/**
 * This class takes care of reading, writing, and saving preferences.
 * These preferences enable and disable modules, as well as sets the activation passcode.
 */
public class Prefs extends Activity {
   	/**
   	 * Names of preference keys that will be stored in Shared Preferences
   	 */
   	public static final String PREFS_NAME= "SharedPrefs";
   	public static final String PREFPASS= "PrefPass";
    public static final String PREFSPASSDEF= "qwertyuiop";
   	public static final String PREFLOCATION= "PrefLocation";
   	public static final String PREFFIND= "PrefFind";
   	public static final String PREFALERT= "PrefAlert";
   	public static final String PREFLOCK= "PrefLock";
   	
   	private SharedPreferences mPrefs;
   	private EditText pCode;
   	private CheckBox pLocation;
   	private CheckBox pFind;
   	private CheckBox pAlert;
   	private CheckBox pLock;
   	
	@Override
	/**
	 * Displays the preference layout when app starts up
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		
		pCode = (EditText)findViewById(R.id.passcode);
		pLocation = (CheckBox)findViewById(R.id.location);
		pFind = (CheckBox)findViewById(R.id.find);
		pAlert = (CheckBox)findViewById(R.id.alert);
		pLock = (CheckBox)findViewById(R.id.lock);
		
	}
	
	@Override
	/**
	 * When the app is opened again, sets the preference views to 
	 * what the values are stored as in the sharedPreferences database
	 */
	protected void onResume() {
		pCode.setText(mPrefs.getString(PREFPASS, PREFSPASSDEF));
		pLocation.setChecked(mPrefs.getBoolean(PREFLOCATION,  true));
		pFind.setChecked(mPrefs.getBoolean(PREFFIND,  true));
		pAlert.setChecked(mPrefs.getBoolean(PREFALERT,  true));
		pLock.setChecked(mPrefs.getBoolean(PREFLOCK,  true));
		super.onResume();

	}
	
	
	@Override
	/**
	 * Stores the selected preferences when user exits the app
	 */
	protected void onPause() {
		Editor e = mPrefs.edit();
		e.putString(PREFPASS, pCode.getText().toString());
		e.putBoolean(PREFLOCATION, pLocation.isChecked());
		e.putBoolean(PREFFIND, pFind.isChecked());
		e.putBoolean(PREFALERT, pAlert.isChecked());
		e.putBoolean(PREFLOCK, pLock.isChecked());
		e.commit();

		Toast.makeText(this, "Settings Saved.", Toast.LENGTH_SHORT).show();
		super.onPause();
	}
}