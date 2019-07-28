package implementations;

import interfaces.List;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayListTest {

    private static final String TEST_STRING = "test";
    private List<String> list;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void size() {
        list = new ArrayList<>();
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

        list = new ArrayList<>();
        boolean resultOfAdd = list.add(TEST_STRING);
        assertTrue(resultOfAdd);
        list.get(1);
    }

    @Test
    public void getByTooSmallIndex() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("too small index.");

        list = new ArrayList<>();
        list.get(-1);
    }

    @Test
    public void getElement() {
        list = new ArrayList<>();
        boolean resultOfAdd = list.add(TEST_STRING);
        String gotFromArray = list.get(0);

        assertTrue(resultOfAdd);
        assertEquals(TEST_STRING, gotFromArray);
    }

    @Test
    public void shouldAdd() {
        list = new ArrayList<>();
        int oldSize = list.size();
        boolean resultOfAdd = list.add(TEST_STRING);
        int newSize = list.size();

        assertTrue(resultOfAdd);
        assertTrue(oldSize < newSize);
        assertEquals(1, newSize);
    }

    @Test
    public void shouldAddByTooBigIndex() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("too big index.");

        list = new ArrayList<>();
        list.add(TEST_STRING, 1);
    }

    @Test
    public void shouldAddByTooSmallIndex() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("too small index.");

        list = new ArrayList<>();
        list.add(TEST_STRING, -1);
    }

    @Test
    public void shouldAddByIndex() {
        list = new ArrayList<>();
        boolean resultOfAdd = list.add(TEST_STRING, 0);
        String gotFromArray = list.get(0);

        assertTrue(resultOfAdd);
        assertEquals(TEST_STRING, gotFromArray);
    }

    @Test
    public void remove() {
        list = new ArrayList<>();
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
        list = new ArrayList<>(11);
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
        list = new ArrayList<>(11);
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

        list = new ArrayList<>();
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            list.add(TEST_STRING);
        }
    }
}