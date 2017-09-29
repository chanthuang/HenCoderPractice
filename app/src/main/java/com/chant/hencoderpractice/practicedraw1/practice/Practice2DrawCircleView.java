package com.chant.hencoderpractice.practicedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice2DrawCircleView extends View {

    private Paint mPaint;

    public Practice2DrawCircleView(Context context) {
        super(context);
        init();
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆
        int gap = (int) (getResources().getDisplayMetrics().density * 20);
        int radius = (Math.min(getWidth(), getHeight()) - 2 * gap) / 4;
        {
            int ltCenterX = getWidth() / 2 - gap / 2 - radius;
            int ltCenterY = getHeight() / 2 - gap / 2 - radius;
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(Color.BLACK);
            canvas.drawCircle(ltCenterX, ltCenterY, radius, mPaint);
        }
        {
            int rtCenterX = getWidth() / 2 + gap / 2 + radius;
            int rtCenterY = getHeight() / 2 - gap / 2 - radius;
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(getResources().getDisplayMetrics().density * 1);
            mPaint.setColor(Color.BLACK);
            canvas.drawCircle(rtCenterX, rtCenterY, radius, mPaint);
        }
        {
            int lbCenterX = getWidth() / 2 - gap / 2 - radius;
            int lbCenterY = getHeight() / 2 + gap / 2 + radius;
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(0xFF468ee4);
            canvas.drawCircle(lbCenterX, lbCenterY, radius, mPaint);
        }
        {
            int rbCenterX = getWidth() / 2 + gap / 2 + radius;
            int rbCenterY = getHeight() / 2 + gap / 2 + radius;
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(getResources().getDisplayMetrics().density * 20);
            mPaint.setColor(Color.BLACK);
            canvas.drawCircle(rbCenterX, rbCenterY, radius, mPaint);
        }
    }
}
