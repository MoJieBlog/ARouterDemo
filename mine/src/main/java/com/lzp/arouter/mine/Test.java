package com.lzp.arouter.mine;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author Li Xiaopeng
 * @date 2019/3/6
 */
public class Test {

    class A {

        public A(String a) throws RuntimeException {

        }
    }

    class B {
        void doTest(){
            A a = new A("");
        }
    }
}
