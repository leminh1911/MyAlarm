package com.example.minh.myalarm;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "wake up !!!!!!!!", Toast.LENGTH_SHORT).show();
        Log.e("toi trong Receiver: ", "xin chao");
        Intent myRing = new Intent(context, Music.class);
        context.startService(myRing);
    }
}
