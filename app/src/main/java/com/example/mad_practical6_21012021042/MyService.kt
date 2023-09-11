package com.example.mad_practical6_21012021042

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MyService : Service() {
    lateinit var Player : MediaPlayer
    companion object{
        val PLAYERKEY = "Service"
        val PLAYERVALUE = "Play/Pause"
    }
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!this::Player.isInitialized){
            Player = MediaPlayer.create(this , R.raw.song)
        }
        if (intent!= null){
          val DATAVALUE = intent.getStringExtra(PLAYERKEY)
            if (DATAVALUE== PLAYERVALUE){
                if (!Player.isPlaying){
                    Player.start()
                }
                else {
                    Player.pause()
                }
            }
        }
        else{
            Player.stop()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        Player.stop()
        super.onDestroy()
    }
}