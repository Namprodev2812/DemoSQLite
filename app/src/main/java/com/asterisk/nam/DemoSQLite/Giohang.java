package com.asterisk.nam.DemoSQLite;

public class Giohang {

    private int mId;
    private String mName;
    private double mprice;
    private int mIndex;

    public Giohang() {

    }

    public Giohang(int mid, String mname, double price, int index) {
        this.mId = mid;
        this.mName = mname;
        this.mprice = price;
        this.mIndex = index;
    }

    public Giohang(String mname, double price, int index) {
        this.mName = mname;
        this.mprice = price;
        this.mIndex = index;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public double getMprice() {
        return mprice;
    }

    public void setMprice(double price) {
        this.mprice = price;
    }

    public int getmIndex() {
        return mIndex;
    }

    public void setmIndex(int index) {
        this.mIndex = index;
    }
}
