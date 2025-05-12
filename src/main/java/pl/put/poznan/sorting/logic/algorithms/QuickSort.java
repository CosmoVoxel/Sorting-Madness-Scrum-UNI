package pl.put.poznan.sorting.logic.algorithms;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Sortowanie rosnąco algorytmem QuickSort
 *
 * @param <T> Typ elementów, które będą sortowane
 */
public class QuickSort<T> implements SortStrategy<T> {


    /**
     * Metoda rekurencyjnie sortująca tablice
     *
     * @param arr        tablica elementów T
     * @param low        początkowy indeks
     * @param high       końcowy indeks
     * @param comparator Comparator elementów typu T, który będzie używany po porównania elementów tablicy
     */
    private void quickSortHelper(T[] arr, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            //Wyznaczenie pivota
            //Wywołanie metody partition do podziału tablicy
            int pi = partition(arr, low, high, comparator);

            //Sortowanie elementów lewej podtablicy
            quickSortHelper(arr, low, pi - 1, comparator);

            //Sortowanie elementów prawej podtablicy
            quickSortHelper(arr, pi + 1, high, comparator);
        }
    }

    //Metoda pomocnicza do wyznaczenia pivotu i sortowania tablicy na 2 części
    //Lewą (mniejsze od lub równe  pivotowi), prawą (większe od pivota)

    /**
     * @param arr        tablica elementów T do podziału
     * @param low        indeks początkowy
     * @param high       indeks końcowy
     * @param comparator Comparator elementów typu T, który będzie używany po porównania elementów tablicy
     * @return pozycja pivota po podziale
     */
    private int partition(T[] arr, int low, int high, Comparator<T> comparator) {

        //Pivot według którego będziemy sortować (ostatni element tablicy arr)
        T pivot = arr[high];
        int i = low - 1;

        //Iteracja przez elementy
        //Jeśli obecny element jest większy lub równy pivotowi zamień go z elementem o indeksie j
        for (int j = low; j <= high - 1; j++) {
            if (comparator.compare(arr[j], pivot) <= 0) {
                i++;
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        //Zamień pivot z elementem i+1 (właściwe miejsce pivota)
        T temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    /**
     * @param data       tablica elementów typu T, który chcemy posortować
     * @param comparator Comparator elementów typu T, który będzie używany po porównania elementów tablicy
     * @return posortowana kopia tablicy
     */
    @Override
    public T[] sort(T[] data, Comparator<T> comparator) {
        T[] arr = Arrays.copyOf(data, data.length); //Tworzymy kopię oryginalnej tablicy

        //Wywołanie metody pomocniczej rozpoczynające proces sortowania
        quickSortHelper(arr, 0, arr.length - 1, comparator);
        return arr;
    }

}

