package interfaces;

/**
 * interface for collections which support double ended queue.
 *
 * @param <T>
 */
public interface Deque<T> {

    /**
     * Adds element to the beginning of the collection.
     * All indexes of previously added elements will be changed.
     *
     * @param element that system needs to put inside the collection.
     */
    void addFirst(T element);

    /**
     * Adds element to the end of the collection.
     *
     * @param element that system needs to put inside the collection.
     */
    void addLast(T element);

    /**
     * Retrieves the first element of the collection.
     *
     * @return first element.
     */
    T getFirst();

    /**
     * Retrieves the last element of the collection.
     *
     * @return last element
     */
    T getLast();
}
