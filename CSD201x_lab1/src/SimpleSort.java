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

    void display() {
        for (int i = 0; i < n; i++)
            System.out.print(" " + a[i]);
        System.out.println();
    }

    // Hàm hoán vị
    void swap(int i, int k) {
        int x;
        x = a[i];
        a[i] = a[k];
        a[k] = x;
    }

    /*
     * Hàm sắp xếp bằng thuật toán nổi bọt, sau mỗi bước sắp xếp hãy gọi tới hàm
     * display() để hiển thị giá trị của mảng a ra màn hình
     */
    public void bubbleSort() {
        for (int j = n - 1; j > 0; j--) {
            boolean didSwap = false;
            for (int k = 0; k < j; k++) {
                if (a[k] > a[k + 1]) {
                    swap(k, k + 1);
                    didSwap = true;
                }
            }

            display();
            if (!didSwap)
                break;
        }
    }

}