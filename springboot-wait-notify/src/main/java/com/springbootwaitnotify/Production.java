package com.springbootwaitnotify;

import java.util.Vector;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @date 2019/3/7 17:45
 */
public class Production implements Runnable {

    private int size;

    private Vector vector;

    private int num;

    public Production(int size, Vector vector, int num) {
        this.num = num;
        this.size = size;
        this.vector = vector;
    }

    @Override
    public void run() {
        for (int i = 0; i < num; i++) {
            synchronized (vector) {
                try {
                    if (size == vector.size()) {
                        System.err.println("数据满了、数据加入等待中...");
                        vector.wait();
                    }
                    vector.add(i);
                    vector.notifyAll();
                    System.err.println("队列中加入新值：" + i + "\t队列当前大小：" + vector.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
