package com.lzp.vpindicator.indicatorView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
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
        indicatorWidth = UiUtils.dip2px(getContext(), 10);
        indicatorHeight = UiUtils.dip2px(getContext(), 3);
        radius = UiUtils.dip2px(getContext(), 1.5f);
        paint = new Paint();
        paint.setAntiAlias(true);

        rectF = new RectF();

        endX = startX + indicatorWidth;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(FrameLayout.LayoutParams.MATCH_PARENT, indicatorWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rectF.set(startX, getMeasuredHeight()-indicatorHeight, endX, getMeasuredHeight());
        paint.setColor(lineColor);
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
    public IndicatorView setIndicatorHeight(int viewHeight) {
        this.indicatorHeight = viewHeight;
        return this;
    }

    @Override
    public IndicatorView setIndicatorWidth(int viewWidth) {
        this.indicatorWidth = viewWidth;
        return this;
    }

    public LineIndicatorView setRadius(float radius) {
        this.radius = radius;
        return this;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }
}
