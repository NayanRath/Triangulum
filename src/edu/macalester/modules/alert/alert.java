package edu.macalester.modules.alert;

import java.io.IOException;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import edu.macalester.modules.triangulumModule;


public class alert extends triangulumModule 
{


	public String getTxt(){
		String loctxt;

		loctxt="alert alert";
		try {
			return playSound(context);
		}
		catch (IllegalArgumentException e){
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loctxt;

	}


	public static String playSound(Context context) throws IllegalArgumentException, SecurityException, IllegalStateException,
	IOException 
	{
		Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		MediaPlayer mMediaPlayer = new MediaPlayer();
		//MediaPlayer.create(context, soundUri);
		mMediaPlayer.setDataSource(context, soundUri);
		//final AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

		mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
		mMediaPlayer.setLooping(true);
		mMediaPlayer.prepare();
		mMediaPlayer.start();
		return "iran";


	}



}