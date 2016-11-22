package com.sunshine.rxjavademo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.fitsleep.sunshinelibrary.utils.Logger;
import com.sunshine.rxjavademo.R;
import com.sunshine.rxjavademo.adapter.SimpleMonthAdapter;
import com.sunshine.rxjavademo.inter.DatePickerController;
import com.sunshine.rxjavademo.view.DayPickerView;

import java.util.Calendar;
import java.util.List;


/**
 * 作者：LiZhao
 * 时间：2016/11/21 15:47
 * 邮箱：44493547@qq.com
 * 备注：
 */
public class CalendarActivity extends AppCompatActivity {
    private static final String TAG = CalendarActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        DayPickerView dayPickerView = (DayPickerView) findViewById(R.id.dpv_calendar);
        DayPickerView.DataModel dataModel = new DayPickerView.DataModel();
        dataModel.yearStart = 2015;
        dataModel.monthStart = 11;
        dataModel.monthCount = 12;
        dataModel.defTag = "￥100";
        dataModel.leastDaysNum = 3;
        dataModel.mostDaysNum = 12;
        dayPickerView.setParameter(dataModel, new DatePickerController() {
            @Override
            public void onDayOfMonthSelected(SimpleMonthAdapter.CalendarDay calendarDay) {
                Logger.e(TAG,"开始的日期:"+calendarDay.toString());
            }

            @Override
            public void onDateRangeSelected(List<SimpleMonthAdapter.CalendarDay> selectedDays) {
                for (SimpleMonthAdapter.CalendarDay calendarDay : selectedDays){
                    Logger.e(TAG,"选中的日期:"+calendarDay.toString());
                }

            }

            @Override
            public void alertSelectedFail(FailEven even) {
                Toast.makeText(getApplicationContext(), "alertSelectedFail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
