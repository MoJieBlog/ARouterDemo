package com.lzp.vpindicator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

/**
 * @author Li Xiaopeng
 * @date 2019/3/5
 */
public class MHorizontalScrollView extends HorizontalScrollView{

    private OnScrollChangeListener listener;
    public MHorizontalScrollView(Context context) {
        super(context);
    }

    public MHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (this.listener!=null){
            listener.onScrollChange(this,l,t,oldl,oldt);
        }
    }

    public void setOnScrollChangeListener(OnScrollChangeListener scrollViewListener) {
        this.listener = scrollViewListener;
    }

    public interface OnScrollChangeListener{
        void onScrollChange(View view, int x, int y,int oldX,int oldY);
    }

}
