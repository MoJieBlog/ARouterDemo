package com.lzp.vpindicator;

/**
 *
 * @author Li Xiaopeng
 * @date 2019/3/4
 */
public interface IIndicator {

    void onPageScrolled(float positionOffset, float toX);

    IndicatorView setIndicatorHeight(int viewHeight);
    IndicatorView setIndicatorWidth(int viewWidth);

}
