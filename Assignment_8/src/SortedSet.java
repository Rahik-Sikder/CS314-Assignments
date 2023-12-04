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
 * In this implementation of the ISet interface the elements in the Set are
 * maintained in ascending order.
 *
 * The data type for E must be a type that implements Comparable.
 *
 * Implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently. An ArrayList must
 * be used as the internal storage container. For methods involving two set,
 * if that method can be done more efficiently if the other set is also a
 * SortedSet, then do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {

    private ArrayList<E> myCon;
    private int size;

    /**
     * create an empty SortedSet
     */
    public SortedSet() {
        myCon = new ArrayList<>();
    }

    /**
     * O(NlogN)
     * Create a copy of other that is sorted.<br>
     * 
     * @param other != null
     */
    public SortedSet(ISet<E> other) {

        // Check preconditions
        if (other == null) {
            throw new IllegalArgumentException("Violation of preconditions: " +
                    "other != null");
        }

        myCon = new ArrayList<>(other.size());
        // O(N) time loop for insertion
        for (E element : other) {
            myCon.add(element);
            size++;
        }
        // O(NlogN) merge sort
        sortList();

    }

    /**
     * O(NlogN)
     * Implements a mergeSort alorithm to sort the ArrayList as shown in class
     */
    private void sortList() {

        // Create a temp array up front that eventually gets copied into myCon during
        // the merge step
        sortListHelper(myCon, 0, size - 1);
    }

    /**
     * O(NlogN)
     * Helper method for sortList, implements the sort step of the mergeSort
     * algorithm as it was
     * shown in class
     * 
     * @param temp ArrayList<E> that will temporarily hold values for the merge step
     * @param low  bottom index of subarray
     * @param high top index of subarray
     */
    private void sortListHelper(ArrayList<E> curArray, int low, int high) {

        // Recursively split up the array until each subarray is one element
        // At which point merge is called
        if (low < high) {
            int center = low + ((high - low) / 2);
            sortListHelper(curArray, low, center);
            sortListHelper(curArray, center + 1, high);
            merge(curArray, low, center, high);
        }
    }

    /**
     * O(N)
     * Merges two arrays
     * 
     * @param curArray
     * @param temp
     * @param low
     * @param center
     * @param high
     */
    private void merge(ArrayList<E> curArray, int low, int center, int high) {

        ArrayList<E> temp = new ArrayList<>(curArray);

        // Store curLeft and curRight indices in temp vars
        int curLeft = low;
        int curRight = center + 1;
        int index = low;

        // Main loop that continues whenever both
        while (curLeft <= center && curRight <= high) {
            E leftElement = curArray.get(curLeft);
            E rightElement = curArray.get(curRight);
            if (leftElement.compareTo(rightElement) <= 0) {
                temp.set(index, leftElement);
                curLeft++;
            } else {
                temp.set(index, rightElement);
                curRight++;
            }
            index++;
        }

        // Copying the rest of the left subarrary
        while (curLeft <= center) {
            E leftElement = curArray.get(curLeft);
            temp.set(index, leftElement);
            curLeft++;
            index++;
        }

        // Copying the rest of the right subarrary
        while (curRight <= high) {
            E rightElement = curArray.get(curRight);
            temp.set(index, rightElement);
            curRight++;
            index++;
        }

        // Copy temp into curArray
        for (int i = low; i <= high; i++) {
            curArray.set(i, temp.get(i));
        }

    }

    /**
     * O(N) if otherSet is instanceOf SortedSet
     * O(N^2) otherwise
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

        boolean isSorted = otherSet instanceof SortedSet<?>;
        SortedSet<E> output = new SortedSet<>();

        // Quick, easy cases
        if (this.size == 0) {
            return output;
        } else if (otherSet.size() == 0) {
            // Should be quicker than using the SortedSet constructor
            output.size = this.size;
            output.myCon = new ArrayList<>(myCon);
            return output;
        }

        // Other set is a SortedSet
        if (isSorted) {
            findSortedDifference(otherSet, output);
        }

        // Other set is an UnsortedSet
        else {
            Iterator<E> iter = this.iterator();

            while (iter.hasNext()) {
                E temp = iter.next();
                if (!otherSet.contains(temp)) {
                    output.add(temp);
                }
            }
        }

        return output;
    }

    /**
     * O(N) - Two iterators with an O(1) next(), running at most N times where
     * N is the number of elements in this set
     * 
     * In the case where thisator.hasNext() is false but otherator.hasNext() is
     * true, a binary
     * search operation is performed for the last element of this, to see if it
     * exists in the
     * other set, and if not then it is added to the output set
     * 
     * Very likely that I overcomplicated this method
     * 
     * Helper method for difference
     * 
     * @param otherSet
     * @param output
     */
    private void findSortedDifference(ISet<E> otherSet, SortedSet<E> output) {
        Iterator<E> thisator = this.iterator();
        Iterator<E> otherator = otherSet.iterator();

        E thisElement = thisator.next();
        E otherElement = otherator.next();

        while (thisator.hasNext() && otherator.hasNext()) {
            int comparison = thisElement.compareTo(otherElement);

            // If elements are equal, call next on both
            if (comparison == 0) {
                thisElement = thisator.next();
                otherElement = otherator.next();
            }
            // If thisElement is less than otherElement
            // put into output and call next
            else if (comparison < 0) {
                output.myCon.add(thisElement);
                output.size++;
                thisElement = thisator.next();
            }
            // Otherwise, increment other elemement
            else {
                otherElement = otherator.next();
            }
        }

        // Add any remaining elements if more than one elements in this remain
        while (thisator.hasNext()) {
            if (!thisElement.equals(otherElement)) {
                output.myCon.add(thisElement);
                output.size++;
            }
            thisElement = thisator.next();
        }

        // Add the last element, logN search, doesn't matter for big picture of Big O
        if (!otherSet.contains(thisElement)) {
            output.myCon.add(thisElement);
            output.size++;
        }
    }

    /**
     * O(N) if otherSet is instanceOf SortedSet
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

        boolean isSorted = otherSet instanceof SortedSet<?>;
        SortedSet<E> output = new SortedSet<>();

        // Quick, easy case
        if (this.size == 0 || otherSet.size() == 0) {
            return output;
        }

        // Other set is a SortedSet
        if (isSorted) {
            findSortedInterserction(otherSet, output);
        }

        // Other set is an UnsortedSet
        else {
            Iterator<E> iter = iterator();

            while (iter.hasNext()) {
                E temp = iter.next();
                if (otherSet.contains(temp)) {
                    output.add(temp);
                }
            }
        }

        return output;
    }

    /**
     * O(N) - Two iterators with an O(1) next(), running at most N times where
     * N is the number of elements in this set
     * 
     * In the case where thisator.hasNext() is false but otherator.hasNext() is
     * true, a binary
     * search operation is performed for the last element of this, to see if it
     * exists in the
     * other set, and if so then it is added to the output set
     * 
     * Very likely that I overcomplicated this method
     * 
     * Helper method for intersection
     * 
     * @param otherSet
     * @param output
     */
    private void findSortedInterserction(ISet<E> otherSet, SortedSet<E> output) {
        Iterator<E> thisator = this.iterator();
        Iterator<E> otherator = otherSet.iterator();

        E thisElement = thisator.next();
        E otherElement = otherator.next();

        while (thisator.hasNext() && otherator.hasNext()) {
            int comparison = thisElement.compareTo(otherElement);

            // If elements are equal, add to output, call next on both
            if (comparison == 0) {
                output.myCon.add(thisElement);
                output.size++;
                thisElement = thisator.next();
                otherElement = otherator.next();
            }
            // If thisElement is less than otherElement call next
            else if (comparison < 0) {
                thisElement = thisator.next();
            }
            // Otherwise, increment other elemement
            else {
                otherElement = otherator.next();
            }
        }

        // Add any remaining elements if more than one elements in this remain
        while (thisator.hasNext()) {
            if (thisElement.equals(otherElement)) {
                output.myCon.add(thisElement);
                output.size++;
            }
            thisElement = thisator.next();
        }

        // Add the last element
        // uses contains because it's only logN time for a quick search for a single
        // element
        if (otherSet.contains(thisElement)) {
            output.myCon.add(thisElement);
            output.size++;
        }

    }

    /**
     * O(N) if otherSet is an instanceof SortedSet
     * O(N^2) otherwise
     * 
     * Create a new set that is the union of this set and otherSet.
     * <br>
     * pre: otherSet != null
     * <br>
     * post: returns a set that is the union of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br>
     * pre: otherSet != null
     * 
     * @param otherSet != null
     * @return a set that is the union of this set and otherSet
     */
    public ISet<E> union(ISet<E> otherSet) {

        // Check preconditions
        if (otherSet == null) {
            throw new IllegalArgumentException("Violation of preconditions: " +
                    "otherSet != null");
        }

        SortedSet<E> output = new SortedSet<>();

        ArrayList<E> outputArray = output.myCon;

        // Add all elements from this set into the output set
        // Will be merged with the otherSet in the addAll() method
        for (E element : myCon) {
            outputArray.add(element);
            output.size++;
        }

        // The check of whether otherSet is an instanceof SortedSet is being done inside
        // of addAll()
        output.addAll(otherSet);

        return output;
    }

    /**
     * O(logN)
     * Returns the index of element if it exists.
     * otherwise it returns the index of last value searched plus 1, then times -1
     * 
     * This implementation is so that the add method can utlize the last position of
     * the low index to find an index to insert at
     * 
     * @param item
     * @return
     */
    private int indexOf(E item) {
        int low = 0;
        int high = size;
        while (low < high) {
            int mid = low + (high - low) / 2;
            E element = myCon.get(mid);
            int comparision = item.compareTo(element);

            if (comparision == 0) {
                return mid;
            } else if (comparision < 0) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        // Gives two pieces of information:
        // 1) Negative number shows item does not exist in set
        // 2) The last index binary search checked
        return -1 * (low + 1);
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

        // O(logN) look up for the index
        int index = indexOf(item);

        // Non-negative index means element exists
        if (index >= 0) {
            return false;
        }

        // Element does not exist. Binary search last checked (-1 * index) - 1
        // Read the method comment for indexOf() to get more information
        index = (-1 * index) - 1;

        // Element is inserted into array. O(N) for avg and worst times
        myCon.add(index, item);
        size++;
        return true;
    }

    /**
     * O(N) if otherSet is an instanceof SortedSet
     * O(N^2) otherwise
     * 
     * A union operation. Add all items of otherSet that
     * are not already present in this set to this set.
     * 
     * @param otherSet != null
     * @return true if this set changed as a result of this operation,
     *         false otherwise.
     */
    public boolean addAll(ISet<E> otherSet) {
        // Check preconditions
        if (otherSet == null) {
            throw new IllegalArgumentException("Violation of preconditions: " +
                    "otherSet != null");
        }

        // Check if elements exist
        if (otherSet.size() == 0) {
            return false;
        }

        boolean isSorted = otherSet instanceof SortedSet<?>;

        if (isSorted) {

            // O(N) for difference
            ISet<E> temp = otherSet.difference(this);

            // Want to store oldSize to see if elements were actually added to the set or not
            int oldSize = this.size;

            // Adding sorted elements directed to myCon,
            // to be merged using the mergeSort merge in the next step
            for (E element : temp) {
                this.myCon.add(element);
                size++;
            }

            // low is the start index
            // center is the oldSize - 1 as center + 1 the exact index where the second
            // array starts
            // high is this.size - 1 or the last index
            merge(myCon, 0, oldSize - 1, this.size - 1);
            return oldSize != size;
        }

        return super.addAll(otherSet);
    }

    /**
     * O(logN)
     * Determine if item is in this set.
     * <br>
     * pre: item != null
     * 
     * @param item element whose presence is being tested.
     *             Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     */
    public boolean contains(E item) {

        // Check preconditions
        if (item == null) {
            throw new IllegalArgumentException("Violation of preconditions: " +
                    "item != null");
        }

        return indexOf(item) >= 0;
    }

    /**
     * O(N)
     * Determine if all of the elements of otherSet are in this set.
     * <br>
     * pre: otherSet != null
     * 
     * @param otherSet != null
     * @return true if this set contains all of the elements in otherSet,
     *         false otherwise.
     */
    public boolean containsAll(ISet<E> otherSet) {

        // Check preconditions
        if (otherSet == null) {
            throw new IllegalArgumentException("Violation of preconditions: " +
                    "otherSet != null");
        }

        // We'll say that null set is a subset of null set in the case that this.size is
        // also 0
        if (otherSet.size() == 0) {
            return true;
        }

        // Pigeonhole Principle
        if (this.size() < otherSet.size()) {
            return false;
        }

        boolean isSorted = otherSet instanceof SortedSet<?>;

        if (isSorted) {

            ISet<E> difference = otherSet.difference(this);
            return difference.size() == 0;
        }

        // Exists in AbstractSet
        return super.containsAll(otherSet);
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

        // Uses a binary search for the element
        // Likely not too much improvement than simply using ArrayList indexOf for
        // elements that
        // are near the very start of the array, but should be slightly better for most
        // cases
        // Still O(N) because of ArrayList remove() though
        int index = indexOf(item);

        if (index < 0) {
            return false;
        }
        myCon.remove(index);
        size--;
        return true;
    }

    /**
     * O(1)
     * Return the smallest element in this SortedSet.
     * <br>
     * pre: size() != 0
     * 
     * @return the smallest element in this SortedSet.
     */
    public E min() {
        return myCon.get(0);
    }

    /**
     * O(1)
     * Return the largest element in this SortedSet.
     * <br>
     * pre: size() != 0
     * 
     * @return the largest element in this SortedSet.
     */
    public E max() {
        return myCon.get(myCon.size() - 1);
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
     * O(N) is other is an instanceof SortedSet
     * O(N^2) otherwise
     * Determine if this set is equal to other.
     * Two sets are equal if they have exactly the same elements.
     * The order of the elements does not matter.
     * <br>
     * pre: none
     * 
     * @param other the object to compare to this set
     * @return true if other is a Set and has the same elements as this set
     */
    public boolean equals(Object other) {

        if (other == null) {
            return false;
        }
        // Check if other is an instance of an ISet before casting
        if (!(other instanceof SortedSet<?>)) {
            return super.equals(other);
        }

        SortedSet<?> otherSet = (SortedSet<?>) other;

        if (this.size() != otherSet.size()) {
            return false;
        }
        Iterator<E> thisator = this.iterator();
        Iterator<?> otherator = otherSet.iterator();

        // Technically only need to check one hasNext, because both sets are the
        // same size
        while (thisator.hasNext() && otherator.hasNext()) {
            // Check if current elements are equal
            if (!thisator.next().equals(otherator.next())) {
                return false;
            }
        }
        return true;
    }

    /**
     * O(1)
     * Return an Iterator object for the elements of this set.
     * pre: none
     * 
     * @return an Iterator object for the elements of this set
     */
    public Iterator<E> iterator() {
        return new SortedSetIterator();
    }

    /**
     * Iterator for SortedSet
     */
    public class SortedSetIterator implements Iterator<E> {

        Iterator<E> iter;

        public SortedSetIterator() {
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