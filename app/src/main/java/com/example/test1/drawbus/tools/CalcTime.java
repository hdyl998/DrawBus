package com.example.test1.drawbus.tools;


import android.util.Log;

// 计算时间的工具类
public class CalcTime {
    long start = 0;
    long end = 0;

    public CalcTime() {
        recordStart();
    }

    public void recordStart() {
        start = System.currentTimeMillis();
    }

    public void printResult(String tag) {
        Log.e("lgdx" + tag + "时间差", getTimeDistenceString());
        recordStart();
    }

    public String getTimeDistenceString() {
        return getTimeDistence() + "毫秒";
    }

    /**
     * 获取时间长度
     *
     * @return
     */
    public long getTimeDistence() {
        end = System.currentTimeMillis();
        return (end - start);
    }
}