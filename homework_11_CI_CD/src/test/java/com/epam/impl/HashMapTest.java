package com.epam.impl;

import com.epam.interfaces.Map;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class HashMapTest {

    @Test
    public void getShouldReturnNumber() {
        //GIVEN
        Map<String, Integer> map = new HashMap<>();
        map.put("get", 17);

        //WHEN
        Integer get = map.get("get");

        //THEN
        assertEquals(17, get.intValue());
    }

    @Test
    public void put() {
        //GIVEN
        Map<String, Integer> map = new HashMap<>();

        //WHEN
        map.put("put", 1);

        //THEN
        Integer numberFromMap = map.get("put");
        assertEquals(1, numberFromMap.intValue());
    }

    @Test
    public void putShouldReplaceValue() {
        //GIVEN
        Map<String, Integer> map = new HashMap<>();

        //WHEN
        map.put("putShouldReplaceValue", 1);
        map.put("putShouldReplaceValue", 2);

        //THEN
        Integer numberFromMap = map.get("putShouldReplaceValue");
        assertEquals(2, numberFromMap.intValue());

    }

    @Test
    public void sizeForNewMapShouldReturnZero() {
        //GIVEN
        Map<String, Integer> map = new HashMap<>();

        //WHEN
        int size = map.size();

        //THEN
        assertThat(size, is(equalTo(0)));
    }

    @Test
    public void sizeShouldReturnTwo() {
        //GIVEN
        Map<String, Integer> map = new HashMap<>();
        map.put("test", 4);
        map.put("test2", 4);

        //WHEN
        int size = map.size();

        //THEN
        assertThat(size, is(equalTo(2)));
    }

    @Test
    public void iterator() {
        //GIVEN
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);

        //WHEN
        Iterator<Map.Entry<String, Integer>> iterator = map.iterator();

        //THEN
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            Map.Entry<String, Integer> entry = iterator.next();
            assertEquals(Integer.parseInt(entry.key), entry.value.intValue());
            assertEquals(count, entry.value.intValue());
        }
        assertEquals(3, count);

    }
}