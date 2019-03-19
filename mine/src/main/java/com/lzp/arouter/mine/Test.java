package com.lzp.arouter.mine;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author Li Xiaopeng
 * @date 2019/3/6
 */
public class Test {
    public static void main(String[] args) {
        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream();

        try {
            pos.connect(pis);
            MyObject myObject = new MyObject();
            ThreadA threadA = new ThreadA(pos,myObject);
            ThreadB threadB = new ThreadB(pis,myObject);
            threadA.start();
            threadB.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
