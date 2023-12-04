/*  Student information for assignment:
 *
 *  On MY honor, RAHIK N SIKDER, this programming assignment is MY own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 0 
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: rns2359
 *  email address: sikder.rahik@utexas.edu
 *  Grader name: Casey, the Knowledgable
 *  Happy Birthday btw :)
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class SimpleHuffProcessor implements IHuffProcessor {

    // Instance variables
    private IHuffViewer myViewer;
    private HuffmanTree huffTree;
    private boolean hasPreprocessed; // Likely not needed, but kept anyways for extra safeguarding
    private boolean shouldCompress; // true when preprocess() >= 0
    private int[] freqs;
    private int header;

    /**
     * Preprocess data so that compression is possible ---
     * count characters/create tree/store state so that
     * a subsequent call to compress will work. The InputStream
     * is <em>not</em> a BitInputStream, so wrap it int one as needed.
     * 
     * @param in           is the stream which could be subsequently compressed
     * @param headerFormat a constant from IHuffProcessor that determines what kind
     *                     of
     *                     header to use, standard count format, standard tree
     *                     format, or
     *                     possibly some format added in the future.
     * @return number of bits saved by compression or some other measure
     *         Note, to determine the number of
     *         bits saved, the number of bits written includes
     *         ALL bits that will be written including the
     *         magic number, the header format number, the header to
     *         reproduce the tree, AND the actual data.
     * @throws IOException if an error occurs while reading from the input file.
     */
    public int preprocessCompress(InputStream in, int headerFormat) throws IOException {
        // showString("Running preprocessCompress");

        // Init freqs, header, ogBits, and compressedBitSize. 
        // Latter two only scoped for method
        freqs = new int[ALPH_SIZE];
        header = headerFormat;
        int ogBits = 0;
        int compressedBitSize = 0;

        // Go through each byte in file
        BitInputStream inStream = new BitInputStream(in);
        int curValue = inStream.read();
        while (curValue != -1) {
            freqs[curValue]++;
            curValue = inStream.read();

            // Count num of bits in the original file
            ogBits += BITS_PER_WORD;
        }
        inStream.close();

        // Create a HuffMan tree using SCF
        huffTree = new HuffmanTree(STORE_COUNTS, freqs, myViewer);
        HashMap<Integer, String> huffMap = huffTree.getMap();
        hasPreprocessed = true;

        // Preprocessing Finished - Now find number of compressed bits
        // First add the Magic Number and the Header, which are both BITS_PER_INT
        compressedBitSize += BITS_PER_INT * 2;

        // Then add the freq / tree data
        if (header == STORE_COUNTS) {
            compressedBitSize += ALPH_SIZE * BITS_PER_INT; // Basically the entire array w/out PEOF
        } else if (header == STORE_TREE) {
            compressedBitSize += BITS_PER_INT; // Integer representing num of STF bits
            compressedBitSize += huffTree.numSTFBits(); // num of STF bits
        }

        // Then add the compressed data
        for (int i = 0; i < ALPH_SIZE; i++) {
            if (huffMap.get(i) != null) {
                compressedBitSize += freqs[i] * huffMap.get(i).length();
            }
        }
        // Finally add PEOF path
        compressedBitSize += huffMap.get(PSEUDO_EOF).length();

        return calcDifference(ogBits, compressedBitSize);
    }

    /**
     * Returns diff between original bits in file and the compressed file bits.
     * Also updates shouldCompress
     * Point of this method is to meet the arbritrary method line limit.
     * 
     * @param ogBits
     * @param compressedBits
     * @return
     */
    private int calcDifference(int ogBits, int compressedBits) {
        int difference = ogBits - compressedBits;
        // showString("Original bits: " + ogBits);
        // showString("Compressed bits: " + compressedBitSize);
        // showString("Difference: " + difference + "\n");

        shouldCompress = difference >= 0;
        return difference;
    }

    /**
     * Compresses input to output, where the same InputStream has
     * previously been pre-processed via <code>preprocessCompress</code>
     * storing state used by this call.
     * <br>
     * pre: <code>preprocessCompress</code> must be called before this method
     * 
     * @param in    is the stream being compressed (NOT a BitInputStream)
     * @param out   is bound to a file/stream to which bits are written
     *              for the compressed file (not a BitOutputStream)
     * @param force if this is true create the output file even if it is larger than
     *              the input file.
     *              If this is false do not create the output file if it is larger
     *              than the input file.
     * @return the number of bits written.
     * @throws IOException if an error occurs while reading from the input file or
     *                     writing to the output file.
     */
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException {

        // Check preconditions
        if (!hasPreprocessed) {
            // Only should run if logic error occurs -> Scott said not to use
            // IOException for this
            throw new IllegalAccessError("No InputStream has been preprocessed prior to call");
        }

        // Return early if compress results in a larger file and force is false
        if (!shouldCompress && !force) {
            return 0;
        }

        // Set up an BitInputStream and BitOutputStream and huffMap
        BitInputStream inStream = new BitInputStream(in);
        BitOutputStream outStream = new BitOutputStream(out);
        int compressedBitSize = 0;

        // First, write out MAGIC NUMBER
        outStream.writeBits(BITS_PER_INT, MAGIC_NUMBER);
        compressedBitSize += BITS_PER_INT;

        // Then, EITHER Write STORE_COUNTRS and the header data for rebuilding the tree
        if (header == STORE_COUNTS) {

            // STORE COUNTS HEADER
            outStream.writeBits(BITS_PER_INT, STORE_COUNTS);
            compressedBitSize += BITS_PER_INT;

            // HEADER DATA
            compressedBitSize += huffTree.writeSCFBits(outStream, freqs);
        }

        // OR Write STORE_TREE and the header data for rebuilding the tree
        else if (header == STORE_TREE) {

            // STORE TREE HEADER
            outStream.writeBits(BITS_PER_INT, STORE_TREE);
            compressedBitSize += BITS_PER_INT;

            // HEADER DATA
            compressedBitSize += huffTree.writeSTFBits(outStream);
        }

        // Then write the compressed data
        int curValue = inStream.read();
        while (curValue != -1) {
            compressedBitSize += huffTree.writeStringPath(outStream, curValue);
            curValue = inStream.read();
        }

        // Then write the PSEUDO_EOF
        compressedBitSize += huffTree.writeStringPath(outStream, PSEUDO_EOF);

        // showString("Finished writing compressed file.");
        // showString("Wrote " + compressedBitSize + " bits\n");

        closeBitReaders(inStream, outStream);
        return compressedBitSize;
    }

    /**
     * Uncompress a previously compressed stream in, writing the
     * uncompressed bits/data to out.
     * 
     * @param in  is the previously compressed data (not a BitInputStream)
     * @param out is the uncompressed file/stream
     * @return the number of bits written to the uncompressed file/stream
     * @throws IOException if an error occurs while reading from the input file or
     *                     writing to the output file.
     */
    public int uncompress(InputStream in, OutputStream out) throws IOException {

        // First create the input and output bit streams
        BitInputStream inStream = new BitInputStream(in);
        BitOutputStream outSteam = new BitOutputStream(out);

        // Check for magic number
        int magicNum = inStream.readBits(BITS_PER_INT);
        if (magicNum != MAGIC_NUMBER) {
            myViewer.showError("Error reading compressed file. \n" +
                    "File did not start with the huff magic number.");
            closeBitReaders(inStream, outSteam);
            return -1;
        }

        // Want to recontruct a HuffmanTree - two constructors for two diff formats

        HuffmanTree scopedHuffTree = null;
        header = inStream.readBits(BITS_PER_INT);

        // If header is in Counts Format
        if (header == STORE_COUNTS) {
            freqs = getSCTFreqs(inStream);
            // Init huffMan tree with Counts Constructor
            scopedHuffTree = new HuffmanTree(STORE_COUNTS, freqs, myViewer);
        }

        // If header is in Tree Format
        else if (header == STORE_TREE) {

            // Convert Arraylist into int[]
            int[] preorderTrav = getSFTPreorderNodes(inStream);

            // Init huffMan tree with Tree Constructor
            scopedHuffTree = new HuffmanTree(STORE_TREE, preorderTrav, myViewer);

        } else {
            myViewer.showError("Error reading compressed file. \n" +
                    "File did not include header format type.");
            closeBitReaders(inStream, outSteam);
            return -1;
        }

        int uncompressedBits = scopedHuffTree.writeUncompress(inStream, outSteam);
        // showString("Finished writing compressed file.");
        // showString("Wrote " + uncompressedBits + " bits\n");
        return uncompressedBits;
    }

    /**
     * Returns an array with nodes in a preorder traveral with internal nodes being
     * -1 from a Standard Tree Format header
     * 
     * @param inStream
     * @return
     * @throws IOException
     */
    private int[] getSFTPreorderNodes(BitInputStream inStream) throws IOException {
        // Need to create an array with this parameter:
        // The array should have nodes in preorder traversal, with internal nodes being
        // equal to -1
        int lengthOfSFT = inStream.readBits(BITS_PER_INT);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < lengthOfSFT;) {
            int curBit = inStream.readBits(1);
            i++;
            // Internal node
            if (curBit == 0)
                list.add(-1);
            // Leaf node
            else {
                list.add(inStream.readBits(BITS_PER_WORD + 1));
                i += BITS_PER_WORD + 1;
            }
        }

        // Convert Arraylist into int[]
        int[] preorderTrav = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            preorderTrav[i] = list.get(i);

        return preorderTrav;
    }

    /**
     * Returns an array of frequencies from a Standard Count Format header
     * 
     * @param inStream
     * @return
     * @throws IOException
     */
    private int[] getSCTFreqs(BitInputStream inStream) throws IOException {
        int[] freqs = new int[ALPH_SIZE];
        for (int k = 0; k < ALPH_SIZE; k++) {
            int frequencyInOriginalFile = inStream.readBits(BITS_PER_INT);
            freqs[k] = frequencyInOriginalFile;
        }
        return freqs;
    }

    /**
     * Literally just to save 1 line of code to make that 25 lines per method
     * 
     * @param in
     * @param out
     */
    private void closeBitReaders(BitInputStream in, BitOutputStream out) {
        in.close();
        out.close();
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
