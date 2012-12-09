package edu.macalester.modules.alert;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import edu.macalester.R;
import edu.macalester.modules.triangulumModule;
/**
 * Extends the module class to create an alert class.
 * This module will play a sound on the phone
 */
public class alert extends triangulumModule{
	/**
	 * Turns up the volume on the phone to 100%, then plays
	 * and mp3 file. Returns a text message "Success" to let the user know
	 * that the sound is being played on their lost phone
	 */
	public String getTxt(){
        MediaPlayer mp = MediaPlayer.create(context, R.raw.alertsound);
        mp.start();
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),AudioManager.FLAG_SHOW_UI);
        audioManager.setStreamVolume(AudioManager.STREAM_RING,audioManager.getStreamMaxVolume(AudioManager.STREAM_RING),AudioManager.FLAG_SHOW_UI);
        return "Success";
    }
}