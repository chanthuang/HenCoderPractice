package com.chant.hencoderpractice.practicedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {

    private Paint mPaint;
    private Path mPath;
    private RectF mRectF;

    {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);

        mPath = new Path();

        mRectF = new RectF();
    }

    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形
        mPath.reset();
        int circleWidth = 200;
        // addArc/arcTo 方法在 APILevel 21 之后才能直接传坐标, 这里用 rect
        mRectF.set(getWidth() / 2 - circleWidth, getHeight() / 2 - circleWidth, getWidth() / 2, getHeight() / 2);
        mPath.addArc(mRectF, -225, 225);

        mRectF.offset(circleWidth, 0);
        mPath.arcTo(mRectF, 180, 225);

        mPath.lineTo(getWidth() / 2, (float) (getHeight() / 2 + circleWidth / 2 * Math.sqrt(2)));
        mPath.close();

        canvas.drawPath(mPath, mPaint);
    }
}
