/* CS 314 STUDENTS: FILL IN THIS HEADER.
 *
 * Student information for assignment:
 *
 *  On my honor, RAHIK N SIKDER, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: rns2359
 *  email address: sikder.rahik@gmail.com
 *  TA name: Casey, the Considerate
 *  Number of slip days I am using: 0
 */

import java.util.LinkedList;
import java.util.List;

/**
 * Shell for a binary search tree class.
 * 
 * @author scottm
 * @param <E> The data type of the elements of this BinarySearchTree.
 *            Must implement Comparable or inherit from a class that implements
 *            Comparable.
 *
 */
public class BinarySearchTree<E extends Comparable<? super E>> {

    // Instance variables
    private BSTNode<E> root;
    private int size;

    /**
     * Add the specified item to this Binary Search Tree if it is not already
     * present.
     * Implemented based on code from the lecture.
     * <br>
     * pre: <tt>value</tt> != null<br>
     * post: Add value to this tree if not already present. Return true if this tree
     * changed as a result of this method call, false otherwise.
     * 
     * @param value the value to add to the tree
     * @return false if an item equivalent to value is already present
     *         in the tree, return true if value is added to the tree and size() =
     *         old size() + 1
     */
    public boolean add(E value) {
        int oldSize = size;
        root = addHelper(root, value);
        return oldSize != size;
    }

    private BSTNode<E> addHelper(BSTNode<E> curNode, E value) {

        // curNode does not exist, so need to create a new node
        if (curNode == null) {
            size++;
            return new BSTNode<>(value);
        }

        // curNode equals the value
        E curVal = curNode.getData();
        int comparison = value.compareTo(curVal);

        // Value exists in Tree Set
        if (comparison == 0) {
            // Nothing happens...
        }

        // Value belongs to left side of tree
        else if (comparison < 0) {
            curNode.setLeft(
                    addHelper(curNode.getLeft(), value));
        }

        // Value belongs to right side of tree
        else {
            curNode.setRight(
                    addHelper(curNode.getRight(), value));
        }

        return curNode;
    }

    /**
     * Remove a specified item from this Binary Search Tree if it is present.
     * Implemented based on code from the lecture.
     * <br>
     * pre: <tt>value</tt> != null<br>
     * post: Remove value from the tree if present, return true if this tree
     * changed as a result of this method call, false otherwise.
     * 
     * @param value the value to remove from the tree if present
     * @return false if value was not present
     *         returns true if value was present and size() = old size() - 1
     */
    public boolean remove(E value) {

        // Check preconditions
        if (value == null) {
            throw new IllegalArgumentException("Violation of preconditions: "
                    + "value != null");
        }

        int oldSize = size;
        root = removeHelper(root, value);
        return oldSize != size;
    }

    private BSTNode<E> removeHelper(BSTNode<E> curNode, E value) {

        // Base Case: curNode is null
        // Rather do this than have the entire code indented
        if (curNode == null) {
            return null;
        }

        int comparison = value.compareTo(curNode.getData());

        // Value is on left side of tree
        if (comparison < 0) {
            curNode.setLeft(
                    removeHelper(curNode.getLeft(), value));
        }

        // Value is on right side of tree
        else if (comparison > 0) {
            curNode.setRight(
                    removeHelper(curNode.getRight(), value));
        }

        // Found value!
        else {

            // The Four Cases, A Java Concerto by Mike "Vivaldi" Scott

            // Spring: Leaf Node
            if (curNode.getLeft() == null && curNode.getRight() == null) {
                curNode = null;
                size--;
            }

            // Summer: Only left child exists
            else if (curNode.getRight() == null) {
                curNode = curNode.getLeft();
                size--;
            }

            // Autumn: Only right child exists
            else if (curNode.getLeft() == null) {
                curNode = curNode.getRight();
                size--;
            }

            // Winter: Both children exist
            else {
                curNode.setData(
                        minHelp(curNode.getRight()));
                curNode.setRight(
                        removeHelper(curNode.getRight(), curNode.getData()));
            }

        }

        return curNode;
    }

    /**
     * Check to see if the specified element is in this Binary Search Tree.
     * <br>
     * pre: <tt>value</tt> != null<br>
     * post: return true if value is present in tree, false otherwise
     * 
     * @param value the value to look for in the tree
     * @return true if value is present in this tree, false otherwise
     */
    public boolean isPresent(E value) {

        // Check preconditions
        if (value == null) {
            throw new IllegalArgumentException("Violation of preconditions: "
                    + "value != null");
        }

        // Case 1: Tree is not empty so we need to search for the element
        BSTNode<E> curNode = root;
        while (curNode != null) {
            E curVal = curNode.getData();
            int comparison = value.compareTo(curVal);

            // Value exists in Tree Set, return true
            if (comparison == 0) {
                return true;
            }

            // Value belongs to left side of tree
            else if (comparison < 0) {
                curNode = curNode.getLeft();
            }

            // Value belongs to right side of tree
            else {
                curNode = curNode.getRight();
            }
        }

        // Element doesn't exist
        return false;
    }

    /**
     * Return how many elements are in this Binary Search Tree.
     * <br>
     * pre: none<br>
     * post: return the number of items in this tree
     * 
     * @return the number of items in this Binary Search Tree
     */
    public int size() {
        return size;
    }

    /**
     * return the height of this Binary Search Tree.
     * <br>
     * pre: none<br>
     * post: return the height of this tree.
     * If the tree is empty return -1, otherwise return the
     * height of the tree
     * 
     * @return the height of this tree or -1 if the tree is empty
     */
    public int height() {
        return heightHelper(root);
    }

    private int heightHelper(BSTNode<E> curNode) {

        // Base Case: curNode is null
        if (curNode == null) {
            return -1;
        }

        return Math.max(
                heightHelper(curNode.getLeft()),
                heightHelper(curNode.getRight()))
                + 1;

    }

    /**
     * Return a list of all the elements in this Binary Search Tree.
     * <br>
     * pre: none<br>
     * post: return a List object with all data from the tree in ascending order.
     * If the tree is empty return an empty List
     * 
     * @return a List object with all data from the tree in sorted order
     *         if the tree is empty return an empty List
     */
    public List<E> getAll() {
        List<E> output = new LinkedList<>();
        getAllHelper(output, root);
        return output;
    }

    private void getAllHelper(List<E> list, BSTNode<E> curNode) {
        if (curNode != null) {
            // Call on left side
            getAllHelper(list, curNode.getLeft());

            // Add data to list
            list.add(curNode.getData());

            // Call on right side
            getAllHelper(list, curNode.getRight());
        }
    }

    /**
     * return the maximum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the largest value in this Binary Search Tree
     * 
     * @return the maximum value in this tree
     */
    public E max() {
        // Case: Root is empty
        if (root == null) {
            return null;
        }

        return maxHelp(root);
    }

    private E maxHelp(BSTNode<E> curNode) {

        // Base Case: Right child does not exist
        if (curNode.getRight() == null) {
            return curNode.getData();
        }

        return maxHelp(curNode.getRight());
    }

    /**
     * return the minimum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the smallest value in this Binary Search Tree
     * 
     * @return the minimum value in this tree
     */
    public E min() {
        // Case: Root is empty
        if (root == null) {
            return null;
        }

        return minHelp(root);
    }

    private E minHelp(BSTNode<E> curNode) {

        // Base Case: Left child does not exist
        if (curNode.getLeft() == null) {
            return curNode.getData();
        }

        return minHelp(curNode.getLeft());
    }

    /**
     * An add method that implements the add algorithm iteratively
     * instead of recursively.
     * <br>
     * pre: data != null
     * <br>
     * post: if data is not present add it to the tree,
     * otherwise do nothing.
     * 
     * @param data the item to be added to this tree
     * @return true if data was not present before this call to add,
     *         false otherwise.
     */
    public boolean iterativeAdd(E data) {

        // Check preconditions
        if (data == null) {
            throw new IllegalArgumentException("Violation of preconditions: "
                    + "data != null");
        }

        // Case 1: Root exists, now we need to search the tree
        BSTNode<E> curNode = root;
        while (curNode != null) {
            E curVal = curNode.getData();
            int comparison = data.compareTo(curVal);

            // Value exists in Tree Set
            if (comparison == 0) {
                return false;
            }
            // Value belongs to left side of tree
            else if (comparison < 0) {
                // If left is null, add value to left
                if (curNode.getLeft() == null) {
                    curNode.setLeft(new BSTNode<>(data));
                    size++;
                    return true;
                }
                // otherwise, just set curNode to left
                curNode = curNode.getLeft();
            }
            // Value belongs to right side of tree
            else {
                // If right is null, add value to right
                if (curNode.getRight() == null) {
                    curNode.setRight(new BSTNode<>(data));
                    size++;
                    return true;
                }
                // otherwise, just set curNode to right
                curNode = curNode.getRight();
            }
        }

        // Case 2: The root doesn't exist so the while loop above doesn't execute
        root = new BSTNode<>(data);
        size++;
        return true;
    }

    /**
     * Return the "kth" element in this Binary Search Tree. If kth = 0 the
     * smallest value (minimum) is returned.
     * If kth = 1 the second smallest value is returned, and so forth.
     * <br>
     * pre: 0 <= kth < size()
     * 
     * @param kth indicates the rank of the element to get
     * @return the kth value in this Binary Search Tree
     */
    public E get(int kth) {

        // Check preconditions
        if (kth < 0 || kth >= size()) {
            throw new IllegalArgumentException("Violation of preconditions: "
                    + "0 <= kth < size()");
        }
        int[] k = { kth };

        return getHelper(k, root);
    }

    private E getHelper(int[] index, BSTNode<E> curNode) {
        if (curNode != null && index[0] >= 0) {
            // Call on left side
            E value = getHelper(index, curNode.getLeft());

            if (value != null) {
                return value;
            }

            // If the index is at 0 then we can return E
            if (index[0] == 0) {
                return curNode.getData();
            }
            // Otherwise decrease the index
            index[0]--;

            // Call on right side
            value = getHelper(index, curNode.getRight());
            if (value != null) {
                return value;
            }
        }
        // No more nodes here
        return null;
    }

    /**
     * Return a List with all values in this Binary Search Tree
     * that are less than the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     * 
     * @param value the cutoff value
     * @return a List with all values in this tree that are less than
     *         the parameter value. If there are no values in this tree less
     *         than value return an empty list. The elements of the list are
     *         in ascending order.
     */
    public List<E> getAllLessThan(E value) {

        // Check preconditions
        if (value == null) {
            throw new IllegalArgumentException("Violation of preconditions: "
                    + "value != null");
        }

        List<E> output = new LinkedList<>();
        getAllLessHelper(output, root, value);
        return output;
    }

    private void getAllLessHelper(List<E> list, BSTNode<E> curNode, E cutoff) {
        if (curNode != null) {
            // Call on left side
            getAllLessHelper(list, curNode.getLeft(), cutoff);

            int comparison = curNode.getData().compareTo(cutoff);

            // Only continue if the current value is less than the cutoff
            if (comparison < 0) {
                // Add data to list
                list.add(curNode.getData());

                // Call on right side
                getAllLessHelper(list, curNode.getRight(), cutoff);
            }

        }
    }

    /**
     * Return a List with all values in this Binary Search Tree
     * that are greater than the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     * 
     * @param value the cutoff value
     * @return a List with all values in this tree that are greater
     *         than the parameter value. If there are no values in this tree
     *         greater than value return an empty list.
     *         The elements of the list are in ascending order.
     */
    public List<E> getAllGreaterThan(E value) {

        // Check preconditions
        if (value == null) {
            throw new IllegalArgumentException("Violation of preconditions: "
                    + "value != null");
        }
        List<E> output = new LinkedList<>();
        getAllGreaterHelper(output, root, value);
        return output;
    }

    private void getAllGreaterHelper(List<E> list, BSTNode<E> curNode, E cutoff) {
        if (curNode != null) {

            // Can get reverse order on a BST by doing a Right - Root - Left traversal
            // or as I'll called it out-order traversal :)
            // it probably has a name already

            // Call on right side
            getAllGreaterHelper(list, curNode.getRight(), cutoff);

            int comparison = curNode.getData().compareTo(cutoff);

            // Only continue if the current value is greater than the cutoff
            if (comparison > 0) {
                // Add data to list
                list.add(0, curNode.getData());

                // Call on left side
                getAllGreaterHelper(list, curNode.getLeft(), cutoff);
            }

        }
    }

    /**
     * Find the number of nodes in this tree at the specified depth.
     * <br>
     * pre: none
     * 
     * @param d The target depth.
     * @return The number of nodes in this tree at a depth equal to
     *         the parameter d.
     */
    public int numNodesAtDepth(int d) {
        return depthHelper(root, 0, d);
    }

    private int depthHelper(BSTNode<E> curNode, int curDepth, int target){

        // If node is null then depth does not matter
        if(curNode == null){
            return 0;
        }

        // Don't need to go any deeper
        if(curDepth == target){
            return 1;
        }

        return depthHelper(curNode.getLeft(), curDepth + 1, target) 
            + depthHelper(curNode.getRight(), curDepth + 1, target);

    }

    /**
     * Prints a vertical representation of this tree.
     * The tree has been rotated counter clockwise 90
     * degrees. The root is on the left. Each node is printed
     * out on its own row. A node's children will not necessarily
     * be at the rows directly above and below a row. They will
     * be indented three spaces from the parent. Nodes indented the
     * same amount are at the same depth.
     * <br>
     * pre: none
     */
    public void printTree() {
        printTree(root, "");
    }

    private void printTree(BSTNode<E> n, String spaces) {
        if (n != null) {
            printTree(n.getRight(), spaces + "  ");
            System.out.println(spaces + n.getData());
            printTree(n.getLeft(), spaces + "  ");
        }
    }

    private static class BSTNode<E extends Comparable<? super E>> {
        private E data;
        private BSTNode<E> left;
        private BSTNode<E> right;

        public BSTNode() {
            this(null);
        }

        public BSTNode(E initValue) {
            this(null, initValue, null);
        }

        public BSTNode(BSTNode<E> initLeft,
                E initValue,
                BSTNode<E> initRight) {
            data = initValue;
            left = initLeft;
            right = initRight;
        }

        public E getData() {
            return data;
        }

        public BSTNode<E> getLeft() {
            return left;
        }

        public BSTNode<E> getRight() {
            return right;
        }

        public void setData(E theNewValue) {
            data = theNewValue;
        }

        public void setLeft(BSTNode<E> theNewLeft) {
            left = theNewLeft;
        }

        public void setRight(BSTNode<E> theNewRight) {
            right = theNewRight;
        }
    }
}