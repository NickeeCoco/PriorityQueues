// Morgane Bentzinger - 26106253

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        SortedPriorityQ spq = new SortedPriorityQ();
        UnsortedPriorityQ upq  = new UnsortedPriorityQ();
        Heap heap = new Heap();

        Random rd = new Random(); // creating Random object

        int size = 100;

        for(int i = 0; i < size; i++) {
            int randomNumber =  rd.nextInt(100); // storing random integers
            // insert new Entry with key = random number, value = 0 (because value is not used in this exercise)
            spq.insert(randomNumber, 0);
            upq.insert(randomNumber, 0);
            heap.insert(randomNumber, 0);
        }

        System.out.println("Unsorted heap: " + heap);
        System.out.println("Unsorted Sorted Priority Queue: " + spq);
        System.out.println("Unsorted Unsorted Priority Queue: " + upq);
        System.out.println();
        System.out.println("Heap sort: " + Arrays.toString(heapSort(heap, heap.size())));
        System.out.println("Heap sort average (ms): " + heapSortAverage(heap, heap.size()));
        System.out.println();
        System.out.println("Insertion sort: " + Arrays.toString(insertionSort(spq, spq.size())));
        System.out.println("Insertion sort average (ms): " + insertionSortAverage(spq, spq.size()));
        System.out.println();
        System.out.println("Selection sort: " + Arrays.toString(selectionSort(upq, upq.size())));
        System.out.println("Selection sort average (ms): " + selectionSortAverage(upq, upq.size()));
        System.out.println();


    }

    /*******************************************
     *   HEAP SORT
     *******************************************/

    // heap sort algorithm
    public static Entry[] heapSort(Heap heap, int size) {
        Entry[] sortedArray = new Entry[size];

        for(int i = 0; i < size; i++) {
            sortedArray[i] = heap.removeMin();
        }

        return sortedArray;
    }

    // heap sort time
    public static double timeHeapSort(Heap heap, int size) {
        long startTime = System.nanoTime();
        heapSort(heap, size);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        double durationMs = (double) duration / 1000000;
        return durationMs;
    }

    // heap sort time average
    public static double heapSortAverage(Heap heap, int size) {
        double[] times = new double[10]; // store 10 running times of the algorithm

        for(int i = 0; i < times.length; i++) {
            double time = timeHeapSort(heap, size);
            times[i] = time;
        }

        double sumTimes = 0;

        for(int i = 1; i < times.length; i++) { // I'm ignoring the first time on purpose, as discussed with Farhad, because the time is way bigger than for the other runs
            sumTimes += times[i];
        }

        System.out.println("All heap sort times (ms): " + Arrays.toString(times));

        return sumTimes / (times.length - 1);
    }

    /*******************************************
     *   INSERTION SORT
     *******************************************/

    // insertion sort algorithm
    public static Entry[] insertionSort(SortedPriorityQ spq, int size) {
        Entry[] sortedArray = new Entry[size];

        for(int i = 0; i < size; i++) {
            sortedArray[i] = spq.removeMin();
        }

        return sortedArray;
    }

    // insertion sort time
    public static double timeInsertionSort(SortedPriorityQ spq, int size) {
        long startTime = System.nanoTime();
        insertionSort(spq, size);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        double durationMs = (double) duration / 1000000;
        return durationMs;
    }

    // insertion sort time average
    public static double insertionSortAverage(SortedPriorityQ spq, int size) {
        double[] times = new double[10]; // store 10 running times of the algorithm

        for(int i = 0; i < times.length; i++) {
            double time = timeInsertionSort(spq, size);
            times[i] = time;
        }

        double sumTimes = 0;

        for(int i = 1; i < times.length; i++) { // I'm ignoring the first time on purpose, as discussed with Farhad, because the time is way bigger than for the other runs
            sumTimes += times[i];
        }

        System.out.println("All insertion sort times: " + Arrays.toString(times));

        return sumTimes / (times.length - 1);
    }

    /*******************************************
     *   SELECTION SORT
     *******************************************/

    // selection sort algorithm
    public static Entry[] selectionSort(UnsortedPriorityQ upq, int size) {
        Entry[] sortedArray = new Entry[size];

        for(int i = 0; i < size; i++) {
            sortedArray[i] = upq.removeMin();
        }

        return sortedArray;
    }

    // selection sort time
    public static double timeSelectionSort(UnsortedPriorityQ upq, int size) {
        long startTime = System.nanoTime();
        selectionSort(upq, size);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        double durationMs = (double) duration / 1000000;
        return durationMs;
    }

    // selection sort time average
    public static double selectionSortAverage(UnsortedPriorityQ upq, int size) {
        double[] times = new double[10]; // store 10 running times of the algorithm

        for(int i = 0; i < times.length; i++) {
            double time = timeSelectionSort(upq, size);
            times[i] = time;
        }

        double sumTimes = 0;

        for(int i = 1; i < times.length; i++) { // I'm ignoring the first time on purpose, as discussed with Farhad, because the time is way bigger than for the other runs
            sumTimes += times[i];
        }

        System.out.println("All selection sort times: " + Arrays.toString(times));

        return sumTimes / times.length;
    }
}
