package com.lzp.arouter;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lzp.arouter.common.Constance;
import com.lzp.vpindicator.ViewpagerIndicator;
import com.lzp.vpindicator.indicatorView.LineIndicatorView;
import com.lzp.vpindicator.tabView.CircleTabView;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = Constance.ACTIVITY_MAIN)
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Autowired(name = "text")
    String text;

    @BindView(R.id.vp_1)
    ViewPager vp1;
    @BindView(R.id.indicator)
    ViewpagerIndicator indicator;


    private ViewPagerAdapter mViewPagerAdapter;
    private int[] mImages = {R.mipmap.yinsuwan_1,
            R.mipmap.yinsuwan_2, R.mipmap.yinsuwan_3,
            R.mipmap.yinsuwan_4, R.mipmap.yinsuwan_5,
            R.mipmap.yinsuwan_1,
            R.mipmap.yinsuwan_2, R.mipmap.yinsuwan_3,
            R.mipmap.yinsuwan_4, R.mipmap.yinsuwan_5,
            R.mipmap.yinsuwan_1,
            R.mipmap.yinsuwan_2, R.mipmap.yinsuwan_3,
            R.mipmap.yinsuwan_4, R.mipmap.yinsuwan_5,
            R.mipmap.yinsuwan_1,
            R.mipmap.yinsuwan_2, R.mipmap.yinsuwan_3,
            R.mipmap.yinsuwan_4, R.mipmap.yinsuwan_5};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        init();
    }

    private void init() {
        mViewPagerAdapter = new ViewPagerAdapter(this, mImages);
        vp1.setOffscreenPageLimit(3);
        vp1.setAdapter(mViewPagerAdapter);
        indicator.setUpWithViewPager(vp1);
        indicator.setLeftMargin(15);
        indicator.setRightMargin(15);

        for (int i = 0; i < mImages.length; i++) {
            indicator.createTab(i,
                    new CircleTabView(this)
                            .setText(String.valueOf(i + 1))
                            .setTabHeight(80)
                            .setTabWidth(80));
        }

        indicator.setIndicatorView(new LineIndicatorView(this),Gravity.CENTER);
    }
}
