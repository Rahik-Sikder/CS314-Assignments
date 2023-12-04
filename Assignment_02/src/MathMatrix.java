import java.util.Arrays;

// MathMatrix.java - CS314 Assignment 2

/*  Student information for assignment:
*
*  On my honor, RAHIK N SIKDER, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID: rns2359
*  email address: sikder.rahik@utexas.edu
*  Unique section number: 52679
*  Number of slip days I am using:
*/

/**
 * A class that models systems of linear equations (Math Matrices)
 * as used in linear algebra.
 */
public class MathMatrix {

    // instance var storing the 2D matrix
    private int[][] values;

    /**
     * create a MathMatrix with cells equal to the values in mat.
     * A "deep" copy of mat is made.
     * Changes to mat after this constructor do not affect this
     * Matrix and changes to this MathMatrix do not affect mat
     * 
     * @param mat mat !=null, mat.length > 0, mat[0].length > 0,
     *            mat is a rectangular matrix
     */
    public MathMatrix(int[][] mat) {

        // Check preconditions
        if (mat == null || mat.length <= 0 || mat[0].length <= 0 || !rectangularMatrix(mat)) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "mat !=null, mat.length > 0, mat[0].length > 0, mat is a rectangular matrix");
        }

        // init values and give it a deep copy of mat
        values = new int[mat.length][mat[0].length];
        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col < mat[row].length; col++) {
                values[row][col] = mat[row][col];
            }
        }
    }

    /**
     * create a MathMatrix of the specified size with all cells set to the
     * intialValue.
     * <br>
     * pre: numRows > 0, numCols > 0
     * <br>
     * post: create a matrix with numRows rows and numCols columns.
     * All elements of this matrix equal initialVal.
     * In other words after this method has been called getVal(r,c) = initialVal
     * for all valid r and c.
     * 
     * @param numRows    numRows > 0
     * @param numCols    numCols > 0
     * @param initialVal all cells of this Matrix are set to initialVal
     */
    public MathMatrix(int numRows, int numCols, int initialVal) {

        // Check preconditions
        if (numRows <= 0 || numCols <= 0) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "numRows > 0, numCols > 0");
        }

        // init values and assign all elements initialVal
        values = new int[numRows][numCols];
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                values[row][col] = initialVal;
            }
        }
    }

    /**
     * get the number of rows.
     * 
     * @return the number of rows in this MathMatrix
     */
    public int getNumRows() {
        return values.length;
    }

    /**
     * get the number of columns.
     * 
     * @return the number of columns in this MathMatrix
     */
    public int getNumColumns() {
        return values[0].length;
    }

    /**
     * get the value of a cell in this MathMatrix.
     * <br>
     * pre: row 0 <= row < getNumRows(), col 0 <= col < getNumColumns()
     * <br>
     * 
     * @param row 0 <= row < getNumRows()
     * @param col 0 <= col < getNumColumns()
     * @return the value at the specified position
     */
    public int getVal(int row, int col) {
        return values[row][col];
    }

    /**
     * implements MathMatrix addition, (this MathMatrix) + rightHandSide.
     * <br>
     * pre: rightHandSide != null, rightHandSide.getNumRows() = getNumRows(),
     * rightHandSide.numCols() = getNumColumns()
     * <br>
     * post: This method does not alter the calling object or rightHandSide
     * 
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(),
     *                      rightHandSide.numCols() = getNumColumns()
     * @return a new MathMatrix that is the result of adding this Matrix to
     *         rightHandSide.
     *         The number of rows in the returned Matrix is equal to the number of
     *         rows in this MathMatrix.
     *         The number of columns in the returned Matrix is equal to the number
     *         of columns in this MathMatrix.
     */
    public MathMatrix add(MathMatrix rightHandSide) {

        // Check preconditions
        if (rightHandSide == null || rightHandSide.getNumRows() != getNumRows() ||
                rightHandSide.getNumColumns() != getNumColumns()) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "rightHandSide != null, rightHandSide.getNumRows() = getNumRows(), " +
                    "rightHandSide.numCols() = getNumColumns()");
        }

        // init MathMatrix to hold the sum
        MathMatrix matrixSum = new MathMatrix(getNumRows(), getNumColumns(), 0);

        // Calculating the sum and storing it in matrixSum
        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumColumns(); col++) {
                matrixSum.values[row][col] = values[row][col] + rightHandSide.values[row][col];
            }
        }

        return matrixSum;
    }

    /**
     * implements MathMatrix subtraction, (this MathMatrix) - rightHandSide.
     * <br>
     * pre: rightHandSide != null, rightHandSide.getNumRows() = getNumRows(),
     * rightHandSide.numCols() = getNumColumns()
     * <br>
     * post: This method does not alter the calling object or rightHandSide
     * 
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(),
     *                      rightHandSide.numCols() = getNumColumns()
     * @return a new MathMatrix that is the result of subtracting rightHandSide
     *         from this MathMatrix. The number of rows in the returned MathMatrix
     *         is equal to the number of rows in this MathMatrix.
     *         The number of columns in the returned MathMatrix is equal to the
     *         number of columns in this MathMatrix.
     */
    public MathMatrix subtract(MathMatrix rightHandSide) {
        // Check preconditions
        if (rightHandSide == null || rightHandSide.getNumRows() != getNumRows() ||
                rightHandSide.getNumColumns() != getNumColumns()) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "rightHandSide != null, rightHandSide.getNumRows() = getNumRows(), " +
                    "rightHandSide.numCols() = getNumColumns()");
        }

        // init MathMatrix to hold the difference
        MathMatrix matrixSum = new MathMatrix(getNumRows(), getNumColumns(), 0);

        // Calculating the difference and storing it in matrixSum
        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumColumns(); col++) {
                matrixSum.values[row][col] = values[row][col] - rightHandSide.values[row][col];
            }
        }

        return matrixSum;
    }

    /**
     * implements matrix multiplication, (this MathMatrix) * rightHandSide.
     * <br>
     * pre: rightHandSide != null, rightHandSide.getNumRows() = getNumColumns()
     * <br>
     * post: This method should not alter the calling object or rightHandSide
     * 
     * @param rightHandSide rightHandSide.getNumRows() = getNumColumns()
     * @return a new MathMatrix that is the result of multiplying
     *         this MathMatrix and rightHandSide.
     *         The number of rows in the returned MathMatrix is equal to the number
     *         of rows in this MathMatrix.
     *         The number of columns in the returned MathMatrix is equal to the
     *         number of columns in rightHandSide.
     */
    public MathMatrix multiply(MathMatrix rightHandSide) {

        // Check preconditions
        if (rightHandSide == null || rightHandSide.getNumRows() != getNumColumns()) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "rightHandSide != null, rightHandSide.getNumRows() = getNumColumns()");
        }

        // init MathMatrix to hold the product
        MathMatrix matrixProduct = new MathMatrix(getNumRows(), rightHandSide.getNumColumns(), 0);

        for (int mainRowIndex = 0; mainRowIndex < getNumRows(); mainRowIndex++) {

            // Store the current row
            int[] curRow = values[mainRowIndex];

            // Goes through each column of rightHandSide
            for (int colIndex = 0; colIndex < rightHandSide.values[0].length; colIndex++) {

                // Store array of current column
                int[] curColumn = new int[rightHandSide.getNumRows()];
                for (int row = 0; row < rightHandSide.getNumRows(); row++) {
                    curColumn[row] = rightHandSide.values[row][colIndex];
                }

                // Get dot product
                int dotProduct = dotProduct(curRow, curColumn);

                // Insert dot product into matrixProduct
                matrixProduct.values[mainRowIndex][colIndex] = dotProduct;

            }

        }

        return matrixProduct;
    }

    /**
     * Finds dot product between two arrays
     * <br>
     * pre: arrayA.length == arrayB.length
     * <br>
     * 
     * @param arrayA
     * @param arrayB
     * @return dot product between arrayA and arrayB
     */
    private int dotProduct(int[] arrayA, int[] arrayB) {

        // Check preconditions
        if (arrayA.length != arrayB.length) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "arrayA.length == arrayB.length");
        }

        int sum = 0;

        for (int i = 0; i < arrayA.length; i++) {
            sum += arrayA[i] * arrayB[i];
        }

        return sum;
    }

    /**
     * Create and return a new Matrix that is a copy
     * of this matrix, but with all values multiplied by a scale
     * value.
     * <br>
     * pre: none
     * <br>
     * post: returns a new Matrix with all elements in this matrix
     * multiplied by factor.
     * In other words after this method has been called
     * returned_matrix.getVal(r,c) = original_matrix.getVal(r, c) * factor
     * for all valid r and c.
     * 
     * @param factor the value to multiply every cell in this Matrix by.
     * @return a MathMatrix that is a copy of this MathMatrix, but with all
     *         values in the result multiplied by factor.
     */
    public MathMatrix getScaledMatrix(int factor) {

        // init MathMatrix to hold the scaled matrix
        MathMatrix matrixScaled = new MathMatrix(getNumRows(), getNumColumns(), 0);

        // Accessing each element of values
        // and storing the scaled value into maxtrixScaled
        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumColumns(); col++) {
                matrixScaled.values[row][col] = values[row][col] * factor;
            }
        }

        return matrixScaled;
    }

    /**
     * accessor: get a transpose of this MathMatrix.
     * This Matrix is not changed.
     * <br>
     * pre: none
     * <br>
     * 
     * @return a transpose of this MathMatrix
     */
    public MathMatrix getTranspose() {

        // init MathMatrix to store transposed matrix
        MathMatrix matrixTransposed = new MathMatrix(getNumColumns(), getNumRows(), 0);

        // Traverse values and store the val into transposed location in
        // matrixTransposed
        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumColumns(); col++) {
                matrixTransposed.values[col][row] = values[row][col];
            }
        }
        return matrixTransposed;
    }

    /**
     * override equals.
     * 
     * @return <tt>true</tt> if rightHandSide is the same size as this MathMatrix
     *         and all values in the two MathMatrix objects are the same,
     *         <tt>false</tt> otherwise
     */
    public boolean equals(Object rightHandSide) {
        /*
         * CS314 Students. The following is standard equals
         * method code. We will learn about in the coming weeks.
         *
         * We use getClass instead of instanceof because we only want a MathMatrix to
         * equal
         * another MathMatrix as opposed to any possible sub classes. We would
         * use instance of if we were implementing am interface and wanted to equal
         * other objects that are instances of that interface but not necessarily
         * MathMatrix objects.
         */

        if (rightHandSide == null || this.getClass() != rightHandSide.getClass()) {
            return false;
        }
        // We know rightHandSide refers to a non-null MathMatrix object, safe to cast.
        MathMatrix otherMathMatrix = (MathMatrix) rightHandSide;
        // Now we can access the private instance variables of otherMathMatrix
        // and / or call MathMatrix methods on otherMathMatrix.

        // Check if both matricies are equal in size and return false if not
        if (otherMathMatrix.getNumRows() != getNumRows() ||
                otherMathMatrix.getNumColumns() != getNumColumns()) {
            return false;
        }

        // Check if all elements of both matricies are equal and return false if not
        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumColumns(); col++) {
                if (values[row][col] != otherMathMatrix.values[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * override toString.
     * 
     * @return a String with all elements of this MathMatrix.
     *         Each row is on a separate line.
     *         Spacing based on longest element in this Matrix.
     */
    public String toString() {

        // Find max length of element
        int maxLength = 0;
        for (int row = 0; row < values.length; row++) {
            for (int col = 0; col < values[0].length; col++) {
                int curLength = ("" + values[row][col]).length();
                // if curLength > maxLength, then maxLength = curLength
                maxLength = (curLength > maxLength) ? curLength : maxLength;
            }
        }
        // One extra space for formatting
        maxLength++;

        // init result and append all formatted elements onto it
        StringBuilder result = new StringBuilder("");
        for (int row = 0; row < values.length; row++) {
            result.append("|");
            for (int col = 0; col < values[0].length; col++) {
                // A Format String for each element
                // "%1$" - 1st argument index
                // maxLength - width
                // d - conversion, result formatted as integer
                String format = "%1$" + maxLength + "d";
                result.append(String.format(format, values[row][col]));
            }
            // Last line should also include a new line break
            result.append("|\n");
        }

        return result.toString();
    }

    /**
     * Return true if this MathMatrix is upper triangular. To
     * be upper triangular all elements below the main
     * diagonal must be 0.
     * <br>
     * pre: this is a square matrix. getNumRows() == getNumColumns()
     * <br>
     * 
     * @return <tt>true</tt> if this MathMatrix is upper triangular,
     *         <tt>false</tt> otherwise.
     */
    public boolean isUpperTriangular() {

        // Check preconditions
        if (getNumRows() != getNumColumns()) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "getNumRows() == getNumColumns()");
        }

        // Start from bottom, no element on top row should be accessed
        for (int row = values.length - 1; row > 0; row--) {
            // col should always be below row (stay under the diagonal)
            for (int col = 0; col < row; col++) {
                if (values[row][col] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * method to ensure mat is rectangular. It is public so that
     * <br>
     * pre: mat != null, mat has at least one row
     * <br>
     * 
     * @param mat
     * @return <tt>true</tt> if all rows in mat have the same number
     *         of columns <tt>false</tt> otherwise
     */
    public static boolean rectangularMatrix(int[][] mat) {
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("argument mat may not be null and must "
                    + " have at least one row. mat = " + Arrays.toString(mat));
        }
        boolean isRectangular = true;
        int row = 1;
        final int COLUMNS = mat[0].length;
        while (isRectangular && row < mat.length) {
            isRectangular = (mat[row].length == COLUMNS);
            row++;
        }
        return isRectangular;
    }

}