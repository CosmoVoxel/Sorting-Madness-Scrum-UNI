package pl.put.poznan.sorting.logic.algorithms;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Sortowanie rosnąco algorytmem BubbleSort
 *
 * @param <T> Typ elementów, które będą sortowane
 */
public class BubbleSort<T> implements SortStrategy<T> {


    /**
     * @param data       tablica elementów typu T, który chcemy posortować
     * @param comparator Comparator elementów typu T, który będzie używany po porównania elementów tablicy
     * @return posortowana kopia tablicy data
     */
    @Override
    public T[] sort(T[] data, Comparator<T> comparator) {
        T[] arr = Arrays.copyOf(data, data.length); //Tworzymy kopię tablice data
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) { //Jeśli arr[j] jest większe od arr[j+1]
                    //Zamień arr[j] z argg[j+1]
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

}
