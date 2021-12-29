public class SimpleSort {
    int[] a;
    int n;

    SimpleSort() {
    }

    SimpleSort(int[] b) {
        n = b.length;
        a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = b[i];
    }

    public void display() {
        for (int i = 0; i < n; i++)
            System.out.print(" " + a[i]);
        System.out.println();
    }

    public int[] get() {
        return a;
    }

    // Swap element at position i with element at position k
    void swap(int i, int k) {
        int x;
        x = a[i];
        a[i] = a[k];
        a[k] = x;
    }

    public void bubbleSort() {
        boolean didSwap;
        for (int j = n - 1; j > 0; j--) {
            didSwap = false;
            for (int k = 0; k < j; k++) {
                if (a[k] > a[k + 1]) {
                    swap(k, k + 1);
                    didSwap = true;
                }
            }

            if (!didSwap)
                break;
        }
    }

    public void selectSort() {
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
        }
    }

    public void insertSort1() {
        for (int i = 1; i < n; i++) {
            for (int k = i - 1; k >= 0; k--) {
                if (a[k + 1] < a[k]) {
                    swap(k + 1, k);
                } else {
                    break;
                }
            }
        }
    }

    public void insertSort2() {
        int k;
        int tmp;
        for (int i = 1; i < n; i++) {
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
        }
    }

    public void insertSort3() {
        int k;
        int tmp;
        for (int i = 1; i < n; i++) {
            if (a[i - 1] <= a[i])
                continue;

            tmp = a[i];
            a[i] = a[i - 1];
            k = i - 2;
            while (k >= 0 && a[k] > tmp) {
                a[k + 1] = a[k];
                k--;
            }
            a[k + 1] = tmp;
        }
    }

    public void insertSort() {
        insertSort2();
    }

    // linear search
    public int Search(int value) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == value)
                return i;
        }

        return -1;
    }
}
