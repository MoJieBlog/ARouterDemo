package com.lzp.arouter;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lzp.arouter.common.Constance;
import com.lzp.vpindicator.IndicatorLayout;
import com.lzp.vpindicator.indicatorView.LineIndicatorView;
import com.lzp.vpindicator.tabView.CircleTabView;
import com.lzp.vpindicator.tabView.TextTabView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = Constance.ACTIVITY_MAIN)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    @Autowired(name = "text")
    String text;

    @BindView(R.id.vp_1)
    ViewPager vp1;
    @BindView(R.id.indicator)
    IndicatorLayout indicator;
    @BindView(R.id.indicator2)
    IndicatorLayout indicator2;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.btnToMine)
    Button btnToMine;
    @BindView(R.id.btnToHome)
    Button btnToHome;


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
        EventBus.getDefault().register(this);
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

        indicator2.setUpWithViewPager(vp1);
        indicator2.setLeftMargin(15);
        indicator2.setRightMargin(15);


        for (int i = 0; i < mImages.length; i++) {
            indicator.createTab(i,
                    new CircleTabView(this)
                            .setText(String.valueOf(i + 1))
                            .setTabHeight(80)
                            .setTabWidth(80));

            TextTabView tabView = (TextTabView) new TextTabView(this)
                    .setText(String.valueOf(i + 1))
                    .setTabHeight(80);
            tabView.setTextSelectedColor(0xff0000ff);
            tabView.setTextUnSelectedColor(0xff666666);
            tabView.setTextSize(18);
            indicator2.createTab(i,
                    tabView);
        }

        LineIndicatorView lineIndicatorView = new LineIndicatorView(this);
        lineIndicatorView.setLineColor(0xffaaff33);
        lineIndicatorView.setRadius(40f);
        indicator.setIndicatorView(lineIndicatorView.setIndicatorHeight(80).setIndicatorWidth(80), Gravity.CENTER);

        LineIndicatorView lineIndicatorView2 = new LineIndicatorView(this);
        lineIndicatorView2.setRadius(5f);
        indicator2.setIndicatorView(lineIndicatorView2.setIndicatorHeight(10).setIndicatorWidth(80), Gravity.BOTTOM);

        ArrayList list0 = new ArrayList();

        ArrayList list1 = new ArrayList();
        for (int i = 0; i < 3; i++) {
            list1.add(i);
        }

        ArrayList list2 = new ArrayList();
        for (int i = 0; i < 11; i++) {
            list2.add(i);
        }

        btnToHome.setOnClickListener(this);
        btnToMine.setOnClickListener(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void setTvTest(String test) {
        tv.setText(test);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnToHome:
                ARouter.getInstance().build(Constance.ACTIVITY_HOME).navigation();
                break;
            case R.id.btnToMine:
                ARouter.getInstance().build(Constance.ACTIVITY_PERSONAL).navigation();
                break;
        }
    }
}
