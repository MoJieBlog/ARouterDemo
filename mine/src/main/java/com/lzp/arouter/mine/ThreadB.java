package com.lzp.arouter.mine;

import java.io.PipedInputStream;

/**
 * @author Li Xiaopeng
 * @date 2019/3/13
 */

public class ThreadB extends Thread {

    private PipedInputStream pis;
    MyObject object;

    public ThreadB(PipedInputStream pis, MyObject myObject) {
        this.pis = pis;
        this.object = myObject;
    }

    @Override
    public void run() {
        super.run();
        for (int i=0;i<10;i++){
            object.methodB();
        }

    }
}
