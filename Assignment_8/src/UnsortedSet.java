/*  Student information for assignment:
 *
 *  On MY honor, RAHIK N SIKDER, 
 *  this programming assignment is MY own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 2
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: rns2359
 *  email address: sikder.rahik@utexas.edu
 *  TA name: Casey, the Dedicated
 */

import java.util.Iterator;
import java.util.ArrayList;

/**
 * A simple implementation of an ISet.
 * Elements are not in any particular order.
 * Students are to implement methods that
 * were not implemented in AbstractSet and override
 * methods that can be done more efficiently.
 * An ArrayList must be used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {

    // Instance variable
    private ArrayList<E> myCon;
    private int size;

    public UnsortedSet() {
        myCon = new ArrayList<>();
    }

    /**
     * O(N)
     * Add an item to this set.
     * <br>
     * item != null
     * 
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation,
     *         false otherwise.
     */
    public boolean add(E item) {

        // Check preconditions
        if (item == null) {
            throw new IllegalArgumentException("Violation of preconditions: " +
                    "item != null");
        }

        // Item already exists in the set
        if (contains(item)) {
            return false;
        }

        // Add item to ArrayList and increment size
        myCon.add(item);
        size++;
        return true;
    }

    /**
     * O(1)
     * Return the number of elements of this set.
     * pre: none
     * 
     * @return the number of items in this set
     */
    public int size() {
        return size;
    }

    /**
     * O(N)
     * Remove the specified item from this set if it is present.
     * pre: item != null
     * 
     * @param item the item to remove from the set. item may not equal null.
     * @return true if this set changed as a result of this operation,
     *         false otherwise
     */
    public boolean remove(E item) {

        // Check preconditions
        if (item == null) {
            throw new IllegalArgumentException("Violation of preconditions: " +
                    "item != null");
        }

        size--;
        return myCon.remove(item);
    }

    /**
     * O(N^2)
     * Create a new set that is the difference of this set and otherSet.
     * Return an ISet of elements that are in this Set but not in otherSet.
     * Also called the relative complement.
     * <br>
     * Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z]
     * then A.difference(B) would return an ISet with elements [X, Y] while
     * B.difference(A) would return an ISet with elements [W].
     * <br>
     * pre: otherSet != null
     * <br>
     * post: returns a set that is the difference of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br>
     * pre: otherSet != null
     * 
     * @param otherSet != null
     * @return a set that is the difference of this set and otherSet
     */
    public ISet<E> difference(ISet<E> otherSet) {

        // Check preconditions
        if (otherSet == null) {
            throw new IllegalArgumentException("Violation of preconditions: " +
                    "otherSet != null");
        }

        // Create an output to return
        UnsortedSet<E> output = new UnsortedSet<>();

        Iterator<E> iter = this.iterator();

        while (iter.hasNext()) {
            E temp = iter.next();
            if (!otherSet.contains(temp)) {
                output.myCon.add(temp);
                output.size++;
            }
        }

        return output;
    }

    /**
     * O(N^2)
     * create a new set that is the intersection of this set and otherSet.
     * <br>
     * pre: otherSet != null<br>
     * <br>
     * post: returns a set that is the intersection of this set
     * and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br>
     * pre: otherSet != null
     * 
     * @param otherSet != null
     * @return a set that is the intersection of this set and otherSet
     */
    public ISet<E> intersection(ISet<E> otherSet) {

        // Check preconditions
        if (otherSet == null) {
            throw new IllegalArgumentException("Violation of preconditions: " +
                    "otherSet != null");
        }

        // Create an output to return
        UnsortedSet<E> output = new UnsortedSet<>();

        Iterator<E> iter = iterator();

        while (iter.hasNext()) {
            E temp = iter.next();
            if (otherSet.contains(temp)) {
                output.myCon.add(temp);
                output.size++;
            }
        }

        return output;

    }

    /**
     * O(1)
     * Return an Iterator object for the elements of this set.
     * pre: none
     * 
     * @return an Iterator object for the elements of this set
     */
    public Iterator<E> iterator() {
        return new UnsortedSetIterator();
    }

    /**
     * Iterator for UnsortedSet
     */
    public class UnsortedSetIterator implements Iterator<E> {
        
        Iterator<E> iter;

        public UnsortedSetIterator(){
            iter = myCon.iterator();
        }

        public boolean hasNext() {
            return iter.hasNext();
        }

        public E next() {
            return iter.next();
        }

        // This is the reason why this class exists
        // Otherwise the iterator.remove() does not change the size
        public void remove() {
            iter.remove();
            size--;
        }

    }

}