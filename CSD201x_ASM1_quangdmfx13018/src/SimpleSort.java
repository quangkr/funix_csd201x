import java.util.ArrayList;
import java.util.List;

public class SimpleSort {
    private double[] a;
    private boolean isSorted = false;
    private int n;

    /**
     * Simple constructor
     */
    SimpleSort() {
    }

    /**
     * Create new SimpleSort object that copies the contents of the given array
     * 
     * @param b The array to copy content from
     */
    SimpleSort(double[] b) {
        // copy array from parameter to instance private field
        n = b.length;
        a = new double[n];
        for (int i = 0; i < n; i++)
            a[i] = b[i];
    }

    /**
     * Return stored array
     * 
     * @return the stored array
     */
    public double[] get() {
        return a;
    }

    /**
     * Print stored array to console
     */
    public void display() {
        System.out.println(toString());
    }

    /**
     * Return stored array as a string
     * 
     * @return the string representation of the array
     */
    public String toString() {
        String result = "";
        result += a[0];
        for (int i = 1; i < n; i++)
            result += (" " + a[i]);

        return result;
    }

    /**
     * Swap the values at the given indexes
     * 
     * @param i The first index
     * @param k The second index
     */
    private void swap(int i, int k) {
        if (i == k)
            return;
        double tmp;
        tmp = a[i];
        a[i] = a[k];
        a[k] = tmp;
    }

    /**
     * Perform bubble sort on the stored array
     * 
     * @param withLog If the method should return the string representation of the
     *                array at every sorting step
     * @return Log of each sorting step
     */
    public List<String> bubbleSort(boolean withLog) {
        List<String> result = new ArrayList<>();

        boolean didSwap;
        for (int j = n - 1; j > 0; j--) {
            didSwap = false;
            for (int k = 0; k < j; k++) {
                if (a[k] > a[k + 1]) {
                    swap(k, k + 1);
                    didSwap = true;
                }
            }

            if (withLog)
                result.add(toString());

            if (!didSwap)
                break;
        }

        isSorted = true;
        return result;
    }

    /**
     * Perform selection sort on the stored array
     * 
     * @param withLog If the method should return the string representation of the
     *                array at every sorting step
     * @return Log of each sorting step
     */
    public List<String> selectSort(boolean withLog) {
        List<String> result = new ArrayList<>();

        int min;
        for (int i = 0; i < n - 1; i++) {
            min = i;
            for (int k = i + 1; k < n; k++) {
                if (a[k] < a[min]) {
                    min = k;
                }
            }

            if (min != i)
                swap(min, i);

            if (withLog)
                result.add(toString());
        }

        isSorted = true;
        return result;
    }

    /**
     * Perform insertion sort on the stored array
     * 
     * @param withLog If the method should return the string representation of the
     *                array at every sorting step
     * @return Log of each sorting step
     */
    public List<String> insertSort(boolean withLog) {
        List<String> result = new ArrayList<>();

        int k;
        double tmp;
        for (int i = 1; i < n; i++) {
            /*
             * if it's greater than or equal to the preceding element there's no need for
             * any swapping, so skip to next iteration
             */
            if (a[i - 1] <= a[i])
                continue;

            tmp = a[i];
            a[i] = a[i - 1];
            for (k = i - 2; k >= 0; k--) {
                if (a[k] > tmp) {
                    a[k + 1] = a[k];
                } else {
                    break;
                }
            }
            a[k + 1] = tmp;

            if (withLog)
                result.add(toString());
        }

        isSorted = true;
        return result;
    }

    /**
     * Perform bubble sort on the stored array without logging
     */
    public void bubbleSort() {
        bubbleSort(false);
    }

    /**
     * Perform selection sort on the stored array without logging
     */
    public void selectSort() {
        selectSort(false);
    }

    /**
     * Perform insertion sort on the stored array without logging
     */
    public void insertSort() {
        insertSort(false);
    }

    /**
     * Perform linear search on the stored array and find the index of the element
     * equals to given value
     * 
     * @param value The value to search for
     * @return The index matched
     */
    public int linearSearchEqual(double value) {
        for (int i = 0; i < n; i++) {
            if (a[i] == value)
                return i;
        }

        return -1;
    }

    /**
     * Perform linear search on the stored array and find the list of indexes of the
     * element that is greater than given value
     * 
     * @param value The value to search for
     * @return The indexes matched
     */
    public List<Integer> linearSearchGreater(double value) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (a[i] > value) {
                result.add(i);
            }
        }

        return result;
    }

    /**
     * Perform binary search on the stored array and return the index of the element
     * equals to the given value
     * 
     * @param value      The value to search for
     * @param isMinIndex If the method should return the smallest index
     * @return The index matched
     */
    public int binarySearch(double value, boolean isMinIndex) {
        if (!isSorted) {
            insertSort();
        }

        int lower = 0;
        int upper = n - 1;
        int mid = -1;

        while (lower <= upper) {
            mid = lower + ((upper - lower) / 2);
            if (a[mid] == value) {
                if (isMinIndex) {
                    while (mid >= 0 && a[mid - 1] == value)
                        mid--;
                }
                return mid;
            }
            if (a[mid] < value) {
                lower = mid + 1;
            } else {
                upper = mid - 1;
            }
        }

        return -1;
    }
}