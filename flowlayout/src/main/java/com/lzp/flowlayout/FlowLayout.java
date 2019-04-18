package com.lzp.flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import come.lzp.utils.LogUtils;

/**
 * @author Li Xiaopeng
 * @date 2019/3/19
 */
public class FlowLayout extends ViewGroup {

    private static final String TAG = "FlowLayout";

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);

        int lineWidth = 0;
        int lineHeight = 0;

        int height = 0;
        int width = 0;

        int childCount = getChildCount();
        for (int childIndex = 0; childIndex < childCount; childIndex++) {
            View childAt = getChildAt(childIndex);
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams layoutParams = (MarginLayoutParams) childAt.getLayoutParams();
            int leftMargin = layoutParams.leftMargin;
            int rightMargin = layoutParams.rightMargin;
            int topMargin = layoutParams.topMargin;
            int bottomMargin = layoutParams.bottomMargin;

            int childWidth = childAt.getMeasuredWidth() + leftMargin + rightMargin;
            int childHeight = childAt.getMeasuredHeight() + topMargin + bottomMargin;

            if ((lineWidth + childWidth) > measureWidth) {
               // LogUtils.logE(TAG, "onMeasure: 换行了" + childIndex);
                //需要换行
                //总宽 = 单行宽度最大值
                width = Math.max(lineWidth, width);
                //总高度 = 所有行的总高度
                height += lineHeight;
                //行高 = 当前行第一个子view的高度
                lineHeight = childHeight;
                //行宽 = 当前行第一个子view的宽度
                lineWidth = childWidth;
            } else {
                //不需要换行
                //行高 = 当前行子View的最大高度
                lineHeight = Math.max(lineHeight, childHeight);
                //宽度 = 当前行子view宽度的总和
                lineWidth += childWidth;
            }

            //最后一行不需要换行，所以要单独处理
            if (childIndex == childCount - 1) {
                height += lineHeight;
                width = Math.max(width, lineWidth);
            }

        }

        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? measureWidth
                : width, (heightMode == MeasureSpec.EXACTLY) ? measureHeight
                : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int lineWidth = 0;
        int lineHeight = 0;
        int top = 0, left = 0;

        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) childAt.getLayoutParams();
            int leftMargin = layoutParams.leftMargin;
            int rightMargin = layoutParams.rightMargin;
            int topMargin = layoutParams.topMargin;
            int bottomMargin = layoutParams.bottomMargin;

            int childWidth = childAt.getMeasuredWidth() + leftMargin + rightMargin;
            int childHeight = childAt.getMeasuredHeight() + topMargin + bottomMargin;

            if (childWidth + lineWidth > getMeasuredWidth()) {
                //如果换行,
                //view顶部 = 上个View的底部
                top += lineHeight;
                left = 0;
                //行高 = 当前行第一个子view的高度
                lineHeight = childHeight;
                //行宽 = 当前行第一个子view的宽度
                lineWidth = childWidth;
            } else {
                //不需要换行
                //行高 = 当前行子View的最大高度
                lineHeight = Math.max(lineHeight, childHeight);
                //宽度 = 当前行子view宽度的总和
                lineWidth += childWidth;
            }

            int lc = left + layoutParams.leftMargin;
            int tc = top + layoutParams.topMargin;
            int rc = lc + childAt.getMeasuredWidth();
            int bc = tc + childAt.getMeasuredHeight();
            childAt.layout(lc, tc, rc, bc);
            //左侧 = 当前子View的宽度+之前的左侧位置
            left += childWidth;
        }
    }
}
