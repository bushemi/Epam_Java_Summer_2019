package implementations;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LinkedListTest {

    private static final String TEST_STRING = "test";
    private LinkedList<String> list;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void size() {
        list = new LinkedList<>();
        int oldSize = list.size();
        boolean resultOfAdd = list.add(TEST_STRING);
        int newSize = list.size();

        assertTrue(resultOfAdd);
        assertTrue(oldSize < newSize);
    }

    @Test
    public void getByTooBigIndex() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("too big index.");

        list = new LinkedList<>();
        boolean resultOfAdd = list.add(TEST_STRING);
        assertTrue(resultOfAdd);
        list.get(1);
    }

    @Test
    public void getByTooSmallIndex() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("too small index.");

        list = new LinkedList<>();
        list.get(-1);
    }

    @Test
    public void getElement() {
        list = new LinkedList<>();
        boolean resultOfAdd = list.add(TEST_STRING);
        String gotFromArray = list.get(0);

        assertTrue(resultOfAdd);
        assertEquals(TEST_STRING, gotFromArray);
    }

    @Test
    public void shouldAdd() {
        list = new LinkedList<>();
        int oldSize = list.size();
        boolean resultOfAdd = list.add(TEST_STRING);
        int newSize = list.size();

        assertTrue(resultOfAdd);
        assertTrue(oldSize < newSize);
        assertEquals(1, newSize);
    }

    @Test
    public void shouldAddTwoElements() {
        list = new LinkedList<>();
        int oldSize = list.size();
        list.addFirst(TEST_STRING);
        list.addFirst(TEST_STRING);
        int newSize = list.size();

        assertTrue(oldSize < newSize);
        assertEquals(2, newSize);
    }

    @Test
    public void getFirst() {
        list = new LinkedList<>();
        String first = "test-add-1";
        list.addFirst(first);
        assertEquals(first, list.getFirst());
        assertEquals(first, list.getLast());

        String second = "test-add-2";
        list.addFirst(second);
        assertEquals(second, list.getFirst());
        assertEquals(first, list.getLast());
    }

    @Test
    public void getFirstIfNoElements() {
        expectedException.expect(IndexOutOfBoundsException.class);
        expectedException.expectMessage("There are no elements in here.");

        list = new LinkedList<>();
        list.getFirst();
    }

    @Test
    public void getLastIfNoElements() {
        expectedException.expect(IndexOutOfBoundsException.class);
        expectedException.expectMessage("There are no elements in here.");

        list = new LinkedList<>();
        list.getLast();
    }

    @Test
    public void shouldAddByIndexTrice() {
        list = new LinkedList<>();
        list.add(TEST_STRING, 0);
        list.add(TEST_STRING, 1);
        list.add(TEST_STRING, 1);

        assertEquals(3, list.size());
    }

    @Test
    public void shouldAddInTheMiddle() {
        list = new LinkedList<>();
        list.add(TEST_STRING);
        list.add(TEST_STRING);
        list.add(TEST_STRING, 1);

        assertEquals(3, list.size());
    }

    @Test
    public void shouldRemoveByFirstIndex() {
        list = new LinkedList<>();
        list.addFirst(TEST_STRING);
        list.addFirst(TEST_STRING);
        list.addFirst(TEST_STRING);
        assertEquals(3, list.size());

        list.remove(2);
        list.remove(0);
        list.remove(0);
        assertEquals(0, list.size());
    }

    @Test
    public void shouldAddByTooBigIndex() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("too big index.");

        list = new LinkedList<>();
        list.add(TEST_STRING, 1);
    }

    @Test
    public void shouldAddByTooSmallIndex() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("too small index.");

        list = new LinkedList<>();
        list.add(TEST_STRING, -1);
    }

    @Test
    public void shouldAddByIndex() {
        list = new LinkedList<>();
        boolean resultOfAdd = list.add(TEST_STRING, 0);
        String gotFromArray = list.get(0);

        assertTrue(resultOfAdd);
        assertEquals(TEST_STRING, gotFromArray);
    }

    @Test
    public void remove() {
        list = new LinkedList<>();
        boolean resultOfAdd = list.add(TEST_STRING);
        int oldSize = list.size();
        String removedElement = list.remove(0);
        int newSize = list.size();

        assertTrue(resultOfAdd);
        assertEquals(1, oldSize);
        assertEquals(TEST_STRING, removedElement);
        assertEquals(0, newSize);
    }

    @Test
    public void replace() {
        list = new LinkedList<>();
        boolean resultOfAdd = list.add("test1-replace");
        assertTrue(resultOfAdd);
        String elementWeNeedToReplace = "test2-replace";
        resultOfAdd = list.add(elementWeNeedToReplace);
        assertTrue(resultOfAdd);
        resultOfAdd = list.add("test3-replace");
        assertTrue(resultOfAdd);

        String replacedElement = list.replace(TEST_STRING, 1);
        assertEquals(elementWeNeedToReplace, replacedElement);
        String newElement = list.get(1);

        assertEquals(TEST_STRING, newElement);
    }

    @Test
    public void checkForBigCollections() {
        list = new LinkedList<>();
        for (long i = 0; i < 2000; i++) {
            list.add(TEST_STRING);
        }
        int size = list.size();
        assertEquals(2000, size);
    }

    @Test
    @Ignore("It runs very long.")
    public void checkForTooBigCollections() {
        expectedException.expect(IndexOutOfBoundsException.class);
        expectedException.expectMessage("There are too much elements to store.");

        list = new LinkedList<>();
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            list.add(TEST_STRING);
        }
    }
}