package com.epam.deadlocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.deadlocks.DeadLockExample.MONITOR_1;
import static com.epam.deadlocks.DeadLockExample.MONITOR_2;

public class DeadLockedThreadTwo implements Runnable {

    private Logger logger = LoggerFactory.getLogger("DeadLockedThreadTwo");

    @Override
    public void run() {
        synchronized (MONITOR_2) {
            logger.info("second thread is an owner of monitor 2");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
            synchronized (MONITOR_1) {
                logger.info("second thread is an owner of monitor 1");
            }
        }
    }
}