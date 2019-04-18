package com.lzp.arouter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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


    Intent intent;
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
        initVp();

        LinkedHashMap<Integer,Integer> map = new LinkedHashMap<>(0, 0.2f, true);
        map.put(0,0);
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        map.put(4,4);
        map.put(5,5);
        map.put(6,6);


        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Log.e(TAG, "获取前"+iterator.next().getValue());
        }

        map.get(2);
        map.get(3);

        Set<Map.Entry<Integer, Integer>> entries1 = map.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iterator1 = entries1.iterator();
        while (iterator1.hasNext()){
            Log.e(TAG, "获取后"+iterator1.next().getValue());
        }

        btnToHome.setOnClickListener(this);
        btnToMine.setOnClickListener(this);
    }

    private void initVp() {




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
        switch (v.getId()) {
            case R.id.btnToHome:
                haveRoot();
                break;
            case R.id.btnToMine:
                ARouter.getInstance().build(Constance.ACTIVITY_PERSONAL).navigation();
                break;
        }
    }

    public static boolean haveRoot() {
        boolean mHaveRoot = false;
        if (!mHaveRoot) {
            int ret = execRootCmdSilent("echo test"); // 通过执行测试命令来检测
            if (ret != -1) {
                Log.i(TAG, "have root!");
                mHaveRoot = true;
            } else {
                Log.i(TAG, "not root!");
            }
        } else {
            Log.i(TAG, "mHaveRoot = true, have root!");
        }
        return mHaveRoot;
    }

    /**
     * 执行命令但不关注结果输出
     */
    public static int execRootCmdSilent(String cmd) {
        int result = -1;
        DataOutputStream dos = null;

        try {
            Process p = Runtime.getRuntime().exec("su");
            dos = new DataOutputStream(p.getOutputStream());

            Log.i(TAG, cmd);
            dos.writeBytes(cmd + "\n");
            dos.flush();
            dos.writeBytes("exit\n");
            dos.flush();
            p.waitFor();
            result = p.exitValue();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
