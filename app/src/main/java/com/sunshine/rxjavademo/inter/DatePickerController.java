package com.sunshine.rxjavademo.inter;


import com.sunshine.rxjavademo.adapter.SimpleMonthAdapter;

import java.util.List;

/**
 * 作者: Sunshine
 * 时间: 2016/9/14.
 * 邮箱: 44493547@qq.com
 * 描述: 回调接口
 */
public interface DatePickerController {

    enum FailEven {
        CONTAIN_NO_SELECTED, CONTAIN_INVALID, NO_REACH_LEAST_DAYS, NO_REACH_MOST_DAYS, END_MT_START;

    }
//	public abstract int getMaxYear();

    void onDayOfMonthSelected(SimpleMonthAdapter.CalendarDay calendarDay);          // 点击日期回调函数，月份记得加1

    void onDateRangeSelected(List<SimpleMonthAdapter.CalendarDay> selectedDays);    // 选择范围回调函数，月份记得加1

//    void onDaysSelected(List<SimpleMonthAdapter.CalendarDay> seleDaysList);       // 多选回调函数，月份记得加1

    void alertSelectedFail(FailEven even);
}