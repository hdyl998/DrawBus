package com.example.test1.drawbus;

import android.graphics.Color;

/**
 * <p>Created by Administrator on 2018/9/10.<p>
 * <p>佛祖保佑，永无BUG<p>
 */

public class BusInfoItem {
    //站点索引
    public int index;
    //站点名字
    public String name;


    public String getName() {
        return name;
    }


    public int getTextCount() {
        return name.length();
    }

    /***
     * 索引显示数字需要加1
     * @return
     */
    public String getIndexString() {
        return (index + 1) + "";
    }

    /***
     * 文本颜色
     * @return
     */
    public int getTextColor() {
        if (isGray()) {
            return ColorConstants.COLOR_GRAY;
        }
        if (isGreen()) {
            return ColorConstants.COLOR_BLACK;
        }
        return Color.WHITE;
    }

    /***
     * 圆圈文本颜色
     * @return
     */
    public int getCircleTextColor() {
        return Color.WHITE;
    }

    /***
     * 圆圈背景颜色
     * @return
     */
    public int getCircleBgColor() {
        if (isGray()) {
            return ColorConstants.COLOR_GRAY;
        }
        if (isGreen()) {
            return ColorConstants.COLOR_GREEN;
        }
        return ColorConstants.COLOR_RED;
    }

    /***
     * 文字背景颜色
     * @return
     */
    public int getTextBgColor() {
        if (isRed()) {
            return ColorConstants.COLOR_RED;
        }
        return Color.TRANSPARENT;
    }


    /****
     * 已过站了
     * @return
     */
    public boolean isGray() {
        return index < BusInfoManager.currentIndex;
    }

    /***
     * 当前站
     * @return
     */
    public boolean isRed() {
        return index == BusInfoManager.currentIndex;
    }

    /***
     * 还没到
     * @return
     */
    public boolean isGreen() {
        return index > BusInfoManager.currentIndex;
    }
}
