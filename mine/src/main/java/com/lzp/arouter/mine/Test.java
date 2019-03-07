package com.lzp.arouter.mine;

/**
 * @author Li Xiaopeng
 * @date 2019/3/6
 */
public class Test {

    class AppView{

        private String id;
        private String name;

        public AppView(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    class MyMap extends AppView{

        public MyMap(String id, String name) {
            super(id, name);
        }
    }

}
