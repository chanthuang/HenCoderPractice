package com.chant.hencoderpractice.practicedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice11PieChartView extends View {

    private static class Data {
        String name;
        float ratio;
        int color;
        float distance;

        public Data(String name, float ratio, int color, float distance) {
            this.name = name;
            this.ratio = ratio;
            this.color = color;
            this.distance = distance;
        }
    }

    private Paint mPaint;
    private RectF mRectF;
    private Data[] mData;
    private int mPieWidth;

    private float mRatioSum;
    private float mGapDegree;

    {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mRectF = new RectF();

        mData = new Data[]{
                new Data("Froyo", 0f, 0xFFf7422d, 0),
                new Data("GB", .1f, 0xFF9d27b2, 0),
                new Data("ICS", .1f, 0xFF9d9d9d, 0),
                new Data("JB", .55f, 0xFF009688, 0),
                new Data("KITKAT", .8f, 0xFF1094f7, 0),
                new Data("L", .9f, 0xFFf7422e, dpToPx(5)),
                new Data("M", .55f, 0xFFffc200, 0),
        };

        mGapDegree = 2f;
        mRatioSum = 0f;
        for (Data data : mData) {
            mRatioSum += data.ratio;
        }
    }

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPieWidth = Math.min(getWidth(), getHeight()) / 3 * 2;
        mRectF.set(-mPieWidth / 2, -mPieWidth / 2, mPieWidth / 2, mPieWidth / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        float degreeSum = 360f - mGapDegree * (mData.length - 1);
        float currentDegree = 0;
        for (int i = 0; i < mData.length; i++) {
            Data data = mData[i];
            float degree = degreeSum * data.ratio / mRatioSum;
            float centerDegree = currentDegree + degree / 2;
            float xMultiplier = (float) (Math.cos(degreeToRadians(centerDegree)));
            float yMultiplier = (float) (Math.sin(degreeToRadians(centerDegree)));
            // 画扇形
            {
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setColor(data.color);
                canvas.save();
                canvas.translate(xMultiplier * data.distance, yMultiplier * data.distance);
                canvas.drawArc(mRectF, currentDegree, degree, true, mPaint);
                canvas.restore();
            }
            // 画线
            {
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setStrokeWidth(dpToPx(1));
                mPaint.setColor(Color.LTGRAY);
                float lineStart = mRectF.width() / 2 + data.distance;
                float linePart1Length = dpToPx(12);
                float linePart2Length = dpToPx(20);
                // -1 if left
                float leftOrRight = (centerDegree % 360 > 90 && centerDegree % 360 < 270) ? -1 : 1;
                float point0X = lineStart * xMultiplier;
                float point0Y = lineStart * yMultiplier;
                float point1X = (lineStart + linePart1Length) * xMultiplier;
                float point1Y = (lineStart + linePart1Length) * yMultiplier;
                float point2X = (lineStart + linePart1Length) * xMultiplier + (leftOrRight * linePart2Length);
                float point2Y = (lineStart + linePart1Length) * yMultiplier;
                canvas.drawLines(new float[]{
                                point0X, point0Y,
                                point1X, point1Y,
                                point1X, point1Y,
                                point2X, point2Y
                        },
                        mPaint);

                // 画字
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setTextSize(dpToPx(13));
                mPaint.setColor(Color.LTGRAY);
                float textWidth = mPaint.measureText(data.name);
                float textLeft = point2X;
                if (leftOrRight < 0) {
                    textLeft += (10 + textWidth) * leftOrRight;
                } else {
                    textLeft += 10;
                }
                float textBaseline = point2Y;
                canvas.drawText(data.name, textLeft, textBaseline, mPaint);
            }
            // 旋转
            currentDegree += degree + mGapDegree;
        }
        canvas.restore();
    }

    private double degreeToRadians(float degree) {
        return degree / 180 * Math.PI;
    }

    private float dpToPx(int dp) {
        return getResources().getDisplayMetrics().density * dp;
    }
}
