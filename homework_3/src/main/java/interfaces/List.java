package interfaces;

/**
 * Interface for collections with random access.
 *
 * @param <T>
 */
public interface List<T> {

    /**
     * Returns size of the collection.
     *
     * @return quantity of elements inside collection.
     */
    int size();

    /**
     * Retrieves an element by index.
     *
     * @param index of needed element.
     * @return element with given index.
     */
    T get(int index);

    /**
     * Adds element to the collection.
     *
     * @param element that system needs to put to collection.
     * @return index of a new element in the collection.
     */
    int add(T element);

    /**
     * Deletes element from the collection by given index and return this deleted element.
     *
     * @param index of needed element.
     * @return deleted element.
     */
    T remove(int index);

    /**
     * Replace one element with another.
     *
     * @param newElement element that we need to put to the collection.
     * @param index      of an element that we need to replace.
     * @return old element from given index.
     */
    T replace(T newElement, int index);
}
