package com.epam;

import implementations.LinkedList;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class LinkedListTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void addFirst() {
        //GIVEN
        LinkedList<String> list = new LinkedList<>();

        //WHEN
        list.addFirst("test-addFirst");

        //THEN
        assertThat(list.size(), is(equalTo(1)));
        assertThat(list.getFirst(), is(equalTo("test-addFirst")));
    }

    @Test
    public void addLast() {
        //GIVEN
        LinkedList<String> list = new LinkedList<>();
        //WHEN
        list.addLast("test-addLast");

        //THEN
        assertThat(list.size(), is(equalTo(1)));
        assertThat(list.getLast(), is(equalTo("test-addLast")));

    }

    @Test
    public void size() {
        //GIVEN
        LinkedList<String> list = new LinkedList<>();
        boolean resultOfAdd = list.add("test-size");

        //WHEN
        int size = list.size();

        //THEN
        assertThat(size, is(equalTo(1)));
        assertTrue(resultOfAdd);
    }

    @Test
    public void getByTooBigIndex() {
        //GIVEN
        LinkedList<String> list = new LinkedList<>();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("too big index.");
        list.add("test-getByTooBigIndex");

        //WHEN
        list.get(1);
    }

    @Test
    public void getByTooSmallIndex() {
        //GIVEN
        LinkedList<String> list = new LinkedList<>();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("too small index.");

        //WHEN
        list.get(-1);
    }

    @Test
    public void getElement() {
        //GIVEN
        LinkedList<String> list = new LinkedList<>();
        boolean resultOfAdd = list.add("test-getElement");

        //WHEN
        String gotFromArray = list.get(0);

        //THEN
        assertTrue(resultOfAdd);
        assertThat(gotFromArray, is(equalTo("test-getElement")));
    }

    @Test
    public void shouldAdd() {
        //GIVEN
        LinkedList<String> list = new LinkedList<>();
        int oldSize = list.size();

        //WHEN
        boolean resultOfAdd = list.add("test-shouldAdd");

        //THEN
        int newSize = list.size();

        assertTrue(resultOfAdd);
        assertTrue(oldSize < newSize);
        assertThat(newSize, is(equalTo(1)));
    }

    @Test
    public void shouldAddTwoElements() {
        //GIVEN
        LinkedList<String> list = new LinkedList<>();
        int oldSize = list.size();

        //WHEN
        list.addFirst("test-shouldAddTwoElements");
        list.addFirst("test-shouldAddTwoElements");

        //THEN
        int newSize = list.size();

        assertTrue(oldSize < newSize);
        assertThat(newSize, is(equalTo(2)));
    }

    @Test
    public void getFirst() {
        //GIVEN
        LinkedList<String> list = new LinkedList<>();
        String first = "test-getFirst";
        list.addFirst(first);

        //WHEN
        String firstFromList = list.getFirst();

        //THEN
        assertThat(first, is(equalTo(firstFromList)));

    }

    @Test
    public void getFirstIfNoElements() {
        //GIVEN
        LinkedList<String> list = new LinkedList<>();
        expectedException.expect(IndexOutOfBoundsException.class);
        expectedException.expectMessage("There are no elements in here.");

        //WHEN
        list.getFirst();
    }

    @Test
    public void getLastIfNoElements() {
        //GIVEN
        LinkedList<String> list = new LinkedList<>();
        expectedException.expect(IndexOutOfBoundsException.class);
        expectedException.expectMessage("There are no elements in here.");

        //WHEN
        list.getLast();
    }

    @Test
    public void shouldAddByIndexTriceSameElement() {
        //GIVEN
        LinkedList<String> list = new LinkedList<>();
        String element = "shouldAddByIndexTrice";

        //WHEN
        list.add(element, 0);
        list.add(element, 1);
        list.add(element, 1);

        //THEN
        assertThat(list.size(), is(equalTo(3)));
    }

    @Test
    public void shouldAddInTheMiddle() {
        //GIVEN
        LinkedList<String> list = new LinkedList<>();
        list.add("test-shouldAddInTheMiddle");
        list.add("test-shouldAddInTheMiddle");

        //WHEN
        list.add("test-newMiddle", 1);

        //THEN

        assertThat(list.size(), is(equalTo(3)));
        assertThat(list.get(1), is(equalTo("test-newMiddle")));
    }

    @Test
    public void shouldRemoveByFirstIndex() {
        //GIVEN
        LinkedList<String> list = new LinkedList<>();
        String element = "test-shouldRemoveByFirstIndex";
        list.addFirst(element);

        //WHEN
        list.remove(0);

        //THEN
        assertThat(list.size(), is(equalTo(0)));
    }

    @Test
    public void shouldAddByTooBigIndex() {
        //GIVEN
        LinkedList<String> list = new LinkedList<>();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("too big index.");

        //WHEN
        list.add("test-shouldAddByTooBigIndex", 1);
    }

    @Test
    public void shouldAddByTooSmallIndex() {
        //GIVEN
        LinkedList<String> list = new LinkedList<>();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("too small index.");

        //WHEN
        list.add("test-shouldAddByTooSmallIndex", -1);
    }

    @Test
    public void shouldAddByIndex() {
        //GIVEN
        LinkedList<String> list = new LinkedList<>();

        //WHEN
        boolean resultOfAdd = list.add("test-shouldAddByIndex", 0);

        //THEN
        String gotFromArray = list.get(0);

        assertTrue(resultOfAdd);
        assertThat(gotFromArray, is(equalTo("test-shouldAddByIndex")));
    }

    @Test
    public void remove() {
        //GIVEN
        LinkedList<String> list = new LinkedList<>();
        boolean resultOfAdd = list.add("test-remove");
        int oldSize = list.size();

        //WHEN
        String removedElement = list.remove(0);

        //THEN
        int newSize = list.size();

        assertTrue(resultOfAdd);
        assertThat(newSize, is(equalTo(0)));
        assertThat(oldSize, is(equalTo(1)));
        assertThat(removedElement, is(equalTo("test-remove")));
    }

    @Test
    public void replace() {
        //GIVEN
        LinkedList<String> list = new LinkedList<>();
        list.add("test1-replace");
        String elementWeNeedToReplace = "test2-replace";
        list.add(elementWeNeedToReplace);
        list.add("test3-replace");

        //WHEN
        String replacedElement = list.replace("test-replace", 1);

        //THEN
        String newElement = list.get(1);
        assertThat(newElement, is(equalTo("test-replace")));
        assertThat(replacedElement, is(equalTo(elementWeNeedToReplace)));
    }

    @Test
    public void checkForBigCollectionsShouldAddAll() {
        //GIVEN
        LinkedList<String> list = new LinkedList<>();

        //WHEN
        for (long i = 0; i < 2000; i++) {
            list.add("checkForBigCollectionsShouldAddAll " + i);
        }

        //THEN
        assertThat(list.size(), is(equalTo(2000)));
    }

    @Test
    @Ignore("It runs very long.")
    public void checkForTooBigCollectionsShouldThrowException() {
        //GIVEN
        LinkedList<Long> list = new LinkedList<>();
        expectedException.expect(IndexOutOfBoundsException.class);
        expectedException.expectMessage("There are too much elements to store.");

        //WHEN
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            list.add(i);
        }
    }
}
