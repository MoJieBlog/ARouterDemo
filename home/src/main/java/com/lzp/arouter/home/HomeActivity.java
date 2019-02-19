package com.lzp.arouter.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lzp.arouter.common.BaseActivity;
import com.lzp.arouter.common.Constance;

/**
 * @author Li Xiaopeng
 * @date 2019/2/19
 */

@Route(path = Constance.ACTIVITY_HOME)
public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ARouter.getInstance().inject(this);
    }
}
