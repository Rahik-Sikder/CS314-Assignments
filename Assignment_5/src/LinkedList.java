/*
 * Student information for assignment: 
 * On my honor, RAHIK N SIKDER, this programming assignment is my own work 
 * and I have not provided this code to any other student. 
 * UTEID: rns2359
 * email address: sikder.rahik@utexas.edu
 * Number of slip days I am using: 2
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements IList<E> {

    // CS314 students. Add you instance variables here.
    /**
     * Header node:
     * -> prev = last node of list
     * -> data = null
     * -> next = first node of list
     */
    private final DoubleListNode<E> HEADER;
    private int size;

    // CS314 students, add constructors here:

    /**
     * Default constructor
     * 
     * Initializes the HEADER node
     */
    public LinkedList() {
        HEADER = new DoubleListNode<E>();
        HEADER.setPrev(HEADER);
        HEADER.setNext(HEADER);
    }

    // CS314 students, add methods here:

    /**
     * Big O => O(N)
     * 
     * Get an element from the list.
     * 
     * pre: 0 <= pos < size()
     * post: return the item at pos
     * 
     * @param pos - specifies which element to get
     * @return the element at the specified position in the list
     */
    public E get(int pos) {
        // getNode() checks the preconditions
        return getNode(pos).getData();
    }

    /**
     * Big O => O(N)
     * 
     * Change the data at the specified position in the list. the old data at that
     * position is returned.
     * 
     * pre: 0 <= pos < size(), item != null
     * post: get(pos) = item, return the old get(pos)
     * 
     * @param pos  - the position in the list to overwrite
     * @param item - the new item that will overwrite the old item, item != null
     * @return the old data at the specified position
     */
    public E set(int pos, E item) {

        // Check preconditions
        if (pos < 0 || pos >= size() || item == null) {
            throw new IllegalArgumentException("Violation of preconditions: "
                    + "0 <= pos < size(), item != null");
        }

        // Get the node at pos
        DoubleListNode<E> curNode = getNode(pos);

        // Save old data
        E oldData = curNode.getData();

        // Set data to item
        curNode.setData(item);

        // Return old data
        return oldData;
    }

    /**
     * Big O => O(N)
     * 
     * Get a node from the list.
     * If pos = size(), the method return the HEADER
     * 
     * pre: 0 <= pos <= size()
     * post: return the node at pos
     * 
     * @param - specifies which element to get
     * @return the node at the specified position in the list
     */
    private DoubleListNode<E> getNode(int pos) {

        // Check preconditions
        if (pos < 0 || pos > size()) {
            throw new IllegalArgumentException("Violation of preconditions: "
                    + "0 <= pos <= size()");
        }

        // Find if it's more optiminal to access from front or back
        boolean getFromFront = pos <= (size() - 1) / 2;

        // Find first node to increment from
        DoubleListNode<E> curNode = HEADER;

        // If getting from front, for loop runs pos + 1 times
        // If getting from back, for loop runs size - pos times
        int numRuns = getFromFront ? pos + 1 : size() - pos;
        for (int i = 0; i < numRuns; i++) {
            curNode = getFromFront ? curNode.getNext() : curNode.getPrev();
        }

        return curNode;
    }

    /**
     * Big O => O(1)
     * 
     * Return the size of this list. In other words the number of elements in this
     * list.
     * 
     * pre: none
     * post: return the number of items in this list
     * 
     * @return the number of items in this list
     */
    public int size() {
        return size;
    }

    /**
     * Big O => O(1)
     * 
     * Add an item to the end of this list.
     * pre: item != null
     * post: size() = old size() + 1, get(size() - 1) = item
     * 
     * @param item the data to add to the end of this list
     */
    public void add(E item) {

        // Check preconditions
        if (item == null) {
            throw new IllegalArgumentException("Violation of preconditions "
                    + "item != null");
        }

        // Get the last node from HEADER
        DoubleListNode<E> lastNode = HEADER.getPrev();

        // Create new last node
        DoubleListNode<E> newLastNode = new DoubleListNode<E>(lastNode, item, HEADER);

        // Link the old lastNode to the newLastNode
        lastNode.setNext(newLastNode);

        // Update HEADER
        HEADER.setPrev(newLastNode);

        // Update size
        size++;

    }

    /**
     * Big O => O(1)
     * 
     * Can also simplify code by calling insert() at
     * pos 0, which would also be O(1) because getNode()
     * will get the first node in O(1) time.
     * 
     * Ended up not doing so because this implementation
     * makes the Big O very clear.
     * 
     * add item to the front of the list.
     * pre: item != null
     * post: size() = old size() + 1, get(0) = item
     * 
     * @param item the data to add to the front of this list
     */
    public void addFirst(E item) {

        // Check preconditions
        if (item == null) {
            throw new IllegalArgumentException("Violation of preconditions "
                    + "item != null");
        }

        // Get the first node from HEADER
        DoubleListNode<E> firstNode = HEADER.getNext();

        // Create a new first node
        DoubleListNode<E> newFirstNode = new DoubleListNode<>(HEADER, item, firstNode);

        // Link the old firstNode to the newFirstNode
        firstNode.setPrev(newFirstNode);

        // Update HEADER
        HEADER.setNext(newFirstNode);

        // Update size
        size++;

    }

    /**
     * Big O => O(1)
     * 
     * add item to the end of the list.
     * pre: item != null
     * post: size() = old size() + 1, get(size() -1) = item
     * 
     * @param item the data to add to the end of this list
     */
    public void addLast(E item) {
        add(item);
    }

    /**
     * Big O => O(N)
     * 
     * Insert an item at a specified position in the list.
     * 
     * pre: 0 <= pos <= size(), item != null
     * post: size() = old size() + 1, get(pos) = item,
     * all elements in the list with a positon >= pos have a position = old position
     * + 1
     * 
     * @param pos  - the position to insert the data at in the list
     * @param item - the data to add to the list, item != null
     */
    public void insert(int pos, E item) {

        // Check precondtions
        if (pos < 0 || pos > size() || item == null) {
            throw new IllegalArgumentException("Violation of preconditions: "
                    + "0 <= pos <= size(), item != null");
        }

        // Get the element right before pos and at pos
        DoubleListNode<E> nextNode = getNode(pos);
        DoubleListNode<E> prevNode = nextNode.getPrev();

        // Create a new node, set the prev and next
        DoubleListNode<E> curNode = new DoubleListNode<E>(prevNode, item, nextNode);

        // Update prevNode
        prevNode.setNext(curNode);

        // Update nextNode
        nextNode.setPrev(curNode);

        // Update size
        size++;

    }

    /**
     * Big O => O(N)
     * 
     * Remove an element in the list based on position.
     * pre: 0 <= pos < size()
     * post: size() = old size() - 1,
     * all elements of list with a position > pos have a position = old position - 1
     * 
     * @param pos - the position of the element to remove from the list
     * @return the data at position pos
     */
    public E remove(int pos) {

        // Check preconditions
        if (pos < 0 || pos >= size()) {
            throw new IllegalStateException("0 <= pos < size()");
        }

        // Get the node about to be remove
        DoubleListNode<E> curNode = getNode(pos);

        // Removes curNode from list
        removeNode(curNode);

        // Return the data from curNode
        return curNode.getData();

    }

    /**
     * Big O => O(1)
     * 
     * remove and return the first element of this list.
     * pre: size() > 0
     * post: size() = old size() - 1
     * 
     * @return the old first element of this list
     */
    public E removeFirst() {

        // Check preconditions
        if (size == 0) {
            throw new IllegalStateException("No elements in Linked List");
        }
        // The getNode method in remove() gets first element in O(1) time
        return remove(0);
    }

    /**
     * Big O => O(1)
     * 
     * remove and return the last element of this list.
     * pre: size() > 0
     * post: size() = old size() - 1
     * 
     * @return the old last element of this list
     */
    public E removeLast() {

        // Check preconditions
        if (size == 0) {
            throw new IllegalStateException("No elements in Linked List");
        }
        // The getNode method in remove() gets last element in O(1) time
        return remove(size() - 1);
    }

    /**
     * Big O => O(N)
     * 
     * Remove all elements in this list from start inclusive to stop exclusive.
     * 
     * pre: 0 <= start <= size(), start <= stop <= size()
     * post: size() = old size() - (stop - start)
     * 
     * @param start - position at beginning of range of elements to be removed
     * @param stop  - stop - 1 is the position at the end of the range of elements
     *              to be removed
     */
    public void removeRange(int start, int stop) {
        // Check preconditions
        if (start < 0 || start > size() || start > stop || stop > size()) {
            throw new IllegalArgumentException("Violation of preconditions: "
                    + "0 <= start <= size(), start <= stop <= size()");
        }

        // Get node at start
        // This node will be removed
        DoubleListNode<E> startNode = getNode(start);

        // Get node at stop
        // This node will not be removed
        DoubleListNode<E> stopNode = getNode(stop);

        // Link the node before startNode to stopNode
        startNode.getPrev().setNext(stopNode);

        // Link stopNode to the node before startNode
        stopNode.setPrev(startNode.getPrev());

        // Update size
        size -= stop - start;
    }

    /**
     * Big O => O(1)
     * 
     * Removes the given node from the list
     * 
     * pre: node.getNext() != null, node.getPrev() != null,
     * node != HEADER
     * 
     * @param node - the node to be removed
     */
    private void removeNode(DoubleListNode<E> node) {

        // Check preconditions
        if (node.getNext() == null || node.getPrev() == null || node == HEADER) {
            throw new IllegalArgumentException("Violation of preconditions: "
                    + "node.getNext() != null, node.getPrev() != null, node != HEADER");
        }

        // Get the prev and next nodes
        DoubleListNode<E> prevNode = node.getPrev();
        DoubleListNode<E> nextNode = node.getNext();

        // Link prevNode to nextNode
        prevNode.setNext(nextNode);

        // Link nextNode to prevNode
        nextNode.setPrev(prevNode);

        // Update size
        size--;

    }

    /**
     * Big O => O(N)
     * 
     * Remove the first occurrence of obj in this list.
     * Return true if this list changed
     * as a result of this call, false otherwise.
     * 
     * pre: obj != null
     * post: if obj is in this list the first occurrence
     * has been removed and size() = old size() - 1.
     * If obj is not present the list is not altered in any way.
     * 
     * @param obj The item to remove from this list. obj != null
     * @return Return true if this list changed
     *         as a result of this call, false otherwise.
     */
    public boolean remove(E obj) {

        // Check preconditions
        if (obj == null) {
            throw new IllegalArgumentException("Violation of preconditions: "
                    + "obj != null");
        }

        // Get the first node
        DoubleListNode<E> curNode = HEADER.getNext();

        // Go through all elements in list
        while (curNode != HEADER) {
            // See if curNode contains obj
            if (curNode.getData().equals(obj)) {
                removeNode(curNode);
                return true;
            }
            curNode = curNode.getNext();
        }
        return false;
    }

    /**
     * Big O => O(1)
     * 
     * return the list to an empty state.
     * 
     * pre: none
     * post: size() = 0
     */
    public void makeEmpty() {
        HEADER.setPrev(HEADER);
        HEADER.setNext(HEADER);
        size = 0;
    }

    /**
     * Big O => O(N)
     * 
     * Return a sublist of elements in this list
     * from start inclusive to stop exclusive.
     * This list is not changed as a result of this call.
     * 
     * pre: 0 <= start <= size(), start <= stop <= size()
     * post: return a list whose size is stop - start
     * and contains the elements at positions start through stop - 1
     * in this list.
     * 
     * @param start index of the first element of the sublist.
     * @param stop  stop - 1 is the index of the last element
     *              of the sublist.
     * @return a list with stop - start elements,
     *         The elements are from positions start inclusive to
     *         stop exclusive in this list.
     *         If start == stop an empty list is returned.
     */
    public IList<E> getSubList(int start, int stop) {

        // Check preconditions
        if (start < 0 || start > size() || start > stop || stop > size()) {
            throw new IllegalArgumentException("Violation of preconditions: "
                    + "0 <= start <= size(), start <= stop <= size()");
        }

        IList<E> output = new LinkedList<>();

        // Get start and stop node
        DoubleListNode<E> curNode = getNode(start);
        DoubleListNode<E> stopNode = getNode(stop);

        // Iterate through list until cur = stop
        while (curNode != stopNode) {
            output.add(curNode.getData());
            curNode = curNode.getNext();
        }

        return output;
    }

    /**
     * Big O => O(N)
     * 
     * Return a String version of this list enclosed in square brackets, [].
     * Elements are in are in order based on position in the list with the
     * first element first. Adjacent elements are separated by comma's
     */
    @Override
    public String toString() {

        StringBuilder output = new StringBuilder("[");

        // Instantiate an iterator
        Iterator<E> iterator = iterator();

        // Iterate through elements
        if (iterator.hasNext()) {
            output.append(iterator.next());
        }
        while (iterator.hasNext()) {
            output.append(", ").append(iterator.next());
        }
        output.append("]");
        return output.toString();
    }

    /**
     * Big O => O(N)
     * 
     * Determine if this IList is equal to other.
     * Two ILists are equal if they contain the same elements in the same order.
     * 
     * @param other - object being compared to this
     * @return - true if this IList is equal to other, false otherwise
     */
    public boolean equals(Object other) {

        // Checks if both refer to the same LinkedList object
        if (other == this) {
            return true;
        }

        // instanceof returns false if other is null
        if (!(other instanceof IList)) {
            return false;
        }

        // Cast to IList
        IList<?> o = (IList<?>) other;

        // Get iterators for both objects
        Iterator<?> otherator = o.iterator();
        Iterator<E> thisator = iterator();

        // Iterate through all elemtns
        while (thisator.hasNext()) {
            if (!otherator.hasNext()) {
                // Lists are not the same size
                return false;
            }
            if (!thisator.next().equals(otherator.next())) {
                return false;
            }
        }

        if (otherator.hasNext()) {
            // Lists are not the same size
            return false;
        }

        return true;
    }

    /**
     * Big O => O(N)
     * 
     * Find the position of an element in the list.
     * pre: item != null
     * post: return the index of the first element equal to item
     * or -1 if item is not present
     * 
     * @param item the element to search for in the list. item != null
     * @return return the index of the first element equal to item
     *         or a -1 if item is not present
     */
    public int indexOf(E item) {

        // Check preconditions
        if (item == null) {
            throw new IllegalArgumentException("Violation of preconditions: "
                    + "item != null");
        }

        return indexOf(item, 0);
    }

    /**
     * Big O => O(N)
     * 
     * find the position of an element in the list starting
     * at a specified position.
     * pre: 0 <= pos < size(), item != null
     * post: return the index of the first element equal
     * to item starting at pos
     * or -1 if item is not present from position pos onward
     * 
     * @param item the element to search for in the list. Item != null
     * @param pos  the position in the list to start searching from
     * @return starting from the specified position
     *         return the index of the first element equal to item
     *         or a -1 if item is not present between pos
     *         and the end of the list
     */
    public int indexOf(E item, int pos) {

        // Check preconditions
        if (item == null || pos < 0 || pos >= size()) {
            throw new IllegalArgumentException("Violation of preconditions: "
                    + "pre: 0 <= pos < size(), item != null");
        }

        // Get the node at pos
        DoubleListNode<E> curNode = getNode(pos);

        int index = pos;

        while (curNode != HEADER) {
            if (curNode.getData().equals(item)) {
                return index;
            }
            curNode = curNode.getNext();
            index++;
        }

        return -1;
    }

    /**
     * A class that represents a node to be used in a linked list.
     * These nodes are doubly linked. All methods are O(1).
     *
     * @author Mike Scott
     * @version 9/25/2023
     */
    private static class DoubleListNode<E> {

        // instance variables

        // The data stored in this node.
        private E data;

        // The link to the next node (presumably in a list).
        private DoubleListNode<E> next;

        // The link to the previous node (presumably in a list).
        private DoubleListNode<E> prev;

        /**
         * default constructor.
         * 
         * pre: none
         * post: getData() = null, getNext() = null, getPrev() = null
         */
        public DoubleListNode() {
            this(null, null, null);
        }

        /**
         * create a DoubleListNode that holds the specified data
         * and refers to the specified next and previous elements.
         * 
         * pre: none
         * 
         * post: getData() = data, getNext() = next, getPrev() = prev
         * 
         * @param prev the previous node
         * @param data the data this DoubleListNode should hold
         * @param next the next node
         */
        public DoubleListNode(DoubleListNode<E> prev, E data,
                DoubleListNode<E> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }

        /*
         * Note, the following methods are not necessary.
         * They are provided as a convenience. As this class is a private
         * nested class the only client is the LinkedList class itself and
         * the implementation of Iterator. We leave it up to the student
         * whether to access and change the private instance variables of a
         * node directly or via these methods.
         * 
         * Rahik Sikder:
         * It would be a shame if these methods were put to waste.
         * I don't like wasting.
         * I don't like shame either.
         * Therefore, I'll use these methods.
         */
        /**
         * return the data in this node.
         * 
         * pre: none
         * 
         * @return the data this DoubleListNode holds
         */
        public E getData() {
            return data;
        }

        /**
         * return the DoubleListNode this ListNode refers to.
         * 
         * pre: none
         * 
         * @return the DoubleListNode this DoubleListNode refers to
         *         (normally the next one in a list)
         */
        public DoubleListNode<E> getNext() {
            return next;
        }

        /**
         * return the DoubleListNode this DoubleListNode refers to.
         * 
         * pre: none
         * 
         * @return the DoubleListNode this DoubleListNode refers to
         *         (normally the previous one in a list)
         */
        public DoubleListNode<E> getPrev() {
            return prev;
        }

        /**
         * set the data in this node.
         * The old data is over written.
         * 
         * pre: none
         * 
         * post: getData() == data
         * 
         * @param data the new data for this DoubleListNode to hold
         */
        public void setData(E data) {
            this.data = data;
        }

        /**
         * set the next node this DoubleListNode refers to.
         * 
         * pre: none
         * 
         * post: getNext() = next
         * 
         * @param next the next node this DoubleListNode should refer to
         */
        public void setNext(DoubleListNode<E> next) {
            this.next = next;
        }

        /**
         * set the previous node this DoubleListNode refers to.
         * 
         * pre: none
         * 
         * post: getPrev() = next
         * 
         * @param prev the previous node this DoubleListNode should refer to
         */
        public void setPrev(DoubleListNode<E> prev) {
            this.prev = prev;
        }

    }

    /**
     * pre: none
     * post: return an Iterator object for this List
     * 
     * @return an Iterator for this list.
     */
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    /**
     * A class that implements an Iterator for the
     * LinkedList class
     * 
     * @author Rahik Sikder
     * @version 10/4/2023
     */
    public class LinkedListIterator implements Iterator<E> {

        DoubleListNode<E> nextNode;
        boolean canRemove;

        public LinkedListIterator() {
            nextNode = HEADER.getNext();
            canRemove = false;
        }

        public boolean hasNext() {
            // Compares object references
            return nextNode != HEADER;
        }

        public E next() {

            // Check preconditions
            if (!hasNext()) {
                throw new NoSuchElementException("hasNext() != true");
            }

            // Get data of the next node
            E curData = nextNode.getData();

            // Shift nextNode to next node
            nextNode = nextNode.getNext();

            // Update canRemove
            canRemove = true;

            // Return data
            return curData;
        }

        public void remove() {
            LinkedList.this.removeNode(nextNode.getPrev());
            canRemove = false;
        }

    }

}