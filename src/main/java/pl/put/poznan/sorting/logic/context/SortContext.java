package pl.put.poznan.sorting.logic.context;

import pl.put.poznan.sorting.logic.SortingResult;
import pl.put.poznan.sorting.logic.algorithms.SortStrategy;

import java.util.Comparator;


/**
 * Kontekst do obsługi wybranej metody sortowania
 *
 * @param <T> Typ elementów, które będą sortowane
 */
public class SortContext<T> {
    /**
     * Atrybut zawierający wybraną strategie sortowania
     */
    private SortStrategy<T> strategy;

    /**
     * Atrybut zawierający wynik sortowania w postaci obiektu klasy SortingResult
     */
    private final SortingResult<T> result = new SortingResult<T>();

    /**
     * Konstruktor kontekstu
     * @param strategy wybrana strategia sortowania
     */
    public SortContext(SortStrategy<T> strategy) {
        this.strategy = strategy;
        this.result.setAlgorithm(this.strategy.getName());
    }

    /**
     * Metoda wywołująca sortowanie
     * @param array tablica do posortowania
     * @param comparator Comparator elementów typu T, który będzie używany po porównania elementów tablicy
     * @throws IllegalArgumentException jeśli tablica danych jest pusta
     */
    public void sort(T[] array, Comparator<T> comparator) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Błąd: Tablica danych do posortowania jest pusta");
        }
        long startTime = System.nanoTime();
        this.result.setSortedData(this.strategy.sort(array, comparator));
        long endTime = System.nanoTime();
        this.result.setExecutionTimeNs(endTime - startTime);
    }

    public SortingResult<T> getResult() {
        return result;
    }
}
