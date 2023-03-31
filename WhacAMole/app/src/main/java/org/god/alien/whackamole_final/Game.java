package org.god.alien.whackamole_final;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.god.alien.whackamole_final.component.GameFragment;

/*
* Usage
* Intent-extras
* "moleImagePath"--the path to the selected image
* "mode"--the selected mode
* */

public class Game extends Activity{

    private GameFragment gameFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Bundle extra = getIntent().getExtras();

        //generally, pop duration should be shorter than kill duration
        if(extra!=null){
            Log.i("====", "ActGame ");
            String moleImagePath = extra.getString("moleImagePath");
            Log.i("====", "ActGame Image " + moleImagePath);
            String mode = extra.getString("mode");
            Log.i("====", "ActGame Mode " + mode);
            Log.i("=====", moleImagePath);
            gameFragment = GameFragment.newInstance(this, mode, moleImagePath);
        }
        else{
            gameFragment = GameFragment.newInstance(this, "hard", null);
        }
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.gameFragment, gameFragment);
        fragmentTransaction.commit();

    }

    public void game_over(boolean isWin){
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), FinalActivity.class);
        intent.putExtra("isWin", isWin);
        intent.putExtra("score", gameFragment.getScore());
        intent.putExtra("popTimes", gameFragment.getPoptimes());
        startActivity(intent);
        finish();//finish main activity
    }

}
