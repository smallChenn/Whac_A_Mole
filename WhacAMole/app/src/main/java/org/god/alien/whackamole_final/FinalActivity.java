package org.god.alien.whackamole_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class FinalActivity extends Activity {
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        final Bundle state = getIntent().getExtras();
        boolean isWin = false;
        int popTimes = 40;
        int score = 0;

        Button newGameButton = (Button) findViewById(R.id.new_game_button);
        Button exitToMainMenuButton = (Button)findViewById(R.id.exitToMainMenuButton);
        TextView isWinTw = (TextView) findViewById(R.id.isWinTw);

        if(state!=null) {
            isWin = state.getBoolean("isWin");
            popTimes = state.getInt("popTimes");
            score = state.getInt("score");
        }

        if (isWin) isWinTw.setText("You Have Won!/n" +
                "You Have Scored: " + Integer.toString(score) +
                "/nOut of " + Integer.toString(popTimes));
        else isWinTw.setText("You Have Lost!" +
        "You Have Scored: " + Integer.toString(score) +
                "/nOut of " + Integer.toString(popTimes));

        radioGroup = (RadioGroup)findViewById(R.id.radio_group);
        radioGroup.check(R.id.hell_rb);

        newGameButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RadioButton selectRadio = (RadioButton) findViewById(radioGroup
                                .getCheckedRadioButtonId());
                        final String mode = selectRadio.getText().toString().split("_")[0];
                        Log.i(getClass().getSimpleName(), "Selected "+ mode);

                        runOnUiThread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(getApplicationContext(), Game.class);
                                        intent.putExtra("mode", mode);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                        );
                    }
                }
        );

        exitToMainMenuButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mainMenuIntent = new Intent();
                        mainMenuIntent.setClass(getApplicationContext(), start.class);
                        startActivity(mainMenuIntent);
                        finish();
                    }
                }
        );

    }
}
