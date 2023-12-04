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

/**
 * Students are to complete this class.
 * Students should implement as many methods
 * as they can using the Iterator from the iterator
 * method and the other methods.
 *
 */
public abstract class AbstractSet<E> implements ISet<E> {

    /**
     * O(N^2)
     * A union operation. Add all items of otherSet that
     * are not already present in this set to this set.
     * 
     * @param otherSet != null
     * @return true if this set changed as a result of this operation,
     *         false otherwise.
     */
    public boolean addAll(ISet<E> otherSet){

        // Check preconditions
        if(otherSet == null){
            throw new IllegalArgumentException("Violation of preconditions: " + 
                "otherSet != null");   
        }

        // Check if elements exist
        if(otherSet.size() == 0){
            return false;
        }

        int oldSize = this.size();

        for(E element : otherSet){
            this.add(element);
        }

        return oldSize != this.size();
    }

    /**
     * O(N)
     * Make this set empty.
     * <br>pre: none
     * <br>post: size() = 0
     */
    public void clear(){
        Iterator<E> iter = iterator();
        while(iter.hasNext()){
            iter.next();
            iter.remove();
        }
    }


    /**
     * O(N)
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
        if(item == null){
            throw new IllegalArgumentException("Violation of preconditions: " + 
                "item != null");   
        }
        
        for (E element : this) {
            if (element.equals(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * O(N^2)
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
        if(otherSet == null){
            throw new IllegalArgumentException("Violation of preconditions: " + 
                "otherSet != null");   
        }

        // We'll say that null set is a subset of null set in the case that this.size is
        // also 0
        if(otherSet.size() == 0){
            return true;
        }

        // Pigeonhole Principle
        if(this.size() < otherSet.size()){
            return false;
        }

        for (E element : otherSet) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * O(N^2)
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

        if(other == null){
            return false;
        }

        // Check if other is an instance of an ISet before casting
        if (!(other instanceof ISet<?>)) {
            return false;
        }

        ISet<?> otherSet = (ISet<?>) other;

        // If sizes are difference, immediately return false
        if (otherSet.size() != this.size()) {
            return false;
        }


        // Have to use iterator in inner loop because the Generic type of otherSet is unknown.
        // Also means contains() or containsAll() can't be used.
        for (E element : this) {
            Iterator<?> iter = otherSet.iterator();
            boolean containsElement = false;
            while (!containsElement && iter.hasNext()) {
                if (element.equals(iter.next())) {
                    containsElement = true;
                }
            }
            if (!containsElement) {
                return false;
            }
        }

        return true;
    }

    /**
     * O(N^2)
     * 
     * Create a new set that is the union of this set and otherSet.
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the union of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the union of this set and otherSet
     */
    public ISet<E> union(ISet<E> otherSet){

        // Check preconditions
        if(otherSet == null){
            throw new IllegalArgumentException("Violation of preconditions: " + 
                "otherSet != null");   
        }

        // Have to call either difference() or intersection() to get an instance of ISet<E> to
        // return at the end. This is why all three cannot be implemented in AbstractSet
        ISet<E> output = this.difference(otherSet);
        output.addAll(otherSet);

        return output;
    }


    /**
     * O(N)
     * 
     * Return a String version of this set.
     * Format is (e1, e2, ... en)
     * 
     * @return A String version of this set.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        String seperator = ", ";
        result.append("(");

        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            result.append(seperator);
        }
        // get rid of extra separator
        if (this.size() > 0 && (result.length() - seperator.length() > 0)) {
            result.setLength(result.length() - seperator.length());
        }

        result.append(")");
        return result.toString();
    }
}