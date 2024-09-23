package com.example.xqtest.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;


/**
 * Parcelable 列, bArray 控制数据长度
 */
public class Market implements Parcelable {

    @Expose
    public byte[] bArray = new byte[ParcelableTestActivity.Companion.getBYTE_LENGTH()];

    @Override
    public int describeContents() {
        return 0;
    }

    private Market() {
    }

    public Market(int size) {
        this.bArray = new byte[size];
        bArray[0] = '0'; // 30H = 48
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByteArray(this.bArray);

    }

    protected Market(Parcel in) {
        in.readByteArray(bArray);
    }

    public static final Creator<Market> CREATOR = new Creator<Market>() {
        @Override
        public Market createFromParcel(Parcel source) {
            return new Market(source);
        }

        @Override
        public Market[] newArray(int size) {
            return new Market[size];
        }
    };
}
