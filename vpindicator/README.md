# viewpagerIndicator

先看效果图

![图片](https://github.com/MoJieBlog/ARouterDemo/blob/master/vpindicator/video/QQ20190312-174647-HD.gif)

先说下为什么写这个，Android出了design库之后，基本上就告别ViewPagerIndicator了，但是因为我们有个严格的产品➕设计，所以好多时候用系统原来的样式并不能满足我们的需求。之前是用第三方，但是用来用去，总是有一些不能满足的地方，可扩展性不是很高。于是有了这篇文章，进入正题。

### 用法
目前只支持Scroll类型的，不支持平分屏幕类型。<br>
目前只支持Scroll类型的，不支持平分屏幕类型。<br>
目前只支持Scroll类型的，不支持平分屏幕类型。<br>

xml
```java
        <com.lzp.vpindicator.IndicatorLayout
            android:id="@+id/indicator2"
            android:layout_width="match_parent"
            android:layout_height="50dp">

        <android.support.v4.view.ViewPager
            android:id="@+id/vp_1"
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:layout_centerInParent="true"
            android:clipChildren="false" />
```

activity
```java

        vp1.setAdapter(mViewPagerAdapter);

        indicator.setUpWithViewPager(vp1);
        indicator.setLeftMargin(15);
        indicator.setRightMargin(15);

         for (int i = 0; i < mImages.length; i++) {
            //目前提供了两种tab,CircleTabView,TextTabView,其他可自定义
            indicator.createTab(i,
                    new CircleTabView(this)
                            .setText(String.valueOf(i + 1))
                            .setTabHeight(80)
                            .setTabWidth(80));
        }

        LineIndicatorView lineIndicatorView = new LineIndicatorView(this);
        lineIndicatorView.setLineColor(0xffaaff33);
        lineIndicatorView.setRadius(40f);
        indicator.setIndicatorView(lineIndicatorView.setIndicatorHeight(80).setIndicatorWidth(80), Gravity.CENTER);
```

### 原理
可以看出代码还是比较多的，最初我也打算用在xml里直接配置，当然可以在xml里配置，但是我觉得那样会使整个库显得太臃肿了。