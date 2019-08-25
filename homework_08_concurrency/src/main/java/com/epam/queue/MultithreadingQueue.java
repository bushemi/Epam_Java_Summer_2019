package com.epam.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MultithreadingQueue implements Queue {
    private Logger logger = LoggerFactory.getLogger("MultithreadingQueue");
    private volatile String value;
    private boolean valueSet = false;

    private final ReentrantLock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public MultithreadingQueue() {
    }

    @Override
    public void put(String element) {
        lock.lock();
        while (valueSet) {
            try {
                condition.await();
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
        value = element;
        valueSet = true;
        logger.info("put {} by {}", element, Thread.currentThread());
        condition.signal();
        lock.unlock();
    }

    @Override
    public String get() {
        lock.lock();
        while (!valueSet) {
            try {
                condition.await();
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
        String result = value;
        value = null;
        valueSet = false;
        logger.info("consumed {} by {}", result, Thread.currentThread());
        condition.signal();
        lock.unlock();
        return result;
    }


}
