package edu.macalester.modules;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

/**
 * Created with IntelliJ IDEA.
 * User: aaron
 * Date: 10/15/12
 * Time: 9:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class locationFetcher extends Service implements LocationListener {

    private LocationManager lmgr;

    public void onCreate(){
        //super.onCreate(savedInstanceState);
        lmgr = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    }

    public static String getTxt(){

//TODO: Emulate GPS
/*        lmgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10*1000, 1000, this);
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
        }*/
        String loctxt;
        loctxt="whoop whoop!";
        //String loctxt;
        //loctxt="asdf";

        return loctxt;
    }

    public void clean(){
        lmgr.removeUpdates(this);
    }

    public void onLocationChanged(Location location) {
        clean();
    }
    public void onDestroy(){
        clean();
    }

    public IBinder onBind(Intent i){
        return null;
    }
    public void onStart(){}
    public void onStop(){
        clean();
    }
    public void onProviderEnabled(String Provider){}
    public void onStatusChanged(String provider,int status, Bundle extras){}
    public void onProviderDisabled(String provider){}

//    private final IBinder binder = new LocalBinder();

//    public class LocalBinder extends Binder {
//        public locationFetcher getService() {
//            return locationFetcher.this;
//        }
//    }

//    @Override
//    public IBinder onBind(Intent intent) {
//        return binder;
//    }

//    @Override
//    public boolean onUnbind(Intent intent) {
//        return super.onUnbind(intent);
//    }
}
