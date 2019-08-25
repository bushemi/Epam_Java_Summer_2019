package com.epam.deadlocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.deadlocks.DeadLockExample.MONITOR_1;
import static com.epam.deadlocks.DeadLockExample.MONITOR_2;

public class DeadLockedThreadOne implements Runnable {

private Logger logger = LoggerFactory.getLogger("DeadLockedThreadOne");

    @Override
    public void run() {
        synchronized (MONITOR_1) {
            logger.info("first thread is an owner of monitor 1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
            synchronized (MONITOR_2){
                logger.info("first thread is an owner of monitor 2");
            }
        }
    }
}