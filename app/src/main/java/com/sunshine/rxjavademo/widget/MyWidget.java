package com.sunshine.rxjavademo.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.fitsleep.sunshinelibrary.utils.Logger;
import com.sunshine.rxjavademo.R;

/**
 * 作者：LiZhao
 * 时间：2016/12/14 16:46
 * 邮箱：44493547@qq.com
 * 备注：
 */
public class MyWidget extends AppWidgetProvider{
    private String[] months={"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
    private String[] days={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        // TODO Auto-generated method stub
        Logger.e("########","小控件要更新了");
        RemoteViews remoteViews=new RemoteViews(context.getPackageName(), R.layout.myappwidget);
        Time time=new Time();
        time.setToNow();
        String month=time.year+" "+months[time.month];
        remoteViews.setTextViewText(R.id.txtDay, new Integer(time.monthDay).toString());
        remoteViews.setTextViewText(R.id.txtMonth, month);
        remoteViews.setTextViewText(R.id.txtWeekDay, days[time.weekDay]+time.hour+":"+time.minute+":"+time.second);
        Intent intent=new Intent("cn.com.karl.widget.click");
        PendingIntent pendingIntent=PendingIntent.getBroadcast(context, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.layout, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        super.onReceive(context, intent);
        if(intent.getAction().equals("cn.com.karl.widget.click")){
            Toast.makeText(context, "点击了widget日历", Toast.LENGTH_SHORT).show();
        }
    }
}
