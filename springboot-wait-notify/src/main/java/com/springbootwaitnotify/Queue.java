package com.springbootwaitnotify;

import java.util.Vector;

public class Queue {

    private static Vector queue = new Vector();

    public static int s = 0;

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        int num = 20, size = 10;
        Thread xf1 = new Thread(new Consume(size, queue, time, num), "消费者1号");
        Thread scz = new Thread(new Production(size, queue, num), "scz");
        xf1.start();
        scz.start();
    }
}
