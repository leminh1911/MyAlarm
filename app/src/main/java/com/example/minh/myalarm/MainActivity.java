package com.example.minh.myalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spnHour;
    private Spinner spnMinute;
    private Button btnSet;
    private Button btnCancel;
    private TextView tvDisplay;

    private Calendar calendar;
    private AlarmManager alarmManager;
    private String hourText;
    private String minuteText;
    private PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();


        ArrayAdapter<String> hourAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.hour));
        hourAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spnHour.setAdapter(hourAdapter);

        ArrayAdapter<String> minuteAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.minute));
        minuteAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spnMinute.setAdapter(minuteAdapter);

    }


    private void initWidget() {
        spnHour = (Spinner) findViewById(R.id.spn_hour);
        spnMinute = (Spinner) findViewById(R.id.spn_minute);
        btnSet = (Button) findViewById(R.id.btnSet);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        tvDisplay = (TextView) findViewById(R.id.tvDisplay);
        btnSet.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        calendar = Calendar.getInstance();




        spnHour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hourText = spnHour.getItemAtPosition(i).toString();
//                Toast.makeText(MainActivity.this, "you just selected : " + spnHour.getItemAtPosition(i).toString() + " h", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnMinute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                minuteText = spnMinute.getItemAtPosition(i).toString();
//                Toast.makeText(MainActivity.this, "you just selected : " + spnMinute.getItemAtPosition(i).toString() + " m", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSet:
                Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
                tvDisplay.setText(hourText + "h" + minuteText + "m");
                int hour = Integer.parseInt(hourText.toString());
                int minute = Integer.parseInt(minuteText.toString());

                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);

                pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0, intent ,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(alarmManager.RTC_WAKEUP, calendar.getTimeInMillis() ,pendingIntent );

                break;
            case R.id.btnCancel:
                tvDisplay.setText("Cancel Alarm");
                break;
        }
    }
}
