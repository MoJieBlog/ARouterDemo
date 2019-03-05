package com.lzp.vpindicator.indicatorView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.lzp.vpindicator.IndicatorView;

import come.lzp.utils.UiUtils;

/**
 * 线性indicator
 *
 * @author Li Xiaopeng
 * @date 2019/3/4
 */
public class LineIndicatorView extends IndicatorView {

    private static final String TAG = "LineIndicatorView";

    private int lineColor = Color.RED;
    private Paint paint;
    private RectF rectF;

    private float startX;
    private float endX;
    private float radius;

    public LineIndicatorView(Context context) {
        this(context, null);
    }

    public LineIndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
       // viewWidth = UiUtils.dip2px(getContext(), 10);
        //viewHeight = UiUtils.dip2px(getContext(), 3);
        viewHeight = 80;
        viewWidth = 80;
        radius = 40;
        //radius = UiUtils.dip2px(getContext(), 1.5f);
        paint = new Paint();
        paint.setColor(lineColor);
        rectF = new RectF();

        endX = startX + viewWidth;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(FrameLayout.LayoutParams.MATCH_PARENT,viewHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rectF.set(startX, 0, endX, viewHeight);
        canvas.drawRoundRect(rectF, radius, radius, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void onPageScrolled(float startX, float endX) {
        this.startX = startX;
        this.endX = endX;
        invalidate();
    }



    @Override
    public IndicatorView setViewHeight(int viewHeight) {
        this.viewHeight = viewHeight;
        return this;
    }

    @Override
    public IndicatorView setViewWidth(int viewWidth) {
        this.viewWidth = viewWidth;
        return this;
    }
}
