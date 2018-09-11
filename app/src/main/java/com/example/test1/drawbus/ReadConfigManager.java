package com.example.test1.drawbus;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>Created by Administrator on 2018/9/11.<p>
 * <p>佛祖保佑，永无BUG<p>
 */

public class ReadConfigManager {

    private static ReadConfigManager manager;


    public static void resetConfig() {
        manager = new ReadConfigManager();
    }

    private ReadConfigManager() {
        readData();
    }

    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        } else {
            return false;
        }
    }

    private final static String KEY_TWO_LINE_NO = "TWOLINENO";
    private final static String KEY_BUS_LINE_NO = "BUSLINENO";
    private final static String KEY_BUS_STOPS = "BUSSTOPS";

    /***
     * 读取SD卡数据
     */
    private void readData() {
        Log.d("tttt", "读数据");
        if (!hasSdcard()) {
            Log.d("tttt", "没有SD卡");
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/download/busdata.txt");
        if (!file.exists()) {
            Log.d("tttt", "配置文件不存在");
            return;
        }
        BufferedReader reader = null;
        try {
            HashMap<String, String> hashMap = new HashMap<>();
            reader = new BufferedReader(new FileReader(file));
            String str = reader.readLine();
            while (str != null) {
                int index = str.indexOf("=");
                if (index != -1) {
                    String key = str.substring(0, index);
                    String value = str.substring(index + 1);
                    hashMap.put(key, value);

                    Log.d("tttt", key + "");

                    Log.d("tttt", value + "");
                }
                str = reader.readLine();
            }
            String var = hashMap.get(KEY_TWO_LINE_NO);
            if (var != null) {
                twoLineLimit = Integer.parseInt(var);
            }
            String var2 = hashMap.get(KEY_BUS_LINE_NO);
            if (var2 != null) {
                busLineNo = var2;
            }
            String var3 = hashMap.get(KEY_BUS_STOPS);
            if (var3 != null) {
                busStops = var3.split(",");
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("tttt", e.toString());
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {

                }
        }

    }


    public static ReadConfigManager getInstance() {
        return manager;
    }


    /***
     * 超过这个值显示成2行
     */
    private int twoLineLimit = 13;

    /**
     * 车的编号
     */
    private String busLineNo = "M386";

    private String[] busStops = {"交易公交站", "葆地", "交", "交", "大开工", "一切", "苦式工", "葆地", "交", "大开工", "一切", "苦式工", "葆地", "交", "大开工", "一切", "苦式工", "葆地", "交", "大开工", "一切", "苦式工", "葆地", "交", "一切", "苦式工", "大运地铁站"};


    /**
     * 显示成2行的临界值
     *
     * @return
     */
    public int getTwoLineLimit() {
        return twoLineLimit;
    }

    /**
     * 车编号
     *
     * @return
     */
    public String getBusLineNo() {
        return busLineNo;
    }


    /***
     * 站点名字
     * @return
     */
    public String[] getBusStops() {
        return busStops;
    }
}
