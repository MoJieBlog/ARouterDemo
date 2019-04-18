package com.lzp.arouter.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lzp.arouter.common.BaseActivity;
import com.lzp.arouter.common.Constance;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Li Xiaopeng
 * @date 2019/2/19
 */
@Route(path = Constance.ACTIVITY_PERSONAL)
public class PersonalActivity extends BaseActivity {

    //@BindView(R.id.rcv)
    //RecyclerView rcv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);

    }
}
