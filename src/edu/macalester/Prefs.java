package edu.macalester;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Prefs extends PreferenceActivity {
	
	private static final String OPT_LOCATION = "location";
	private static final boolean OPT_LOCATION_DEF = true;
	private static final String OPT_ALERT = "alert";
	private static final boolean OPT_ALERT_DEF = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}
}