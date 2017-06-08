package com.example.jklim.ykk_git;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jklim on 2017-06-05.
 */

public class Fast_button extends Service {
    private KeyguardManager km = null;
    private KeyguardManager.KeyguardLock keylock = null;
    private int count = 0;
    long start = 0;
    long end = 0;
    long time = 0;
    private static final String Tag = "0";


    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            if(action.equals("android.intent.action.SCREEN_ON")) {
                count ++;
                if(count ==1){
                    start = System.currentTimeMillis();
                    Log.d(Tag,"count 1 실행");
                }
                else if(count == 3){
                    end = System.currentTimeMillis();
                    Log.d(Tag,"count 3 실행");
                    count = 0;

                }
            }

            time = (end-start)/1000;
            if(time > 0 && time <3 ) {
                Toast.makeText(context, "걸린 시간 : "+ time, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, Emergency_Activity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                count = 0;
                end = 0;
                start = 0;
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        km = (KeyguardManager) this.getSystemService(Activity.KEYGUARD_SERVICE);
        if (km != null) {
            keylock = km.newKeyguardLock("test");
            keylock.disableKeyguard();
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "서비스 실행", Toast.LENGTH_LONG).show();
        IntentFilter filter = new IntentFilter();
        filter.addAction(intent.ACTION_SCREEN_OFF);
        filter.addAction(intent.ACTION_SCREEN_ON);
        registerReceiver(mReceiver, filter);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (keylock != null) {
            keylock.reenableKeyguard();
        }
        if (mReceiver != null)
            unregisterReceiver(mReceiver);
        Toast.makeText(this, "서비스종료", Toast.LENGTH_LONG).show();
    }
}
