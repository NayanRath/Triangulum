package edu.macalester;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Prefs extends Activity {
   	
   	public static final String PREFS_NAME= "SharedPrefs";
   	public static final String PREFPASS= "PrefPass";
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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mPrefs = getSharedPreferences(PREFS_NAME, 0);
		
		pCode = (EditText)findViewById(R.id.passcode);
		pLocation = (CheckBox)findViewById(R.id.location);
		pFind = (CheckBox)findViewById(R.id.find);
		pAlert = (CheckBox)findViewById(R.id.alert);
		pLock = (CheckBox)findViewById(R.id.lock);
		
	}
	
	@Override
	protected void onResume() {
		pCode.setText(mPrefs.getString(PREFPASS, "qwertyuiop"));
		pLocation.setChecked(mPrefs.getBoolean(PREFLOCATION,  false));
		pFind.setChecked(mPrefs.getBoolean(PREFFIND,  false));
		pAlert.setChecked(mPrefs.getBoolean(PREFALERT,  false));
		pLock.setChecked(mPrefs.getBoolean(PREFLOCK,  false));
		super.onResume();

	}
	
	
	@Override
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