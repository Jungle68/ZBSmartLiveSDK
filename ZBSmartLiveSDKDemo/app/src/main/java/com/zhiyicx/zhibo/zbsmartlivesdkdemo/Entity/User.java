package com.zhiyicx.zhibo.zbsmartlivesdkdemo.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jungle on 16/7/26.
 * com.zhiyicx.zhibo.zbsmartlivesdkdemo.Entity
 * ZBSmartLiveSDKDemo
 * email:335891510@qq.com
 */
public class User implements Parcelable {

    /**
     * uid : 12g5d77bzb6b4z570776c7
     */

    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uid);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.uid = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
