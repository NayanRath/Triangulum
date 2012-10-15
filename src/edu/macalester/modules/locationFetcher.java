package edu.macalester.modules;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created with IntelliJ IDEA.
 * User: aaron
 * Date: 10/15/12
 * Time: 9:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class locationFetcher extends Activity implements LocationListener {

    private LocationManager lmgr;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        lmgr = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    }

    public String getData(){
        lmgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10*1000, 1000, this);
        lmgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,90*1000, 1000, this);
        Criteria crit=new Criteria();
        String best=lmgr.getBestProvider(crit, true);
        String loctxt;
        if (best == null){
            loctxt = "???";
        } else {
            Location loc = lmgr.getLastKnownLocation(best);
            Double lat = loc.getLatitude();
            Double lon = loc.getLongitude();
            Double alt = loc.getAltitude();
            loctxt= lat.toString()+", "+lon.toString()+", "+alt.toString();
        }
        return loctxt;
    }

    public void onLocationChanged(Location location) {
        lmgr.removeUpdates(this);
    }

    public void onProviderEnabled(String Provider){}
    public void onStatusChanged(String provider,int status, Bundle extras){}
    public void onProviderDisabled(String provider){}
}
