package com.example.test1.drawbus;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Created by Administrator on 2018/9/10.<p>
 * <p>佛祖保佑，永无BUG<p>
 */

public class BusView extends View {

    int leftOffset = 15;//左部留白
    int rightOffset = 15;//右部留白
    int cirCleSize = 10;//圆圈大小
    int busStopNameSize = 12;

    int topOffset = 5;//顶部留白
    int bottomOffset = 5;//底部留白

    int lineWidth = 5;//线条宽度

    int textCircleOffset = 14;//圈与文本之间的留白

    int textSizeCirlce = 12;//圆圈文字大小

    int spaceOffset = 1;//色块增大范围

    public BusView(Context context) {
        super(context);
        initViews();
    }

    public BusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public BusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    @TargetApi(21)
    public BusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews();
    }

    private BusInfoManager infoManager = new BusInfoManager();

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    {
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    private void initViews() {
        leftOffset = dip2px(leftOffset);
        rightOffset = dip2px(rightOffset);
        cirCleSize = dip2px(cirCleSize);
        busStopNameSize = dip2px(busStopNameSize);

        topOffset = dip2px(topOffset);
        bottomOffset = dip2px(bottomOffset);

        textCircleOffset = dip2px(textCircleOffset);
        lineWidth = dip2px(lineWidth);

        textSizeCirlce = dip2px(textSizeCirlce);

        spaceOffset = dip2px(spaceOffset);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getWidth() == 0) {
            return;
        }
        if (infoManager.isNeedDrawDoubleLine()) {
            oneHeight = getHeight() / 5;//大概5等分
            drawBgLine(canvas);//画线
            drawOthers(canvas);//画其它的
            drawNextStation(canvas);//画下一站文本
        } else {
            oneHeight = getHeight() / 3;//大概3等分
            drawBgLineShort(canvas);
            drawOthersShort(canvas);
            drawNextStationShort(canvas);
        }
    }

    private void drawNextStationShort(Canvas canvas) {
        int firstTextSize = dip2px(12);
        int secondTextSize = dip2px(16);
        mPaint.setTextSize(firstTextSize);
        String text = "下一站:";
        float length = mPaint.measureText(text) / 2;
        int centerX = (int) (getWidth() / 2 - length);
        int y1 = oneHeight / 2 + getFontHeight(mPaint) / 2;
        mPaint.setColor(ColorConstants.COLOR_GRAY);
        canvas.drawText(text, centerX, y1, mPaint);

        mPaint.setTextSize(secondTextSize);

        mPaint.setTextAlign(Paint.Align.LEFT);
        int y2 = oneHeight / 2 + getFontHeight(mPaint) / 2;
        String nextStation = infoManager.getNextStationName();
        canvas.drawText(nextStation, centerX + length, y2, mPaint);
        mPaint.setColor(ColorConstants.COLOR_BLACK);
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    private void drawOthersShort(Canvas canvas) {
        int allCount = infoManager.getCount();
        float xTotal = (getWidth() - leftOffset - rightOffset);//x总长度
        float XOneLength = xTotal / (allCount - 1);//上面X每份长度

        for (int i = 0; i < allCount; i++) {
            int x = (int) (leftOffset + XOneLength * i);
            int y = oneHeight;
            drawBusInfoItem(canvas, infoManager.getListInfos().get(i), x, y);
            drawBottomText(canvas, infoManager.getListInfos().get(i), x, y);
        }
    }


    private void drawBgLineShort(Canvas canvas) {
        Path path = new Path();
        path.moveTo(leftOffset, oneHeight);
        path.lineTo(getWidth() - rightOffset, oneHeight);

        mPaint.setStrokeWidth(lineWidth);
        mPaint.setColor(0xff4d5c66);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
    }


    private void drawNextStation(Canvas canvas) {
        int firstTextSize = dip2px(12);
        int secondTextSize = dip2px(16);
        mPaint.setTextSize(firstTextSize);
        String text = "下一站:";
        float length = mPaint.measureText(text) / 2;
        int centerX = (int) (getWidth() / 2 - length);
        int y1 = getHeight() / 2 + getFontHeight(mPaint) / 2;
        mPaint.setColor(ColorConstants.COLOR_GRAY);
        canvas.drawText(text, centerX, y1, mPaint);

        mPaint.setTextSize(secondTextSize);

        mPaint.setTextAlign(Paint.Align.LEFT);
        int y2 = getHeight() / 2 + getFontHeight(mPaint) / 2;
        String nextStation = infoManager.getNextStationName();
        canvas.drawText(nextStation, centerX + length, y2, mPaint);
        mPaint.setColor(ColorConstants.COLOR_BLACK);
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    private void drawOthers(Canvas canvas) {
        int allCount = infoManager.getCount();
        int halfCount = (allCount + 1) / 2;//防止为奇数
        float xTotal = (getWidth() - leftOffset - rightOffset);//x总长度
        float upXOneLength = xTotal / (halfCount - 1);//上面X每份长度
        float downXOneLength = xTotal / ((allCount - halfCount) - 1);//下面X每份长度


        for (int i = 0; i < halfCount; i++) {
            int x = (int) (leftOffset + upXOneLength * i);
            int y = oneHeight * 2;
            drawBusInfoItem(canvas, infoManager.getListInfos().get(i), x, y);
            drawTopText(canvas, infoManager.getListInfos().get(i), x, y);
        }
        int count = 0;
        for (int i = allCount - 1; i >= halfCount; i--) {
            int x = (int) (leftOffset + downXOneLength * count);
            int y = oneHeight * 3;
            drawBusInfoItem(canvas, infoManager.getListInfos().get(i), x, y);
            drawBottomText(canvas, infoManager.getListInfos().get(i), x, y);
            count++;
        }
//        mPaint.setStyle(Paint.Style.FILL);
    }

    /***
     * 画背景线
     * @param canvas
     */
    private void drawBgLine(Canvas canvas) {
        Path path = new Path();
        path.moveTo(leftOffset, 2 * oneHeight);
        path.lineTo(getWidth() - rightOffset, 2 * oneHeight);
        path.lineTo(getWidth() - rightOffset, 3 * oneHeight);
        path.lineTo(leftOffset, 3 * oneHeight);

        mPaint.setStrokeWidth(lineWidth);
        mPaint.setColor(0xff4d5c66);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
    }

    private void drawBusInfoItem(Canvas canvas, BusInfoItem item, int x, int y) {
        drawCircle(canvas, item, x, y);
    }

    private void drawCircle(Canvas canvas, BusInfoItem item, int x, int y) {
        mPaint.setColor(item.getCircleBgColor());
        canvas.drawCircle(x, y, cirCleSize, mPaint);
        mPaint.setColor(item.getCircleTextColor());
        mPaint.setTextSize(textSizeCirlce);
        int fontHeight = getFontHeight(mPaint);
        canvas.drawText(item.getIndexString(), x, y + fontHeight / 2, mPaint);
    }

    /**
     * 画文
     *
     * @param canvas
     * @param item
     * @param x
     * @param y
     */
    private void drawTopText(Canvas canvas, BusInfoItem item, int x, int y) {
        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setStrokeWidth(1);
        mPaint.setTextSize(busStopNameSize);

        int textCount = item.getTextCount();
        float fontHeight = getFontVerticalHeight();
        //文字太长,全部都绘制

        //文字绘制空间
        int textSpace = 2 * oneHeight - topOffset - textCircleOffset;
        int maxTextCount = (int) (textSpace / fontHeight);

        List<Point> lists = new ArrayList<>(maxTextCount);
        //文字太多
        if (textCount > maxTextCount) {
            for (int i = 0; i < maxTextCount; i++) {
                lists.add(new Point(x, (int) (fontHeight + topOffset + fontHeight * i)));
            }
        } else {//文字少
            for (int i = textCount - 1; i >= 0; i--) {
                lists.add(new Point(x, (int) (topOffset + textSpace - fontHeight * i)));
            }
        }
        //画背景
        int bgColor = item.getTextBgColor();
        if (bgColor != Color.TRANSPARENT) {
            mPaint.setColor(item.getTextBgColor());
            if (!lists.isEmpty()) {
                Point point = lists.get(0);
                canvas.drawRect(point.x - fontHeight / 2 - spaceOffset, point.y - fontHeight - spaceOffset, point.x + fontHeight / 2 + spaceOffset, topOffset + textSpace + spaceOffset * 3, mPaint);
            }
        }


        mPaint.setColor(item.getTextColor());
        int count = 0;
        for (char ch : item.getName().toCharArray()) {
            if (lists.size() <= count) {
                break;
            }
            Point point = lists.get(count);
            canvas.drawText(String.valueOf(ch), point.x, point.y, mPaint);
            count++;
        }
    }

    /***
     * 调整行高
     * @return
     */
    private float getFontVerticalHeight() {
        return getFontHeight(mPaint) * 1.6f;
    }

    private void drawBottomText(Canvas canvas, BusInfoItem item, int x, int y) {
        //偏移一值
        y += textCircleOffset;

        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setStrokeWidth(1);
        mPaint.setTextSize(busStopNameSize);

        int textCount = item.getTextCount();
        float fontHeight = getFontVerticalHeight();//两个竖直文字之间的间隔
        //文字太长,全部都绘制

        //文字绘制空间
        int textSpace = 2 * oneHeight - topOffset - textCircleOffset;
        int maxTextCount = (int) (textSpace / fontHeight);

        int marginTop = y;
        List<Point> lists = new ArrayList<>(maxTextCount);

        int maxCount = Math.min(maxTextCount, textCount);
        for (int i = 0; i < maxCount; i++) {
            lists.add(new Point(x, (int) (fontHeight + marginTop + fontHeight * i)));
        }
        //画背景
        int bgColor = item.getTextBgColor();
        if (bgColor != Color.TRANSPARENT) {
            mPaint.setColor(item.getTextBgColor());
            if (!lists.isEmpty()) {
                Point point = lists.get(0);
                canvas.drawRect(point.x - fontHeight / 2 - spaceOffset, point.y - fontHeight - spaceOffset, point.x + fontHeight / 2 + spaceOffset, point.y - fontHeight + fontHeight * maxCount + spaceOffset * 3, mPaint);
            }
        }

        mPaint.setColor(item.getTextColor());
        int count = 0;
        for (char ch : item.getName().toCharArray()) {
            if (lists.size() <= count) {
                break;
            }
            Point point = lists.get(count);
            canvas.drawText(String.valueOf(ch), point.x, point.y, mPaint);
            count++;
        }
    }


    int oneHeight;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /***
     * 重置
     */
    public void reset() {
        infoManager.reset();
        invalidate();
    }

    /***
     * 下一站
     */
    public void nextStation() {
        infoManager.nextStation();
        invalidate();
    }


    /***
     * 指定第几站
     */
    public void setCurrentStation(int currentStation) {
        infoManager.setCurrentStation(currentStation);
        invalidate();
    }


    /**
     * 得到字体高度，用于画Y轴字体与线居中
     *
     * @param paint
     * @return
     */
    protected int getFontHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) (Math.ceil(fm.descent - fm.ascent) + 2) / 2;
    }


    public BusInfoManager getInfoManager() {
        return infoManager;
    }
}
