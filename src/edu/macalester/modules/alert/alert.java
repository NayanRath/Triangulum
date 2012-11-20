package edu.macalester.modules.alert;

import android.media.MediaPlayer;
import edu.macalester.R;
import edu.macalester.modules.triangulumModule;

public class alert extends triangulumModule{
    public String getTxt(){

        //SoundPool pool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        //int sound = pool.load("/src/edu/test.edu.macalester/modules/alert/trousers.mp3",1);
        //pool.play(sound, 1.0f, 1.0f, 0, 0, 1.0f);


        MediaPlayer mp = MediaPlayer.create(context, R.raw.alertsound);
        mp.start();
        /*try{
            //File mFile = new File("src/edu/test.edu.macalester/modules/alert/trousers.mp3");
            //FileInputStream fs = new FileInputStream(mFile);
            //mp.setDataSource(fs.getFD());
            mp.setDataSource(context.getApplicationContext().getFilesDir()+"src/edu/test.edu.macalester/modules/alert/trousers.mp3");
            mp.start();
        } catch (IOException e){
            return"fail";
        }*/
        return "Success";
    }
}