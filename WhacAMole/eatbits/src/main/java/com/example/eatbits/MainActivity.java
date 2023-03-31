package com.example.eatbits;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
        //播放音乐
        player= MediaPlayer
                .create(MainActivity.this,
                        R.raw.bg);
        player.start();
        player.setLooping(true);
    }
    @Override
    protected void onStop() {
        super.onStop();
        player.pause();
    }
    @Override
    protected void onStart() {
        super.onStart();
        player.start();
    }

    //程序退出之前的调用
    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stop();
        player.release();
    }
}
