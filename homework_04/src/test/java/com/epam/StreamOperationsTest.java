package com.epam;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Stream;

import static java.lang.String.valueOf;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class StreamOperationsTest {
    private static final City DNIPRO = new City("Dnipro", "dp", 1_000_000);
    private static final City ODESA = new City("Odesa", "od", 1_000_000);
    private static final City KYIV = new City("Kyiv", "kv", 2_900_000);
    private static final City KHARKIV = new City("Kharkiv", "kh", 2_100_000);
    private static final City LVIV = new City("Lviv", "lv", 720_000);
    private Random random = new Random();
    private static final Logger LOG = LoggerFactory.getLogger("StreamOperationsTest");

    @Test
    public void evenOddMarker() {
        List<Integer> integers = Stream.generate(() -> random.nextInt(50)).limit(20).collect(toList());
        String evenOddMarked = StreamOperations.evenOddMarker(integers);
        LOG.info(evenOddMarked);
        validateNumbers(evenOddMarked);
    }

    private void validateNumbers(String text) {
        Arrays.stream(text.split(",")).forEach(element -> {
            int i = Integer.parseInt(element.substring(1));
            char leftover = i % 2 > 0 ? 'o' : 'e';
            assertEquals(leftover, element.charAt(0));
        });
    }

    @Test
    public void getLargestCityPerState() {
        Collection<City> cities = getCities();
        Map<String, City> largestCityPerState = StreamOperations.getLargestCityPerState(cities);
        for (Map.Entry<String, City> stringCityEntry : largestCityPerState.entrySet()) {
            LOG.info("state={}, city={}", stringCityEntry.getKey(), stringCityEntry.getValue());
        }
        validateCitiesByState(largestCityPerState);
    }

    private void validateCitiesByState(Map<String, City> mapWithCities) {
        List<Pair> pairs = Arrays.asList(new Pair("od", ODESA),
                new Pair("lv", LVIV),
                new Pair("kv", KYIV),
                new Pair("dp", DNIPRO),
                new Pair("kh", KHARKIV));
        pairs.forEach(pair ->
                assertEquals(pair.getValue(), mapWithCities.get(pair.getKey())));
    }

    private class Pair<K, V> {
        private K key;
        private V value;

        private Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private K getKey() {
            return key;
        }

        private V getValue() {
            return value;
        }
    }

    @Test
    public void getLargestCityPerStateByCollections() {
        Collection<City> cities = getCities();
        Map<String, City> largestCityPerState = StreamOperations.getLargestCityPerStateByCollections(cities);
        for (Map.Entry<String, City> stringCityEntry : largestCityPerState.entrySet()) {
            LOG.info("state={}, city={}", stringCityEntry.getKey(), stringCityEntry.getValue());
        }
        validateCitiesByState(largestCityPerState);
    }

    @Test
    public void zipWithStrings() {
        Stream<String> stream1 = Stream.of("one", "one", "one", "one", "one");
        Stream<String> stream2 = Stream.of("two", "two", "two", "two");
        List<String> zipped = StreamOperations.zip(stream1, stream2).collect(toList());
        zipped.forEach(LOG::info);
        assertEquals(8, zipped.size());
        List<String> expectedElements = Arrays.asList("one", "two", "one", "two", "one", "two", "one", "two");
        for (int i = 0; i < zipped.size(); i++) {
            assertEquals(expectedElements.get(i), zipped.get(i));
        }
    }

    @Test
    public void zipWithNumbers() {
        Stream<Integer> stream1 = Stream.of(1, 1, 1, 1, 1, 1, 1, 1);
        Stream<Integer> stream2 = Stream.of(2, 2, 2);
        List<Integer> zipped = StreamOperations.zip(stream1, stream2).collect(toList());
        zipped.forEach(number -> LOG.info(valueOf(number)));
        assertEquals(6, zipped.size());
        List<Integer> expectedElements = Arrays.asList(1, 2, 1, 2, 1, 2);
        for (int i = 0; i < zipped.size(); i++) {
            assertEquals(expectedElements.get(i), zipped.get(i));
        }
    }

    private Collection<City> getCities() {
        return Arrays.asList(
                new City("Kryvyi Rih", "dp", 630_000),
                DNIPRO,
                new City("Novomoskovsk", "dp", 72_500),
                new City("Nikopol", "dp", 136_000),
                new City("Drohobich", "lv", 77_000),
                new City("Stryi", "lv", 59_000),
                LVIV,
                new City("Berezivka", "od", 13_000),
                new City("Izmail", "od", 72_000),
                ODESA,
                new City("Lozova", "kh", 65_000),
                KHARKIV,
                new City("Zolochiv", "kh", 8_400),
                new City("Velykyi Burluk", "kh", 3_900),
                new City("Brovary", "kv", 98_000),
                new City("Fasiv", "kv", 48_000),
                KYIV,
                new City("Bucha", "kv", 29_000)
        );
    }
}