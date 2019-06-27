package com.example.englishgrammertest;

import android.app.Service;
import android.content.*;
import android.media.*;
import android.os.*;

public class BackgroundMusic extends Service {
    MediaPlayer player;
    public IBinder onBind(Intent arg0){
        return null;
    }
    public void onCreate(){
        super.onCreate();
        player=MediaPlayer.create(this,R.raw.music);
        player.setLooping(true);
        player.setVolume(100, 100);
        player.start();
    }
    public void onStart(Intent intent,int startId){
        //super.onStart(intent, startId);
    }
    /*public int onStartCommand(Intent intent,int flags,int startId){
        player.start();
        //return 1;
    }*/
    public boolean onUnbind(Intent arg0){
        return (Boolean) null;
    }
    public void onStop(){

    }
    public void stopMusic(){
        player.stop();
        player.release();
        player=null;

    }

    public void onDestroy(){
        super.onDestroy();
        if(player!=null){
            try{
                player.stop();
                player.release();
            }finally{
                player=null;
            }
        }
    }
}
