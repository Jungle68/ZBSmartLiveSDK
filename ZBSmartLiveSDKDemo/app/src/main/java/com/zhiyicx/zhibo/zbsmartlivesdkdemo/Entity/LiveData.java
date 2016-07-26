package com.zhiyicx.zhibo.zbsmartlivesdkdemo.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jungle on 16/7/26.
 * com.zhiyicx.zhibo.zbsmartlivesdkdemo.Entity
 * ZBSmartLiveSDKDemo
 * email:335891510@qq.com
 */
public class LiveData implements Parcelable {
    private Stream stream;
    private User  user;

    public Stream getStream() {
        return stream;
    }

    public void setStream(Stream stream) {
        this.stream = stream;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.stream, flags);
        dest.writeParcelable(this.user, flags);
    }

    public LiveData() {
    }

    protected LiveData(Parcel in) {
        this.stream = in.readParcelable(Stream.class.getClassLoader());
        this.user = in.readParcelable(User.class.getClassLoader());
    }

    public static final Parcelable.Creator<LiveData> CREATOR = new Parcelable.Creator<LiveData>() {
        @Override
        public LiveData createFromParcel(Parcel source) {
            return new LiveData(source);
        }

        @Override
        public LiveData[] newArray(int size) {
            return new LiveData[size];
        }
    };
}
