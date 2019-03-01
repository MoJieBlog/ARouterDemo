package com.lzp.vpindicator;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import come.lzp.utils.UiUtils;

/**
 * @author Li Xiaopeng
 * @date 2019/3/1
 */
public class ViewpagerIndicator extends HorizontalScrollView implements ViewPager.OnPageChangeListener {

    private static final String TAG = "ViewpagerIndicator";
    /**
     * 滚动模式
     */
    public static final int INDICATOR_MODE_SCROLL = 0;

    /**
     * 均分模式
     */
    public static final int INDICATOR_MODE_FIX = 1;

    private Context context;
    private Resources resources;

    private int indicatorMode = INDICATOR_MODE_SCROLL;//默认滚动模式

    /**
     * 当前选中的position
     */
    private int currentPosition = 0;

    private LinearLayout parentView;//装载tab的容器
    private ViewPager viewPager;

    private int leftMargin = 0;
    private int rightMargin = 0;

    public ViewpagerIndicator(Context context) {
        this(context, null);
    }

    public ViewpagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.resources = context.getResources();
        init();
    }

    private void init() {
        setOverScrollMode(OVER_SCROLL_NEVER);
        setScrollbarFadingEnabled(true);
        setHorizontalScrollBarEnabled(false);
        createParentView();
    }

    private void createParentView() {
        parentView = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        parentView.setLayoutParams(params);
        parentView.setGravity(Gravity.CENTER);
        parentView.setOrientation(LinearLayout.HORIZONTAL);
        addView(parentView);
    }

    public void setUpWithViewPager(ViewPager viewPager) {
        if (viewPager == null || viewPager.getAdapter() == null) {
            throw new RuntimeException("viewpager or pagerAdapter is null.");
        }
        this.viewPager = viewPager;
        this.viewPager.addOnPageChangeListener(this);
        this.currentPosition = this.viewPager.getCurrentItem();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (indicatorMode == INDICATOR_MODE_SCROLL) {
            scrollToCenter(position, positionOffset);
        }
    }

    /**
     * 滚动到中间
     *
     * @param position
     * @param positionOffset
     */
    private void scrollToCenter(int position, float positionOffset) {
        if (position < parentView.getChildCount()) {
            View positionView = parentView.getChildAt(position);
            View afterView = parentView.getChildAt(position + 1);
            int positionViewRight = positionView.getRight();
            int positionViewWidth = positionView.getWidth();

            int afterViewWidth = afterView == null ? 0 : afterView.getWidth();
            int winWide = UiUtils.getWinWide(context);

            int offsetStart = positionViewRight - positionViewWidth / 2 - winWide / 2;
            int scrollX = (int) ((afterViewWidth / 2 + positionViewWidth / 2 + leftMargin + rightMargin) * positionOffset) + offsetStart;

            scrollTo(scrollX, 0);

        }
    }

    @Override
    public void onPageSelected(int position) {
        if (currentPosition == position) {
            return;
        }
        int childCount = parentView.getChildCount();
        View currentTab = parentView.getChildAt(currentPosition);
        if (childCount > position) {
            View childAt = parentView.getChildAt(position);
            if (childAt instanceof TabView && currentTab instanceof TabView) {
                ((TabView) currentTab).unselected();
                ((TabView) childAt).selected();
                this.currentPosition = position;
            } else {
                throw new RuntimeException("this tab is not a tabView.");
            }
        } else {
            throw new RuntimeException("tabs count is less than pages.");
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public void createTab(final int pos, @NonNull TabView tab) {
        setTabLayoutParams(tab);
        parentView.addView(tab, pos);
        tab.setSelected(pos == currentPosition);
        tab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager != null) {
                    viewPager.setCurrentItem(pos, true);
                }
            }
        });
    }

    public void setLeftMargin(int leftMargin) {
        this.leftMargin = leftMargin;
    }

    public void setRightMargin(int rightMargin) {
        this.rightMargin = rightMargin;
    }

    private void setTabLayoutParams(@NonNull TabView tab) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                tab.getTabWidth() == 0 ? LinearLayout.LayoutParams.WRAP_CONTENT : tab.getTabWidth(),
                tab.getTabHeight() == 0 ? LinearLayout.LayoutParams.WRAP_CONTENT : tab.getTabHeight());
        layoutParams.rightMargin = rightMargin;
        layoutParams.leftMargin = leftMargin;
        tab.setLayoutParams(layoutParams);
    }

}
