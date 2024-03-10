public class Matrix<T> {
    private final T[][] data;
    private final int numRows;
    private final int numCols;

    public Matrix(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        data = (T[][]) new Object[numRows][numCols];
    }

    public void set(int row, int col, T value) {
        if (row >= 0 && row < numRows && col >= 0 && col < numCols) {
            data[row][col] = value;
        } else {
            throw new IndexOutOfBoundsException("Invalid row or column index.");
        }
    }

    public T get(int row, int col) {
        if (row >= 0 && row < numRows && col >= 0 && col < numCols) {
            return data[row][col];
        } else {
            throw new IndexOutOfBoundsException("Invalid row or column index.");
        }
    }

    public Matrix<T> add(Matrix<T> other) {
        if (numRows != other.numRows || numCols != other.numCols) {
            throw new IllegalArgumentException("Matrices must have the same dimensions for addition.");
        }

        Matrix<T> result = new Matrix<>(numRows, numCols);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                result.set(i, j, (T) (addition(data[i][j], other.get(i, j))));
            }
        }
        return result;
    }

    public Matrix<T> subtract(Matrix<T> other) {
        if (numRows != other.numRows || numCols != other.numCols) {
            throw new IllegalArgumentException("Matrices must have the same dimensions for subtraction.");
        }

        Matrix<T> result = new Matrix<>(numRows, numCols);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                result.set(i, j, (T) (subtract(data[i][j], other.get(i, j))));
            }
        }
        return result;
    }

    public Matrix<T> multiply(Matrix<T> other) {
        if (numCols != other.numRows) {
            throw new IllegalArgumentException("Number of columns in the first matrix must be equal to the number of rows in the second matrix for multiplication.");
        }

        Matrix<T> result = new Matrix<>(numRows, other.numCols);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < other.numCols; j++) {
                T sum = null;
                for (int k = 0; k < numCols; k++) {
                    T product = (T) (multiply(data[i][k], other.get(k, j)));
                    if (sum == null) {
                        sum = product;
                    } else {
                        sum = (T) (addition(sum, product));
                    }
                }
                result.set(i, j, sum);
            }
        }
        return result;
    }

    // Helper methods for arithmetic operations
    public <T> T addition(T a, T b) {
        return (T) (Number) ((Integer) a + (Integer) b);
    }

    public <T> T subtract(T a, T b) {
        return (T) (Number) ((Integer) a - (Integer) b);
    }

    public <T> T multiply(T a, T b) {
        return (T) (Number) ((Integer) a * (Integer) b);
    }

    public void print() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Matrix<Integer> matrix1 = new Matrix<>(2, 2);
        matrix1.set(0, 0, 1);
        matrix1.set(0, 1, 2);
        matrix1.set(1, 0, 3);
        matrix1.set(1, 1, 4);

        Matrix<Integer> matrix2 = new Matrix<>(2, 2);
        matrix2.set(0, 0, 5);
        matrix2.set(0, 1, 6);
        matrix2.set(1, 0, 7);
        matrix2.set(1, 1, 8);

        System.out.println("Matrix 1:");
        matrix1.print();
        System.out.println("Matrix 2:");
        matrix2.print();

        Matrix<Integer> sumResult = matrix1.add(matrix2);
        System.out.println("Sum Result:");
        sumResult.print();

        Matrix<Integer> subtractResult = matrix1.subtract(matrix2);
        System.out.println("Subtraction Result:");
        subtractResult.print();

        Matrix<Integer> multiplyResult = matrix1.multiply(matrix2);
        System.out.println("Multiplication Result:");
        multiplyResult.print();
    }
}
