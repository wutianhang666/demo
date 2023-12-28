package com.icss.etc.test;

public class SynchronizedTest implements Runnable{
    static int count;

    public synchronized void add() {
        count++;
        System.out.println(Thread.currentThread().getName() + "count = " + count);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            add();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest syncDemo = new SynchronizedTest();

        Thread t1 = new Thread(syncDemo);
        Thread t2 = new Thread(syncDemo);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("---------#######-----------" + count);
    }
}
