package com.sunshine.rxjavademo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者: Sunshine
 * 时间: 2016/9/19.
 * 邮箱: 44493547@qq.com
 * 描述: 传递的类
 */
public class HelloMsg implements Parcelable {
    private String msg;
    private int pid;

    public HelloMsg(String msg, int pid) {
        this.msg = msg;
        this.pid = pid;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.msg);
        dest.writeInt(this.pid);
    }

    protected HelloMsg(Parcel in) {
        this.msg = in.readString();
        this.pid = in.readInt();
    }

    public static final Parcelable.Creator<HelloMsg> CREATOR = new Parcelable.Creator<HelloMsg>() {
        @Override
        public HelloMsg createFromParcel(Parcel source) {
            return new HelloMsg(source);
        }

        @Override
        public HelloMsg[] newArray(int size) {
            return new HelloMsg[size];
        }
    };
}
