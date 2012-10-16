package edu.macalester;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import edu.macalester.modules.locationFetcher;

public class TriangulumMain extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent lf = new Intent(this, locationFetcher.class);

        startService(lf);
        String loc = locationFetcher.getTxt();
        stopService(lf);
        TextView meh = (TextView) findViewById(R.id.meh);
        meh.setText(loc);
    }
}
