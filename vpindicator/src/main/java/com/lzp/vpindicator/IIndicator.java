package com.lzp.vpindicator;

import android.support.v4.view.ViewPager;

/**
 * 和{@link ViewPager.OnPageChangeListener}一样，如果需要扩展，可以继续添加
 *
 * @author Li Xiaopeng
 * @date 2019/3/4
 */
public interface IIndicator {

    void onPageScrolled(float positionOffset, float toX);

    IndicatorView setIndicatorHeight(int viewHeight);
    IndicatorView setIndicatorWidth(int viewWidth);

}
