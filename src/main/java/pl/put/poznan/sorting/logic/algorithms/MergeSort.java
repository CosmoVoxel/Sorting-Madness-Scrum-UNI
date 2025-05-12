package pl.put.poznan.sorting.logic.algorithms;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Sortowanie rosnąco algorytmem MergeSort
 *
 * @param <T> Typ elementów, które będą sortowane
 */
public class MergeSort<T> implements SortStrategy<T> {
    /**
     * Metoda do scalania dwóch tablic elementów
     *
     * @param left       lewa tablica
     * @param right      prawa tablica
     * @param comparator Comparator elementów typu T, który będzie używany po porównania elementów tablicy
     * @return posortowane scalenie lewej i prawej tablicy
     */
    private T[] merge(T[] left, T[] right, Comparator<T> comparator) {
        T[] result = Arrays.copyOf(left, left.length + right.length);
        int i = 0, j = 0, k = 0;

        //Dopóki obie tablice nie są puste, porównuj obecne elementy i dodaj większy do wyniku
        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        //Jeśli któraj tablica jest pusta, dodaj reszte elementów drugej tablicy dow wyniku
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }
        return result;
    }

    /**
     * @param data       tablica elementów typu T, który chcemy posortować
     * @param comparator Comparator elementów typu T, który będzie używany po porównania elementów data
     * @return posortowana kopia tablicy data
     */
    @Override
    public T[] sort(T[] data, Comparator<T> comparator) {
        T[] arr = Arrays.copyOf(data, data.length); //Tworzymy kopię oryginalnej tablicy
        if (arr.length <= 1) { //Jeśli jeden element w tablicy, zwróć tablice
            return arr;
        }
        int mid = arr.length / 2; //Punkt dzielący tablicę na lewą i prawą

        //Rekurencyjne dzielenie lewe i prawej części tablicy
        T[] left = sort(Arrays.copyOfRange(arr, 0, mid), comparator);
        T[] right = sort(Arrays.copyOfRange(arr, mid, arr.length), comparator);
        return merge(left, right, comparator);
    }


}

