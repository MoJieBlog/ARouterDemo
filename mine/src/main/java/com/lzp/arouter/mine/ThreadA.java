package com.lzp.arouter.mine;

import java.io.PipedOutputStream; /**
 * @author Li Xiaopeng
 * @date 2019/3/13
 */
public class ThreadA  extends Thread {

    PipedOutputStream pos;

    MyObject object;
    public ThreadA(PipedOutputStream pos,MyObject myObject) {
        this.pos = pos;
        this.object = myObject;
    }

    //省略构造方法
    @Override
    public void run() {
        super.run();
        for (int i=0;i<10;i++){
            object.methodA();
        }
    }
}
