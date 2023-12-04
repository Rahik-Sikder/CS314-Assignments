import java.util.Random;

/*  Student information for assignment:
 *
 *  UTEID: rns2359
 *  email address: sikder.rahik@utexas.edu
 *  Grader name: Casey, the Great
 *  Number of slip days I am using:
 */

/* CS314 Students. Put your experiment results and
 * answers to questions here.
 * --------------------------------------------------------
 * Questions
 * --------------------------------------------------------
 * 1. I expect the add method to take at least 68 seconds (68s) when doubled again.
 *      This is because the 3900x3900 matrix took ~17s to run, and a factor of 
 *      approximately 4 over the experiments puts the doubled matrix to have at least 
 *      a 17s * 4 = 68s runtime.
 *      In actual runtime it'll be around 74s as machines are imperfect the factor 
 *      steadily increases over 4 each experiment. 
 * 
 * 2. The Big O of the add operation is O(n^2). This is supported by the timing data 
 *      which suggests that the runtime increases by a factor of 4 whenever n is doubled,
 *      consistant with O(n^2) which sees a 4-fold increase in runtime per doubling.
 * 
 * 3. I expect the multiplication method to take at least ~783 seconds when doubled again.
 *      This is because the 800x800 matric took ~87 seconds to run, and a factor of
 *      approximately 9 over the experiments puts the doubled matrix to have at least 
 *      a 87s * 9 = 783s runtime
 * 
 * 4. The Big O of the multiply operation is O(n^3). The timing data does support this, as
 *      an O(n^3) algorithm would see a factor of 8 increase in runtime per doubling of n.
 *      The timing data suggests a factor of 9, which is close to 8 but is likely increased
 *      due to the inconsistently of real world computing.  
 * 
 * 5. On the CS Lab linux machines I am able to create a matrix of size 31848 x 31848 before
 *      Java throws java.lang.OutOfMemoryError: Java heap space.
 *      Memory used (MB) = (31848 ^ 2) * 4 / 1000000 = 4057 MB
 *      The program therefore allocated 4057 MB of memory.
 *      Running the `free -m` cmd tells us there is a total of 15928 MB of total memory.
 *      A percentage can be taken from the 4057 MB the program used and 15928 MB of total
 *      RAM to say that the program used 25.47% of the total mem before crashing. 
 *      
 *      
 * --------------------------------------------------------
 * Experiment Results
 * --------------------------------------------------------
 *            EXPERIMENT 1 - MATRIX ADDITION
 * --------------------------------------------------------
 * Matrix 975x975 had elapsed time: 1.091191216 seconds.
 *
 * Matrix 1950x1950 had elapsed time: 4.239074637 seconds.
 *
 * Matrix 3900x3900 had elapsed time: 17.423141242 seconds.
 *
 * --------------------------------------------------------
 *         EXPERIMENT 2 - MATRIX MULTIPLICATION
 * --------------------------------------------------------
 * Matrix 200x200 had elapsed time: 1.020333062 seconds.
 *
 * Matrix 400x400 had elapsed time: 9.644471015 seconds.
 *
 * Matrix 800x800 had elapsed time: 86.975648366 seconds.
 * --------------------------------------------------------
 * 
 */

/**
 * A class to run tests on the MathMatrix class
 */
public class MathMatrixTester {

    /**
     * main method that runs simple test on the MathMatrix class
     *
     * @param args not used
     */
    public static void main(String[] args) {
        runTests();
        // runExperiment1();
        // runExperiment2();
        // runHeapMemExperiement();
    }

    // Finds the largest possible MathMatrix able to be 
    // created before Java throws a heap memory error
    private static void runHeapMemExperiement() {
         // Extra Line for formatting purposes
        System.out.println();
        int increase = 100;
        int index = 100;

        while (index < Integer.MAX_VALUE) {

            // Just to make it more fun
            // Loading dot
            if (index % (increase * 100) == 0) {
                // clears line
                System.out.print("\033[2K\033[1G");
                System.out.println("Currently at index: " + index);
            } else if (index % (increase) == 0) {
                // (A fun little) loading dot
                System.out.print(".");
            }

            try {
                new MathMatrix(index, index, 0);
            } catch (Error e) {
                // clears line
                System.out.print("\033[2K\033[1G");
                System.out.println("At index " + index + ": " + e);

                // set index back by the increase
                index -= increase;
                if (increase > 1) {
                    increase /= 10;
                    System.out.println("Starting again from index: " + index);
                } else {
                    index = Integer.MAX_VALUE - 1;
                }

            }
            index += increase;
        }

    }

    // Runs all of the tests for MathMatrix
    private static void runTests() {
        System.out.println("*********************************************************");

        // Testing MathMatrix Constructors
        testContructors();
        System.out.println("*********************************************************");

        // Testing MathMatrix getNumRows()
        testGetNumRows();
        System.out.println("*********************************************************");

        // Testing MathMatrix getNumColumns()
        testGetNumColumns();
        System.out.println("*********************************************************");

        // Testing MathMatrix getValue()
        testGetValue();
        System.out.println("*********************************************************");

        // Testing MathMatrix add()
        testAddition();
        System.out.println("*********************************************************");

        // Testing MathMatrix subtract()
        testSubtraction();
        System.out.println("*********************************************************");

        // Testing MathMatrix multiply()
        testMultiplication();
        System.out.println("*********************************************************");

        // Testing MathMatrix getScaledMatrix()
        testScaledMatrix();
        System.out.println("*********************************************************");

        // Testing MathMatrix getTranspose()
        testGetTransposed();
        System.out.println("*********************************************************");

        // Testing MathMatrix toString()
        testToString();
        System.out.println("*********************************************************");

        // Testing MathMatric isUpperTriangular()
        testIsUpperTriangle();
        System.out.println("*********************************************************");

        // Testing MathMatric isUpperTriangular()
        testEquals();
        System.out.println("*********************************************************");
    }

    // 2 Tests
    public static void testContructors() {

        /**
         * Constructor Test 1 - Size and Init Val
         */
        MathMatrix testA = new MathMatrix(4, 10, 56);
        int[][] dataA = new int[][] {
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 }, };
        printTestResult(get2DArray(testA), dataA, "Constructor Test 1 - Size and Init Val");

        /**
         * Constructor Test 2 - Deep Copy
         */
        int[][] shallowCopyDecoy = new int[][] {
                { 0, 0 },
                { 0, 0 }
        };
        testA = new MathMatrix(shallowCopyDecoy);
        shallowCopyDecoy[1][1] = 5;
        // If deep copy was made then test2 equals data2
        // If a shallow copy was made, then test2 equals shallowCopyDecoy
        int[][] dataB = {
                { 0, 0 },
                { 0, 0 }
        };
        printTestResult(get2DArray(testA), dataB, "Constructor Test 2 - Deep Copy");
    }

    // 2 Tests
    public static void testGetNumRows() {

        /**
         * Num Rows Test 1
         */
        int[][] dataA = new int[][] {
                { 0, 1, 2 },
                { 3, 4, 5 }
        };
        MathMatrix testA = new MathMatrix(dataA);
        printTestResult(testA.getNumRows(), 2, "Num Rows Test 1");

        /**
         * Num Rows Test 2
         */
        int[][] dataB = new int[][] {
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, -1 }, };

        MathMatrix testB = new MathMatrix(dataB);
        printTestResult(testB.getNumRows(), 4, "Num Rows Test 2");
    }

    // 2 Tests
    public static void testGetNumColumns() {
        /**
         * Num Columns Test 1
         */
        int[][] dataA = new int[][] {
                { 0, 1, 2 },
                { 3, 4, 5 }
        };
        MathMatrix testA = new MathMatrix(dataA);
        printTestResult(testA.getNumColumns(), 3, "Num Columns Test 1");

        /**
         * Num Columns Test 2
         */
        int[][] dataB = new int[][] {
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, -1 }, };

        MathMatrix testB = new MathMatrix(dataB);
        printTestResult(testB.getNumColumns(), 10, "Num Columns Test 2");

    }

    // 2 Tests
    public static void testGetValue() {

        /**
         * Get Value Test 1
         */
        int[][] dataA = new int[][] {
                { 0, 1, 2 },
                { 3, 4, 5 }
        };
        MathMatrix testA = new MathMatrix(dataA);
        printTestResult(testA.getVal(0, 2), 2, "Get Value Test 1 ");

        /**
         * Get Value Test 2
         */
        int[][] dataB = new int[][] {
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, -1 }, };

        MathMatrix testB = new MathMatrix(dataB);
        printTestResult(testB.getVal(3, 9), -1, "Get Value Test 2 ");

    }

    // 3 Tests (of 2)
    public static void testAddition() {

        /**
         * Addition Test 1 - Matrix A left unchanged
         * Addition Test 2 - Matrix B left unchanged
         * Addition Test 3 - Adding MathMatrices
         */
        int[][] dataA = new int[][] {
                { 0, 1, 2 },
                { 3, 4, 5 }
        };
        MathMatrix test1 = new MathMatrix(dataA);
        // MathMatrix full of 1s
        int[][] dataB = new int[][] {
                { 1, 1, 1 },
                { 1, 1, 1 }
        };
        MathMatrix test2 = new MathMatrix(2, 3, 1);
        MathMatrix mathMatrixSum = test1.add(test2);
        int[][] dataSum = new int[][] {
                { 1, 2, 3 },
                { 4, 5, 6 }
        };
        printTestResult(get2DArray(test1), dataA, "Addition Test 1 - Matrix A left unchanged");
        printTestResult(get2DArray(test2), dataB, "Addition Test 2 - Matrix B left unchanged");
        printTestResult(get2DArray(mathMatrixSum), dataSum, "Addition Test 3 - Matrix left unchanged");

    }

    // 3 Tests (of 2)
    public static void testSubtraction() {

        /**
         * Subtraction Test 1 - Matrix A left unchanged
         * Subtraciton Test 2 - Matrix B left unchanged
         * Subtraction Test 3 - Subtracting Matrices
         */
        int[][] dataA = new int[][] {
                { 0, 1, 2 },
                { 3, 4, 5 }
        };
        MathMatrix test1 = new MathMatrix(dataA);
        // MathMatrix full of 1s
        int[][] dataB = new int[][] {
                { 1, 1, 1 },
                { 1, 1, 1 }
        };
        MathMatrix test2 = new MathMatrix(2, 3, 1);
        MathMatrix mathMatrixSum = test1.subtract(test2);
        int[][] dataSum = new int[][] {
                { -1, 0, 1 },
                { 2, 3, 4 }
        };
        printTestResult(get2DArray(test1), dataA, "Subtraction Test 1 - Matrix A left unchanged");
        printTestResult(get2DArray(test2), dataB, "Subtraction Test 2 - Matrix B left unchanged");
        printTestResult(get2DArray(mathMatrixSum), dataSum, "Subtraction Test 3 - Subtracting Matrices");

    }

    // 4 Tests (of 2)
    public static void testMultiplication() {

        /**
         * Multiply Test 1 - Matrix A left unchanged
         * Multiply Test 2 - Matrix B left unchanged
         * Multiply Test 3 - Multiplying Matrices
         */
        int[][] dataA = new int[][] {
                { 1, 2 },
                { 3, 4 },
                { 5, 6 }
        };
        int[][] dataB = new int[][] {
                { 1, 3, 5 },
                { 2, 4, 6 }
        };
        // arrayA x arrayB
        int[][] dataProduct = new int[][] {
                { 5, 11, 17 },
                { 11, 25, 39 },
                { 17, 39, 61 }
        };

        MathMatrix testA = new MathMatrix(dataA);
        MathMatrix testB = new MathMatrix(dataB);
        MathMatrix mathMatrixProduct = testA.multiply(testB);

        printTestResult(get2DArray(testA), dataA, "Multiply Test 1 - Matrix A left unchanged");
        printTestResult(get2DArray(testB), dataB, "Multiply Test 2 - Matrix B left unchanged");
        printTestResult(get2DArray(mathMatrixProduct), dataProduct, "Multiply Test 3 - Multiplying Matrices");

        /**
         * Multiply Test 4 - 1x1 Matrix
         */

        testA = new MathMatrix(1, 1, 2); // [ [2] ]
        testB = new MathMatrix(1, 1, 5); // [ [5] ]
        // Should equal 10
        mathMatrixProduct = testA.multiply(testB);
        printTestResult(get2DArray(mathMatrixProduct), new int[][] { { 10 } }, "Multiply Test 4 - 1x1 Matrix");

    }

    // 2 Tests
    public static void testScaledMatrix() {
        /**
         * Scaled Test 1 - Matrix A Unchanged
         * Scaled Test 2 - Get Scaled Matrix
         */

        int[][] dataA = new int[][] {
                { 0, 1, 2 },
                { 3, 4, 5 }
        };
        MathMatrix testA = new MathMatrix(dataA);
        int factor = 2;
        int[][] scaledDataA = new int[][] {
                { 0, 2, 4 },
                { 6, 8, 10 }
        };
        printTestResult(get2DArray(testA), dataA, "Scaled Test 1 - Matrix A Unchanged");
        printTestResult(get2DArray(testA.getScaledMatrix(factor)), scaledDataA, "Scaled Test 2 - Get Scaled Matrix");
    }

    // 2 Tests
    public static void testGetTransposed() {
        /**
         * Transposed Test 1 - Matrix A Unchanged
         * Transposed Test 2 - Get Transposed Matrix
         */

        int[][] dataA = new int[][] {
                { 0, 1, 2 },
                { 3, 4, 5 }
        };
        MathMatrix testA = new MathMatrix(dataA);
        int[][] transposedDataA = new int[][] {
                { 0, 3 },
                { 1, 4 },
                { 2, 5 }
        };
        printTestResult(get2DArray(testA), dataA, "Transposed Test 1 - Matrix A Unchanged");
        printTestResult(get2DArray(testA.getTranspose()), transposedDataA, "Transposed Test 2 - Get Transposed Matrix");
    }

    // 2 Tests
    public static void testToString() {
        /**
         * To String Test 1 - Basic Test
         */
        int[][] dataA = new int[][] {
                { 0, 1, 2 },
                { 3, 14, 5 }
        };
        MathMatrix testA = new MathMatrix(dataA);
        String dataAString = "|  0  1  2|\n" +
                "|  3 14  5|\n";
        printTestResult(testA.toString(), dataAString, "To String Test 1 - Basic Test");

        /**
         * To String Test 2 - Longer element
         */
        int[][] dataB = new int[][] {
                { 56, 563, 5633, 536, 56, 56, 56, 56, 0, 56 },
                { 56, 5600, 56, 56, 56, 526, 56, 33356, 56, 56 },
                { 56, 56, 536, 56, -5600, 56, 56, 56, 5622, 56 },
                { 56, -4356, 56, 56, 56, 56, 56, 56, 56, 9999999 }, };
        MathMatrix testB = new MathMatrix(dataB);
        String dataBString = "|      56     563    5633     536      56      56      56      56       0      56|\n" +
                "|      56    5600      56      56      56     526      56   33356      56      56|\n" +
                "|      56      56     536      56   -5600      56      56      56    5622      56|\n" +
                "|      56   -4356      56      56      56      56      56      56      56 9999999|\n";
        printTestResult(testB.toString(), dataBString, "To String Test 2 - Longer element");
    }

    // 3 Tests (of 2)
    public static void testIsUpperTriangle() {
        /**
         * Upper Triangle Test 1
         */

        int[][] dataA = new int[][] {
                { 0, 1, 3 },
                { 3, 0, 1 },
                { 0, 0, 0 }
        };
        MathMatrix testA = new MathMatrix(dataA);
        boolean isDataUpper = false;
        printTestResult(testA.isUpperTriangular(), isDataUpper, "Upper Triangle Test 1");

        /**
         * Upper Triangle Test 2
         */

        int[][] dataB = new int[][] {
                { 6, 6, 6, 6, 6 },
                { 0, 6, 6, 6, 6 },
                { 0, 0, 6, 0, 6 },
                { 0, 0, 0, 6, 6 },
                { 0, 0, 0, 0, 6 } };

        MathMatrix testB = new MathMatrix(dataB);
        isDataUpper = true;
        printTestResult(testB.isUpperTriangular(), isDataUpper, "Upper Triangle Test 2");

        /**
         * Upper Triangle Test 3 - 1x1 Matrix
         */

        int[][] dataC = new int[][] {
                { 1, 1 },
                { 0, 1 } };

        MathMatrix testC = new MathMatrix(dataC);
        isDataUpper = true;
        printTestResult(testC.isUpperTriangular(), isDataUpper, "Upper Triangle Test 3 - 2x2 Matrix");
    }

    // 2 Tests
    public static void testEquals() {
        /**
         * Equals Test 1
         */
        int[][] dataA = new int[][] {
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, -1 }, };
        MathMatrix testA = new MathMatrix(dataA);
        int[][] dataB = new int[][] {
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, -1 }, };
        MathMatrix testB = new MathMatrix(dataB);

        boolean expectedBoolean = true;

        printTestResult(testA.equals(testB), expectedBoolean, "Equals Test 1");

        /**
         * Equals Test 2
         */
        int[][] dataC = new int[][] {
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, -1 }, };
        MathMatrix testC = new MathMatrix(dataC);
        int[][] dataD = new int[][] {
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 56 },
                { 56, 56, 56, 56, 56, 56, 56, 56, 56, 0 }, };
        MathMatrix testD = new MathMatrix(dataD);

        expectedBoolean = false;

        printTestResult(testC.equals(testD), expectedBoolean, "Equals Test 2");

    }

    // create a matrix with random values
    // pre: rows > 0, cols > 0, randNumGen != null
    public static MathMatrix createMat(Random randNumGen, int rows,
            int cols, final int LIMIT) {

        if (randNumGen == null) {
            throw new IllegalArgumentException("randomNumGen variable may no be null");
        } else if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("rows and columns must be greater than 0. " +
                    "rows: " + rows + ", cols: " + cols);
        }

        int[][] temp = new int[rows][cols];
        final int SUB = LIMIT / 4;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                temp[r][c] = randNumGen.nextInt(LIMIT) - SUB;
            }
        }

        return new MathMatrix(temp);
    }


    // private static void runExperiment1() {

    //     // Extra Line for formatting purposes
    //     System.out.println();

    //     Stopwatch s = new Stopwatch();
    //     // Init constants for the experiment
    //     Random random = new Random();
    //     // N = 1500 -> Run time is approx 1.04-1.07 seconds
    //     final int INITIAL_SIZE = 975;
    //     final int NUM_EXPERIMENTS = 1000;

    //     // Create two matrices and fill them with random values
    //     // Limit does not affect the run time of the experiment
    //     MathMatrix matrixA = createMat(random, INITIAL_SIZE, INITIAL_SIZE, 1000);
    //     MathMatrix matrixB = createMat(random, INITIAL_SIZE, INITIAL_SIZE, 1000);

    //     // Output Organization
    //     System.out.println(" *            EXPERIMENT 1 - MATRIX ADDITION               ");
    //     System.out.println(" * --------------------------------------------------------");

    //     /**
    //      * INITIAL SIZE EXPERIMENT
    //      */
    //     int curDimensions = INITIAL_SIZE;
    //     s.start();
    //     for (int i = 0; i < NUM_EXPERIMENTS; i++) {
    //         matrixA.add(matrixB);
    //     }
    //     s.stop();
    //     String time = s.toString();
    //     String output = " * Matrix " + curDimensions + "x" + curDimensions + " had ";
    //     System.out.print(output);
    //     System.out.println(time + "\n *");

    //     /**
    //      * 1ST DOUBLE SIZE EXPERIMENT
    //      */
    //     curDimensions *= 2;
    //     matrixA = createMat(random, curDimensions, curDimensions, 1000);
    //     matrixB = createMat(random, curDimensions, curDimensions, 1000);
    //     s.start();
    //     for (int i = 0; i < NUM_EXPERIMENTS; i++) {
    //         matrixA.add(matrixB);
    //     }
    //     s.stop();
    //     time = s.toString();
    //     output = " * Matrix " + curDimensions + "x" + curDimensions + " had ";
    //     System.out.print(output);
    //     System.out.println(time + "\n *");

    //     /**
    //      * 2ND DOUBLE SIZE EXPERIMENT
    //      */
    //     curDimensions *= 2;
    //     matrixA = createMat(random, curDimensions, curDimensions, 1000);
    //     matrixB = createMat(random, curDimensions, curDimensions, 1000);
    //     s.start();
    //     for (int i = 0; i < NUM_EXPERIMENTS; i++) {
    //         matrixA.add(matrixB);
    //     }
    //     s.stop();
    //     time = s.toString();
    //     output = " * Matrix " + curDimensions + "x" + curDimensions + " had ";
    //     System.out.print(output);
    //     System.out.println(time + "\n *");

    // }

    // private static void runExperiment2() {
    //      // Extra Line for formatting purposes
    //     System.out.println();

    //     Stopwatch s = new Stopwatch();
    //     // Init constants for the experiment
    //     Random random = new Random();
    //     final int INITIAL_SIZE = 200;
    //     final int NUM_EXPERIMENTS = 100;

    //     // Create two matrices and fill them with random values
    //     MathMatrix matrixA = createMat(random, INITIAL_SIZE, INITIAL_SIZE, 1000);
    //     MathMatrix matrixB = createMat(random, INITIAL_SIZE, INITIAL_SIZE, 1000);

    //     System.out.println(" * --------------------------------------------------------");
    //     System.out.println(" *         EXPERIMENT 2 - MATRIX MULTIPLICATION            ");
    //     System.out.println(" * --------------------------------------------------------");
    //     /**
    //      * INITIAL SIZE EXPERIMENT
    //      */
    //     int curDimensions = INITIAL_SIZE;
    //     s.start();
    //     for (int i = 0; i < NUM_EXPERIMENTS; i++) {
    //         matrixA.multiply(matrixB);
    //     }
    //     s.stop();
    //     String time = s.toString();
    //     String output = " * Matrix " + curDimensions + "x" + curDimensions + " had ";
    //     System.out.print(output);
    //     System.out.println(time + "\n *");

    //     /**
    //      * 1ST DOUBLE SIZE EXPERIMENT
    //      */
    //     curDimensions *= 2;
    //     matrixA = createMat(random, curDimensions, curDimensions, 1000);
    //     matrixB = createMat(random, curDimensions, curDimensions, 1000);
    //     s.start();
    //     for (int i = 0; i < NUM_EXPERIMENTS; i++) {
    //         matrixA.multiply(matrixB);
    //     }
    //     s.stop();
    //     time = s.toString();
    //     output = " * Matrix " + curDimensions + "x" + curDimensions + " had ";
    //     System.out.print(output);
    //     System.out.println(time + "\n *");

    //     /**
    //      * 2ND DOUBLE SIZE EXPERIMENT
    //      */
    //     curDimensions *= 2;
    //     matrixA = createMat(random, curDimensions, curDimensions, 1000);
    //     matrixB = createMat(random, curDimensions, curDimensions, 1000);
    //     s.start();
    //     for (int i = 0; i < NUM_EXPERIMENTS; i++) {
    //         matrixA.multiply(matrixB);
    //     }
    //     s.stop();
    //     time = s.toString();
    //     output = " * Matrix " + curDimensions + "x" + curDimensions + " had ";
    //     System.out.print(output);
    //     System.out.println(time + "\n *");

    // }
 



    // Prints test results for two 2D int arrays
    private static void printTestResult(int[][] data1, int[][] data2, String testName) {
        System.out.print((testName + " -------------------------------------------------")
                .substring(0, 50));
        String result = equals(data1, data2) ? " Passed" : " Failed";
        System.out.println(result);
    }

    // Prints test results for two Strings
    private static void printTestResult(String data1, String data2, String testName) {
        System.out.print((testName + " -------------------------------------------------")
                .substring(0, 50));
        String result = (data1.equals(data2)) ? " Passed" : " Failed";
        System.out.println(result);
    }

    // Prints test results for two ints
    private static void printTestResult(int data1, int data2, String testName) {
        System.out.print((testName + " -------------------------------------------------")
                .substring(0, 50));
        String result = (data1 == data2) ? " Passed" : " Failed";
        System.out.println(result);
    }

    // Prints test results for two booleans
    private static void printTestResult(boolean data1, boolean data2, String testName) {
        System.out.print((testName + " -------------------------------------------------")
                .substring(0, 50));
        String result = (data1 == data2) ? " Passed" : " Failed";
        System.out.println(result);
    }

    // pre: m != null, m is at least 1 by 1 in size
    // return a 2d array of ints the same size as m and with
    // the same elements
    private static int[][] get2DArray(MathMatrix m) {
        // check precondition
        if ((m == null) || (m.getNumRows() == 0)
                || (m.getNumColumns() == 0)) {
            throw new IllegalArgumentException("Violation of precondition: get2DArray");
        }

        int[][] result = new int[m.getNumRows()][m.getNumColumns()];
        for (int r = 0; r < result.length; r++) {
            for (int c = 0; c < result[0].length; c++) {
                result[r][c] = m.getVal(r, c);
            }
        }
        return result;
    }

    // pre: data1 != null, data2 != null, data1 and data2 are at least 1 by 1
    // matrices
    // data1 and data2 are rectangular matrices
    // post: return true if data1 and data2 are the same size and all elements are
    // the same
    private static boolean equals(int[][] data1, int[][] data2) {
        // check precondition
        if ((data1 == null) || (data1.length == 0)
                || (data1[0].length == 0) || !rectangularMatrix(data1)
                || (data2 == null) || (data2.length == 0)
                || (data2[0].length == 0) || !rectangularMatrix(data2)) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "equals check on 2d arrays of ints");
        }
        boolean result = (data1.length == data2.length) && (data1[0].length == data2[0].length);
        int row = 0;
        while (result && row < data1.length) {
            int col = 0;
            while (result && col < data1[0].length) {
                result = (data1[row][col] == data2[row][col]);
                col++;
            }
            row++;
        }

        return result;
    }

    // method to ensure mat is rectangular
    // pre: mat != null, mat is at least 1 by 1
    private static boolean rectangularMatrix(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + " Parameter mat may not be null"
                    + " and must be at least 1 by 1");
        }
        return MathMatrix.rectangularMatrix(mat);
    }

}
