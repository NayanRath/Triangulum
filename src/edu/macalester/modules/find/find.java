package edu.macalester.modules.find;

import android.location.Location;
import android.util.Log;
import edu.macalester.modules.locationFetcher.locationFetcher;
import edu.macalester.modules.triangulumModule;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: aaron
 * Date: 11/29/12
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class find extends triangulumModule {

    public String getTxt(){
        String out="";
        try{
            locationFetcher locFetch= new locationFetcher();
            locFetch.setContext(context);
            Location loc=locFetch.getLoc();
            Double lat = loc.getLatitude();
            Double lon = loc.getLongitude();
            String whereurl ="http://maps.google.com/maps/api/geocode/json?latlng="+lat.toString()+","+lon.toString()+"&sensor=true";
            //URLConnection gmcon = whereurl.openConnection();
            //BufferedReader in = new BufferedReader(new InputStreamReader(whereurl.openStream()));
           // String line = in.readLine();

            //initialize
            InputStream is = null;
            String result = "";
            JSONObject jArray = null;

            //http post
            try{
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(whereurl);
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();

            }catch(Exception e){
                Log.e("log_tag", "Error in http connection "+e.toString());
            }

            //convert response to string
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                result=sb.toString();
            }catch(Exception e){
                Log.e("log_tag", "Error converting result " + e.toString());
            }
            //try parse the string to a JSON object
            try{
                jArray = new JSONObject(result);
            }catch(JSONException e){
                Log.e("log_tag", "Error parsing data "+e.toString());
            }

            out = jArray.getJSONObject("results").getString("formatted_address");

        } catch (Exception e){
            out="could not locate :(";
        }
        return out;
    }
}
