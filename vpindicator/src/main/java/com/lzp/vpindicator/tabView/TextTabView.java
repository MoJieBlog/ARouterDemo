package com.lzp.vpindicator.tabView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.lzp.vpindicator.TabView;

/**
 * @author Li Xiaopeng
 * @date 2019/3/1
 */
public class TextTabView extends TabView{

    /**
     * 文字颜色
     */
    private int textSelectedColor = 0xffff0000;
    private int textUnSelectedColor = 0xff000000;

    /**
     * 文字大小
     */
    private int textSize = 15;

    private TextView textView;

    public TextTabView(Context context) {
        this(context,null);
    }

    public TextTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        textView = new TextView(context);
        textView.setTextSize(textSize);
        textView.setTextColor(textUnSelectedColor);
        textView.setGravity(Gravity.CENTER);
        addView(textView);
    }

    public TextTabView setText(String text) {
        if (textView != null) {
            textView.setText(text);
        }
        return this;
    }

    @Override
    public TabView setTabHeight(int tabHeight) {
        this.tabHeight = tabHeight;
        return this;
    }

    @Override
    public TabView setTabWidth(int tabWidth) {
        this.tabWidth = tabWidth;
        return this;
    }

    @Override
    public void selected() {
        selected = true;
        textView.setTextColor(textSelectedColor);
    }

    @Override
    public void unselected() {
        selected = false;
        textView.setTextColor(textUnSelectedColor);
    }
}
