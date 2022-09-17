package com.example.baymax;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class ReminderActivity extends AppCompatActivity {

    TimePicker timePicker;
    TextView textView;
    Button btnSetAlarm;
    int mhour,mminute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        timePicker=findViewById(R.id.timepicker);
        textView=findViewById(R.id.textView);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mhour=hourOfDay;
                mminute=minute;
                textView.setText(textView.getText().toString()+" "+mhour+":"+mminute);
            }
        });
    }

    public void setTimer(View v){
        AlarmManager alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);

        Date date=new Date();
        Calendar cal_alarm=Calendar.getInstance();
        Calendar cal_now=Calendar.getInstance();

        cal_now.setTime(date);
        cal_alarm.setTime(date);

        cal_alarm.set(Calendar.HOUR_OF_DAY,mhour);
        cal_alarm.set(Calendar.MINUTE,mminute);
        cal_alarm.set(Calendar.SECOND,0);

        if(cal_alarm.before(cal_now)){
            cal_alarm.add(Calendar.DATE,1);
        }
        //  PendingIntent contentIntent = PendingIntent.getActivity(ReminderActivity.this, 24122,
        //        i, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent i =new Intent(ReminderActivity.this,MyBroadcastReciever.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(ReminderActivity.this,24444,i,0);
        alarmManager.set(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(),pendingIntent);
    }
}
