package edu.macalester.modules.locationFetcher;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import edu.macalester.modules.triangulumModule;

/**
 * Created with IntelliJ IDEA.
 * User: aaron
 * Date: 10/18/12
 * Time: 1:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class locationFetcher extends triangulumModule implements LocationListener {

    private LocationManager lmgr;

    public Location getLoc(){
        lmgr = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);

        lmgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10*1000, 1000, this );
        lmgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,90*1000, 1000, this);
        Criteria crit=new Criteria();
        String best=lmgr.getBestProvider(crit, true);
        Location loc;
        if (best == null){
            loc = null;
        } else {
            try{
                loc = lmgr.getLastKnownLocation(best);
                Double lat = loc.getLatitude();
                Double lon = loc.getLongitude();
                Double alt = loc.getAltitude();
            } catch (Exception e){
                loc=null;
            }
        }
        return loc;
    };

    public String getTxt(){
        Location loc = getLoc();
        if (loc==null){
            return "Could not locate :(";
        }
        Double lat = loc.getLatitude();
        Double lon = loc.getLongitude();
        Double alt = loc.getAltitude();
        String loctxt=lat.toString()+", "+lon.toString();
        return loctxt;
    }

    public void remUps(){
        lmgr.removeUpdates(this);
    }

    public void onProviderEnabled(String s){}
    public void onProviderDisabled(String s){}
    public void onStatusChanged(String s, int i, Bundle b){}
    public void onLocationChanged(Location l){
        remUps();
    }

}
