package pl.put.poznan.sorting.logic.algorithms;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Sortowanie rosnąco algorytmem SelectionSort
 *
 * @param <T> Typ elementów, które będą sortowane
 */
public class SelectionSort<T> implements SortStrategy<T> {
    @Override
    public T[] sort(T[] data, Comparator<T> comparator) {
        T[] arr = Arrays.copyOf(data, data.length);
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[j], arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            T temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        return arr;
    }


}

