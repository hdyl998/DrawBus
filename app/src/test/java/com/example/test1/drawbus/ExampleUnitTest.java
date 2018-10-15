package com.example.test1.drawbus;

import android.text.TextUtils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    class MinStack {

        int value[];
        int index = -1;


        int minValue = Integer.MAX_VALUE;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            value = new int[16];
        }

        public void push(int x) {
            index++;
            if (index >= value.length) {
                doubleSize();
            }
            value[index] = x;
            minValue = Math.min(minValue, x);
        }

        private void doubleSize() {
            int value2[] = new int[value.length * 2];
            System.arraycopy(value, 0, value2, 0, value.length);
            value = value2;
        }

        private void reCaculate() {
            minValue = Integer.MAX_VALUE;
            for (int i = 0; i <= index; i++) {
                minValue = Math.min(minValue, value[i]);
            }
        }

        public void pop() {
            if (index >= 0) {
                int topValue = top();
                index--;
                if (topValue <= minValue) {
                    reCaculate();
                }

            } else {
                minValue = Integer.MAX_VALUE;
            }
        }

        public int top() {
            return value[index];
        }

        public int getMin() {
            return minValue;
        }

    }

    public static boolean isEmpty(CharSequence str) {
        return str == null;
    }

    public static boolean isEmpty2(CharSequence str) {
        return str.length() == 0;
    }

    public int strStr(String haystack, String needle) {
        if (isEmpty(haystack) || isEmpty(needle)) {
            return -1;
        }
        if (isEmpty2(needle)) {
            return 0;
        }
        int leng1 = haystack.length();
        int leng2 = needle.length();

        if (leng2 > leng1) {
            return -1;
        }
        for (int i = 0; i < leng1; i++) {
            if (contains(haystack, needle, i, leng1, leng2)) {
                return i;
            }
        }
        return -1;
    }

    private boolean contains(String haystack, String needle, int startIndex, int firstLength, int secondLength) {
        for(int i=0;i<secondLength;i++){
            needle.charAt(i)==
        }


        for (int i = startIndex; i < firstLength; i++) {
            int index2 = i - startIndex;
            if (index2 >= secondLength) {
                return true;
            }
            if (haystack.charAt(i) != needle.charAt(index2)) {
                return false;
            }
        }
        return true;
    }


    @Test
    public void addition_isCorrect() throws Exception {
        int a = strStr("mississippi", "issipi");
        System.out.println(a);
        //["MinStack","push","push","push","top","pop","getMin","pop","getMin","pop","push","top","getMin","push","top","getMin","pop","getMin"]
//          [[],[2147483646],[2147483646],[2147483647],[],[],[],[],[],[],[2147483647],[],[],[-2147483648],[],[],[],[]]
//        MinStack minStack = new MinStack();
//        minStack.push(-2);
//        minStack.push(0);
//        minStack.push(-3);
//
//        System.out.println(minStack.getMin());
//        minStack.pop();
//        System.out.println(minStack.getMin());  //--> 返回 -3.


    }


    public static void main(String[] args) {
        HashMap<Integer, String> hm = new HashMap<Integer, String>();
        //定义HashMap变量用于存储每张排的编号以及牌型
        ArrayList<Integer> array = new ArrayList<Integer>();
        //定义ArrayList变量存储排的编号
        String[] colors = {"♤", "♥", "♣", "♢"};
        //定义数组存储排的花色
        String[] numbers = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
        //定义数组存储牌值
        int index = 0;
        //定义编号
        for (String number : numbers) {
            //遍历排值数组
            for (String color : colors) {
                //遍历花色
                hm.put(index, color.concat(number));
                //将花色与牌值拼接，并将编号与拼接后的结果存储到hm中
                array.add(index);
                //将编号存储到array中
                index++;
            }
        }/* * 将小王和大王存储到hm中 */
        hm.put(index, "小王");
        array.add(index);
        index++;
        hm.put(index, "大王");
        array.add(index);
        Collections.shuffle(array);
        //调用Collections集合的shuffle()方法，将array中存储的编号进行随机的置换，即打乱顺序
        /* * 定义四个TreeSet集合的变量用于存储底牌编号以及三个玩家的牌的编号 * 采用TreeSet集合是因为TreeSet集合可以实现自然排序 */
        TreeSet<Integer> playerOne = new TreeSet<Integer>();
        TreeSet<Integer> PlayerTwo = new TreeSet<Integer>();
        TreeSet<Integer> playerThree = new TreeSet<Integer>();
        TreeSet<Integer> dipai = new TreeSet<Integer>();
        //遍历编号的集合，实现发牌
        for (int x = 0; x < array.size(); x++) {
            if (x >= array.size() - 3) {
                dipai.add(array.get(x));
            } else if (x % 3 == 0) {
                playerOne.add(array.get(x));
            } else if (x % 3 == 1) {
                PlayerTwo.add(array.get(x));
            } else if (x % 3 == 2) {
                playerThree.add(array.get(x));
            }
        }
        lookPoker("底牌", dipai, hm);
        lookPoker("xiequn", PlayerTwo, hm);
        lookPoker("huangshen", playerOne, hm);

        lookPoker("diannao", playerThree, hm);
    }

    /**
     * 遍历每个玩家的牌以及底牌 * @param name * @param ts * @param hm
     */
    public static void lookPoker(String name, TreeSet<Integer> ts, HashMap<Integer, String> hm) {
        System.out.print(name + ":\t");
        //打印玩家名称
        for (Integer key : ts) {
            //遍历玩家TreeSet集合，获得玩家的牌的编号
            String value = hm.get(key);
            //根据玩家牌编号获取具体的牌值
            System.out.print(value + "  ");
            //打印
        }
        System.out.println();
    }
}