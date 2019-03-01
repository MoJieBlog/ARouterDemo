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

        //ARouter.getInstance().build(Constance.ACTIVITY_HOME).navigation();

        //携带参数
        // ARouter.getInstance().build(Constance.ACTIVITY_HOME).withString("test","test").navigation();
        //带返回。startActivityForResult
      /* ARouter.getInstance().build(Constance.ACTIVITY_HOME)
               //.withTransition(enterAnim,exitAnim) 动画

               .navigation(this,100);*/

       /* ARouter.getInstance().build(Constance.ACTIVITY_HOME).navigation(this, new NavCallback() {
            @Override
            public void onArrival(Postcard postcard) {
                Log.e(TAG, "onArrival: ");
            }

            @Override
            public void onFound(Postcard postcard) {
                super.onFound(postcard);
                Log.e(TAG, "onFound: ");
            }

            @Override
            public void onInterrupt(Postcard postcard) {
                super.onInterrupt(postcard);
                Log.e(TAG, "onInterrupt: ");
            }

            @Override
            public void onLost(Postcard postcard) {
                super.onLost(postcard);
                Log.e(TAG, "onLost: ");
            }
        });*/
    }
}
