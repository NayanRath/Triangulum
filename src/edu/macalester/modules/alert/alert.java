package edu.macalester.modules.alert;

import android.media.MediaPlayer;
import edu.macalester.modules.triangulumModule;

import java.io.IOException;

class alert extends triangulumModule{
    public String getTxt(){

        /*SoundPool pool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        int s = pool.play(R.raw.alert, (float)1.0,(float)1.0,);
        pool.play(s,(float)1.0,(float)1.0,1,0,(float)1.0);*/

        MediaPlayer mp = new MediaPlayer();
        try{
            mp.setDataSource("src/edu/macalester/modules/alert/trousers.mp3");
        } catch (IOException e){
            return"fail";
        }
        return "Success";
    }
}