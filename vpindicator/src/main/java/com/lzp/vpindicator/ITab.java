package com.lzp.vpindicator;

/**
 * 指示器tab接口
 * tab：肯定有两个状态
 * 选中状态
 * 未选中状态
 *
 * @author Li Xiaopeng
 * @date 2019/3/1
 */
public interface ITab {

    TabView setTabHeight(int tabHeight);
    TabView setTabWidth(int tabWidth);
    /**
     * 选中状态
     */
    void selected();

    /**
     * 未选中状态
     */
    void unselected();

    /**
     * 设置选中与否
     * @param selected
     */
    void setSelected(boolean selected);
}
