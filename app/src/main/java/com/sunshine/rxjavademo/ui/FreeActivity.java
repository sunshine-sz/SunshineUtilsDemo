package com.sunshine.rxjavademo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sunshine.rxjavademo.R;
import com.sunshine.rxjavademo.view.FreeRadioGroup;

/**
 * 作者：LiZhao
 * 时间：2017.1.4 10:48
 * 邮箱：44493547@qq.com
 * 备注：
 */
public class FreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free);
        FreeRadioGroup radioGroup = (FreeRadioGroup) findViewById(R.id.group);
        final TextView tvContent = (TextView) findViewById(R.id.tv_content);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_1:
                        tvContent.setText("页面1");
                        break;
                    case R.id.rb_2:
                        tvContent.setText("页面2");
                        break;
                    case R.id.rb_3:
                        tvContent.setText("页面3");
                        break;
                }
            }
        });
    }
}
