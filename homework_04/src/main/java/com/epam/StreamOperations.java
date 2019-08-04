package com.epam;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

class StreamOperations {
    private StreamOperations() {
    }

    static String evenOddMarker(List<Integer> numbers) {
        return numbers.stream().map(StreamOperations::addLetter).collect(Collectors.joining(","));
    }

    private static String addLetter(Integer number) {
        if (number % 2 > 0) {
            return "o" + number;
        } else {
            return "e" + number;
        }
    }

    static Map<String, City> getLargestCityPerState(Collection<City> cities) {
        Map<String, List<City>> citiesByState = cities.stream()
                .collect(Collectors.groupingBy(City::getState));
        Map<String, City> result = new HashMap<>();
        citiesByState.keySet()
                .forEach(state -> result.put(state, getCityWithMaxPopulation(citiesByState.get(state))));
        return result;
    }

    private static City getCityWithMaxPopulation(List<City> cities) {
        return cities.stream()
                .max(Comparator.comparing(City::getPopulation))
                .orElseThrow(IllegalArgumentException::new);
    }

    static Map<String, City> getLargestCityPerStateByCollections(Collection<City> cities) {
        Map<String, City> result = new HashMap<>();
        for (City city : cities) {
            String state = city.getState();
            City cityFromMap = result.get(state);
            if (isNull(cityFromMap)) {
                result.put(state, city);
            } else {
                City largestCity = city.getPopulation() > cityFromMap.getPopulation() ? city : cityFromMap;
                result.put(state, largestCity);
            }
        }
        return result;
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> iterator1 = first.collect(Collectors.toList()).iterator();
        Iterator<T> iterator2 = second.collect(Collectors.toList()).iterator();
        List<T> result = new ArrayList<>();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            result.add(iterator1.next());
            result.add(iterator2.next());
        }
        return result.stream();
    }
}
