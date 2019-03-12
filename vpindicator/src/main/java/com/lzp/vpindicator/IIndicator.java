package com.lzp.vpindicator;

/**
 * @author Li Xiaopeng
 * @date 2019/3/4
 */
public interface IIndicator {

    /**
     * @param startX 当前的开始坐标
     * @param toX    当前的目标坐标
     * @param offset 偏移量
     */
    void onPageScrolled(float startX, float toX, float offset);

    IndicatorView setIndicatorHeight(int viewHeight);

    IndicatorView setIndicatorWidth(int viewWidth);

}
