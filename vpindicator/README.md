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
如果只是想用，到这里就可以结束了（其实不往下看这个库一点价值都没有）。如果想自定义，请接着往下看。

### 原理
可以看出代码还是比较多的，最初我也打算用在xml里直接配置，但是想想好不容易将这个功能拆分为三个部分，为什么又要糅合到一起？更何况java中是一行代码,xml中不同样是一行代码吗？（为自己的懒找了个好理由）所以就没写。

说正事，我把整个View分为三部分，IndicatorLayout（放tab和指示器的容器）,TabView,IndicatorView（指示器，就是常见的底部的横线）。
#### IndicatorLayout,TabView,Indicator的关系
IndicatorLayout继承FrameLayout，里面放了两个HorizontalScrollView，一个用来装tabView，一个用来装IndicatorView，因为IndicatorView可能在顶部，可能底部或者中间，所以我把高度设置为MATCH_PARENT，然后设置IndicatorView时传进来Gravity就可以了，当然可以不传，因为默认是在底部的。然后就是常见的自定义View流程了。没啥可说的。重点是ViewPager切换时，横坐标的计算比较麻烦。

#### 扩展性
##### tabView
只需要继承TabView，实现以下抽象方法
```java
    public abstract TabView setTabHeight(int tabHeight);
    public abstract TabView setTabWidth(int tabWidth);
    /**
     * 选中状态
     */
    public abstract void selected();

    /**
     * 未选中状态
     */
    public abstract void unselected();
```

这里举个栗子，相信大家的聪明才智一看就懂了
```java
public class TextTabView extends TabView {

    /**
     * 文字颜色
     */
    private int textSelectedColor = 0xffff0000;
    private int textUnSelectedColor = 0xff000000;

    /**
     * 文字大小
     */
    private int textSize = 15;

    private TextView textView;

    public TextTabView(Context context) {
        this(context, null);
    }

    public TextTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        textView = new TextView(context);
        textView.setTextSize(textSize);
        textView.setTextColor(textUnSelectedColor);
        textView.setGravity(Gravity.CENTER);
        addView(textView);
    }

    public TextTabView setText(String text) {
        if (textView != null) {
            textView.setText(text);
        }
        return this;
    }

    @Override
    public TabView setTabHeight(int tabHeight) {
        this.tabHeight = tabHeight;
        return this;
    }

    @Override
    public TabView setTabWidth(int tabWidth) {
        this.tabWidth = tabWidth;
        return this;
    }

    @Override
    public void selected() {
        selected = true;
        textView.setTextColor(textSelectedColor);
    }

    @Override
    public void unselected() {
        selected = false;
        textView.setTextColor(textUnSelectedColor);
    }

/***********************以下算是额外方法了，可根据需要看自己是否需要写***************************/
    public void setTextSelectedColor(int textSelectedColor) {
        this.textSelectedColor = textSelectedColor;
        textView.setTextColor(textSelectedColor);
    }

    public void setTextUnSelectedColor(int textUnSelectedColor) {
        this.textUnSelectedColor = textUnSelectedColor;
        textView.setTextColor(textUnSelectedColor);
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        textView.setTextSize(textSize);
    }
}
```
##### indicator
同样很简单，继承IndicatorView，会实现以下方法
```java

    /**
     * @param startX 当前的开始坐标
     * @param toX    当前的目标坐标
     * @param offset 偏移量
     */
    void onPageScrolled(float positionOffset, float toX, ,float positionOffset);

    IndicatorView setIndicatorHeight(int viewHeight);
    IndicatorView setIndicatorWidth(int viewWidth);
```
然后就是重写onDraw，画出你想要的形状了。

#### 结束语
第一次这么严肃的写博客，求轻喷。

