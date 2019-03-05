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

    protected int viewHeight = 0,viewWidth = 0;//如果设置为0，怎认为自适应

    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public int getViewHeight() {
        return viewHeight;
    }

    public int getViewWidth() {
        return viewWidth;
    }
}
