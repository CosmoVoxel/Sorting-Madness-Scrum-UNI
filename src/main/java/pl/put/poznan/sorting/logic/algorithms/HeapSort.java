package pl.put.poznan.sorting.logic.algorithms;

import java.util.Arrays;
import java.util.Comparator;
/**
 * Sortowanie rosnąco algorytmem HeapSort
 *
 * @param <T> Typ elementów, które będą sortowane
 */
public class HeapSort<T> implements SortStrategy<T> {

    /**
     * Metoda rekurencyjna do utrzymywania właściwości kopca maksymalnego
     * @param arr tablica elementów typu T, która jest naszym kopcem
     * @param n rozmiar kopca
     * @param i indeks bieżącego elementu
     * @param comparator  Comparator elementów typu T, który będzie używany po porównania elementów tablicy
     */
    private void heapify(T[] arr, int n, int i, Comparator<T> comparator) {
        int largest = i; //Największy element (na start jest to bieżący element)
        int left = 2 * i + 1; //Indeks lewego dziecka
        int right = 2 * i + 2; //Indeks prawego dziecka

        if (left < n && comparator.compare(arr[left], arr[largest]) > 0) { //Jeśli lewe dziecko istnieje i jest większe niż obecny element
            largest = left;
        }
        if (right < n && comparator.compare(arr[right], arr[largest]) > 0) { //Jeśli prawe dziecko istnieje i jest większe niż obecny element
            largest = right;
        }

        if (largest != i) {// Jeśli największy element nie jest bieżącym elementem, zamień je miejscami i rekurencyjnie popraw strukturę kopca
            T temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest, comparator);
        }
    }

    /**
     *
     * @param data       tablica elementów typu T, który chcemy posortować
     * @param comparator Comparator elementów typu T, który będzie używany po porównania elementów tablicy
     * @return posortowana kopia tablicy data
     */
    @Override
    public T[] sort(T[] data, Comparator<T> comparator) {
        T[] arr = Arrays.copyOf(data, data.length); //Tworzymy kopie
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, comparator);
        }
        for (int i = n - 1; i >= 0; i--) {
            T temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0, comparator);
        }
        return arr;
    }


}

