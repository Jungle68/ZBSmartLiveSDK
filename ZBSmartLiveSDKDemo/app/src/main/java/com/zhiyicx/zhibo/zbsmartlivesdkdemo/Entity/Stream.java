package com.zhiyicx.zhibo.zbsmartlivesdkdemo.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jungle on 16/7/26.
 * com.zhiyicx.zhibo.zbsmartlivesdkdemo.Entity
 * ZBSmartLiveSDKDemo
 * email:335891510@qq.com
 */
public class Stream implements Parcelable {

    /**
     * id : z1.zhiyicx.zhibo_1000
     * title : dwadwa
     * location : 巴兰基利亚巴兰基
     * label : 标签A, 标签B
     * icon : {"0":"/public/uploads/2016-04-12/images/79e61c044aff11138f4c72f3f1d9144f_1"}
     */

    private String id;
    private String title;
    private String location;
    private String label;
    /**
     * 0 : /public/uploads/2016-04-12/images/79e61c044aff11138f4c72f3f1d9144f_1
     */

    private IconBean icon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public IconBean getIcon() {
        return icon;
    }

    public void setIcon(IconBean icon) {
        this.icon = icon;
    }

    public static class IconBean implements Parcelable {
        @SerializedName("0")
        private String value0;

        public String getValue0() {
            return value0;
        }

        public void setValue0(String value0) {
            this.value0 = value0;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.value0);
        }

        public IconBean() {
        }

        protected IconBean(Parcel in) {
            this.value0 = in.readString();
        }

        public static final Creator<IconBean> CREATOR = new Creator<IconBean>() {
            @Override
            public IconBean createFromParcel(Parcel source) {
                return new IconBean(source);
            }

            @Override
            public IconBean[] newArray(int size) {
                return new IconBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.location);
        dest.writeString(this.label);
        dest.writeParcelable(this.icon, flags);
    }

    public Stream() {
    }

    protected Stream(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.location = in.readString();
        this.label = in.readString();
        this.icon = in.readParcelable(IconBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<Stream> CREATOR = new Parcelable.Creator<Stream>() {
        @Override
        public Stream createFromParcel(Parcel source) {
            return new Stream(source);
        }

        @Override
        public Stream[] newArray(int size) {
            return new Stream[size];
        }
    };
}
