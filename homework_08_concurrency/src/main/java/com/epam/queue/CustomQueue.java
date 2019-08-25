package com.epam.queue;

public class CustomQueue {

    public static void main(String[] args) throws InterruptedException {
        MyQueue queue = new MyQueue();
        Consumer consumer = new Consumer(queue);
        Producer producer = new Producer(queue);
        producer.start();
        consumer.start();
        Thread.sleep(500);
        consumer.interrupt();
    }

}

class MyQueue {
    private int value;
    private boolean valueSet = false;

    public synchronized int get() {
        while (!valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Get interruption was handled");
                Thread.currentThread().interrupt();
                return 0;
            }
        }
        System.out.println("Received: " + value);
        valueSet = false;
        notify();
        return value;
    }

    public synchronized void put(int value) {
        while (valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Put interruption was handled");
                return;
            }
        }
        this.value = value;
        valueSet = true;
        System.out.println("Sent: " + value);
        notify();
    }
}

class Producer extends Thread {
    private MyQueue queue;

    public Producer(MyQueue queue) {
        this.queue = queue;
        this.setName("Producer");
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            queue.put(i);
        }
    }
}

class Consumer extends Thread {
    private MyQueue queue;

    public Consumer(MyQueue queue) {
        this.queue = queue;
        this.setName("Consumer");
    }

    @Override
    public void run() {
        while (true) {
            queue.get();
            if (isInterrupted()) {
                return;
            }
        }
    }
}
