package com.springbootwaitnotify;

import java.util.Vector;

public class Consume implements Runnable {

    private int size;

    private Vector vector;

    private long time;

    private int num;

    public Consume(int size, Vector vector, long time, int num) {
        this.num = num;
        this.time = time;
        this.size = size;
        this.vector = vector;
    }

    public void xf() throws InterruptedException {
        synchronized (vector) {
            if (vector.isEmpty()) {
                System.err.println("队列没有数据、进入等待中...");
                vector.wait();
            }
            vector.notifyAll();
            System.err.println(Thread.currentThread().getName() + "消费数据：" + vector.remove(0) + "\t还剩下：" + vector);
            Queue.s = Queue.s + 1;
            Thread.sleep(50);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (Queue.s == num) {
                    System.err.println("消费者关闭");
                    System.err.println("消耗时间：" + (System.currentTimeMillis() - time));
                    break;
                }
                xf();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
