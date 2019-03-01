package com.lzp.vpindicator;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * @author Li Xiaopeng
 * @date 2019/3/1
 */
public abstract class TabView extends FrameLayout implements ITab {

    protected Context context;
    protected Resources resources;

    protected int tabHeight = 0,tabWidth = 0;//如果设置为0，怎认为自适应

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.resources = context.getResources();
    }

    public int getTabHeight() {
        return tabWidth;
    }

    public int getTabWidth() {
        return tabHeight;
    }
}
