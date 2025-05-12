package pl.put.poznan.sorting.logic.algorithms;

import java.util.Comparator;

/**
 * Interfejs dla algorytmów sortujących
 *
 * @param <T> Typ elementów, które będą sortowane
 */
public interface SortStrategy<T> {
    default String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * @param data       tablica elementów typu T, który chcemy posortować
     * @param comparator Comparator elementów typu T, który będzie używany po porównania elementów tablicy
     * @return posortowana kopia tablicy
     */
    T[] sort(T[] data, Comparator<T> comparator); // metoda do sortowania dowolnego typu danych, wedlug podanego w atrybucie comparatora

}
