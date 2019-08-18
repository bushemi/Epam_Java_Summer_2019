1) Протестировать индексированный и связной список из домашней работы #3 <br/>
Тест-кейсы должны покрывать все контракты компонентов, описанные в домашней работе #3 и включать в себя позитивные и негативные сценарии<br/>
Протестировать все сценарии с выбросом исключений с помощью @Rule <br/>
При тестировании использовать jUnit 4 и hamcrest, кейсы должны быть организованы с разделением на секции GIVEN-WHEN-THEN <br/>
2) Протестировать класс Processor, зависящий от компонентов Producer и Consumer. Для проверки взаимодействия Processor’а с его зависимостями использовать Mockito.<br/> 
Чтобы проверить как Processor использует зависимости используйте Mockito#verify() <br/>
Чтобы проверить аргумент Consumer#consume() используйте ArgumentCaptor. http://www.javabyexamples.com/mockito-recipe-capture-arguments-with-argumentcaptor/ <br/>
Обязательный тест-кейс – замокать результат Producer#produce() для того что-бы протестировать негативный сценарий – выброс IllegalStateException. <br/>


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

public class Producer {

    public String produce() {
        return "Magic value";
    }
}

public class Consumer {

    public void consume(String value) {
        System.out.println("Consumed -> " + value);
    }
}
