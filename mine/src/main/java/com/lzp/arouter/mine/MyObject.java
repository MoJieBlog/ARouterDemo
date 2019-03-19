package com.lzp.arouter.mine;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Li Xiaopeng
 * @date 2019/3/13
 */
public class MyObject {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int i = 0;

    private boolean startB = false;

    public void methodA() {
        lock.lock();
        i++;
        System.out.println("执行a" + i);
        if (i >= 5) {
            startB = true;
        }
        lock.unlock();
    }

    public void methodB() {
        try {
            lock.lock();
            if (!startB) {
                condition.await();
            }else{
                condition.signal();
                i++;
                System.out.println("执行b" + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
