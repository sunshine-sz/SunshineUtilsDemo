package com.sunshine.rxjavademo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.sunshine.rxjavademo.R;
import com.sunshine.rxjavademo.adapter.SimpleMonthAdapter;
import com.sunshine.rxjavademo.inter.DatePickerController;
import com.sunshine.rxjavademo.view.DayPickerView;

import java.util.List;


/**
 * 作者：LiZhao
 * 时间：2016/11/21 15:47
 * 邮箱：44493547@qq.com
 * 备注：
 */
public class CalendarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        DayPickerView dayPickerView = (DayPickerView) findViewById(R.id.dpv_calendar);
        DayPickerView.DataModel dataModel = new DayPickerView.DataModel();
        dataModel.yearStart = 2016;
        dataModel.monthStart = 1;
        dataModel.monthCount = 12;
        dataModel.defTag = "￥100";
        dataModel.leastDaysNum = 7;
        dataModel.mostDaysNum = 7;
//        dataModel.selectedDays = new SimpleMonthAdapter.CalendarDay(Calendar.getInstance(),dataModel.defTag);
        dayPickerView.setParameter(dataModel, new DatePickerController() {
            @Override
            public void onDayOfMonthSelected(SimpleMonthAdapter.CalendarDay calendarDay) {
                Toast.makeText(getApplicationContext(), "onDayOfMonthSelected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDateRangeSelected(List<SimpleMonthAdapter.CalendarDay> selectedDays) {
                Toast.makeText(getApplicationContext(), "onDateRangeSelected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void alertSelectedFail(FailEven even) {
                Toast.makeText(getApplicationContext(), "alertSelectedFail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
