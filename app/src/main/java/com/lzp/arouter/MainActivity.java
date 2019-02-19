package com.lzp.arouter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lzp.arouter.common.Constance;

@Route(path = Constance.ACTIVITY_MAIN)
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Autowired(name = "text")
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ARouter.getInstance().inject(this);

        //ARouter.getInstance().build(Constance.ACTIVITY_HOME).navigation();

        //携带参数
       // ARouter.getInstance().build(Constance.ACTIVITY_HOME).withString("test","test").navigation();
        //带返回。startActivityForResult
      /* ARouter.getInstance().build(Constance.ACTIVITY_HOME)
               //.withTransition(enterAnim,exitAnim) 动画

               .navigation(this,100);*/

        ARouter.getInstance().build(Constance.ACTIVITY_HOME).navigation(this, new NavCallback() {
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
        });
    }
}
