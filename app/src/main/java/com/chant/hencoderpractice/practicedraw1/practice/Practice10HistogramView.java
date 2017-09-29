package com.chant.hencoderpractice.practicedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;

public class Practice10HistogramView extends View {

    private Paint mPaint;
    private Pair[] mData;

    {
        mPaint = new Paint();
        mPaint.setStrokeWidth(2);

        mData = new Pair[] {
                new Pair("Froyo", 0f),
                new Pair("GB", .1f),
                new Pair("ICS", .1f),
                new Pair("JB", .55f),
                new Pair("KITKAT", .8f),
                new Pair("L", .9f),
                new Pair("M", .55f),
        };
    }

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        int chartWidth = getWidth() * 8 / 10;
        int chartHeight = getHeight() * 6 / 10;
        int chartLeft = getWidth() / 2 - chartWidth / 2;
        int chartTop = (getHeight()  - chartHeight) / 4;
        int chartBottom = chartHeight + chartTop;

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        canvas.drawLine(chartLeft, chartTop, chartLeft, chartBottom, mPaint);
        canvas.drawLine(chartLeft, chartBottom, chartLeft + chartWidth, chartBottom, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(getResources().getDisplayMetrics().scaledDensity * 12);
        float gap = getWidth() / 40;
        float columnWidth = (chartWidth - gap) / mData.length - gap;
        canvas.save();
        canvas.translate(getWidth() / 2 - chartWidth / 2, 0);
        for (Pair<String, Float> data : mData) {
            canvas.translate(gap, 0);

            mPaint.setColor(Color.GREEN);
            canvas.drawRect(0, chartBottom - chartHeight * data.second, columnWidth, chartBottom, mPaint);

            mPaint.setColor(Color.WHITE);
            float textWidth = mPaint.measureText(data.first);
            canvas.drawText(data.first, columnWidth / 2 - textWidth / 2, chartBottom - mPaint.getFontMetricsInt().top, mPaint);

            canvas.translate(columnWidth, 0);
        }
        canvas.restore();

        mPaint.setTextSize(getResources().getDisplayMetrics().scaledDensity * 18);
        mPaint.setColor(Color.WHITE);
        String title = "直方图";
        float textWidth = mPaint.measureText(title);
        int titleHeight = mPaint.getFontMetricsInt().bottom - mPaint.getFontMetricsInt().top;
        float titleTop = chartBottom + (getHeight() - chartBottom) / 2 - titleHeight / 2;
        float titleBaseLine = titleTop - mPaint.getFontMetrics().top;
        canvas.drawText(title, getWidth() / 2 - textWidth / 2, titleBaseLine, mPaint);
    }
}
