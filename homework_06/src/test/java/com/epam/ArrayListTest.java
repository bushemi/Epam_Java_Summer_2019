package com.epam;

import implementations.ArrayList;
import interfaces.List;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ArrayListTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void size() {
        //GIVEN
        List<String> list = new ArrayList<>();
        list.add("testSize");

        //WHEN
        int size = list.size();

        //THEN
        assertThat(size, is(equalTo(1)));
    }

    @Test
    public void getShouldReturnElement() {
        //GIVEN
        List<String> list = new ArrayList<>();
        list.add("testGet");

        //WHEN
        String result = list.get(0);

        //THEN
        assertThat(result, is(equalTo("testGet")));
    }

    @Test
    public void getShouldThrowException() {
        //GIVEN
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("too big index.");
        List<String> list = new ArrayList<>();

        //WHEN
        list.get(0);
    }

    @Test
    public void addShouldAdd() {
        //GIVEN
        List<String> list = new ArrayList<>();

        //WHEN
        list.add("testAdd");

        //THEN
        assertThat(list.size(), is(equalTo(1)));
        assertThat(list.get(0), is(equalTo("testAdd")));
    }

    @Test
    public void addShouldThrowException() {
        //GIVEN
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Too small capacity");
        List<Integer> list = new ArrayList<>(0);

        //WHEN
        IntStream.range(0, 110).forEach(list::add);
    }

    @Test
    public void addShouldAddHundredElements() {
        //GIVEN
        List<Integer> list = new ArrayList<>();

        //WHEN
        IntStream.range(0, 100).forEach(list::add);

        //THEN
        assertThat(list.size(), is(equalTo(100)));
    }

    @Test
    public void addByIndexShouldAddElement() {
        //GIVEN
        List<Integer> list = new ArrayList<>();
        IntStream.range(0, 10).forEach(list::add);

        //WHEN
        list.add(77, 5);

        //THEN
        assertThat(list.get(5), is(equalTo(77)));
    }

    @Test
    public void removeForTwoElements() {
        //GIVEN
        List<String> list = new ArrayList<>();
        list.add("testRemove2-1");
        list.add("testRemove2-2");

        //WHEN
        list.remove(0);

        //THEN
        assertThat(list.size(), is(equalTo(1)));
        assertThat(list.get(0), is(equalTo("testRemove2-2")));
    }

    @Test
    public void removeForThreeElements() {
        //GIVEN
        List<String> list = new ArrayList<>();
        list.add("testRemove3-1");
        list.add("testRemove3-2");
        list.add("testRemove3-3");

        //WHEN
        list.remove(1);

        //THEN
        assertThat(list.size(), is(equalTo(2)));
        assertThat(list.get(1), is(equalTo("testRemove3-3")));
    }

    @Test
    public void replace() {
        //GIVEN
        List<String> list = new ArrayList<>();
        list.add("testReplace");
        list.add("testShouldBeReplaced");
        list.add("testReplace");

        //WHEN
        list.replace("wasReplaced", 1);

        //THEN
        assertThat(list.get(1), is(equalTo("wasReplaced")));
    }

    @Test
    @Ignore("It runs very long.")
    public void checkForTooBigCollections() {
        expectedException.expect(IndexOutOfBoundsException.class);
        expectedException.expectMessage("There are too much elements to store.");

        List<Long> list = new ArrayList<>();
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            list.add(i);
        }
    }
}
