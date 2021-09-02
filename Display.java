import java.lang.reflect.Array;
import java.util.Arrays;

class DisplayRandom extends DisplayRandomBase {

    public DisplayRandom(String[] csvLines) {
        super(csvLines);
    }

    @Override
    public Plane[] sort() {
        return Utils.mergeSort(this.getData(), 0, this.getData().length -1);
    }

    /* Implement all the necessary methods here */
}

class DisplayPartiallySorted extends DisplayPartiallySortedBase {

    public DisplayPartiallySorted(String[] scheduleLines, String[] extraLines) {
        super(scheduleLines, extraLines);
    }

    @Override
    Plane[] sort() {
        Utils.insertionSort(super.getSchedule(), super.getSchedule().length);
        return super.getSchedule();
    }


    /* Implement all the necessary methods here */
}

class Utils {

    /**
     *  Create an insertion sort algorithm to sort planes
     * From Tutorial Lecture Slides:
     *
     *Input: Array A of n length
     * for i ← 1 to n - 1 do
     * v ← A[i]
     * j ← i - 1
     * while j ≥ 0 and A[j] > v do
     * A[j + 1] ← A[j]
     * j ← j - 1
     * A[j + 1] ← v
     *
     * @param arr The array to sort
     * @param size the size of the array being sorted
     * @return sorted list of planes
     */
    public static void insertionSort(Plane[] arr, int size) {
        if (size > 0) {
            for (int i = 1; i < size; i++) {
                Plane v = arr[i];
                int j = i - 1;

                while (j >= 0 && arr[j].compareTo(v) > 0) {
                    arr[j+1] = arr[j];
                    j--;
                }
                arr[j+1] = v;
            }
        }
    }

    /**
     *
     * Merge sort adapted from lecture slides
     *
     * Pseudocode
     * Algorithm mergeSort(A, l, r)
     * Input an array A
     * Output A sorted between
     * indices l and r
     * if l < r
     * m ¬ ⌊ (l + r) ÷ 2 ⌋
     * mergeSort(S, l, m)
     * mergeSort(S, m + 1, r)
     * merge(S, l, m, r)
     *
     *
     * @param arr The array to
     * @return sorted array
     */
    public static Plane[] mergeSort(Plane[] arr, int left, int right) {

        if (left < right) {

            int middle = (left + right) / 2;
            mergeSort(arr, left, middle);
            mergeSort(arr, middle+1, right);
            // Perform the merge
            merge(arr, left, middle, right);
        }

        return arr;
    }

    /**
     * Using Pseudocode from lectures:
     *
     * Algorithm merge(A, l, m r)
     * Input an array A with two sorted halves
     * Output sorted union of A[l..m] and A[m..r]
     * n1 ¬ m – l + 1 // size of first half of A
     * n2 ¬ r – m // size of second half of A
     * L ¬ copy of A[l..m], R ¬ copy of A[m..r]
     * i ¬ 0, j ¬ 0, k ¬ 1
     * while i < n1 and j < n2 do // merge into A
     * if L[i] <= R[j] then
     * A[k++] = L[i++]
     * else
     * A[k++] = R[j++]
     * while i < n1 do // copy rest of L into A
     * A[k++] = L[i++]
     * while j < n2 do // copy rest of R into A
     * A[k++] = R[j++]
     *
     * @param arr the array to sort into left and right
     * @param left the beginning of the left side
     * @param middle the the beginning of the middle item
     * @param right  the beginning of the right side
     */
    public static void merge(Plane[] arr, int left, int middle, int right) {

        int sizeOfFirstHalf = middle - left + 1;
        int sizeOfSecondHalf = right - middle;

        Plane[] leftCopy = new Plane[sizeOfFirstHalf];
        Plane[] rightCopy = new Plane[sizeOfSecondHalf];

        /*Copy data to temp arrays*/
        for (int i = 0; i < sizeOfFirstHalf; ++i)
            leftCopy[i] = arr[left + i];
        for (int j = 0; j < sizeOfSecondHalf; ++j)
            rightCopy[j] = arr[middle + 1 + j];

        int i, j;
        i = j = 0;
        int k = left;
        while (i < sizeOfFirstHalf && j < sizeOfSecondHalf) {
            if (leftCopy[i].compareTo(rightCopy[j]) <= 0) {
                arr[k++] = leftCopy[i++];
            } else {
                arr[k++] = rightCopy[j++];
            }
        }

        // Copy the rest of the left side into arr
        while (i < sizeOfFirstHalf) {
            arr[k++] = leftCopy[i++];
        }

        // Copy the rest of the right side into arr
        while (j < sizeOfSecondHalf) {
            arr[k++] = rightCopy[j++];
        }

    }

}
