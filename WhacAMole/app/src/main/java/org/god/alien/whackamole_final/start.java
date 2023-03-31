package org.god.alien.whackamole_final;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class start extends Activity{

    int playnum=0;
    int Clicknum=0;

    private MediaPlayer mediaPlayer;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        currentPosition = 0;

        Button btn=(Button) findViewById(R.id.button);
        Button btn2=(Button) findViewById(R.id.button2);
        Button btn3=(Button) findViewById(R.id.button3);
        Button btn4=(Button) findViewById(R.id.button4);

        MyListener l=new MyListener();
        btn.setOnClickListener(l);
        btn2.setOnClickListener(l);
        btn3.setOnClickListener(l);
        btn4.setOnClickListener(l);
        btn4.setBackgroundResource(R.drawable.stop);
            if(playnum==0){play();}
            else{}

    }
    public class MyListener implements View.OnClickListener{
        //所有组件继承view

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public  void onClick(View view){
            switch (view.getId()){
                case R.id.button:{
                    playnum++;
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), Game.class);
                    startActivity(intent);
                    finish();
                    break;
                }
                case R.id.button2:{
                    playnum++;
                    Intent i=new Intent();
                    i.setClass(start.this,Zidingyi.class);
                    startActivity(i);
                    finish();
                    break;
                }
                case R.id.button3:{
                    playnum++;

                        Intent i=new Intent();
                        i.setClass(start.this,Contactus.class);
                        startActivity(i);
                    break;
                }
                case R.id.button4:{
                Clicknum++;
                    if(Clicknum%2==1){
                        ((Button)findViewById(R.id.button4)).setBackgroundResource(R.drawable.play);
                        Log.i("====", "Pausing mediaPlayer");
                        mediaPlayer.pause();
                        currentPosition = mediaPlayer.getCurrentPosition();
                    }
                    else{
                        ((Button)findViewById(R.id.button4)).setBackgroundResource(R.drawable.stop);
                        mediaPlayer.seekTo(currentPosition);
                        mediaPlayer.start();
                    }

                }

                }
            }
        }

    @Override
    public void onSaveInstanceState(Bundle state){
        super.onSaveInstanceState(state);
        state.putInt("current_position", currentPosition);
    }

    @Override
    public void onRestoreInstanceState(Bundle state){
        super.onRestoreInstanceState(state);
        currentPosition = state.getInt("current_position");
    }

    @Override
    public void onPause(){
        super.onPause();
        mediaPlayer.pause();
        currentPosition = mediaPlayer.getCurrentPosition();
    }

    @Override
    public void onResume(){
        super.onResume();
        mediaPlayer.seekTo(currentPosition);
        mediaPlayer.start();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mediaPlayer.stop();
    }

    private void play(){
        try{

            mediaPlayer= MediaPlayer.create(this,R.raw.a);
            mediaPlayer.start();//播放音乐

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
