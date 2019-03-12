package com.lzp.vpindicator;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Li Xiaopeng
 * @date 2019/3/4
 */
public abstract class IndicatorView extends View implements IIndicator{

    protected int indicatorHeight = 0,indicatorWidth = 0;//如果设置为0，认为自适应

    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public int getIndicatorHeight() {
        return indicatorHeight;
    }

    public int getIndicatorWidth() {
        return indicatorWidth;
    }
}
