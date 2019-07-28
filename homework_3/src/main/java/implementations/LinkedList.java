package implementations;

import interfaces.Deque;
import interfaces.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class LinkedList<T> implements List<T>, Deque<T> {
    private Node<T> first;
    private Node<T> last;

    private int size = 0;

    @Override
    public void addFirst(T element) {
        checkForOverSizedCollection();
        first = new Node<>(null, element, first);
        if (isNull(last)) {
            last = first;
        }
        Node<T> next = first.getNext();
        if (nonNull(next)) {
            next.setPrevious(first);
        }
        size++;
    }

    private void checkForOverSizedCollection() {
        if (size >= Integer.MAX_VALUE) {
            throw new IndexOutOfBoundsException("There are too much elements to store.");
        }
    }

    @Override
    public void addLast(T element) {
        checkForOverSizedCollection();

        last = new Node<>(last, element, null);
        if (isNull(first)) {
            first = last;
        }
        Node<T> previous = last.previous;
        if (nonNull(previous)) {
            previous.setNext(last);
        }
        size++;
    }

    @Override
    public T getFirst() {
        checkForNoElements(first);
        return first.getCurrent();
    }

    private void checkForNoElements(Node<T> element) {
        if (isNull(element)) {
            throw new IndexOutOfBoundsException("There are no elements in here.");
        }
    }

    private void checkForTooBigIndex(int index) {
        if (index > size) throw new IllegalArgumentException("too big index.");
    }

    private void checkForTooSmallIndex(int index) {
        if (index < 0) throw new IllegalArgumentException("too small index.");
    }

    @Override
    public T getLast() {
        checkForNoElements(last);
        return last.getCurrent();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        checkForTooSmallIndex(index);
        checkForTooBigIndex(index + 1);

        return findNodeByIndex(index).getCurrent();
    }

    private Node<T> findNodeByIndex(int index) {
        checkForNoElements(first);
        Node<T> currentElement = first;
        for (int i = 0; i < index; i++) {
            currentElement = currentElement.getNext();
        }
        return currentElement;
    }

    @Override
    public boolean add(T element) {
        addLast(element);
        return true;
    }

    @Override
    public boolean add(T element, int index) {
        checkForOverSizedCollection();
        checkForTooSmallIndex(index);
        checkForTooBigIndex(index);

        if (index == 0) {
            addFirst(element);
            return true;
        }
        if (index == size) {
            addLast(element);
            return true;
        }
        Node<T> nodeByIndex = findNodeByIndex(index);
        Node<T> previous = nodeByIndex.getPrevious();
        Node<T> newNode = new Node<>(previous, element, nodeByIndex);
        if (nonNull(previous)){
            previous.setNext(newNode);
        }
        newNode.setNext(nodeByIndex);
        nodeByIndex.setPrevious(newNode);
        size++;
        return true;
    }

    @Override
    public T remove(int index) {
        checkForNoElements(first);
        checkForTooSmallIndex(index);
        checkForTooBigIndex(index);
        if (size == 1) {
            T foundElement = first.getCurrent();
            first = null;
            last = null;
            size--;
            return foundElement;
        }
        Node<T> nodeByIndex = findNodeByIndex(index);
        Node<T> previous = nodeByIndex.getPrevious();
        Node<T> next = nodeByIndex.getNext();
        if (nonNull(previous)){
            previous.setNext(next);
        }
        if (nonNull(next)){
            next.setPrevious(previous);
        }
        size--;
        return nodeByIndex.getCurrent();
    }

    @Override
    public T replace(T newElement, int index) {
        checkForTooSmallIndex(index);
        checkForTooBigIndex(index);
        Node<T> nodeByIndex = findNodeByIndex(index);
        T previousValue = nodeByIndex.getCurrent();
        nodeByIndex.setCurrent(newElement);
        return previousValue;
    }

    private class Node<E> {
        private Node<E> previous;
        private E current;
        private Node<E> next;

        Node(Node<E> previous, E current, Node<E> next) {
            this.previous = previous;
            this.current = current;
            this.next = next;
        }

        Node<E> getPrevious() {
            return previous;
        }

        void setPrevious(Node<E> previous) {
            this.previous = previous;
        }

        Node<E> getNext() {
            return next;
        }

        void setNext(Node<E> next) {
            this.next = next;
        }

        E getCurrent() {
            return current;
        }

        void setCurrent(E current) {
            this.current = current;
        }
    }
}
