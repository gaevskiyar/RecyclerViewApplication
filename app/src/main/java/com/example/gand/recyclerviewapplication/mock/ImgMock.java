package com.example.gand.recyclerviewapplication.mock;

import android.graphics.Color;

public class ImgMock {

    private String mName;
    private int mBGColor;

    public int getBGColor() {
        return mBGColor;
    }

    public void setBGColor(int mBGColor) {
        this.mBGColor = mBGColor;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public ImgMock(String Name, int bgColor) {
        this.mName = Name;
        this.mBGColor = bgColor;
    }
}
