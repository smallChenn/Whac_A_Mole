package org.god.alien.whackamole_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Timer;
import java.util.TimerTask;

public class Welcome extends Activity {
    private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_welcom);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcom);
        //对背景图片实现时钟控制，使其自动跳转到下一界面
        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {

                Intent in = new Intent(Welcome.this, start.class);
                startActivity(in);
                //实现两个页面之间的淡入淡出效果
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        };
        //设置背景图片的停留时间
        timer.schedule(tt, 4000);
    }

}


