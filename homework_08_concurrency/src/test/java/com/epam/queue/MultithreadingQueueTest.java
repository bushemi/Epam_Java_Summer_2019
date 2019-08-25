package com.epam.queue;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class MultithreadingQueueTest {
    private static Queue queue = new MultithreadingQueue();

    @Test
    public void test() throws InterruptedException {
        //GIVEN
        Thread producer = new Thread(MultithreadingQueueTest::produceToTheQueue);
        Thread consumer = new Thread(MultithreadingQueueTest::consumeFromTheQueue);

        //WHEN
        producer.start();
        consumer.start();

        //THEN
        producer.join();
        consumer.join();
    }

    private static void consumeFromTheQueue() {
        for (int secondIndex = 0; secondIndex < 5; secondIndex++) {
            String valueFromQueue = queue.get();
            assertThat(valueFromQueue, is(equalTo("test " + secondIndex)));
        }
    }

    private static void produceToTheQueue() {
        for (int firstIndex = 0; firstIndex < 5; firstIndex++) {
            int finalFirstIndex = firstIndex;
            queue.put("test " + finalFirstIndex);
        }
    }

}