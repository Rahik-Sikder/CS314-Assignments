import java.util.Iterator;
import java.util.LinkedList;

public class MyPriorityQueue<E extends Comparable<? super E>> {

    // Instance Variables
    private LinkedList<E> list;
    private int size;

    // Constructor
    public MyPriorityQueue() {
        list = new LinkedList<>();
    }

    /*
     * Adds an element to the queue based upon priority
     */
    public boolean offer(E element) {

        // If size is 0, then just add to list
        if (size == 0) {
            list.add(element);
            size++;
            return true;
        }

        // Otherwise find where E belongs
        int index = 0;
        Iterator<E> iter = list.iterator();
        while (iter.hasNext() && iter.next().compareTo(element) <= 0) {
            index++;
        }
        list.add(index, element);
        size++;
        return true;
    }

    /*
     * Removes and returns the first value in the queue
     */
    public E poll() {

        // Return null if no elements are in list
        if (size == 0) {
            return null;
        }

        // Return first element and decrement size
        size--;
        return list.removeFirst();
    }

    /*
     * Returns first value in the queue
     */
    public E peek() {
        // Return null if no elements are in list
        if (size == 0) {
            return null;
        }

        // Return first element
        return list.getFirst();
    }

    /**
     * Returns the size of the queue
     */
    public int size() {
        return size;
    }

    /**
     * Returns a string representation of the queue
     */
    @Override
    public String toString() {
        return list.toString();
    }


}
