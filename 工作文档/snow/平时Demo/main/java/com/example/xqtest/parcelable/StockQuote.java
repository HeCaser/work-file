package com.example.xqtest.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

/**
 * Created by John on 17/11/21.
 *
 * 外层 Parcelable,
 */

public class StockQuote implements Parcelable {

    @Expose
    private  Market market;

    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public StockQuote(Market market) {
        this.market = market;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(size);
        dest.writeParcelable(market, flags);
    }

    protected StockQuote(Parcel in) {
        size = in.readInt();
        this.market = in.readParcelable(Market.class.getClassLoader());
    }

    public static final Creator<StockQuote> CREATOR = new Creator<StockQuote>() {
        @Override
        public StockQuote createFromParcel(Parcel source) {
            return new StockQuote(source);
        }

        @Override
        public StockQuote[] newArray(int size) {
            return new StockQuote[size];
        }
    };


}
