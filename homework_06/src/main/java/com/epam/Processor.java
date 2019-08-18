package com.epam;

public class Processor {
    private Producer producer;
    private Consumer consumer;

    public void process() {
        String value = producer.produce();
        if (value == null) {
            throw new IllegalStateException();
        }
        consumer.consume(value);
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }
}
