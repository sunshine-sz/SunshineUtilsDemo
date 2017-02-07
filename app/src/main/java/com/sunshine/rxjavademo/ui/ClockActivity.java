package com.sunshine.rxjavademo.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.fitsleep.sunshinelibrary.utils.TimeUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.sunshine.rxjavademo.R;
import com.sunshine.rxjavademo.view.ClockView;

import java.util.Calendar;

public class ClockActivity extends AppCompatActivity {

    private static final String TAG = ClockActivity.class.getSimpleName();
    private long minData = 1447568280000l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        ClockView clockView = (ClockView) findViewById(R.id.clock_view);
        final TextView time = (TextView) findViewById(R.id.tv_time);
        Calendar calendar = Calendar.getInstance();
        clockView.setHour(calendar.get(Calendar.HOUR_OF_DAY));
        clockView.setMinute(calendar.get(Calendar.MINUTE));
        clockView.setSecond(calendar.get(Calendar.SECOND));
        clockView.setOnTimeChangeListener(new ClockView.OnTimeChangeListener() {
            @Override
            public void onTimeChange(View view, int hour, int minute, int second) {
                time.setText(hour+":"+minute+":"+second);
            }
        });
        setAlarm();
    }

    private void setAlarm() {
        final AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        // 指定要启动的是Activity组件,通过PendingIntent调用getActivity来设置
        Intent intent = new Intent(ClockActivity.this, CalendarActivity.class);
        final PendingIntent pi = PendingIntent.getActivity(ClockActivity.this, 0, intent, 0);
//        alarmManager.setAlarmClock();
        findViewById(R.id.bt_alarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                new TimePickerDialog(ClockActivity.this, 0, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker,  int hourOfDay, int minute) {
                        //设置当前时间
                        Calendar c = Calendar.getInstance();
                        c.setTimeInMillis(System.currentTimeMillis());
                        // 根据用户选择的时间来设置Calendar对象
                        c.set(Calendar.HOUR, hourOfDay);
                        c.set(Calendar.MINUTE, minute);
                        // ②设置AlarmManager在Calendar对应的时间启动Activity
                        alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
                        // 提示闹钟设置完毕:
                        ToastUtils.showMessage("闹钟设置完毕");

                    }
                },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
            }
        });
    }
}
