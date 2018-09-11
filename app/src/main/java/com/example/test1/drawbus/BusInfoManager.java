package com.example.test1.drawbus;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Created by Administrator on 2018/9/10.<p>
 * <p>佛祖保佑，永无BUG<p>
 */

public class BusInfoManager {


    public List<BusInfoItem> listInfos;

    {
        listInfos = new ArrayList<>(ReadConfigManager.getInstance().getBusStops().length);
        int index = 0;
        for (String s : ReadConfigManager.getInstance().getBusStops()) {
            BusInfoItem infoItem = new BusInfoItem();
            infoItem.index = index;
            infoItem.name = s;
            listInfos.add(infoItem);
            index++;
        }
    }

    public int getCount() {
        return getListInfos().size();
    }

    public List<BusInfoItem> getListInfos() {
        return listInfos;
    }

    //当前的索引
    public static int currentIndex;


    /***
     *线路:19
     * @return
     */
    public String getBusNo() {
        return ReadConfigManager.getInstance().getBusLineNo();
    }

    //获得区间名字
    public String getAreaString() {
        return listInfos.get(0).name + "→" + listInfos.get(listInfos.size() - 1).name;
    }


    /***
     * 多余指定行数,则需要绘制双行
     * @return
     */
    public boolean isNeedDrawDoubleLine() {
        return listInfos.size() > ReadConfigManager.getInstance().getTwoLineLimit();
    }

    /***
     * 获取下一站的名字
     * @return
     */
    public String getNextStationName() {
        if (currentIndex + 1 < listInfos.size()) {
            return listInfos.get(currentIndex + 1).name;
        }
        return "";
    }


    /***
     * 下一站
     */
    public void nextStation() {
        if (currentIndex + 1 < listInfos.size()) {
            currentIndex++;
        }
    }

    public void setCurrentStation(int currentStation) {
        currentIndex = currentStation - 1;
        if (currentIndex < 0 || currentStation >= listInfos.size()) {
            throw new RuntimeException("error you set");
        }
    }

    /***
     * 重置
     */
    public void reset() {
        currentIndex = 0;
    }
}
