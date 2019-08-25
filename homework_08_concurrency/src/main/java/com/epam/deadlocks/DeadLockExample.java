package com.epam.deadlocks;

public class DeadLockExample {
    public static final Object MONITOR_1 = new Object();
    public static final Object MONITOR_2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new DeadLockedThreadOne());
        Thread thread2 = new Thread(new DeadLockedThreadTwo());

        thread1.start();
        thread2.start();
    }
}
