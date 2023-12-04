import java.io.IOException;
import java.util.HashMap;

public class HuffmanTree implements IHuffConstants {

    // Instance Variables
    private TreeNode root;
    private IHuffViewer myViewer;
    private HashMap<Integer, String> huffMap;
    private int internalNodes;
    private int leafNodes;

    // Constants
    private final char ZERO = '0';

    public HuffmanTree(int headerFormat, int[] data, IHuffViewer viewer) {

        setViewer(viewer);
        if (headerFormat == STORE_COUNTS) {
            setWithCountFormat(data);
        } else if (headerFormat == STORE_TREE) {
            setWithTreeFormat(data);
        }
    }

    public void fillEncodingMap() {
        fillEncodingMapHelper(root, "");
    }

    private void fillEncodingMapHelper(TreeNode curNode, String curPath) {
        if (curNode != null) {

            // If the node is a leaf then add path to map
            if (curNode.isLeaf()) {
                huffMap.put(curNode.getValue(), curPath.toString());
            }
            // Otherwise, add onto path and call its children
            else {
                fillEncodingMapHelper(curNode.getLeft(), curPath + "0");
                fillEncodingMapHelper(curNode.getRight(), curPath + "1");
            }

        }
    }

    /**
     * Creates a Huffman tree using a given array from a Standard Count Format
     * 
     * @param freqs
     * @return
     */
    private void setWithCountFormat(int[] freqs) {
        // Go through every freq, create a node, and add it to a priority queue
        MyPriorityQueue<TreeNode> queue = new MyPriorityQueue<>();
        for (int value = 0; value < ALPH_SIZE; value++) {
            if (freqs[value] > 0) {
                queue.offer(new TreeNode(value, freqs[value]));
            }
        }

        // Add the PSEUDO_EOF
        queue.offer(new TreeNode(PSEUDO_EOF, 1));

        leafNodes = queue.size();

        // Condense the priority queue into a tree with huffTreeRoot as the root
        while (queue.size() > 1) {
            TreeNode nodeA = queue.poll();
            TreeNode nodeB = queue.poll();
            // The value for this newNode does not matter
            TreeNode newNode = new TreeNode(nodeA, -1, nodeB);
            queue.offer(newNode);
            internalNodes++;
        }

        root = queue.poll();
        huffMap = new HashMap<>();
        fillEncodingMap();
    }

    /**
     * Creates a Huffman tree using a given array from a Standard Tree Format
     * The array should have nodes in preorder traversal, with internal nodes being
     * equal to -1
     * 
     * @param nodes
     * @return
     */
    private void setWithTreeFormat(int[] nodes) {

        internalNodes = 0;
        leafNodes = 0;
        root = setWithTreeHelper(nodes, new int[] { 0 });

        // Not necessary to run bc if this constructor is run we are uncompressing
        // huffMap = new HashMap<>();
        // fillEncodingMap();
        huffMap = null;
    }

    /*
     * If the header is created properly, index should never be out of bounds.
     */
    private TreeNode setWithTreeHelper(int[] nodes, int[] index) {

        if (index[0] < nodes.length) {
            // If node is internal, create the left and right children then return the
            // internal
            if (nodes[index[0]] == -1) {
                internalNodes++;
                index[0]++;
                TreeNode leftChild = setWithTreeHelper(nodes, index);
                TreeNode rightChild = setWithTreeHelper(nodes, index);

                return new TreeNode(leftChild, -1, rightChild);
            }
            // Otherwise, set the value to the node value and return leaf
            // Frequency irrelavent bc Tree was already constructed during compression
            leafNodes++;
            int val = nodes[index[0]];
            index[0]++;
            return new TreeNode(val, -1);
        }
        return null;
    }

    /**
     * Writes the String path representation of a given value if present in huffMap
     * Returns the number of bits written
     * 
     * @param outStream
     * @param path
     */
    public int writeStringPath(BitOutputStream outStream, Integer value) {

        String path = huffMap.get(value);
        int bitsWritten = 0;
        if (path != null) {
            bitsWritten = path.length();
            for (char c : path.toCharArray()) {
                if (c == ZERO) {
                    outStream.writeBits(1, 0);
                } else {
                    outStream.writeBits(1, 1);
                }
            }
        }
        return bitsWritten;
    }

    public int writeSCFBits(BitOutputStream outStream, int[] freqs) {
        int compressedBits = 0;
        for (int k = 0; k < ALPH_SIZE; k++) {
            outStream.writeBits(BITS_PER_INT, freqs[k]);
            compressedBits += BITS_PER_INT;
        }
        return compressedBits;
    }

    /**
     * Returns the number of bits written
     * 
     * @param outStream
     * @return
     */
    public int writeSTFBits(BitOutputStream outStream) {
        // Write the number of bits the tree is going to store
        int lengthOfTree = numSTFBits();
        outStream.writeBits(BITS_PER_INT, lengthOfTree);
        return BITS_PER_INT + writeSTFBitsHelper(root, outStream);
    }

    private int writeSTFBitsHelper(TreeNode curNode, BitOutputStream outStream) {
        int bitsWritten = 0;
        // This check should be irrelavant because HuffmanTree is a full Binary tree
        if (curNode != null) {
            // If the node is a leaf then write 1 plus the in value
            if (curNode.isLeaf()) {
                outStream.writeBits(1, 1);
                bitsWritten = 1;
                outStream.writeBits(BITS_PER_WORD + 1, curNode.getValue());
                bitsWritten += BITS_PER_WORD + 1;
            }
            // Otherwise, write 0 and then call its children
            else {
                outStream.writeBits(1, 0);
                bitsWritten = 1;
                bitsWritten += writeSTFBitsHelper(curNode.getLeft(), outStream);
                bitsWritten += writeSTFBitsHelper(curNode.getRight(), outStream);
            }
        }
        return bitsWritten;
    }

    /**
     * Returns the number of bits written in the header data for Standard Tree
     * Format
     * Does not include the initial int detailing the num of STF bits
     * 
     * @return
     */
    public int numSTFBits() {
        return internalNodes + leafNodes + leafNodes * (BITS_PER_WORD + 1);
    }

    /**
     * Uncompress the data in a file
     * 
     * @param inStream
     * @param outsteam
     * @return
     */
    public int writeUncompress(BitInputStream inStream, BitOutputStream outSteam)
            throws IOException {

        boolean outOfBits = false;
        int uncompressedBits = 0;
        TreeNode curNode = root;
        while (!outOfBits) {

            // First check if curNode is a leaf
            // Needs to be done first in the case we are uncompressing a blank file where
            // the root
            // node is the PEOF
            if (curNode.isLeaf()) {
                Integer value = curNode.getValue();
                if (value == PSEUDO_EOF) {
                    inStream.close();
                    outSteam.close();
                    return uncompressedBits;
                } else {
                    outSteam.writeBits(BITS_PER_WORD, value);
                    uncompressedBits += BITS_PER_WORD;
                    // Reset path
                    curNode = root;
                }
            }

            // Now move the curNode according to the path
            int curBit = inStream.readBits(1);
            // Move left
            if (curBit == 0) {
                curNode = curNode.getLeft();
            }
            // Move right
            else if (curBit == 1) {
                curNode = curNode.getRight();
            }
            // Reached end of file
            else {
                outOfBits = true;
            }
        }

        // If this code is reached then a PSUEDO_EOF value does not exist in file
        throw new IOException("Error reading compressed file. \n" +
                "unexpected end of input. No PSEUDO_EOF value.");
    }

    /**
     * Returns the huffMap
     * 
     * @return
     */
    public HashMap<Integer, String> getMap() {
        return huffMap;
    }

    public void printHuffMap() {
        for (Integer x : huffMap.keySet()) {
            showString("value: " + x + " path: " + huffMap.get(x));
        }
    }

    public void setViewer(IHuffViewer viewer) {
        myViewer = viewer;
    }

    private void showString(String s) {
        if (myViewer != null) {
            myViewer.update(s);
        }
    }

}
