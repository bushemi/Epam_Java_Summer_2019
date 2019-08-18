package implementations;

import interfaces.List;

public class ArrayList<T> implements List<T> {
    private int size = 0;

    private static final int DEFAULT_CAPACITY = 10;

    private int currentCapacity = DEFAULT_CAPACITY;

    private Object[] array;

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Too small capacity");
        currentCapacity = capacity;
        array = new Object[currentCapacity];
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        checkForTooSmallIndex(index);
        checkForTooBigIndex(index + 1);
        return (T) array[index];
    }

    private void checkForTooBigIndex(int index) {
        if (index > size) throw new IllegalArgumentException("too big index.");
    }

    private void checkForTooSmallIndex(int index) {
        if (index < 0) throw new IllegalArgumentException("too small index.");
    }

    public boolean add(T element) {
        stretchCollectionIfNeeded(size);
        array[size] = element;
        size++;
        return true;
    }

    private void stretchCollectionIfNeeded(int size) {
        checkForOverSizedCollection();
        if (size >= currentCapacity) {
            currentCapacity++;
            Object[] newArray = new Object[currentCapacity];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    private void checkForOverSizedCollection() {
        if (currentCapacity >= Integer.MAX_VALUE) {
            throw new IndexOutOfBoundsException("There are too much elements to store.");
        }
    }

    @Override
    public boolean add(T element, int index) {
        checkForTooSmallIndex(index);
        checkForTooBigIndex(index);
        stretchCollectionIfNeeded(size + 1);
        System.arraycopy(array, index, array, index + 1,
                size - index);
        array[index] = element;
        size++;
        return true;
    }

    public T remove(int index) {
        checkForTooSmallIndex(index);
        checkForTooBigIndex(index);
        T element = (T) array[index];
        Object[] arrayBeforeIndex = new Object[index];
        System.arraycopy(array, 0, arrayBeforeIndex, 0, index);
        int srcPos = size - (index + 1);
        Object[] arrayAfterIndex = new Object[srcPos];
        System.arraycopy(array, srcPos + 1, arrayAfterIndex, 0, srcPos);

        System.arraycopy(arrayBeforeIndex, 0, array, 0, index);
        System.arraycopy(arrayAfterIndex, 0, array, index, srcPos);
        size--;
        return element;
    }

    public T replace(T newElement, int index) {
        checkForTooSmallIndex(index);
        checkForTooBigIndex(index);
        T oldElement = (T) array[index];
        array[index] = newElement;
        return oldElement;
    }
}
