package com.example.test1.drawbus;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Created by Administrator on 2018/9/10.<p>
 * <p>佛祖保佑，永无BUG<p>
 */

public class BusInfoManager {

    //公交站台名字
//    public String[] strings = {"交易公交站", "葆地", "交","交", "大开工", "一切", "苦式工", "葆地", "交", "大开工", "一切", "苦式工", "葆地", "交", "大开工", "一切", "苦式工", "葆地", "交", "大开工", "一切", "苦式工", "葆地", "交", "一切", "苦式工", "大运地铁站"};

    //公交站台名字
    public String[] strings = {"交易公交站", "葆地","葆地", "交122","交222",  "大运地铁站"};
    public List<BusInfoItem> listInfos = new ArrayList<>();

    {
        int index = 0;
        for (String s : strings) {
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


    public String busNo = "19";

    /***
     *线路:19
     * @return
     */
    public String getBusNo() {
        return busNo;
    }

    //获得区间名字
    public String getAreaString() {
        return listInfos.get(0).name + "→" + listInfos.get(listInfos.size() - 1).name;
    }


    /***
     * 超过指定行数,则需要绘制双行
     * @return
     */
    public boolean isNeedDrawDoubleLine() {
        return listInfos.size() >= 13;
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
