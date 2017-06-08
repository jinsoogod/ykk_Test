package com.example.jklim.ykk_git;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by jklim on 2017-06-05.
 */

public class Emergency_Activity extends AppCompatActivity {

    MediaPlayer mp;
    private Button bStart;
    private Button bStop;
    boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency);

        bStop  = (Button)findViewById(R.id.stop_btn);

        mp = MediaPlayer.create(getApplicationContext(), R.raw.siren);
        mp.setLooping(true);
        mp.start();

        bStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 음악 종료
                isPlaying = false;

                mp.stop();
                mp.release();

                finish();

            }
        });

    }

}
