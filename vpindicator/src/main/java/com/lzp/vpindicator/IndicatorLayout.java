package com.lzp.vpindicator;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import come.lzp.utils.UiUtils;

/**
 * @author Li Xiaopeng
 * @date 2019/3/1
 */
public class IndicatorLayout extends FrameLayout implements ViewPager.OnPageChangeListener {

    private static final String TAG = "ViewpagerIndicator";

    private Context context;
    private Resources resources;

    /**
     * 当前选中的position
     */
    private int currentPosition = 0;

    private ViewPager viewPager;

    private MHorizontalScrollView tabScrollView;
    private LinearLayout tabContainer;//装载tab的容器

    private MHorizontalScrollView indicatorScrollView;
    private LinearLayout indicatorContainer;

    private int leftMargin = 0;
    private int rightMargin = 0;

    private IndicatorView indicatorView;

    public IndicatorLayout(Context context) {
        this(context, null);
    }

    public IndicatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.resources = context.getResources();
        init();
    }

    private void init() {
        createTabContainer();
        tabScrollView.setOnScrollChangeListener(new MHorizontalScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (indicatorScrollView != null && indicatorView != null) {
                    indicatorScrollView.scrollTo(scrollX, scrollY);
                }
            }
        });
    }

    private void createIndicatorContainer() {
        indicatorScrollView = new MHorizontalScrollView(context);
        indicatorScrollView.setHorizontalScrollBarEnabled(false);
        ViewGroup.LayoutParams scrollViewparams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        indicatorScrollView.setLayoutParams(scrollViewparams);
        indicatorContainer = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        indicatorContainer.setLayoutParams(layoutParams);
        indicatorScrollView.addView(indicatorContainer);
        addView(indicatorScrollView, 0);
    }

    private void createTabContainer() {
        tabScrollView = new MHorizontalScrollView(context);
        ViewGroup.LayoutParams scrollViewparams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        tabScrollView.setLayoutParams(scrollViewparams);
        tabScrollView.setHorizontalScrollBarEnabled(false);
        tabContainer = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        tabContainer.setLayoutParams(layoutParams);
        tabScrollView.addView(tabContainer);
        addView(tabScrollView);
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
        scrollToCenter(position, positionOffset);
        if (indicatorView != null) {
            updateIndicator(position, positionOffset);
        }
    }

    private void updateIndicator(int position, float positionOffset) {
        if (indicatorContainer != null && tabContainer != null) {
            View positionView = tabContainer.getChildAt(position);
            int positionLeft = positionView.getLeft();
            int positionViewWidth = positionView.getWidth();
            View afterView = tabContainer.getChildAt(position + 1);
            int afterViewWith = 0;
            if (afterView != null) {
                afterViewWith = afterView.getWidth();
            }
            int viewWidth = indicatorView.getIndicatorWidth();
            if (positionOffset <= 0.5) {
                float startX = positionLeft
                        + (positionViewWidth - viewWidth) / 2;
                float endX = startX
                        + viewWidth
                        + ((positionViewWidth + afterViewWith) / 2 + rightMargin + leftMargin) * (positionOffset) * 2;
                indicatorView.onPageScrolled(startX, endX,positionOffset);
            } else {
                float startX = positionLeft
                        + (positionViewWidth - viewWidth) / 2
                        + (positionViewWidth + rightMargin + leftMargin) * (positionOffset - 0.5f) * 2;
                float endX = positionLeft +
                        (positionViewWidth + viewWidth) / 2 +
                        ((positionViewWidth + afterViewWith) / 2 + rightMargin + leftMargin);
                indicatorView.onPageScrolled(startX, endX,positionOffset);
            }
        }
    }

    /**
     * 滚动到中间
     *
     * @param position       当前显示的靠左侧的item的position
     * @param positionOffset 当前显示的靠左侧的item的偏移量
     */
    private void scrollToCenter(int position, float positionOffset) {
        if (position < tabContainer.getChildCount()) {
            View positionView = tabContainer.getChildAt(position);
            View afterView = tabContainer.getChildAt(position + 1);
            int positionViewRight = positionView.getRight();
            int positionViewWidth = positionView.getWidth();

            int afterViewWidth = afterView == null ? 0 : afterView.getWidth();
            int winWide = UiUtils.getWinWide(context);

            int offsetStart = positionViewRight - positionViewWidth / 2 - winWide / 2;
            int scrollX = (int) ((afterViewWidth / 2 + positionViewWidth / 2 + leftMargin + rightMargin) * positionOffset) + offsetStart;

            tabScrollView.scrollTo(scrollX, 0);

        }
    }

    @Override
    public void onPageSelected(int position) {
        if (currentPosition == position) {
            return;
        }
        int childCount = tabContainer.getChildCount();
        View currentTab = tabContainer.getChildAt(currentPosition);
        if (childCount > position) {
            View childAt = tabContainer.getChildAt(position);
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
        tabContainer.addView(tab, pos);
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
        layoutParams.gravity = Gravity.CENTER;
        tab.setLayoutParams(layoutParams);
    }

    public void setIndicatorView(IndicatorView indicatorView) {
        this.indicatorView = indicatorView;
        createIndicatorContainer();
        indicatorContainer.addView(indicatorView);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) indicatorView.getLayoutParams();
        layoutParams.gravity = Gravity.BOTTOM;
    }

    public void setIndicatorView(IndicatorView indicatorView, int gravity) {
        this.indicatorView = indicatorView;
        createIndicatorContainer();
        indicatorContainer.addView(indicatorView);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) indicatorView.getLayoutParams();
        layoutParams.gravity = gravity;
    }
}
